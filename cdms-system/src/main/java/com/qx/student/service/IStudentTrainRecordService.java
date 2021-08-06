package com.qx.student.service;

import com.qx.student.domain.StudentTrainRecord;
import java.util.List;

/**
 * 学生训练记录Service接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
public interface IStudentTrainRecordService 
{
    /**
     * 查询学生训练记录
     * 
     * @param id 学生训练记录ID
     * @return 学生训练记录
     */
    public StudentTrainRecord selectStudentTrainRecordById(Long id);

    /**
     * 查询学生训练记录列表
     * 
     * @param studentTrainRecord 学生训练记录
     * @return 学生训练记录集合
     */
    public List<StudentTrainRecord> selectStudentTrainRecordList(StudentTrainRecord studentTrainRecord);

    /**
     * 新增学生训练记录
     * 
     * @param studentTrainRecord 学生训练记录
     * @return 结果
     */
    public int insertStudentTrainRecord(StudentTrainRecord studentTrainRecord);

    /**
     * 修改学生训练记录
     * 
     * @param studentTrainRecord 学生训练记录
     * @return 结果
     */
    public int updateStudentTrainRecord(StudentTrainRecord studentTrainRecord);

    /**
     * 批量删除学生训练记录
     * 
     * @param ids 需要删除的学生训练记录ID
     * @return 结果
     */
    public int deleteStudentTrainRecordByIds(Long[] ids);

    /**
     * 删除学生训练记录信息
     * 
     * @param id 学生训练记录ID
     * @return 结果
     */
    public int deleteStudentTrainRecordById(Long id);
}
