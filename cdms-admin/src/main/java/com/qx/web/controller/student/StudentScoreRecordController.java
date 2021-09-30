package com.qx.web.controller.student;


import java.util.List;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CaseQuestion;
import com.qx.cases.service.*;
import com.qx.student.domain.*;
import com.qx.student.domain.vo.ImpChooseVo;
import com.qx.student.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 学生训练分数Controller
 * 
 * @author aaa
 * @date 2021-08-20
 */
@RestController
@RequestMapping("/student/record")
public class StudentScoreRecordController extends BaseController
{
    @Autowired
    private IStudentScoreRecordService studentScoreRecordService;

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
    private IMedicalWriteRecordService medicalWriteRecordService;

    @Autowired
    private IXlCheckRecordService xlCheckRecordService;

    @Autowired
    private ICaseQuestionService caseQuestionService;
    /**
     * 查询学生训练分数列表
     */
    @PreAuthorize("@ss.hasPermi('student:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentScoreRecord studentScoreRecord)
    {
        startPage();
        List<StudentScoreRecord> list = studentScoreRecordService.selectStudentScoreRecordList(studentScoreRecord);
        return getDataTable(list);
    }

    /**
     * 导出学生训练分数列表
     */
    @PreAuthorize("@ss.hasPermi('student:record:export')")
    @Log(title = "学生训练分数", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StudentScoreRecord studentScoreRecord)
    {
        List<StudentScoreRecord> list = studentScoreRecordService.selectStudentScoreRecordList(studentScoreRecord);
        ExcelUtil<StudentScoreRecord> util = new ExcelUtil<StudentScoreRecord>(StudentScoreRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取学生训练分数详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(studentScoreRecordService.selectStudentScoreRecordById(id));
    }

    @GetMapping(value = "getMissScore/{recordId}")
    public AjaxResult getMissScore(@PathVariable("recordId") Long recordId){
        AjaxResult ajax = AjaxResult.success();
        if (recordId!=null && !"".equals(recordId)) {
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            if (studentTrainRecord==null){
                return AjaxResult.error("未查询到检查记录");
            }
            List<CaseQuestion> historyMissRecord=null;
            List<CaseCheckItem>tgMissRecord=null;
            List<CaseCheckItem>jsMissRecord=null;
//            List<CaseCheckItem>jsWrongRecord=null;
            List<CaseCheckItem>xlMissRecord=null;
//            List<CaseCheckItem>xlWrongRecord=null;
            List<CaseCheckItem>fzMissRecord=null;
//            Map<String,String> impChooseRecord= new IdentityHashMap<String,String>() ;;
            List<ImpChooseVo>impChooseVos =null;
            TreatmentRecord treatChooseRecord =null;
            if (studentTrainRecord!=null){
                //获取病史采集漏采项 1
                if (studentTrainRecord.getHistoryRecordId() != null && !"".equals(studentTrainRecord.getHistoryRecordId())){
                    //给病史采集项添加所属类型
                    historyMissRecord=caseQuestionService.selectCaseQuestionListType(historyTakingRecordService.selectHistoryMissQuestions(studentTrainRecord.getHistoryRecordId()));
                }
                //获取体格检查漏采项 1
                if (studentTrainRecord.getTgRecordId() !=null && !"".equals(studentTrainRecord.getTgRecordId())) {
                    tgMissRecord = tgCheckRecordService.selectTgMissRecordById(studentTrainRecord.getTgRecordId());
                }
                //获取精神检查漏采项 和 错采项 1
                if (studentTrainRecord.getJsRecordId() !=null && !"".equals(studentTrainRecord.getJsRecordId())) {
                    jsMissRecord =jsCheckRecordService.selectJsMissRecordById(studentTrainRecord.getJsRecordId());
//                    jsWrongRecord =jsCheckRecordService.selectJsWrongRecordById(studentTrainRecord.getJsRecordId());
                }
                //获取心理测量漏采项 和 错采项 1
                if (studentTrainRecord.getXlRecordId()!=null&&!"".equals(studentTrainRecord.getXlRecordId())){
                    xlMissRecord=xlCheckRecordService.selectXlMissRecordById(studentTrainRecord.getXlRecordId());
//                    xlWrongRecord=xlCheckRecordService.selectXlWrongRecordById(studentTrainRecord.getXlRecordId());
                }
                //获取辅助检查漏采项 1
                if (studentTrainRecord.getFzRecordId() !=null && !"".equals(studentTrainRecord.getFzRecordId())) {
                    fzMissRecord =fzCheckRecordService.selectFzMissRecordById(studentTrainRecord.getFzRecordId());
                }
                //获取学生诊断答案 1
                if (studentTrainRecord.getImpRecordId() !=null && !"".equals(studentTrainRecord.getImpRecordId())) {
                    impChooseVos = impRecordService.getImpChooseVo(studentTrainRecord.getImpRecordId());
                }
                //获取学生治疗答案 1
                if (studentTrainRecord.getTreatRecordId() !=null && !"".equals(studentTrainRecord.getTreatRecordId())) {
                    treatChooseRecord = treatmentRecordService.selectTreatmentRecordById(studentTrainRecord.getTreatRecordId());
                }
                //获取学生病例书写（固定模板）
            }

            ajax.put("historyMissRecord",historyMissRecord);
            ajax.put("tgMissRecord",tgMissRecord);
            ajax.put("jsMissRecord",jsMissRecord);
//            ajax.put("jsWrongRecord",jsWrongRecord);
            ajax.put("xlMissRecord",xlMissRecord);
//            ajax.put("xlWrongRecord",xlWrongRecord);
            ajax.put("fzMissRecord",fzMissRecord);
            ajax.put("impChooseRecord",impChooseVos);
            ajax.put("treatChooseRecord",treatChooseRecord);
        }
            return ajax;
    }
    /*
    *
    * 计算学生得分
    */
    @GetMapping(value = "countScore/{recordId}")
    public AjaxResult countScore(@PathVariable("recordId") Long recordId){
        AjaxResult ajax = AjaxResult.success();
        if (recordId!=null && !"".equals(recordId)){
            StudentTrainRecord studentTrainRecord = studentTrainRecordService.selectStudentTrainRecordById(recordId);
            if (studentTrainRecord==null){
                return AjaxResult.error("未查询到检查记录");
            }
            StudentScoreRecord studentScoreRecord =new StudentScoreRecord();
                //计算问诊分数 1
                if (studentTrainRecord!=null && studentTrainRecord.getHistoryRecordId() != null && !"".equals(studentTrainRecord.getHistoryRecordId())){
                    studentScoreRecord.setHistoryScore(historyTakingRecordService.countHistoryScore(studentTrainRecord.getHistoryRecordId()));
                }
                //计算体格检查分数 1
                if (studentTrainRecord!=null && studentTrainRecord.getTgRecordId() !=null && !"".equals(studentTrainRecord.getTgRecordId())) {
                    studentScoreRecord.setTgScore(tgCheckRecordService.countTgScore(studentTrainRecord.getTgRecordId()));
                }
                //计算精神检查分数 1
                if (studentTrainRecord!=null && studentTrainRecord.getJsRecordId() !=null && !"".equals(studentTrainRecord.getJsRecordId())) {
                    studentScoreRecord.setJsScore(jsCheckRecordService.countJsScore(studentTrainRecord.getJsRecordId()));
                }
                //计算心理测量分数 1
                if (studentTrainRecord!=null && studentTrainRecord.getXlRecordId() !=null & !"".equals(studentTrainRecord.getXlRecordId())){
                    studentScoreRecord.setXlScore(xlCheckRecordService.countXlScore(studentTrainRecord.getXlRecordId()));
                }
                //计算辅助检查分数 1
                if (studentTrainRecord!=null && studentTrainRecord.getFzRecordId() !=null && !"".equals(studentTrainRecord.getFzRecordId())) {
                    studentScoreRecord.setFzScore(fzCheckRecordService.countFzScore(studentTrainRecord.getFzRecordId()));
                }
                //计算诊断得分 1
                if (studentTrainRecord !=null && studentTrainRecord.getImpRecordId() !=null && !"".equals(studentTrainRecord.getImpRecordId())) {
                    studentScoreRecord.setImpScore(impRecordService.countImpScore(studentTrainRecord.getImpRecordId()));
                }
                //计算治疗分数 1
                 if (studentTrainRecord!=null && studentTrainRecord.getTreatRecordId() !=null && !"".equals(studentTrainRecord.getTreatRecordId())) {
                    studentScoreRecord.setTreatScore(treatmentRecordService.countTreatScore(studentTrainRecord.getTreatRecordId()));
                }
                //计算病例书写分数 1
                if (studentTrainRecord!=null && studentTrainRecord.getMedicalRecordId() !=null && !"".equals(studentTrainRecord.getMedicalRecordId())) {
                    studentScoreRecord.setMedicalScore(medicalWriteRecordService.countMedicalScore(studentTrainRecord.getMedicalRecordId()));
                }
            //同步学生分数信息表
            studentScoreRecord = studentScoreRecordService.changeStudentScore(
                    recordId,studentTrainRecord.getStuId(),studentTrainRecord.getPatientId(), studentScoreRecord
                    );
            ajax.put("studentScoreRecord",studentScoreRecord);
        }
        return ajax;
    }

    /**
     * 获取学生训练分数分析
     */
    @GetMapping(value = "getScore/{recordId}")
    public AjaxResult getScore(@PathVariable("recordId") Long recordId)
    {
        AjaxResult ajax =AjaxResult.success();
        if (recordId!=null && !"".equals(recordId)) {
            StudentScoreRecord studentScoreRecord = studentScoreRecordService.selectStudentScoreRecordByRecordId(recordId);
            if (studentScoreRecord != null) {
                ajax = studentScoreRecordService.getScoringRate(studentScoreRecord);
                Double sumScore = studentScoreRecord.getSumScore();
                Double avgScore = studentScoreRecordService.selectStudentScoreAvg();
                Double maxScore = studentScoreRecordService.selectStudentScoreMax();
                int account = studentScoreRecordService.getScoreAccount(sumScore);
                String level = studentScoreRecordService.getLevel(sumScore);
                ajax.put("avgScore", avgScore);
                ajax.put("maxScore", maxScore);
                ajax.put("account", account);
                ajax.put("level", level);
            } else {
                return AjaxResult.error("未答题或未提交分数");
            }
            ajax.put("data", studentScoreRecord);
        }
        return ajax;
    }

    @GetMapping(value = "getScoringRate/{recordId}")
    public AjaxResult getScoringRate(@PathVariable("recordId") Long recordId)
    {
        AjaxResult ajax =AjaxResult.success();
        StudentScoreRecord studentScoreRecord =studentScoreRecordService.selectStudentScoreRecordByRecordId(recordId);
        if (studentScoreRecord!=null){
            ajax=studentScoreRecordService.getScoringRate(studentScoreRecord);
        }
        ajax.put("data",studentScoreRecord);
        return ajax;
    }


    @GetMapping(value = "getScoreData")
    public AjaxResult getScoreData(Double sumScore){

        AjaxResult ajax =AjaxResult.success();
        Double avgScore;
        Double maxScore;
        int account;
        String level;
        avgScore = studentScoreRecordService.selectStudentScoreAvg();
        maxScore = studentScoreRecordService.selectStudentScoreMax();
        account = studentScoreRecordService.getScoreAccount(sumScore);
        level = studentScoreRecordService.getLevel(sumScore);
        ajax.put("avgScore",avgScore);
        ajax.put("maxScore",maxScore);
        ajax.put("account",account);
        ajax.put("level",level);
        return ajax;
    }


    /**
     * 新增学生训练分数
     */
    @PreAuthorize("@ss.hasPermi('student:record:add')")
    @Log(title = "学生训练分数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentScoreRecord studentScoreRecord)
    {
        return toAjax(studentScoreRecordService.insertStudentScoreRecord(studentScoreRecord));
    }

    /**
     * 修改学生训练分数
     */
    @PreAuthorize("@ss.hasPermi('student:record:edit')")
    @Log(title = "学生训练分数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentScoreRecord studentScoreRecord)
    {
        return toAjax(studentScoreRecordService.updateStudentScoreRecord(studentScoreRecord));
    }

    /**
     * 删除学生训练分数
     */
    @PreAuthorize("@ss.hasPermi('student:record:remove')")
    @Log(title = "学生训练分数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentScoreRecordService.deleteStudentScoreRecordByIds(ids));
    }
}
