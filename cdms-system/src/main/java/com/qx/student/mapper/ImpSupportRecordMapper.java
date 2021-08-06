package com.qx.student.mapper;

import com.qx.student.domain.ImpSupportRecord;
import java.util.List;

/**
 * 诊断依据支持记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-28
 */
public interface ImpSupportRecordMapper 
{
    /**
     * 查询诊断依据支持记录
     * 
     * @param id 诊断依据支持记录ID
     * @return 诊断依据支持记录
     */
    public ImpSupportRecord selectImpSupportRecordById(Long id);

    /**
     * 查询诊断依据支持记录列表
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 诊断依据支持记录集合
     */
    public List<ImpSupportRecord> selectImpSupportRecordList(ImpSupportRecord impSupportRecord);

    /**
     * 新增诊断依据支持记录
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 结果
     */
    public int insertImpSupportRecord(ImpSupportRecord impSupportRecord);

    /**
     * 修改诊断依据支持记录
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 结果
     */
    public int updateImpSupportRecord(ImpSupportRecord impSupportRecord);

    /**
     * 删除诊断依据支持记录
     * 
     * @param id 诊断依据支持记录ID
     * @return 结果
     */
    public int deleteImpSupportRecordById(Long id);

    /**
     * 批量删除诊断依据支持记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpSupportRecordByIds(Long[] ids);
}
