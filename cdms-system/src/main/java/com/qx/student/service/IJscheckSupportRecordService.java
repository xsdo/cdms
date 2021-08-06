package com.qx.student.service;

import com.qx.student.domain.JscheckSupportRecord;
import java.util.List;

/**
 * 精神检查项目支持记录Service接口
 * 
 * @author aaa
 * @date 2021-06-17
 */
public interface IJscheckSupportRecordService 
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
     * 批量删除精神检查项目支持记录
     * 
     * @param ids 需要删除的精神检查项目支持记录ID
     * @return 结果
     */
    public int deleteJscheckSupportRecordByIds(Long[] ids);

    /**
     * 删除精神检查项目支持记录信息
     * 
     * @param id 精神检查项目支持记录ID
     * @return 结果
     */
    public int deleteJscheckSupportRecordById(Long id);

    /**
     * 批量插入
     * @param list 精神检查项目集合
     * @return 结果
     */
    int insertJscheckSupportRecordByList(List<JscheckSupportRecord> list);

    /**
     * 查询精神检查项目支持记录列表-联合查询
     *
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @param patientId 案例患者id
     * @return 精神检查项目支持记录集合
     */
    List<JscheckSupportRecord> selectJscheckSupportRecordList1(Long patientId, JscheckSupportRecord jscheckSupportRecord);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateJscheckSupportRecordBatch(List<JscheckSupportRecord> list);
}
