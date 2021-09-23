package com.qx.student.mapper;

import com.qx.student.domain.XlcheckSupportRecord;
import java.util.List;

/**
 * 心理测量项目支持记录Mapper接口
 * 
 * @author aaa
 * @date 2021-09-09
 */
public interface XlcheckSupportRecordMapper 
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

    public int insertXlcheckSupportRecordByList(List<XlcheckSupportRecord> xlcheckSupportRecordList);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateXlcheckSupportRecordBatch(List<XlcheckSupportRecord> list);
    /**
     * 修改心理测量项目支持记录
     * 
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return 结果
     */
    public int updateXlcheckSupportRecord(XlcheckSupportRecord xlcheckSupportRecord);

    /**
     * 删除心理测量项目支持记录
     * 
     * @param id 心理测量项目支持记录ID
     * @return 结果
     */
    public int deleteXlcheckSupportRecordById(Long id);

    /**
     * 批量删除心理测量项目支持记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXlcheckSupportRecordByIds(Long[] ids);
}
