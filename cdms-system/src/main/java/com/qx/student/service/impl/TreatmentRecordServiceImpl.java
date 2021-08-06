package com.qx.student.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseTreatment;
import com.qx.cases.service.ICaseTreatmentService;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.TreatmentRecordMapper;
import com.qx.student.domain.TreatmentRecord;
import com.qx.student.service.ITreatmentRecordService;

/**
 * 治疗记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-08
 */
@Service
public class TreatmentRecordServiceImpl implements ITreatmentRecordService 
{
    @Autowired
    private TreatmentRecordMapper treatmentRecordMapper;

    @Autowired
    private ICaseTreatmentService caseTreatmentService;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    /**
     * 查询治疗记录
     * 
     * @param id 治疗记录ID
     * @return 治疗记录
     */
    @Override
    public TreatmentRecord selectTreatmentRecordById(Long id)
    {
        TreatmentRecord record = treatmentRecordMapper.selectTreatmentRecordById(id);
        String[] ids = record.getTreatmentIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseTreatment> list = caseTreatmentService.selectTreatmentByIds(itemIds);
        record.setTreatment(list);
        return record;
    }

    /**
     * 查询治疗记录列表
     * 
     * @param treatmentRecord 治疗记录
     * @return 治疗记录
     */
    @Override
    public List<TreatmentRecord> selectTreatmentRecordList(TreatmentRecord treatmentRecord)
    {
        return treatmentRecordMapper.selectTreatmentRecordList(treatmentRecord);
    }

    /**
     * 新增治疗记录
     * 
     * @param treatmentRecord 治疗记录
     * @param trainRecord 学生训练记录
     * @return 结果
     */
    @Override
    public int insertTreatmentRecord(TreatmentRecord treatmentRecord, StudentTrainRecord trainRecord)
    {
        treatmentRecordMapper.insertTreatmentRecord(treatmentRecord);
        trainRecord.setTreatRecordId(treatmentRecord.getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    /**
     * 修改治疗记录
     * 
     * @param treatmentRecord 治疗记录
     * @return 结果
     */
    @Override
    public int updateTreatmentRecord(TreatmentRecord treatmentRecord)
    {
        return treatmentRecordMapper.updateTreatmentRecord(treatmentRecord);
    }

    /**
     * 批量删除治疗记录
     * 
     * @param ids 需要删除的治疗记录ID
     * @return 结果
     */
    @Override
    public int deleteTreatmentRecordByIds(Long[] ids)
    {
        return treatmentRecordMapper.deleteTreatmentRecordByIds(ids);
    }

    /**
     * 删除治疗记录信息
     * 
     * @param id 治疗记录ID
     * @return 结果
     */
    @Override
    public int deleteTreatmentRecordById(Long id)
    {
        return treatmentRecordMapper.deleteTreatmentRecordById(id);
    }
}
