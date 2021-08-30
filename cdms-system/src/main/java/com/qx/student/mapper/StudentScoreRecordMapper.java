package com.qx.student.mapper;

import com.qx.student.domain.StudentScoreRecord;
import java.util.List;

/**
 * 学生训练分数Mapper接口
 * 
 * @author aaa
 * @date 2021-08-20
 */
public interface StudentScoreRecordMapper 
{
    /**
     * 查询学生训练分数
     * 
     * @param id 学生训练分数ID
     * @return 学生训练分数
     */
    public StudentScoreRecord selectStudentScoreRecordById(Long id);


    public StudentScoreRecord selectStudentScoreRecordByRecordId(Long recordId);

    /**
     * 查询学生训练分数列表
     * 
     * @param studentScoreRecord 学生训练分数
     * @return 学生训练分数集合
     */
    public List<StudentScoreRecord> selectStudentScoreRecordList(StudentScoreRecord studentScoreRecord);

    /**
     * 查询学生训练分数平均值
     *
     * @param
     * @return 结果
     */
    public Double  selectStudentScoreAvg();
    /**
     * 查询学生训练分数最大值
     *
     * @param
     * @return 结果
     */
    public Double selectStudentScoreMax();

    public int hasScoreCount();

    public int hasScoreCountBySum(Double sumScore);


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
     * 删除学生训练分数
     * 
     * @param id 学生训练分数ID
     * @return 结果
     */
    public int deleteStudentScoreRecordById(Long id);

    /**
     * 批量删除学生训练分数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStudentScoreRecordByIds(Long[] ids);
}
