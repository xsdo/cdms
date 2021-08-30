package com.qx.student.service;

import com.qx.common.core.domain.AjaxResult;
import com.qx.student.domain.StudentScoreRecord;
import java.util.List;

/**
 * 学生训练分数Service接口
 * 
 * @author aaa
 * @date 2021-08-20
 */
public interface IStudentScoreRecordService 
{
    /**
     * 查询学生训练分数
     * 
     * @param id 学生训练分数ID
     * @return 学生训练分数
     */
    public StudentScoreRecord selectStudentScoreRecordById(Long id);

    StudentScoreRecord selectStudentScoreRecordByRecordId(Long recordId);



    StudentScoreRecord changeStudentScore(Long recordId, Long stuId, Long patientId, StudentScoreRecord studentScoreRecord
    );

    //分数平均值
    Double  selectStudentScoreAvg();
    //分数最大值
    Double  selectStudentScoreMax();

    int getScoreAccount(Double sumScore);

    String getLevel(int account);

    AjaxResult getScoringRate(StudentScoreRecord studentScoreRecord);

    /**
     * 查询学生训练分数列表
     * 
     * @param studentScoreRecord 学生训练分数
     * @return 学生训练分数集合
     */
    public List<StudentScoreRecord> selectStudentScoreRecordList(StudentScoreRecord studentScoreRecord);

    /**
     * 新增学生训练分数
     * 
     * @param studentScoreRecord 学生训练分数
     * @return 结果
     */
    public int insertStudentScoreRecord(StudentScoreRecord studentScoreRecord);

    /**
     * 修改学生训练分数
     * 
     * @param studentScoreRecord 学生训练分数
     * @return 结果
     */
    public int updateStudentScoreRecord(StudentScoreRecord studentScoreRecord);

    /**
     * 批量删除学生训练分数
     * 
     * @param ids 需要删除的学生训练分数ID
     * @return 结果
     */
    public int deleteStudentScoreRecordByIds(Long[] ids);

    /**
     * 删除学生训练分数信息
     * 
     * @param id 学生训练分数ID
     * @return 结果
     */
    public int deleteStudentScoreRecordById(Long id);
}
