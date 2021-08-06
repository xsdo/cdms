package com.qx.student.mapper;

import com.qx.student.domain.JscheckSupportRecord;
import java.util.List;

/**
 * 精神检查项目支持记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-17
 */
public interface JscheckSupportRecordMapper 
{
    /**
     * 查询精神检查项目支持记录
     * 
     * @param id 精神检查项目支持记录ID
     * @return 精神检查项目支持记录
     */
    public JscheckSupportRecord selectJscheckSupportRecordById(Long id);

    /**
     * 查询精神检查项目支持记录列表
     * 
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @return 精神检查项目支持记录集合
     */
    public List<JscheckSupportRecord> selectJscheckSupportRecordList(JscheckSupportRecord jscheckSupportRecord);

    /**
     * 新增精神检查项目支持记录
     * 
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @return 结果
     */
    public int insertJscheckSupportRecord(JscheckSupportRecord jscheckSupportRecord);

    /**
     * 修改精神检查项目支持记录
     * 
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @return 结果
     */
    public int updateJscheckSupportRecord(JscheckSupportRecord jscheckSupportRecord);

    /**
     * 删除精神检查项目支持记录
     * 
     * @param id 精神检查项目支持记录ID
     * @return 结果
     */
    public int deleteJscheckSupportRecordById(Long id);

    /**
     * 批量删除精神检查项目支持记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJscheckSupportRecordByIds(Long[] ids);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertJscheckSupportRecordByList(List<JscheckSupportRecord> list);


    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateJscheckSupportRecordBatch(List<JscheckSupportRecord> list);
}
