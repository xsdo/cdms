package com.qx.student.service.impl;

import java.util.Date;
import java.util.List;

import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.vo.MedicalWriteRecordVo;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.MedicalWriteRecordMapper;
import com.qx.student.domain.MedicalWriteRecord;
import com.qx.student.service.IMedicalWriteRecordService;

/**
 * 病历书写Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-25
 */
@Service
public class MedicalWriteRecordServiceImpl implements IMedicalWriteRecordService 
{
    @Autowired
    private MedicalWriteRecordMapper medicalWriteRecordMapper;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;
    /**
     * 查询病历书写
     * 
     * @param id 病历书写ID
     * @return 病历书写
     */
    @Override
    public MedicalWriteRecord selectMedicalWriteRecordById(Long id)
    {
        return medicalWriteRecordMapper.selectMedicalWriteRecordById(id);
    }

    /**
     * 查询病历书写列表
     * 
     * @param medicalWriteRecord 病历书写
     * @return 病历书写
     */
    @Override
    public List<MedicalWriteRecord> selectMedicalWriteRecordList(MedicalWriteRecord medicalWriteRecord)
    {
        return medicalWriteRecordMapper.selectMedicalWriteRecordList(medicalWriteRecord);
    }

    @Override
    public Double countMedicalScore(Long id){
        Double countMedicalScore =new Double(0);
        MedicalWriteRecord medicalWriteRecord = medicalWriteRecordMapper.selectMedicalWriteRecordById(id);
        //基本信息0分 +主体（10部分)+医师 2 每个信息1分
        if (medicalWriteRecord!=null){
            if (medicalWriteRecord.getChiefComplaint()!=null&&!medicalWriteRecord.getChiefComplaint().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getHpi()!=null&&!medicalWriteRecord.getHpi().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getPastHistory()!=null&&!medicalWriteRecord.getPastHistory().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getPersonalHistory()!=null&&!medicalWriteRecord.getPersonalHistory().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getMmch()!=null&&!medicalWriteRecord.getMmch().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getFamilyHistory()!=null&&!medicalWriteRecord.getFamilyHistory().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getTgCheck()!=null&&!medicalWriteRecord.getTgCheck().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getJsCheck()!=null&&!medicalWriteRecord.getJsCheck().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getFzCheck()!=null&&!medicalWriteRecord.getFzCheck().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getPrimaryDiagnosis()!=null&&!medicalWriteRecord.getPrimaryDiagnosis().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getSuperiorDoctor()!=null&&!medicalWriteRecord.getSuperiorDoctor().equals("")){ countMedicalScore+=1.0; }
            if (medicalWriteRecord.getResidentDoctor()!=null&&!medicalWriteRecord.getResidentDoctor().equals("")){ countMedicalScore+=1.0; }
        }
        return countMedicalScore;
    }
    /**
     * 新增病历书写
     * 
     * @param recordVo 病历书写
     * @return 结果
     */
    @Override
    public int insertMedicalWriteRecord(MedicalWriteRecordVo recordVo)
    {
        //添加病历书写记录
        medicalWriteRecordMapper.insertMedicalWriteRecord(recordVo.getMedicalWriteRecord());
        //更新学生训练记录表中的数据
        StudentTrainRecord trainRecord = new StudentTrainRecord();
        trainRecord.setId(recordVo.getStudentTrainId());
        trainRecord.setMedicalRecordId(recordVo.getMedicalWriteRecord().getId());
        trainRecord.setEndTime(new Date());
        trainRecord.setStatus("1");
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    @Override
    public int insertMedicalWriteRecord(MedicalWriteRecord medicalWriteRecord, Long studentTrainId)
    {
        //添加病历书写记录
        medicalWriteRecordMapper.insertMedicalWriteRecord(medicalWriteRecord);
        //更新学生训练记录表中的数据
        StudentTrainRecord trainRecord = new StudentTrainRecord();
        trainRecord.setId(studentTrainId);
        trainRecord.setMedicalRecordId(medicalWriteRecord.getId());
        trainRecord.setEndTime(new Date());
        trainRecord.setStatus("1");
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    /**
     * 修改病历书写
     * 
     * @param medicalWriteRecord 病历书写
     * @return 结果
     */
    @Override
    public int updateMedicalWriteRecord(MedicalWriteRecord medicalWriteRecord)
    {
        return medicalWriteRecordMapper.updateMedicalWriteRecord(medicalWriteRecord);
    }

    /**
     * 批量删除病历书写
     * 
     * @param ids 需要删除的病历书写ID
     * @return 结果
     */
    @Override
    public int deleteMedicalWriteRecordByIds(Long[] ids)
    {
        return medicalWriteRecordMapper.deleteMedicalWriteRecordByIds(ids);
    }

    /**
     * 删除病历书写信息
     * 
     * @param id 病历书写ID
     * @return 结果
     */
    @Override
    public int deleteMedicalWriteRecordById(Long id)
    {
        return medicalWriteRecordMapper.deleteMedicalWriteRecordById(id);
    }
}
