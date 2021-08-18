package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseQuestion;
import com.qx.cases.service.ICaseQuestionService;
import com.qx.student.domain.HistorySupportRecord;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.service.IHistorySupportRecordService;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.HistoryTakingRecordMapper;
import com.qx.student.domain.HistoryTakingRecord;
import com.qx.student.service.IHistoryTakingRecordService;

/**
 * 病史采集记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Service
public class HistoryTakingRecordServiceImpl implements IHistoryTakingRecordService 
{
    @Autowired
    private HistoryTakingRecordMapper historyTakingRecordMapper;

    @Autowired
    private ICaseQuestionService caseQuestionService;

    @Autowired
    private IHistorySupportRecordService historySupportRecordService;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    /**
     * 查询病史采集记录
     * 
     * @param id 病史采集记录ID
     * @return 病史采集记录
     */
    @Override
    public HistoryTakingRecord selectHistoryTakingRecordById(Long id)
    {

        HistoryTakingRecord historyTakingRecord = historyTakingRecordMapper.selectHistoryTakingRecordById(id);
//        String[] ids = historyTakingRecord.getQuestionIds().split(",");
//        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
//        Long[] questionIds =  longList.toArray(new Long[]{});
        //根据id数组查询所有的问题集合及其类型
        List<CaseQuestion> list = new ArrayList<>();
        List<HistorySupportRecord> historySupportRecords = historySupportRecordService.selectHistorySupportRecordListByHistoryId(id);
        for (HistorySupportRecord record:historySupportRecords){
            CaseQuestion cqs =record.getQuestion();
            cqs.setId(record.getQuestionId());
            //record.setId(record.getQuestionId());
            //list.add(record.getQuestion());
            list.add(cqs);
        }
//        List<CaseQuestion> list1 = caseQuestionService.selectCaseQuestionByIds(questionIds);
        List<CaseQuestion> questionList = caseQuestionService.selectCaseQuestionListType(list);
        historyTakingRecord.setQuestion(questionList);
        return historyTakingRecord;
    }

    /**
     * 查询病史采集记录列表
     * 
     * @param historyTakingRecord 病史采集记录
     * @return 病史采集记录
     */
    @Override
    public List<HistoryTakingRecord> selectHistoryTakingRecordList(HistoryTakingRecord historyTakingRecord)
    {
        return historyTakingRecordMapper.selectHistoryTakingRecordList(historyTakingRecord);
    }

    /**
     * 新增病史采集记录
     * @param historyTakingRecord 病史采集记录
     * @param questionIds 问题ids
     * @param studentTrainRecord 学生训练记录
     * @return
     */
    @Override
    public int insertHistoryTakingRecord(HistoryTakingRecord historyTakingRecord,Long[] questionIds,StudentTrainRecord studentTrainRecord)
    {
        //保存病史采集记录
        historyTakingRecordMapper.insertHistoryTakingRecord(historyTakingRecord);
        //保存病史采集诊断支持记录
        List<HistorySupportRecord> list = new ArrayList<>();
        for (int i=0;i<questionIds.length;i++){
            HistorySupportRecord record = new HistorySupportRecord();
            record.setHistoryRecordId(historyTakingRecord.getId());
            record.setQuestionId(questionIds[i]);
            list.add(record);
        }
        historySupportRecordService.insertHistorySupportRecordByList(list);
        //保存学生训练记录
        studentTrainRecord.setHistoryRecordId(historyTakingRecord.getId());
        return studentTrainRecordService.insertStudentTrainRecord(studentTrainRecord);
    }

    /**
     * 修改病史采集数据及其诊断支持记录表数据
     * @param historyTakingRecord 病史采集记录
     * @param questionIds 问题ids
     */
    @Override
    public void updateHistoryTakingRecord(HistoryTakingRecord historyTakingRecord,Long[] questionIds)
    {
        //更新病史采集记录
        historyTakingRecordMapper.updateHistoryTakingRecord(historyTakingRecord);
        //更新病史采集问题诊断支持记录
        for (int i=0;i<questionIds.length;i++){
            HistorySupportRecord historySupportRecord = new HistorySupportRecord();
            historySupportRecord.setHistoryRecordId(historyTakingRecord.getId());
            historySupportRecord.setQuestionId(questionIds[i]);
            List<HistorySupportRecord> list = historySupportRecordService.selectHistorySupportRecordList(historySupportRecord);
            if (list.size()==1){
                continue;
            }
            historySupportRecordService.insertHistorySupportRecord(historySupportRecord);
        }
    }

    /**
     * 批量删除病史采集记录
     * 
     * @param ids 需要删除的病史采集记录ID
     * @return 结果
     */
    @Override
    public int deleteHistoryTakingRecordByIds(Long[] ids)
    {
        return historyTakingRecordMapper.deleteHistoryTakingRecordByIds(ids);
    }

    /**
     * 删除病史采集记录信息
     * 
     * @param id 病史采集记录ID
     * @return 结果
     */
    @Override
    public int deleteHistoryTakingRecordById(Long id)
    {
        return historyTakingRecordMapper.deleteHistoryTakingRecordById(id);
    }
}
