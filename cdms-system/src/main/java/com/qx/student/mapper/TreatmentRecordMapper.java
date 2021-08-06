package com.qx.student.mapper;

import com.qx.student.domain.TreatmentRecord;
import java.util.List;

/**
 * 治疗记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-08
 */
public interface TreatmentRecordMapper 
{
    /**
     * 查询治疗记录
     * 
     * @param id 治疗记录ID
     * @return 治疗记录
     */
    public TreatmentRecord selectTreatmentRecordById(Long id);

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
     * @return 结果
     */
    public int insertTreatmentRecord(TreatmentRecord treatmentRecord);

    /**
     * 修改治疗记录
     * 
     * @param treatmentRecord 治疗记录
     * @return 结果
     */
    public int updateTreatmentRecord(TreatmentRecord treatmentRecord);

    /**
     * 删除治疗记录
     * 
     * @param id 治疗记录ID
     * @return 结果
     */
    public int deleteTreatmentRecordById(Long id);

    /**
     * 批量删除治疗记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTreatmentRecordByIds(Long[] ids);
}
