package com.qx.student.service;

import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.TreatmentRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 治疗记录Service接口
 * 
 * @author aaa
 * @date 2021-06-08
 */
@Transactional
public interface ITreatmentRecordService 
{
    /**
     * 查询治疗记录
     * 
     * @param id 治疗记录ID
     * @return 治疗记录
     */
    public TreatmentRecord selectTreatmentRecordById(Long id);

    Double countTreatScore(Long id);

    /**
     * 查询治疗记录列表
     * 
     * @param treatmentRecord 治疗记录
     * @return 治疗记录集合
     */
    public List<TreatmentRecord> selectTreatmentRecordList(TreatmentRecord treatmentRecord);

    /**
     * 新增治疗记录
     * 
     * @param treatmentRecord 治疗记录
     * @param trainRecord 学生训练记录
     * @return 结果
     */
    public int insertTreatmentRecord(TreatmentRecord treatmentRecord, StudentTrainRecord trainRecord);

    /**
     * 修改治疗记录
     * 
     * @param treatmentRecord 治疗记录
     * @return 结果
     */
    public int updateTreatmentRecord(TreatmentRecord treatmentRecord);

    /**
     * 批量删除治疗记录
     * 
     * @param ids 需要删除的治疗记录ID
     * @return 结果
     */
    public int deleteTreatmentRecordByIds(Long[] ids);

    /**
     * 删除治疗记录信息
     * 
     * @param id 治疗记录ID
     * @return 结果
     */
    public int deleteTreatmentRecordById(Long id);
}
