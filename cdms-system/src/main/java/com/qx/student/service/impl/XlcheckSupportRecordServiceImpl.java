package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.service.ICaseCheckItemService;
import com.qx.cases.service.ICasePatientItemService;
import com.qx.student.domain.JscheckSupportRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.XlcheckSupportRecordMapper;
import com.qx.student.domain.XlcheckSupportRecord;
import com.qx.student.service.IXlcheckSupportRecordService;

/**
 * 心理测量项目支持记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-09-09
 */
@Service
public class XlcheckSupportRecordServiceImpl implements IXlcheckSupportRecordService 
{
    @Autowired
    private XlcheckSupportRecordMapper xlcheckSupportRecordMapper;

    @Autowired
    private ICasePatientItemService casePatientItemService;

    /**
     * 查询心理测量项目支持记录
     * 
     * @param id 心理测量项目支持记录ID
     * @return 心理测量项目支持记录
     */
    @Override
    public XlcheckSupportRecord selectXlcheckSupportRecordById(Long id)
    {
        return xlcheckSupportRecordMapper.selectXlcheckSupportRecordById(id);
    }

    /**
     * 查询心理测量项目支持记录列表
     * 
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return 心理测量项目支持记录
     */
    @Override
    public List<XlcheckSupportRecord> selectXlcheckSupportRecordList(XlcheckSupportRecord xlcheckSupportRecord)
    {
        return xlcheckSupportRecordMapper.selectXlcheckSupportRecordList(xlcheckSupportRecord);
    }

    /**
     * 新增心理测量项目支持记录
     * 
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return 结果
     */
    @Override
    public int insertXlcheckSupportRecord(XlcheckSupportRecord xlcheckSupportRecord)
    {
        return xlcheckSupportRecordMapper.insertXlcheckSupportRecord(xlcheckSupportRecord);
    }

    @Override
    public int insertXlcheckSupportRecordByList(List<XlcheckSupportRecord> xlcheckSupportRecordList){
        return xlcheckSupportRecordMapper.insertXlcheckSupportRecordByList(xlcheckSupportRecordList);
    }

    @Override
    public int updateXlcheckSupportRecordBatch(List<XlcheckSupportRecord> xlcheckSupportRecordList){
        return xlcheckSupportRecordMapper.updateXlcheckSupportRecordBatch(xlcheckSupportRecordList);
    }

    /**
     * 查询心理测量项目支持记录集合
     * @param patientId 案例患者id
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return
     */
    @Override
    public List<XlcheckSupportRecord> selectXlcheckSupportRecordList1(Long patientId, XlcheckSupportRecord xlcheckSupportRecord) {
        List<XlcheckSupportRecord> xlcheckSupportRecords = xlcheckSupportRecordMapper.selectXlcheckSupportRecordList(xlcheckSupportRecord);
        for (XlcheckSupportRecord record:xlcheckSupportRecords){
            CasePatientItem patientItem = casePatientItemService.selectCasePatientItemById(patientId,record.getItemId());
            if (patientItem!=null){
                //获取该案例下对应的检查项目数据
                record.getItem().setPatientItem(patientItem);
            }
        }
        return xlcheckSupportRecords;
    }

    /**
     * 查询心理测量项目支持记录集合（核心）
     * @param patientId 案例患者id
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return
     */
    @Override
    public List<XlcheckSupportRecord> selectXlcheckSupportRecordListCore(Long patientId, XlcheckSupportRecord xlcheckSupportRecord) {
        List<XlcheckSupportRecord>xlcheckSupportRecordList = new ArrayList<>();
        List<XlcheckSupportRecord>xlcheckSupportRecords =xlcheckSupportRecordMapper.selectXlcheckSupportRecordList(xlcheckSupportRecord);
        for (XlcheckSupportRecord record:xlcheckSupportRecords){
            CasePatientItem patientItem = casePatientItemService.selectCasePatientItemByCore(patientId, record.getItemId());
            if (patientItem!=null){
                if (patientItem.getIsCore()==1){
                    //获取该案例下对应的检查项目数据
                    record.getItem().setPatientItem(patientItem);
                    if (record!=null){
                        xlcheckSupportRecordList.add(record);
                    }
                }
            }
        }
        return xlcheckSupportRecordList;
    }

    /**
     * 修改心理测量项目支持记录
     * 
     * @param xlcheckSupportRecord 心理测量项目支持记录
     * @return 结果
     */
    @Override
    public int updateXlcheckSupportRecord(XlcheckSupportRecord xlcheckSupportRecord)
    {
        return xlcheckSupportRecordMapper.updateXlcheckSupportRecord(xlcheckSupportRecord);
    }

    /**
     * 批量删除心理测量项目支持记录
     * 
     * @param ids 需要删除的心理测量项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteXlcheckSupportRecordByIds(Long[] ids)
    {
        return xlcheckSupportRecordMapper.deleteXlcheckSupportRecordByIds(ids);
    }

    /**
     * 删除心理测量项目支持记录信息
     * 
     * @param id 心理测量项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteXlcheckSupportRecordById(Long id)
    {
        return xlcheckSupportRecordMapper.deleteXlcheckSupportRecordById(id);
    }
}
