package com.qx.web.controller.client;

import com.qx.cases.domain.*;
import com.qx.cases.service.*;
import com.qx.common.constant.HttpStatus;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.utils.ServletUtils;
import com.qx.common.utils.StringUtils;
import com.qx.student.domain.*;
import com.qx.student.domain.vo.LoginStudent;
import com.qx.student.service.*;
import com.qx.student.util.TokenUtil;
import com.qx.system.domain.SysDictData;
import com.qx.system.domain.SysNation;
import com.qx.system.service.ISysDictDataService;
import com.qx.system.service.ISysDictTypeService;
import com.qx.system.service.ISysNationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 学生端-标准案例
 */
@Api( tags = "标准化病例相关接口")
@RestController
@RequestMapping("/client/case")
public class caseController extends BaseController {

    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private IHistorySupportRecordService historySupportRecordService;

    @Autowired
    private ITgcheckSupportRecordService tgcheckSupportRecordService;

    @Autowired
    private IJscheckSupportRecordService jscheckSupportRecordService;

    @Autowired
    private IFzcheckSupportRecordService fzcheckSupportRecordService;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    @Autowired
    private IImpRecordService impRecordService;

    @Autowired
    private ITreatmentRecordService treatmentRecordService;

    @Autowired
    private IFzCheckRecordService fzCheckRecordService;

    @Autowired
    private IJsCheckRecordService jsCheckRecordService;

    @Autowired
    private ITgCheckRecordService tgCheckRecordService;

    @Autowired
    private IHistoryTakingRecordService historyTakingRecordService;

    @Autowired
    private ICaseCheckItemService caseCheckItemService;

    @Autowired
    private ICaseQuestionService caseQuestionService;

    @Autowired
    private ICasePatientService casePatientService;

    @Autowired
    private ICasePatientItemService patientItemService;

    @Autowired
    private ICaseImpService caseImpService;

    @Autowired
    private ICaseTreatmentService caseTreatmentService;

    @Autowired
    private TokenUtil tokenUtil;


    /**
     * 查询所有案例患者
     * @param casePatient
     * @return
     */
    @ApiOperation("获取所有案例患者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "casePatient", value = "案例患者", dataType = "CasePatient")
    })
    @GetMapping("/patients")
    public AjaxResult listPatient(CasePatient casePatient)
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        List<CasePatient> list = casePatientService.selectCasePatientList(casePatient);
        return AjaxResult.success(list);
    }

    /**
     * 查询某个案例患者，并且查看是否有对应的训练记录
     *
     * @param patientId
     * @return
     */
    @ApiOperation("根据id获取案例患者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "患者id", required = true, dataType = "Long")
    })
    @GetMapping("/patient/{patientId}")
    public AjaxResult getPatient(@PathVariable Long patientId)
    {

        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        //查询该案例患者信息
        CasePatient patient = casePatientService.selectCasePatientById(patientId);
        AjaxResult result = AjaxResult.success();
        result.put("patient",patient);
        StudentTrainRecord studentTrainRecord = new StudentTrainRecord();
        studentTrainRecord.setStuId(loginStudent.getUser().getId());
        studentTrainRecord.setPatientId(patientId);
        studentTrainRecord.setStatus("0");
        //获取学生上次的训练记录
        List<StudentTrainRecord> list = studentTrainRecordService.selectStudentTrainRecordList(studentTrainRecord);
        result.put("trainRecord",list.size()>0?list.get(0):null);
        return result;
    }

    /**
     * 病史采集
     * @param caseQuestion
     * @param recordId
     * @return
     */
    @ApiOperation("病史采集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "caseQuestion", value = "问题库",  dataType = "CaseQuestion"),
            @ApiImplicitParam(name = "recordId", value = "学生训练记录id",  dataType = "Long")
    })
    @GetMapping("/historyTaking")
    public AjaxResult listQuestion(CaseQuestion caseQuestion,Long recordId)
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        AjaxResult ajaxResult = AjaxResult.success();
        //获取问题库
        List<CaseQuestion> list = caseQuestionService.selectCaseQuestionList(caseQuestion);
        List<CaseQuestion> caseQuestions = caseQuestionService.selectCaseQuestionListType(list);
        ajaxResult.put("questions",caseQuestions);

        //获取学生病史采集记录
        HistoryTakingRecord historyTakingRecord = null;
        if (recordId!=null && !"".equals(recordId)){
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            if (studentTrainRecord!=null && studentTrainRecord.getHistoryRecordId()!=null &&!"".equals(studentTrainRecord.getHistoryRecordId())) {
                historyTakingRecord = historyTakingRecordService.selectHistoryTakingRecordById(studentTrainRecord.getHistoryRecordId());
            }
        }
        ajaxResult.put("historyTakingRecord", historyTakingRecord);
        return ajaxResult;
    }


    /**
     * 体格检查
     * @param caseCheckItem
     * @param recordId
     * @return
     */
    @ApiOperation("体格检查")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "caseCheckItem", value = "检查项目",  dataType = "CaseCheckItem"),
            @ApiImplicitParam(name = "recordId", value = "学生训练记录id",  dataType = "Long")
    })
    @GetMapping("/tg")
    public AjaxResult listTg(CaseCheckItem caseCheckItem,Long recordId)
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        AjaxResult ajaxResult = AjaxResult.success();
        //获取体格检查项目
        caseCheckItem.setCategory("0");
        List<CaseCheckItem> list = caseCheckItemService.selectCaseCheckItemList(caseCheckItem);
        List<CaseCheckItem> caseCheckItems = caseCheckItemService.buildItemTree(list);
        ajaxResult.put("tgItems",caseCheckItems);

        //获取学生体格检查记录
        TgCheckRecord tgCheckRecord = null;
        if (recordId!=null && !"".equals(recordId)){
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            if (studentTrainRecord!=null && studentTrainRecord.getTgRecordId()!=null &&!"".equals(studentTrainRecord.getTgRecordId())) {
                tgCheckRecord = tgCheckRecordService.selectTgCheckRecordById(studentTrainRecord.getTgRecordId(), studentTrainRecord.getPatientId());

            }
        }
        ajaxResult.put("tgCheckRecord", tgCheckRecord);
        return ajaxResult;
    }

    /**
     * 精神状况检查
     * @param caseCheckItem
     * @param recordId
     * @return
     */
    @ApiOperation("精神状况检查")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "caseCheckItem", value = "检查项目", dataType = "CaseCheckItem"),
            @ApiImplicitParam(name = "recordId", value = "学生训练记录id",  dataType = "Long")
    })
    @GetMapping("/js")
    public AjaxResult listJs(CaseCheckItem caseCheckItem,Long recordId)
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        AjaxResult ajaxResult = AjaxResult.success();
        //获取精神状况检查项目
        caseCheckItem.setCategory("1");
        List<CaseCheckItem> list = caseCheckItemService.selectCaseCheckItemList(caseCheckItem);
        List<CaseCheckItem> caseCheckItems = caseCheckItemService.buildItemTree(list);
        ajaxResult.put("jsItems",caseCheckItems);
        //获取学生精神状况检查记录
        JsCheckRecord jsCheckRecord = null;
        if (recordId!=null && !"".equals(recordId)){
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            if (studentTrainRecord!=null && studentTrainRecord.getJsRecordId()!=null &&!"".equals(studentTrainRecord.getJsRecordId())) {
                jsCheckRecord = jsCheckRecordService.selectJsCheckRecordById(studentTrainRecord.getJsRecordId(), studentTrainRecord.getPatientId());

            }
        }
        ajaxResult.put("jsCheckRecord", jsCheckRecord);
        return ajaxResult;
    }

    /**
     * 辅助检查
     * @param caseCheckItem
     * @param recordId
     * @return
     */
    @ApiOperation("辅助检查")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "caseCheckItem", value = "检查项目",  dataType = "CaseCheckItem"),
            @ApiImplicitParam(name = "recordId", value = "学生训练记录id",  dataType = "Long")
    })
    @GetMapping("/fz")
    public AjaxResult listFz(CaseCheckItem caseCheckItem,Long recordId)
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        AjaxResult ajaxResult = AjaxResult.success();
        //获取辅助检查项目
        caseCheckItem.setCategory("2");
        List<CaseCheckItem> list = caseCheckItemService.selectCaseCheckItemList(caseCheckItem);
        List<CaseCheckItem> caseCheckItems = caseCheckItemService.buildItemTree(list);
        ajaxResult.put("fzItems",caseCheckItems);

        //获取学生辅助检查记录
        FzCheckRecord fzCheckRecord = null;
        if (recordId!=null && !"".equals(recordId)){
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            if (studentTrainRecord !=null && studentTrainRecord.getFzRecordId()!=null &&!"".equals(studentTrainRecord.getFzRecordId())) {
                fzCheckRecord = fzCheckRecordService.selectFzCheckRecordById(studentTrainRecord.getFzRecordId(), studentTrainRecord.getPatientId());
            }
        }
        ajaxResult.put("fzCheckRecord", fzCheckRecord);
        return ajaxResult;
    }

    /**
     * 诊断
     * @param imp
     * @param recordId
     * @return
     */
    @ApiOperation("诊断")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imp", value = "诊断",  dataType = "CaseImp"),
            @ApiImplicitParam(name = "recordId", value = "学生训练记录id", dataType = "Long")
    })
    @GetMapping("/imp")
    public AjaxResult listImp(CaseImp imp,Long recordId){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);        }

        AjaxResult ajaxResult = AjaxResult.success();

        //获取诊断库
        List<CaseImp> list = caseImpService.selectCaseImpList(imp);
        ajaxResult.put("imps",list);

        //获取学生诊断记录
        List<HistorySupportRecord> historySupportRecords = null;
        List<TgcheckSupportRecord> tgcheckSupportRecords = null;
        List<JscheckSupportRecord> jscheckSupportRecords = null;
        List<FzcheckSupportRecord> fzcheckSupportRecords = null;
        ImpRecord impRecord = null;
        if (recordId!=null && !"".equals(recordId)){
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            //获取诊断数据
            if (studentTrainRecord !=null && studentTrainRecord.getImpRecordId() !=null && !"".equals(studentTrainRecord.getImpRecordId())) {
                impRecord = impRecordService.selectImpRecordById(studentTrainRecord.getImpRecordId());
            }
            //获取问诊模块数据
            if (studentTrainRecord!=null && studentTrainRecord.getHistoryRecordId() != null && !"".equals(studentTrainRecord.getHistoryRecordId())){
                HistorySupportRecord record = new HistorySupportRecord();
                record.setHistoryRecordId(studentTrainRecord.getHistoryRecordId());
                historySupportRecords = historySupportRecordService.selectHistorySupportRecordList(record);
            }
            //获取体格检查模块的数据
            if (studentTrainRecord!=null && studentTrainRecord.getTgRecordId() !=null && !"".equals(studentTrainRecord.getTgRecordId())) {
                TgcheckSupportRecord tgcheckSupportRecord = new TgcheckSupportRecord();
                tgcheckSupportRecord.setCheckRecordId(studentTrainRecord.getTgRecordId());
                tgcheckSupportRecords = tgcheckSupportRecordService.selectTgcheckSupportRecordList1(studentTrainRecord.getPatientId(), tgcheckSupportRecord);
            }
            //获取精神检查模块的数据
            if (studentTrainRecord!=null && studentTrainRecord.getJsRecordId() !=null && !"".equals(studentTrainRecord.getJsRecordId())) {
                JscheckSupportRecord jscheckSupportRecord = new JscheckSupportRecord();
                jscheckSupportRecord.setCheckRecordId(studentTrainRecord.getJsRecordId());
                jscheckSupportRecords = jscheckSupportRecordService.selectJscheckSupportRecordList1(studentTrainRecord.getPatientId(), jscheckSupportRecord);
            }
            //获取辅助检查模块的数据
            if (studentTrainRecord!=null && studentTrainRecord.getFzRecordId() !=null && !"".equals(studentTrainRecord.getFzRecordId())) {
                FzcheckSupportRecord fzcheckSupportRecord = new FzcheckSupportRecord();
                fzcheckSupportRecord.setCheckRecordId(studentTrainRecord.getFzRecordId());
                fzcheckSupportRecords = fzcheckSupportRecordService.selectFzcheckSupportRecordList1(studentTrainRecord.getPatientId(), fzcheckSupportRecord);

            }
        }
        ajaxResult.put("impRecord", impRecord);
        ajaxResult.put("historyRecords", historySupportRecords);
        ajaxResult.put("tgRecords", tgcheckSupportRecords);
        ajaxResult.put("jsRecords", jscheckSupportRecords);
        ajaxResult.put("fzRecords", fzcheckSupportRecords);
        return ajaxResult;
    }

    /**
     * 治疗
     * @param treatment
     * @param recordId 学生训练记录ID
     * @return
     */
    @ApiOperation("治疗")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "treatment", value = "治疗项目",  dataType = "CaseTreatment"),
            @ApiImplicitParam(name = "recordId", value = "学生训练记录id",  dataType = "Long")
    })
    @GetMapping("/treatment")
    public AjaxResult listTreatment(CaseTreatment treatment,Long recordId){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        AjaxResult ajaxResult = AjaxResult.success();
        //获取治疗项目
        List<CaseTreatment> list = caseTreatmentService.selectCaseTreatmentList(treatment);
        List<CaseTreatment> treatments = caseTreatmentService.buildTreatmentTree(list);
        ajaxResult.put("treatments",treatments);

        //获取学生治疗记录
        TreatmentRecord treatmentRecord = null;
        if (recordId!=null && !"".equals(recordId)){
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            if (studentTrainRecord!=null && studentTrainRecord.getTreatRecordId()!=null &&!"".equals(studentTrainRecord.getTreatRecordId())){
                treatmentRecord = treatmentRecordService.selectTreatmentRecordById(studentTrainRecord.getTreatRecordId());
            }
        }
        ajaxResult.put("treatmentRecord",treatmentRecord);
        return ajaxResult;
    }
    /**
     * 病历书写
     * @param id 学生训练记录ID
     * @return
     */
    @ApiOperation("病历书写")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @GetMapping("/medicalWrite/{id}")
    public AjaxResult getMedical(@PathVariable Long id){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        AjaxResult ajaxResult = AjaxResult.success();

        //获取案例患者
        StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);
        CasePatient patient = casePatientService.selectCasePatientById(studentTrainRecord.getPatientId());
        String sex = sysDictDataService.selectDictLabel("sys_user_sex", patient.getSex());
        patient.setSex(sex);
        String maritalStatus = sysDictDataService.selectDictLabel("sys_user_maritalStatus", patient.getMaritalStatus());
        patient.setMaritalStatus(maritalStatus);

        ajaxResult.put("patient",patient);
        return ajaxResult;
    }

    /**
     * 继续上一次训练
     * @param id
     * @return
     */
    @ApiOperation("继续上一次")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @GetMapping("/getLastTrain/{id}")
    public AjaxResult getLast(@PathVariable Long id){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(id);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("studentTrainRecord",studentTrainRecord);
        //治疗
        if(studentTrainRecord.getTreatRecordId()!=null && !"".equals(studentTrainRecord.getTreatRecordId())){
            ajaxResult.put("process","treatment");
            return ajaxResult;
        }
        //诊断
        if (studentTrainRecord.getImpRecordId()!=null && !"".equals(studentTrainRecord.getImpRecordId())){
            ajaxResult.put("process","imp");
            return ajaxResult;
        }
        //辅助检查
        if (studentTrainRecord.getFzRecordId()!=null && !"".equals(studentTrainRecord.getFzRecordId())){
            ajaxResult.put("process","fz");
            return ajaxResult;
        }
        //精神状况检查
        if(studentTrainRecord.getJsRecordId()!=null && !"".equals(studentTrainRecord.getJsRecordId())){

            ajaxResult.put("process","js");
            return ajaxResult;
        }
        //体格检查
        if(studentTrainRecord.getTgRecordId()!=null && !"".equals(studentTrainRecord.getTgRecordId())){

            ajaxResult.put("process","tg");
            return ajaxResult;
        }
        //病史采集
        if(studentTrainRecord.getHistoryRecordId()!=null && !"".equals(studentTrainRecord.getHistoryRecordId())){
            ajaxResult.put("process","historyTaking");
            return ajaxResult;
        }
        return ajaxResult;
    }

    /**
     * 取消上次训练
     * @param id
     * @return
     */
    @ApiOperation("取消上一次")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生训练记录id", required = true, dataType = "Long")
    })
    @GetMapping("/cancelLastTrain/{id}")
    public AjaxResult cancelLast(@PathVariable Long id){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        StudentTrainRecord studentTrainRecord = new StudentTrainRecord();
        studentTrainRecord.setId(id);
        studentTrainRecord.setStatus("2");
        studentTrainRecordService.updateStudentTrainRecord(studentTrainRecord);
//        studentTrainRecordService.deleteStudentTrainRecordById(id);
        return toAjax(studentTrainRecordService.updateStudentTrainRecord(studentTrainRecord));
    }

    /**
     * 获取治疗项目对应的数据
     * @param ids
     * @return
     */
    @ApiOperation("治疗")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "治疗项目数组", required = true, dataType = "Long", allowMultiple=true)
    })
    @GetMapping("/treatment/choose")
    public AjaxResult listTreatment1( Long[] ids){
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        List<CaseTreatment> list = caseTreatmentService.selectTreatmentByIds(ids);
        return AjaxResult.success(list);
    }

    /**
     * 选择项目获取对应的数据
     * @param item
     * @return
     */
    @ApiOperation("检查项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "item", value = "患者项目",  dataType = "CasePatientItem"),
    })
    @GetMapping("/item/choose")
    public AjaxResult chooseItem(CasePatientItem item)
    {
        LoginStudent loginStudent = tokenUtil.getLoginStudent(ServletUtils.getRequest());
        if (loginStudent==null){
            String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", ServletUtils.getRequest().getRequestURI());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        }

        CasePatientItem patientItem = patientItemService.selectCasePatientItemById(item.getPatientId(), item.getItemId());
        return AjaxResult.success(patientItem);
    }
}
