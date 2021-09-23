package com.qx.student.service;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.XlCheckRecord;
import java.util.List;

/**
 * 心理测量记录Service接口
 * 
 * @author aaa
 * @date 2021-09-09
 */
public interface IXlCheckRecordService 
{
    /**
     * 查询心理测量记录
     * 
     * @param id 心理测量记录ID
     * @return 心理测量记录
     */
//    public XlCheckRecord selectXlCheckRecordById(Long id);

    XlCheckRecord selectXLCheckRecordById(Long id, Long patientId);

    /**
     * 查询心理测量记录列表
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 心理测量记录集合
     */
    public List<XlCheckRecord> selectXlCheckRecordList(XlCheckRecord xlCheckRecord);

    int insertXlCheckRecord(XlCheckRecord xlCheckRecord, Long[] itemIds, StudentTrainRecord trainRecord);

    void updateXlCheckRecord(XlCheckRecord xlCheckRecord, Long[] itemIds);

    /**
     * 新增心理测量记录
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 结果
     */
    public int insertXlCheckRecord(XlCheckRecord xlCheckRecord);

    //获取错采项
    List<CaseCheckItem> selectXlWrongRecordById(Long id);

    List<CaseCheckItem> selectXlMissRecordById(Long id);

    //计算心理测量分数
    Double countXlScore(Long id);

    /**
     * 修改心理测量记录
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 结果
     */
    public int updateXlCheckRecord(XlCheckRecord xlCheckRecord);

    /**
     * 批量删除心理测量记录
     * 
     * @param ids 需要删除的心理测量记录ID
     * @return 结果
     */
    public int deleteXlCheckRecordByIds(Long[] ids);

    /**
     * 删除心理测量记录信息
     * 
     * @param id 心理测量记录ID
     * @return 结果
     */
    public int deleteXlCheckRecordById(Long id);
}
