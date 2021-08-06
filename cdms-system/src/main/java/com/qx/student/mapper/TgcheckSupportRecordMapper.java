package com.qx.student.mapper;

import com.qx.student.domain.TgcheckSupportRecord;
import java.util.List;

/**
 * 体格检查项目支持记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-17
 */
public interface TgcheckSupportRecordMapper 
{
    /**
     * 查询体格检查项目支持记录
     * 
     * @param id 体格检查项目支持记录ID
     * @return 体格检查项目支持记录
     */
    public TgcheckSupportRecord selectTgcheckSupportRecordById(Long id);

    /**
     * 查询体格检查项目支持记录列表
     * 
     * @param tgcheckSupportRecord 体格检查项目支持记录
     * @return 体格检查项目支持记录集合
     */
    public List<TgcheckSupportRecord> selectTgcheckSupportRecordList(TgcheckSupportRecord tgcheckSupportRecord);

    /**
     * 新增体格检查项目支持记录
     * 
     * @param tgcheckSupportRecord 体格检查项目支持记录
     * @return 结果
     */
    public int insertTgcheckSupportRecord(TgcheckSupportRecord tgcheckSupportRecord);

    /**
     * 修改体格检查项目支持记录
     * 
     * @param tgcheckSupportRecord 体格检查项目支持记录
     * @return 结果
     */
    public int updateTgcheckSupportRecord(TgcheckSupportRecord tgcheckSupportRecord);

    /**
     * 删除体格检查项目支持记录
     * 
     * @param id 体格检查项目支持记录ID
     * @return 结果
     */
    public int deleteTgcheckSupportRecordById(Long id);

    /**
     * 批量删除体格检查项目支持记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTgcheckSupportRecordByIds(Long[] ids);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertTgcheckSupportRecordByList(List<TgcheckSupportRecord> list);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateTgcheckSupportRecordBatch(List<TgcheckSupportRecord> list);
}
