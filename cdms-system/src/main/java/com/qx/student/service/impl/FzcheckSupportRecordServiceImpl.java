package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.service.ICasePatientItemService;
import com.qx.student.domain.TgcheckSupportRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.FzcheckSupportRecordMapper;
import com.qx.student.domain.FzcheckSupportRecord;
import com.qx.student.service.IFzcheckSupportRecordService;

/**
 * 辅助检查项目支持记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-17
 */
@Service
public class FzcheckSupportRecordServiceImpl implements IFzcheckSupportRecordService 
{
    @Autowired
    private FzcheckSupportRecordMapper fzcheckSupportRecordMapper;
    @Autowired
    private ICasePatientItemService casePatientItemService;
    /**
     * 查询辅助检查项目支持记录
     * 
     * @param id 辅助检查项目支持记录ID
     * @return 辅助检查项目支持记录
     */
    @Override
    public FzcheckSupportRecord selectFzcheckSupportRecordById(Long id)
    {
        return fzcheckSupportRecordMapper.selectFzcheckSupportRecordById(id);
    }

    /**
     * 查询辅助检查项目支持记录列表
     * 
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return 辅助检查项目支持记录
     */
    @Override
    public List<FzcheckSupportRecord> selectFzcheckSupportRecordList(FzcheckSupportRecord fzcheckSupportRecord)
    {
        return fzcheckSupportRecordMapper.selectFzcheckSupportRecordList(fzcheckSupportRecord);
    }

    /**
     * 新增辅助检查项目支持记录
     * 
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return 结果
     */
    @Override
    public int insertFzcheckSupportRecord(FzcheckSupportRecord fzcheckSupportRecord)
    {
        return fzcheckSupportRecordMapper.insertFzcheckSupportRecord(fzcheckSupportRecord);
    }

    /**
     * 修改辅助检查项目支持记录
     * 
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return 结果
     */
    @Override
    public int updateFzcheckSupportRecord(FzcheckSupportRecord fzcheckSupportRecord)
    {
        return fzcheckSupportRecordMapper.updateFzcheckSupportRecord(fzcheckSupportRecord);
    }

    /**
     * 批量删除辅助检查项目支持记录
     * 
     * @param ids 需要删除的辅助检查项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteFzcheckSupportRecordByIds(Long[] ids)
    {
        return fzcheckSupportRecordMapper.deleteFzcheckSupportRecordByIds(ids);
    }

    /**
     * 删除辅助检查项目支持记录信息
     * 
     * @param id 辅助检查项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteFzcheckSupportRecordById(Long id)
    {
        return fzcheckSupportRecordMapper.deleteFzcheckSupportRecordById(id);
    }

    /**
     * 批量插入
     * @param list 辅助检查项目集合
     * @return
     */
    @Override
    public int insertFzcheckSupportRecordByList(List<FzcheckSupportRecord> list) {
        return fzcheckSupportRecordMapper.insertJscheckSupportRecordByList(list);
    }

    /**
     * 查询辅助检查项目支持记录集合
     * @param patientId 案例患者id
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return
     */
    @Override
    public List<FzcheckSupportRecord> selectFzcheckSupportRecordList1(Long patientId, FzcheckSupportRecord fzcheckSupportRecord) {
        List<FzcheckSupportRecord> fzcheckSupportRecords = fzcheckSupportRecordMapper.selectFzcheckSupportRecordList(fzcheckSupportRecord);
        for (FzcheckSupportRecord record:fzcheckSupportRecords){

            CasePatientItem patientItem = casePatientItemService.selectCasePatientItemById(patientId, record.getItemId());
            if (patientItem!=null){
                //获取该案例下对应的检查项目数据
                record.getItem().setPatientItem(patientItem);
            }
        }
        return fzcheckSupportRecords;
    }

    /**
     * 查询辅助检查项目支持记录集合（核心）
     * @param patientId 案例患者id
     * @param fzcheckSupportRecord 辅助检查项目支持记录
     * @return
     */
    @Override
    public List<FzcheckSupportRecord> selectFzcheckSupportRecordListCore(Long patientId, FzcheckSupportRecord fzcheckSupportRecord) {
        List<FzcheckSupportRecord> fzcheckSupportRecordList = new ArrayList<>();
        List<FzcheckSupportRecord> fzcheckSupportRecords = fzcheckSupportRecordMapper.selectFzcheckSupportRecordList(fzcheckSupportRecord);
        for (FzcheckSupportRecord record:fzcheckSupportRecords){

            CasePatientItem patientItem = casePatientItemService.selectCasePatientItemByCore(patientId, record.getItemId());
            if (patientItem!=null){
                if (patientItem.getIsCore()==1){
                    //获取该案例下对应的检查项目数据
                    record.getItem().setPatientItem(patientItem);
                    if (record!=null){
                        fzcheckSupportRecordList.add(record);
                    }
                }
            }
        }
        return fzcheckSupportRecordList;
    }
    /**
     * 批量更新
     * @param list
     * @return
     */
    @Override
    public int updateFzcheckSupportRecordBatch(List<FzcheckSupportRecord> list) {
        return fzcheckSupportRecordMapper.updateFzcheckSupportRecordBatch(list);
    }
}
