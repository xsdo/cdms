package com.qx.student.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import com.qx.common.core.domain.AjaxResult;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.StudentScoreRecordMapper;
import com.qx.student.domain.StudentScoreRecord;
import com.qx.student.service.IStudentScoreRecordService;

/**
 * 学生训练分数Service业务层处理
 * 
 * @author aaa
 * @date 2021-08-20
 */
@Service
public class StudentScoreRecordServiceImpl implements IStudentScoreRecordService
{
    @Autowired
    private StudentScoreRecordMapper studentScoreRecordMapper;

    /**
     * 查询学生训练分数
     *
     * @param id 学生训练分数ID
     * @return 学生训练分数
     */
    @Override
    public StudentScoreRecord selectStudentScoreRecordById(Long id)
    {
        return studentScoreRecordMapper.selectStudentScoreRecordById(id);
    }

    @Override
    public StudentScoreRecord selectStudentScoreRecordByRecordId(Long recordId)
    {
        return studentScoreRecordMapper.selectStudentScoreRecordByRecordId(recordId);
    }

    @Override
    public StudentScoreRecord changeStudentScore(Long recordId, Long stuId, Long patientId, StudentScoreRecord studentScoreRecord
    ){
        if (studentScoreRecord==null){ return null;}
        StudentScoreRecord studentScoreRecordOld =this.selectStudentScoreRecordByRecordId(recordId);
        studentScoreRecord.setStuId(stuId);
        studentScoreRecord.setPatientId(patientId);
        studentScoreRecord.setRecordId(recordId);
        Double sumScore =new Double(0);
        sumScore=getSum(sumScore,studentScoreRecord);
        studentScoreRecord.setSumScore(sumScore);
        if (studentScoreRecordOld==null){
            //新增分数记录表
            this.insertStudentScoreRecord(studentScoreRecord);
        }else {
            //更新分数记录表
            studentScoreRecord.setId(studentScoreRecordOld.getId());
            studentScoreRecord.setUpdataTime(new Date());
            this.updateStudentScoreRecord(studentScoreRecord);
        }
        return studentScoreRecord;
    }

    public Double getSum(Double sumScore ,StudentScoreRecord studentScoreRecord){
        if (studentScoreRecord==null){return new Double(0);}
        if (studentScoreRecord.getHistoryScore()!=null){sumScore+=studentScoreRecord.getHistoryScore();}
        if (studentScoreRecord.getTgScore()!=null){sumScore+=studentScoreRecord.getTgScore();}
        if (studentScoreRecord.getJsScore()!=null){sumScore+=studentScoreRecord.getJsScore();}
        if (studentScoreRecord.getXlScore()!=null){sumScore+=studentScoreRecord.getXlScore();}
        if (studentScoreRecord.getFzScore()!=null){sumScore+=studentScoreRecord.getFzScore();}
        if (studentScoreRecord.getImpScore()!=null){sumScore+=studentScoreRecord.getImpScore();}
        if (studentScoreRecord.getTreatScore()!=null){sumScore+=studentScoreRecord.getTreatScore();}
        if (studentScoreRecord.getMedicalScore()!=null){sumScore+=studentScoreRecord.getMedicalScore();}
        return sumScore;
    }
    /**
     * 查询学生训练分数平均值
     *
     * @param
     * @return 学生训练分数
     */
    @Override
    public  Double  selectStudentScoreAvg()
    {
        double d = studentScoreRecordMapper.selectStudentScoreAvg();
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
//        return studentScoreRecordMapper.selectStudentScoreAvg();
    }

    /**
     * 查询学生训练分数最大值
     *
     * @param
     * @return 学生训练分数
     */
    @Override
    public  Double  selectStudentScoreMax( )
    {
        return studentScoreRecordMapper.selectStudentScoreMax();
    }

    //计算当前成绩战胜百分多少用户
    @Override
    public int getScoreAccount(Double sumScore){
        int account= 0;
        int scoreAll =studentScoreRecordMapper.hasScoreCount();
        int scoreBySum = studentScoreRecordMapper.hasScoreCountBySum(sumScore);
        if (scoreAll>0){
            account=scoreBySum*100/scoreAll;
        }
        return account;
    }

    //根据战胜用户判断成绩登记
    @Override
    public String getLevel (int account){
        String level ="D";
        if (account>=90){level="A";}
        else if (account>=80&&account<90){level="B";}
        else if (account>=60&&account<80){level="C";}

        return level;
    }

    //获取每个项目得分率
    @Override
    public AjaxResult getScoringRate(StudentScoreRecord studentScoreRecord){
        AjaxResult ajaxResult =AjaxResult.success();
        if (studentScoreRecord!=null){
            int historySR=new Double(studentScoreRecord.getHistoryScore()*100/22.5).intValue();
            int tgSR=new Double(studentScoreRecord.getTgScore()*100/6).intValue();
            int jsSR=new Double(studentScoreRecord.getJsScore()*100/10.5).intValue();
            int xlSR=new Double(studentScoreRecord.getXlScore()*100/7).intValue();
            int fzSR=new Double(studentScoreRecord.getFzScore()*100/9).intValue();
            int impSR=new Double(studentScoreRecord.getImpScore()*100/20).intValue();
            int treatSR=new Double(studentScoreRecord.getTreatScore()*100/20).intValue();
            int medicalSR=new Double(studentScoreRecord.getMedicalScore()*100/5).intValue();
            ajaxResult.put("historySR",historySR+"%");
            ajaxResult.put("tgSR",tgSR+"%");
            ajaxResult.put("jsSR",jsSR+"%");
            ajaxResult.put("xlSR",xlSR+"%");
            ajaxResult.put("fzSR",fzSR+"%");
            ajaxResult.put("impSR",impSR+"%");
            ajaxResult.put("treatSR",treatSR+"%");
            ajaxResult.put("medicalSR",medicalSR+"%");
        }
        return ajaxResult;
    }
    /**
     * 查询学生训练分数列表
     * 
     * @param studentScoreRecord 学生训练分数
     * @return 学生训练分数
     */
    @Override
    public List<StudentScoreRecord> selectStudentScoreRecordList(StudentScoreRecord studentScoreRecord)
    {
        return studentScoreRecordMapper.selectStudentScoreRecordList(studentScoreRecord);
    }

    /**
     * 新增学生训练分数
     * 
     * @param studentScoreRecord 学生训练分数
     * @return 结果
     */
    @Override
    public int insertStudentScoreRecord(StudentScoreRecord studentScoreRecord)
    {
        studentScoreRecord.setCreateTime(DateUtils.getNowDate());
        return studentScoreRecordMapper.insertStudentScoreRecord(studentScoreRecord);
    }

    /**
     * 修改学生训练分数
     * 
     * @param studentScoreRecord 学生训练分数
     * @return 结果
     */
    @Override
    public int updateStudentScoreRecord(StudentScoreRecord studentScoreRecord)
    {
        return studentScoreRecordMapper.updateStudentScoreRecord(studentScoreRecord);
    }

    /**
     * 批量删除学生训练分数
     * 
     * @param ids 需要删除的学生训练分数ID
     * @return 结果
     */
    @Override
    public int deleteStudentScoreRecordByIds(Long[] ids)
    {
        return studentScoreRecordMapper.deleteStudentScoreRecordByIds(ids);
    }

    /**
     * 删除学生训练分数信息
     * 
     * @param id 学生训练分数ID
     * @return 结果
     */
    @Override
    public int deleteStudentScoreRecordById(Long id)
    {
        return studentScoreRecordMapper.deleteStudentScoreRecordById(id);
    }
}
