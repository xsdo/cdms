package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.vo.ImpRecordVo;
import com.qx.student.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.ImpRecordMapper;
import com.qx.student.domain.ImpRecord;

/**
 * 诊断记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-18
 */
@Service
public class ImpRecordServiceImpl implements IImpRecordService 
{
    @Autowired
    private ImpRecordMapper impRecordMapper;
    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    @Autowired
    private IHistorySupportRecordService historySupportRecordService;

    @Autowired
    private ITgcheckSupportRecordService tgcheckSupportRecordService;

    @Autowired
    private IJscheckSupportRecordService jscheckSupportRecordService;

    @Autowired
    private IFzcheckSupportRecordService fzcheckSupportRecordService;

    /**
     * 查询诊断记录
     * 
     * @param id 诊断记录ID
     * @return 诊断记录
     */
    @Override
    public ImpRecord selectImpRecordById(Long id)
    {
        return impRecordMapper.selectImpRecordById(id);
    }

    /**
     * 查询诊断记录列表
     * 
     * @param impRecord 诊断记录
     * @return 诊断记录
     */
    @Override
    public List<ImpRecord> selectImpRecordList(ImpRecord impRecord)
    {
        return impRecordMapper.selectImpRecordList(impRecord);
    }

    /**
     * 新增诊断记录
     * 
     * @param impRecordVo 诊断记录
     * @return 结果
     */
    @Override
    public int insertImpRecord(ImpRecordVo impRecordVo)
    {
        //新增诊断数据
        impRecordMapper.insertImpRecord(impRecordVo.getImpRecord());
        //更新病史采集问题支持记录
        if (impRecordVo.getHistorySupportRecordList()!=null && impRecordVo.getHistorySupportRecordList().size()>0){

            historySupportRecordService.updateHistorySupportRecordBatch(impRecordVo.getHistorySupportRecordList());
        }
        //更新体格检查项目支持记录
        if (impRecordVo.getTgcheckSupportRecordList()!=null && impRecordVo.getTgcheckSupportRecordList().size()>0){

            tgcheckSupportRecordService.updateTgcheckSupportRecordBatch(impRecordVo.getTgcheckSupportRecordList());
        }
        //更新精神检查项目支持记录
        if (impRecordVo.getJscheckSupportRecordList()!=null && impRecordVo.getJscheckSupportRecordList().size()>0){

            jscheckSupportRecordService.updateJscheckSupportRecordBatch(impRecordVo.getJscheckSupportRecordList());
        }
        //更新辅助检查项目支持记录
        if (impRecordVo.getFzcheckSupportRecordList()!=null && impRecordVo.getFzcheckSupportRecordList().size()>0){

            fzcheckSupportRecordService.updateFzcheckSupportRecordBatch(impRecordVo.getFzcheckSupportRecordList());
        }
        //更新诊断依据支持记录

        //更新学生训练记录
        StudentTrainRecord trainRecord = new StudentTrainRecord();
        trainRecord.setId(impRecordVo.getStudentTrainId());
        trainRecord.setImpRecordId(impRecordVo.getImpRecord().getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);

    }

    /**
     * 修改诊断记录
     * 
     * @param impRecordVo 诊断记录
     * @return 结果
     */
    @Override
    public void updateImpRecord(ImpRecordVo impRecordVo)
    {
        //更新诊断记录
        impRecordMapper.updateImpRecord(impRecordVo.getImpRecord());

        //更新病史采集问题支持记录
        if (impRecordVo.getHistorySupportRecordList()!=null && impRecordVo.getHistorySupportRecordList().size()>0){

            historySupportRecordService.updateHistorySupportRecordBatch(impRecordVo.getHistorySupportRecordList());
        }
        //更新体格检查项目支持记录
        if (impRecordVo.getTgcheckSupportRecordList()!=null && impRecordVo.getTgcheckSupportRecordList().size()>0){

            tgcheckSupportRecordService.updateTgcheckSupportRecordBatch(impRecordVo.getTgcheckSupportRecordList());
        }
        //更新精神检查项目支持记录
        if (impRecordVo.getJscheckSupportRecordList()!=null && impRecordVo.getJscheckSupportRecordList().size()>0){

            jscheckSupportRecordService.updateJscheckSupportRecordBatch(impRecordVo.getJscheckSupportRecordList());
        }
        //更新辅助检查项目支持记录
        if (impRecordVo.getFzcheckSupportRecordList()!=null && impRecordVo.getFzcheckSupportRecordList().size()>0){

            fzcheckSupportRecordService.updateFzcheckSupportRecordBatch(impRecordVo.getFzcheckSupportRecordList());
        }
        //更新诊断依据支持记录
    }

    /**
     * 批量删除诊断记录
     * 
     * @param ids 需要删除的诊断记录ID
     * @return 结果
     */
    @Override
    public int deleteImpRecordByIds(Long[] ids)
    {
        return impRecordMapper.deleteImpRecordByIds(ids);
    }

    /**
     * 删除诊断记录信息
     * 
     * @param id 诊断记录ID
     * @return 结果
     */
    @Override
    public int deleteImpRecordById(Long id)
    {
        return impRecordMapper.deleteImpRecordById(id);
    }
}
