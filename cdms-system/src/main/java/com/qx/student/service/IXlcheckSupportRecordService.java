package com.qx.student.service;

import com.qx.student.domain.XlcheckSupportRecord;
import java.util.List;

/**
 * 心理测量项目支持记录Service接口
 * 
 * @author aaa
 * @date 2021-09-09
 */
public interface IXlcheckSupportRecordService 
{
    /**
     * 查询心理测量项目支持记录
     * 
     * @param id 心理测量项目支持记录ID
     * @return 心理测量项目支持记录
     */
    public XlcheckSupportRecord selectXlcheckSupportRecordById(Long id);

    /**
     * 查询心理测量项目支持记录列表
     * 
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return 心理测量项目支持记录集合
     */
    public List<XlcheckSupportRecord> selectXlcheckSupportRecordList(XlcheckSupportRecord xlcheckSupportRecord);

    /**
     * 新增心理测量项目支持记录
     * 
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return 结果
     */
    public int insertXlcheckSupportRecord(XlcheckSupportRecord xlcheckSupportRecord);

    int insertXlcheckSupportRecordByList(List<XlcheckSupportRecord> xlcheckSupportRecordList);

    int updateXlcheckSupportRecordBatch(List<XlcheckSupportRecord> xlcheckSupportRecordList);

    List<XlcheckSupportRecord> selectXlcheckSupportRecordList1(Long patientId, XlcheckSupportRecord xlcheckSupportRecord);

    List<XlcheckSupportRecord> selectXlcheckSupportRecordListCore(Long patientId, XlcheckSupportRecord xlcheckSupportRecord);

    /**
     * 修改心理测量项目支持记录
     * 
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return 结果
     */
    public int updateXlcheckSupportRecord(XlcheckSupportRecord xlcheckSupportRecord);

    /**
     * 批量删除心理测量项目支持记录
     * 
     * @param ids 需要删除的心理测量项目支持记录ID
     * @return 结果
     */
    public int deleteXlcheckSupportRecordByIds(Long[] ids);

    /**
     * 删除心理测量项目支持记录信息
     * 
     * @param id 心理测量项目支持记录ID
     * @return 结果
     */
    public int deleteXlcheckSupportRecordById(Long id);
}
