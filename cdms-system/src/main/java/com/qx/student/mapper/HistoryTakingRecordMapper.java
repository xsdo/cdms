package com.qx.student.mapper;

import com.qx.student.domain.HistoryTakingRecord;
import java.util.List;

/**
 * 病史采集记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
public interface HistoryTakingRecordMapper 
{
    /**
     * 查询病史采集记录
     * 
     * @param id 病史采集记录ID
     * @return 病史采集记录
     */
    public HistoryTakingRecord selectHistoryTakingRecordById(Long id);

    /**
     * 查询病史采集记录列表
     * 
     * @param historyTakingRecord 病史采集记录
     * @return 病史采集记录集合
     */
    public List<HistoryTakingRecord> selectHistoryTakingRecordList(HistoryTakingRecord historyTakingRecord);

    /**
     * 新增病史采集记录
     * 
     * @param historyTakingRecord 病史采集记录
     * @return 结果
     */
    public int insertHistoryTakingRecord(HistoryTakingRecord historyTakingRecord);

    /**
     * 修改病史采集记录
     * 
     * @param historyTakingRecord 病史采集记录
     * @return 结果
     */
    public int updateHistoryTakingRecord(HistoryTakingRecord historyTakingRecord);

    /**
     * 删除病史采集记录
     * 
     * @param id 病史采集记录ID
     * @return 结果
     */
    public int deleteHistoryTakingRecordById(Long id);

    /**
     * 批量删除病史采集记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHistoryTakingRecordByIds(Long[] ids);
}
