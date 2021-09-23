package com.qx.student.mapper;

import com.qx.student.domain.XlCheckRecord;
import java.util.List;

/**
 * 心理测量记录Mapper接口
 * 
 * @author aaa
 * @date 2021-09-09
 */
public interface XlCheckRecordMapper 
{
    /**
     * 查询心理测量记录
     * 
     * @param id 心理测量记录ID
     * @return 心理测量记录
     */
    public XlCheckRecord selectXlCheckRecordById(Long id);

    /**
     * 查询心理测量记录列表
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 心理测量记录集合
     */
    public List<XlCheckRecord> selectXlCheckRecordList(XlCheckRecord xlCheckRecord);

    /**
     * 新增心理测量记录
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 结果
     */
    public int insertXlCheckRecord(XlCheckRecord xlCheckRecord);

    /**
     * 修改心理测量记录
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 结果
     */
    public int updateXlCheckRecord(XlCheckRecord xlCheckRecord);

    /**
     * 删除心理测量记录
     * 
     * @param id 心理测量记录ID
     * @return 结果
     */
    public int deleteXlCheckRecordById(Long id);

    /**
     * 批量删除心理测量记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXlCheckRecordByIds(Long[] ids);
}
