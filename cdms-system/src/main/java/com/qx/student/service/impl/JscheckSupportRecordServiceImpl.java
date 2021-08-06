package com.qx.student.service.impl;

import java.util.List;

import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.service.ICasePatientItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.JscheckSupportRecordMapper;
import com.qx.student.domain.JscheckSupportRecord;
import com.qx.student.service.IJscheckSupportRecordService;

/**
 * 精神检查项目支持记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-17
 */
@Service
public class JscheckSupportRecordServiceImpl implements IJscheckSupportRecordService 
{
    @Autowired
    private JscheckSupportRecordMapper jscheckSupportRecordMapper;
    @Autowired
    private ICasePatientItemService casePatientItemService;
    /**
     * 查询精神检查项目支持记录
     * 
     * @param id 精神检查项目支持记录ID
     * @return 精神检查项目支持记录
     */
    @Override
    public JscheckSupportRecord selectJscheckSupportRecordById(Long id)
    {
        return jscheckSupportRecordMapper.selectJscheckSupportRecordById(id);
    }

    /**
     * 查询精神检查项目支持记录列表
     * 
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @return 精神检查项目支持记录
     */
    @Override
    public List<JscheckSupportRecord> selectJscheckSupportRecordList(JscheckSupportRecord jscheckSupportRecord)
    {
        return jscheckSupportRecordMapper.selectJscheckSupportRecordList(jscheckSupportRecord);
    }

    /**
     * 新增精神检查项目支持记录
     * 
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @return 结果
     */
    @Override
    public int insertJscheckSupportRecord(JscheckSupportRecord jscheckSupportRecord)
    {
        return jscheckSupportRecordMapper.insertJscheckSupportRecord(jscheckSupportRecord);
    }

    /**
     * 修改精神检查项目支持记录
     * 
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @return 结果
     */
    @Override
    public int updateJscheckSupportRecord(JscheckSupportRecord jscheckSupportRecord)
    {
        return jscheckSupportRecordMapper.updateJscheckSupportRecord(jscheckSupportRecord);
    }

    /**
     * 批量删除精神检查项目支持记录
     * 
     * @param ids 需要删除的精神检查项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteJscheckSupportRecordByIds(Long[] ids)
    {
        return jscheckSupportRecordMapper.deleteJscheckSupportRecordByIds(ids);
    }

    /**
     * 删除精神检查项目支持记录信息
     * 
     * @param id 精神检查项目支持记录ID
     * @return 结果
     */
    @Override
    public int deleteJscheckSupportRecordById(Long id)
    {
        return jscheckSupportRecordMapper.deleteJscheckSupportRecordById(id);
    }

    @Override
    public int insertJscheckSupportRecordByList(List<JscheckSupportRecord> list) {
        return jscheckSupportRecordMapper.insertJscheckSupportRecordByList(list);
    }

    /**
     * 查询精神检查项目支持记录集合
     * @param patientId 案例患者id
     * @param jscheckSupportRecord 精神检查项目支持记录
     * @return
     */
    @Override
    public List<JscheckSupportRecord> selectJscheckSupportRecordList1(Long patientId, JscheckSupportRecord jscheckSupportRecord) {
        List<JscheckSupportRecord> fzcheckSupportRecords = jscheckSupportRecordMapper.selectJscheckSupportRecordList(jscheckSupportRecord);
        for (JscheckSupportRecord record:fzcheckSupportRecords){

            CasePatientItem patientItem = casePatientItemService.selectCasePatientItemById(patientId, record.getItemId());
            if (patientItem!=null){
                //获取该案例下对应的检查项目数据
                record.getItem().setPatientItem(patientItem);
            }
        }
        return fzcheckSupportRecords;
    }

    /**
     * 批量更新
     * @param list
     * @return
     */
    @Override
    public int updateJscheckSupportRecordBatch(List<JscheckSupportRecord> list) {
        return jscheckSupportRecordMapper.updateJscheckSupportRecordBatch(list);
    }
}
