package com.qx.web.controller.client;

import com.alibaba.fastjson.JSON;
import com.qx.common.constant.Constants;
import com.qx.common.constant.HttpStatus;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.utils.ServletUtils;
import com.qx.common.utils.StringUtils;
import com.qx.framework.manager.AsyncManager;
import com.qx.framework.manager.factory.AsyncFactory;
import com.qx.framework.security.LoginUser;
import com.qx.framework.util.SecurityUtils;
import com.qx.student.domain.*;
import com.qx.student.domain.vo.ImpRecordVo;
import com.qx.student.domain.vo.LoginStudent;
import com.qx.student.domain.vo.MedicalWriteRecordVo;
import com.qx.student.service.*;
import com.qx.student.util.TokenUtil;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生端-学生和训练记录相关
 */

@Api(tags = "客户端学生管理接口")
@RestController
@RequestMapping("/client/student")
public class StudentController extends BaseController {

    @Autowired
    private IMedicalWriteRecordService medicalWriteRecordService;

    @Autowired
    private IImpRecordService impRecordService;

    @Autowired
    private ITreatmentRecordService treatmentRecordService;

    @Autowired
    private IFzCheckRecordService fzCheckRecordService;

    @Autowired
    private IJsCheckRecordService jsCheckRecordService;

    @Autowired
    private IXlCheckRecordService xlCheckRecordService;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    @Autowired
    private ITgCheckRecordService tgCheckRecordService;

    @Autowired
    private IHistoryTakingRecordService historyTakingRecordService;


    @Autowired
    private IZStudentService studentService;

    @Autowired
    private TokenUtil tokenUtil;




    /**
     * 登录验证
     * @param sno
     * @param password

     * @return
     */
    @ApiOperation("学生登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno", value = "学号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("/login")
    public AjaxResult login(String sno, String password){

        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = studentService.login(sno, password);
        ajax.put(Constants.TOKENA, token);
        return ajax;
    }


    /**
     * 退出登录
     * @return
     */
    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public AjaxResult logout(){

        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (StringUtils.isNotNull(loginStudent))
        {
            String sno = loginStudent.getUsername();
            // 删除用户缓存记录
            tokenUtil.delLoginStudent(loginStudent.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(sno, Constants.LOGOUT, "退出成功"));
        }
        return AjaxResult.error(HttpStatus.SUCCESS, "退出成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation("获取学生信息")
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        ZStudent student = loginStudent.getUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("student", student);
        return ajax;
    }

    /**
     * 重置密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(String oldPassword, String newPassword)
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        String sno = loginStudent.getUsername();
        String password = loginStudent.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if(studentService.resetStuPwd(sno,SecurityUtils.encryptPassword(newPassword))==1){
            loginStudent.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenUtil.setLoginStudent(loginStudent);
        }
        AjaxResult ajax = AjaxResult.success();
        return ajax;
    }


    @ApiOperation("存储病历书写记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordVo", value = "病历书写",required = true, dataType = "com.qx.student.domain.vo.MedicalWriteRecordVo")
    })
    @PostMapping("/addMedicalWrite")
    public AjaxResult addMedicalWrite(@RequestBody MedicalWriteRecordVo recordVo){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordVo.getStudentTrainId());
        if (trainRecord.getMedicalRecordId()==null || "".equals(trainRecord.getMedicalRecordId())){
            //添加病历书写记录
            recordVo.getMedicalWriteRecord().setPatientId(trainRecord.getPatientId());
            medicalWriteRecordService.insertMedicalWriteRecord(recordVo);
        }else {
            //更新病例书写记录
            MedicalWriteRecord medicalWriteRecord =recordVo.getMedicalWriteRecord();
            medicalWriteRecord.setId(trainRecord.getMedicalRecordId());
            medicalWriteRecordService.updateMedicalWriteRecord(medicalWriteRecord);
        }
        result.put("trainRecord",trainRecord);
        return result;
    }



    /**
     * 存储治疗记录
     * @param ids
     * @return
     */
    @ApiOperation("存储治疗记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "检查项目ids", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @PostMapping("/addTreatment")
    public AjaxResult addTreatmentRecord(String ids,Long id){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        TreatmentRecord treatmentRecord = new TreatmentRecord();
        treatmentRecord.setTreatmentIds(ids);

        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);

        if (trainRecord.getTreatRecordId()==null || "".equals(trainRecord.getTreatRecordId())){
            //新增辅助检查记录
            treatmentRecordService.insertTreatmentRecord(treatmentRecord,trainRecord);
        }else{
            //更新辅助检查记录
            treatmentRecord.setId(trainRecord.getTreatRecordId());
            treatmentRecordService.updateTreatmentRecord(treatmentRecord);
        }

        result.put("trainRecord",trainRecord);
        return result;

    }


    /**
     * 存储诊断记录
     * @param impRecordVo 诊断记录扩展类
     * @return
     */

    @ApiOperation("存储诊断记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "impRecordVo", value = "诊断扩展类,studentTrainId是学生诊断记录,impRecord的impIds和type都是用,拼接字符串，type：0主要1次要2鉴别，其中四个集合的每一个对象都需要传递参数id和support[0支持1不支持]",
                    required = true, dataType = "com.qx.student.domain.vo.ImpRecordVo",
                    example = "{'impRecordVo':{'studentTrainId':1,'impRecord':{'impIds':'2,3','type':'0,1'},'historySupportRecordList':[{'id':1,'support':'0'},{'id':2,'support':'0'}]}}"
            )
    })
    @PostMapping("/addImp")
    public AjaxResult addImpRecord(@RequestBody ImpRecordVo impRecordVo){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        //获取学生训练记录
        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(impRecordVo.getStudentTrainId());
        if (trainRecord!=null){
            if (trainRecord.getImpRecordId()==null || "".equals(trainRecord.getImpRecordId())){
                //新增诊断记录
                impRecordService.insertImpRecordN(impRecordVo);
            }else{
                //更新诊断记录
                impRecordVo.getImpRecord().setId(trainRecord.getImpRecordId());
                impRecordService.updateImpRecordN(impRecordVo);

            }
        }

        result.put("trainRecord",trainRecord);
        return result;
    }

    @PostMapping("/addImpTest")
    public AjaxResult addImpTest(@RequestBody ImpRecordVo impRecordVo){
        if (impRecordVo==null){ return AjaxResult.error("提交信息为空"); }

        AjaxResult result = AjaxResult.success();
        //获取学生训练记录
        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(impRecordVo.getStudentTrainId());

        if (trainRecord.getImpRecordId()==null || "".equals(trainRecord.getImpRecordId())){
            //新增诊断记录
            impRecordService.insertImpRecordN(impRecordVo);
        }else{
            //更新诊断记录
            impRecordVo.getImpRecord().setId(trainRecord.getImpRecordId());
            impRecordService.updateImpRecordN(impRecordVo);

        }
        result.put("trainRecord",trainRecord);
        return result;
    }

    /**
     * 存储辅助检查记录
     * @param ids
     * @return
     */
    @ApiOperation("存储辅助检查记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "检查项目ids", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @PostMapping("/addFz")
    public AjaxResult addFzCheckRecord(String ids,Long id){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        FzCheckRecord fzRecord = new FzCheckRecord();
        fzRecord.setItemIds(ids);
        List<Long> longList = Arrays.asList(ids.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);

        if (trainRecord.getFzRecordId()==null || "".equals(trainRecord.getFzRecordId())){
            //新增辅助检查记录
            fzCheckRecordService.insertFzCheckRecord(fzRecord,itemIds,trainRecord);
        }else{
            //更新辅助检查记录
            fzRecord.setId(trainRecord.getFzRecordId());
            fzCheckRecordService.updateFzCheckRecord(fzRecord,itemIds);
        }

        result.put("trainRecord",trainRecord);
        return result;
    }

    /**
     * 存储心理测量检查记录
     * @param ids
     * @return
     */
    @ApiOperation("存储心理测量检查记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "检查项目ids", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @PostMapping("/addXl")
    public AjaxResult addXlCheckRecord(String ids,Long id) {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent == null) {
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        XlCheckRecord xlRecord = new XlCheckRecord();
        xlRecord.setItemIds(ids);
        List<Long> longList = Arrays.asList(ids.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);

        if (trainRecord.getXlRecordId() == null || "".equals(trainRecord.getXlRecordId())){
            //新增心理测量检查记录
            xlCheckRecordService.insertXlCheckRecord(xlRecord,itemIds,trainRecord);
        }else {
            //更新心理测量检查记录
            xlRecord.setId(trainRecord.getXlRecordId());
            xlCheckRecordService.updateXlCheckRecord(xlRecord,itemIds);
        }
        result.put("trainRecord",trainRecord);
        return result;
    }

    /**
     * 存储精神状况检查记录
     * @param ids
     * @return
     */
    @ApiOperation("存储精神状况检查记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "检查项目ids", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @PostMapping("/addJs")
    public AjaxResult addJsCheckRecord(String ids,Long id) {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent == null) {
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        JsCheckRecord jsRecord = new JsCheckRecord();
        jsRecord.setItemIds(ids);
        List<Long> longList = Arrays.asList(ids.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);

        if (trainRecord.getJsRecordId() == null || "".equals(trainRecord.getJsRecordId())) {
            //新增精神状况检查记录
            jsCheckRecordService.insertJsCheckRecord(jsRecord,itemIds,trainRecord);
        }else{
            //更新精神状况检查记录
            jsRecord.setId(trainRecord.getJsRecordId());
            jsCheckRecordService.updateJsCheckRecord(jsRecord,itemIds);
        }
        result.put("trainRecord",trainRecord);
        return result;
    }

    /**
     * 存储体格检查记录
     * @param ids
     * @return
     */
    @ApiOperation("存储体格检查记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "检查项目ids", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @PostMapping("/addTg")
    public AjaxResult addTgCheckRecord(String ids,Long id){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        TgCheckRecord tgRecord = new TgCheckRecord();
        tgRecord.setItemIds(ids);
        //获取项目数组
        List<Long> longList = Arrays.asList(ids.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});

        StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);

        if (trainRecord.getTgRecordId() == null || "".equals(trainRecord.getTgRecordId())){
            //新增体格检查记录
            tgCheckRecordService.insertTgCheckRecord(tgRecord,itemIds,trainRecord);

        }else{
            //更新
            tgRecord.setId(trainRecord.getTgRecordId());
            tgCheckRecordService.updateTgCheckRecord(tgRecord,itemIds);
        }
        result.put("trainRecord",trainRecord);
        return result;
    }

    /**
     * 存储病史采集数据
     * @param ids
     * @return
     */
    @ApiOperation("存储病史采集数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "问题ids", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "patientId", value = "案例患者id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "学生训练记录id", dataType = "Long")
    })
    @PostMapping("/addHistoryTaking")
    public AjaxResult addHistoryTakingRecord(String ids,Long patientId,Long id){
        //获取登录用户信息
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }
        AjaxResult result = AjaxResult.success();
        HistoryTakingRecord historyTakingRecord = new HistoryTakingRecord();
        historyTakingRecord.setQuestionIds(ids);
        //获取问题数组
        List<Long> longList = Arrays.asList(ids.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] questionIds =  longList.toArray(new Long[]{});
        if (id != null && !"".equals(id)) {
            //更新数据
            //查询学生训练记录表获取病史采集记录id
            StudentTrainRecord trainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);
            historyTakingRecord.setId(trainRecord.getHistoryRecordId());
            //根据获取的id更新病史采集记录
            historyTakingRecordService.updateHistoryTakingRecord(historyTakingRecord,questionIds);

        } else {
            //新增数据
            StudentTrainRecord studentTrainRecord = new StudentTrainRecord();
            studentTrainRecord.setPatientId(patientId);
            studentTrainRecord.setStuId(loginStudent.getUser().getId());
            studentTrainRecord.setStartTime(new Date());
            //插入病史采集记录
            historyTakingRecordService.insertHistoryTakingRecord(historyTakingRecord,questionIds,studentTrainRecord);
            result.put("studentTrainRecord", studentTrainRecord);

        }
        return result;
    }
}
