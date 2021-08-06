package com.qx.student.mapper;

import com.qx.student.domain.FzcheckSupportRecord;
import java.util.List;

/**
 * 辅助检查项目支持记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-17
 */
public interface FzcheckSupportRecordMapper 
{
    /**
     * 查询辅助检查项目支持记录
     * 
     * @param id 辅助检查项目支持记录ID
     * @return 辅助检查项目支持记录
     */
    public FzcheckSupportRecord selectFzcheckSupportRecordById(Long id);

    /**
     * 查询辅助检查项目支持记录列表
     * 
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return 辅助检查项目支持记录集合
     */
    public List<FzcheckSupportRecord> selectFzcheckSupportRecordList(FzcheckSupportRecord fzcheckSupportRecord);

    /**
     * 新增辅助检查项目支持记录
     * 
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return 结果
     */
    public int insertFzcheckSupportRecord(FzcheckSupportRecord fzcheckSupportRecord);

    /**
     * 修改辅助检查项目支持记录
     * 
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return 结果
     */
    public int updateFzcheckSupportRecord(FzcheckSupportRecord fzcheckSupportRecord);

    /**
     * 删除辅助检查项目支持记录
     * 
     * @param id 辅助检查项目支持记录ID
     * @return 结果
     */
    public int deleteFzcheckSupportRecordById(Long id);

    /**
     * 批量删除辅助检查项目支持记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFzcheckSupportRecordByIds(Long[] ids);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertJscheckSupportRecordByList(List<FzcheckSupportRecord> list);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateFzcheckSupportRecordBatch(List<FzcheckSupportRecord> list);
}
