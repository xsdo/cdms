package com.qx.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.ImpSupportRecordMapper;
import com.qx.student.domain.ImpSupportRecord;
import com.qx.student.service.IImpSupportRecordService;

/**
 * 诊断依据支持记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-28
 */
@Service
public class ImpSupportRecordServiceImpl implements IImpSupportRecordService 
{
    @Autowired
    private ImpSupportRecordMapper impSupportRecordMapper;

    /**
     * 查询诊断依据支持记录
     * 
     * @param id 诊断依据支持记录ID
     * @return 诊断依据支持记录
     */
    @Override
    public ImpSupportRecord selectImpSupportRecordById(Long id)
    {
        return impSupportRecordMapper.selectImpSupportRecordById(id);
    }

    /**
     * 查询诊断依据支持记录列表
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 诊断依据支持记录
     */
    @Override
    public List<ImpSupportRecord> selectImpSupportRecordList(ImpSupportRecord impSupportRecord)
    {
        return impSupportRecordMapper.selectImpSupportRecordList(impSupportRecord);
    }

    /**
     * 新增诊断依据支持记录
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 结果
     */
    @Override
    public int insertImpSupportRecord(ImpSupportRecord impSupportRecord)
    {
        return impSupportRecordMapper.insertImpSupportRecord(impSupportRecord);
    }

    /**
     * 修改诊断依据支持记录
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 结果
     */
    @Override
    public int updateImpSupportRecord(ImpSupportRecord impSupportRecord)
    {
        return impSupportRecordMapper.updateImpSupportRecord(impSupportRecord);
    }

    /**
     * 批量删除诊断依据支持记录
     * 
     * @param ids 需要删除的诊断依据支持记录ID
     * @return 结果
     */
    @Override
    public int deleteImpSupportRecordByIds(Long[] ids)
    {
        return impSupportRecordMapper.deleteImpSupportRecordByIds(ids);
    }

    /**
     * 删除诊断依据支持记录信息
     * 
     * @param id 诊断依据支持记录ID
     * @return 结果
     */
    @Override
    public int deleteImpSupportRecordById(Long id)
    {
        return impSupportRecordMapper.deleteImpSupportRecordById(id);
    }
}
