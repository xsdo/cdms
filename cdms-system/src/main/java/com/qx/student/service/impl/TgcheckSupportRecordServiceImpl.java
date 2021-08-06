package com.qx.student.service.impl;

import java.util.List;

import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.service.ICasePatientItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.TgcheckSupportRecordMapper;
import com.qx.student.domain.TgcheckSupportRecord;
import com.qx.student.service.ITgcheckSupportRecordService;

/**
 * 体格检查项目支持记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-17
 */
@Service
public class TgcheckSupportRecordServiceImpl implements ITgcheckSupportRecordService 
{
    @Autowired
    private TgcheckSupportRecordMapper tgcheckSupportRecordMapper;

    @Autowired
    private ICasePatientItemService casePatientItemService;
    /**
     * 查询体格检查项目支持记录
     * 
     * @param id 体格检查项目支持记录ID
     * @return 体格检查项目支持记录
     */
    @Override
    public TgcheckSupportRecord selectTgcheckSupportRecordById(Long id)
    {
        return tgcheckSupportRecordMapper.selectTgcheckSupportRecordById(id);
    }

    /**
     * 查询体格检查项目支持记录列表
     * 
     * @param tgcheckSupportRecord 体格检查项目支持记录
     * @return 体格检查项目支持记录
     */
    @Override
    public List<TgcheckSupportRecord> selectTgcheckSupportRecordList(TgcheckSupportRecord tgcheckSupportRecord)
    {
        return tgcheckSupportRecordMapper.selectTgcheckSupportRecordList(tgcheckSupportRecord);
    }

    /**
     * 查询体格检查项目支持记录列表-联合查询
     *
     * @param tgcheckSupportRecord 体格检查项目支持记录
     * @param patientId 案例id
     * @return 体格检查项目支持记录
     */
    @Override
    public List<TgcheckSupportRecord> selectTgcheckSupportRecordList1(Long patientId,TgcheckSupportRecord tgcheckSupportRecord)
    {
        List<TgcheckSupportRecord> tgcheckSupportRecords = tgcheckSupportRecordMapper.selectTgcheckSupportRecordList(tgcheckSupportRecord);
        for (TgcheckSupportRecord record:tgcheckSupportRecords){

            CasePatientItem patientItem = casePatientItemService.selectCasePatientItemById(patientId, record.getItemId());
            if (patientItem!=null){
                //获取该案例下对应的检查项目数据
                record.getItem().setPatientItem(patientItem);
            }
        }
        return tgcheckSupportRecords;
    }
    /**
     * 新增体格检查项目支持记录
     * 
     * @param tgcheckSupportRecord 体格检查项目支持记录
     * @return 结果
     */
    @Override
    public int insertTgcheckSupportRecord(TgcheckSupportRecord tgcheckSupportRecord)
    {
        return tgcheckSupportRecordMapper.insertTgcheckSupportRecord(tgcheckSupportRecord);
    }

    /**
     * 修改体格检查项目支持记录
     * 
     * @param tgcheckSupportRecord 体格检查项目支持记录
     * @return 结果
     */
    @Override
    public int updateTgcheckSupportRecord(TgcheckSupportRecord tgcheckSupportRecord)
    {
        return tgcheckSupportRecordMapper.updateTgcheckSupportRecord(tgcheckSupportRecord);
    }

    /**
     * 批量删除体格检查项目支持记录
     * 
     * @param ids 需要删除的体格检查项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteTgcheckSupportRecordByIds(Long[] ids)
    {
        return tgcheckSupportRecordMapper.deleteTgcheckSupportRecordByIds(ids);
    }

    /**
     * 删除体格检查项目支持记录信息
     * 
     * @param id 体格检查项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteTgcheckSupportRecordById(Long id)
    {
        return tgcheckSupportRecordMapper.deleteTgcheckSupportRecordById(id);
    }

    /**
     * 批量插入
     * @param list 体格检查项目集合
     * @return
     */
    @Override
    public int insertTgcheckSupportRecordByList(List<TgcheckSupportRecord> list) {
        return tgcheckSupportRecordMapper.insertTgcheckSupportRecordByList(list);
    }

    /**
     * 批量更新
     * @param list
     * @return
     */
    @Override
    public int updateTgcheckSupportRecordBatch(List<TgcheckSupportRecord> list) {
        return tgcheckSupportRecordMapper.updateTgcheckSupportRecordBatch(list);
    }

}
