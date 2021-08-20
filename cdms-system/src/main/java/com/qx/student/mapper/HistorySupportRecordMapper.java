package com.qx.student.mapper;

import com.qx.student.domain.HistorySupportRecord;
import java.util.List;

/**
 * 病史采集问题支持记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-17
 */
public interface HistorySupportRecordMapper 
{
    /**
     * 查询病史采集问题支持记录
     * 
     * @param id 病史采集问题支持记录ID
     * @return 病史采集问题支持记录
     */
    public HistorySupportRecord selectHistorySupportRecordById(Long id);

    /**
     * 查询病史采集问题支持记录列表
     * 
     * @param historySupportRecord 病史采集问题支持记录
     * @return 病史采集问题支持记录集合
     */
    public List<HistorySupportRecord> selectHistorySupportRecordList(HistorySupportRecord historySupportRecord);


    /**
     * 查询病史采集问题支持记录列表 （核心）
     *
     * @param historySupportRecord 病史采集问题支持记录
     * @return 病史采集问题支持记录集合
     */
    public List<HistorySupportRecord> selectHistorySupportRecordListCore(HistorySupportRecord historySupportRecord);

    /**
     * 查询病史采集问题支持记录列表
     *
     * @param historyRecordId 病史采集记录id
     * @return 病史采集问题支持记录集合
     */
    public List<HistorySupportRecord> selectHistorySupportRecordListByHistoryId(Long historyRecordId);
    /**
     * 新增病史采集问题支持记录
     * 
     * @param historySupportRecord 病史采集问题支持记录
     * @return 结果
     */
    public int insertHistorySupportRecord(HistorySupportRecord historySupportRecord);

    /**
     * 修改病史采集问题支持记录
     * 
     * @param historySupportRecord 病史采集问题支持记录
     * @return 结果
     */
    public int updateHistorySupportRecord(HistorySupportRecord historySupportRecord);

    /**
     * 删除病史采集问题支持记录
     * 
     * @param id 病史采集问题支持记录ID
     * @return 结果
     */
    public int deleteHistorySupportRecordById(Long id);

    /**
     * 批量删除病史采集问题支持记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHistorySupportRecordByIds(Long[] ids);

    /**
     * 批量插入
     * @param list
     * @return 结果
     */
    int insertHistorySupportRecordByList(List<HistorySupportRecord> list);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateHistorySupportRecordBatch(List<HistorySupportRecord> list);
}
