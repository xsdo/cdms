package com.qx.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.HistorySupportRecordMapper;
import com.qx.student.domain.HistorySupportRecord;
import com.qx.student.service.IHistorySupportRecordService;

/**
 * 病史采集问题支持记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-17
 */
@Service
public class HistorySupportRecordServiceImpl implements IHistorySupportRecordService 
{
    @Autowired
    private HistorySupportRecordMapper historySupportRecordMapper;

    /**
     * 查询病史采集问题支持记录
     * 
     * @param id 病史采集问题支持记录ID
     * @return 病史采集问题支持记录
     */
    @Override
    public HistorySupportRecord selectHistorySupportRecordById(Long id)
    {
        return historySupportRecordMapper.selectHistorySupportRecordById(id);
    }

    /**
     * 查询病史采集问题支持记录列表
     * 
     * @param historySupportRecord 病史采集问题支持记录
     * @return 病史采集问题支持记录
     */
    @Override
    public List<HistorySupportRecord> selectHistorySupportRecordList(HistorySupportRecord historySupportRecord)
    {
        return historySupportRecordMapper.selectHistorySupportRecordList(historySupportRecord);
    }

    /**
     * 查询病史采集问题支持记录列表（核心）
     *
     * @param historySupportRecord 病史采集问题支持记录
     * @return 病史采集问题支持记录
     */
    @Override
    public List<HistorySupportRecord> selectHistorySupportRecordListCore(HistorySupportRecord historySupportRecord)
    {
        return historySupportRecordMapper.selectHistorySupportRecordListCore(historySupportRecord);
    }
    /**
     * 新增病史采集问题支持记录
     * 
     * @param historySupportRecord 病史采集问题支持记录
     * @return 结果
     */
    @Override
    public int insertHistorySupportRecord(HistorySupportRecord historySupportRecord)
    {
        return historySupportRecordMapper.insertHistorySupportRecord(historySupportRecord);
    }

    /**
     * 修改病史采集问题支持记录
     * 
     * @param historySupportRecord 病史采集问题支持记录
     * @return 结果
     */
    @Override
    public int updateHistorySupportRecord(HistorySupportRecord historySupportRecord)
    {
        return historySupportRecordMapper.updateHistorySupportRecord(historySupportRecord);
    }

    /**
     * 批量删除病史采集问题支持记录
     * 
     * @param ids 需要删除的病史采集问题支持记录ID
     * @return 结果
     */
    @Override
    public int deleteHistorySupportRecordByIds(Long[] ids)
    {
        return historySupportRecordMapper.deleteHistorySupportRecordByIds(ids);
    }

    /**
     * 删除病史采集问题支持记录信息
     * 
     * @param id 病史采集问题支持记录ID
     * @return 结果
     */
    @Override
    public int deleteHistorySupportRecordById(Long id)
    {
        return historySupportRecordMapper.deleteHistorySupportRecordById(id);
    }

    @Override
    public int insertHistorySupportRecordByList(List<HistorySupportRecord> list) {
        return historySupportRecordMapper.insertHistorySupportRecordByList(list);
    }

    /**
     * 批量更新
     * @param list
     * @return
     */
    @Override
    public int updateHistorySupportRecordBatch(List<HistorySupportRecord> list) {
        return historySupportRecordMapper.updateHistorySupportRecordBatch(list);
    }

    @Override
    public List<HistorySupportRecord> selectHistorySupportRecordListByHistoryId(Long id) {
        return historySupportRecordMapper.selectHistorySupportRecordListByHistoryId(id);
    }
}
