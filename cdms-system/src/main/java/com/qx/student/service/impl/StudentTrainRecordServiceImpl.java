package com.qx.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.StudentTrainRecordMapper;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.service.IStudentTrainRecordService;

/**
 * 学生训练记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Service
public class StudentTrainRecordServiceImpl implements IStudentTrainRecordService 
{
    @Autowired
    private StudentTrainRecordMapper studentTrainRecordMapper;

    /**
     * 查询学生训练记录
     * 
     * @param id 学生训练记录ID
     * @return 学生训练记录
     */
    @Override
    public StudentTrainRecord selectStudentTrainRecordById(Long id)
    {
        return studentTrainRecordMapper.selectStudentTrainRecordById(id);
    }

    /**
     * 查询学生训练记录列表
     * 
     * @param studentTrainRecord 学生训练记录
     * @return 学生训练记录
     */
    @Override
    public List<StudentTrainRecord> selectStudentTrainRecordList(StudentTrainRecord studentTrainRecord)
    {
        return studentTrainRecordMapper.selectStudentTrainRecordList(studentTrainRecord);
    }

    /**
     * 新增学生训练记录
     * 
     * @param studentTrainRecord 学生训练记录
     * @return 结果
     */
    @Override
    public int insertStudentTrainRecord(StudentTrainRecord studentTrainRecord)
    {
        return studentTrainRecordMapper.insertStudentTrainRecord(studentTrainRecord);
    }

    /**
     * 修改学生训练记录
     * 
     * @param studentTrainRecord 学生训练记录
     * @return 结果
     */
    @Override
    public int updateStudentTrainRecord(StudentTrainRecord studentTrainRecord)
    {
        return studentTrainRecordMapper.updateStudentTrainRecord(studentTrainRecord);
    }

    /**
     * 批量删除学生训练记录
     * 
     * @param ids 需要删除的学生训练记录ID
     * @return 结果
     */
    @Override
    public int deleteStudentTrainRecordByIds(Long[] ids)
    {
        return studentTrainRecordMapper.deleteStudentTrainRecordByIds(ids);
    }

    /**
     * 删除学生训练记录信息
     * 
     * @param id 学生训练记录ID
     * @return 结果
     */
    @Override
    public int deleteStudentTrainRecordById(Long id)
    {
        return studentTrainRecordMapper.deleteStudentTrainRecordById(id);
    }
}
