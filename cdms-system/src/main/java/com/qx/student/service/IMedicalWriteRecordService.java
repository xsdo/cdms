package com.qx.student.service;

import com.qx.student.domain.MedicalWriteRecord;
import com.qx.student.domain.vo.MedicalWriteRecordVo;

import java.util.List;

/**
 * 病历书写Service接口
 * 
 * @author aaa
 * @date 2021-06-25
 */
public interface IMedicalWriteRecordService 
{
    /**
     * 查询病历书写
     * 
     * @param id 病历书写ID
     * @return 病历书写
     */
    public MedicalWriteRecord selectMedicalWriteRecordById(Long id);

    /**
     * 查询病历书写列表
     * 
     * @param medicalWriteRecord 病历书写
     * @return 病历书写集合
     */
    public List<MedicalWriteRecord> selectMedicalWriteRecordList(MedicalWriteRecord medicalWriteRecord);

    /**
     * 新增病历书写
     * 
     * @param medicalWriteRecord 病历书写
     * @return 结果
     */
    public int insertMedicalWriteRecord(MedicalWriteRecordVo medicalWriteRecord);

    /**
     * 修改病历书写
     * 
     * @param medicalWriteRecord 病历书写
     * @return 结果
     */
    public int updateMedicalWriteRecord(MedicalWriteRecord medicalWriteRecord);

    /**
     * 批量删除病历书写
     * 
     * @param ids 需要删除的病历书写ID
     * @return 结果
     */
    public int deleteMedicalWriteRecordByIds(Long[] ids);

    /**
     * 删除病历书写信息
     * 
     * @param id 病历书写ID
     * @return 结果
     */
    public int deleteMedicalWriteRecordById(Long id);
}
