package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.student.domain.JscheckSupportRecord;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.TgcheckSupportRecord;
import com.qx.student.service.IJscheckSupportRecordService;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.JsCheckRecordMapper;
import com.qx.student.domain.JsCheckRecord;
import com.qx.student.service.IJsCheckRecordService;

/**
 * 精神状况检查记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Service
public class JsCheckRecordServiceImpl implements IJsCheckRecordService 
{
    @Autowired
    private JsCheckRecordMapper jsCheckRecordMapper;

    @Autowired
    private CasePatientItemMapper casePatientItemMapper;

    @Autowired
    private IJscheckSupportRecordService jscheckSupportRecordService;
    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;
    /**
     * 查询精神状况检查记录
     * 
     * @param id 精神状况检查记录ID
     * @return 精神状况检查记录
     */
    @Override
    public JsCheckRecord selectJsCheckRecordById(Long id,Long patientId)
    {
        JsCheckRecord record = jsCheckRecordMapper.selectJsCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CasePatientItem> list = casePatientItemMapper.selectCasePatientItemByIds(patientId,itemIds);
        record.setPatientItem(list);
        return record;
    }

    /**
     * 查询精神状况检查记录列表
     * 
     * @param jsCheckRecord 精神状况检查记录
     * @return 精神状况检查记录
     */
    @Override
    public List<JsCheckRecord> selectJsCheckRecordList(JsCheckRecord jsCheckRecord)
    {
        return jsCheckRecordMapper.selectJsCheckRecordList(jsCheckRecord);
    }

    /**
     * 新增精神状况检查记录
     * 
     * @param jsCheckRecord 精神状况检查记录
     * @param itemIds 项目ids
     * @param trainRecord 学生训练记录
     * @return 结果
     */
    @Override
    public int insertJsCheckRecord(JsCheckRecord jsCheckRecord,Long[] itemIds,StudentTrainRecord trainRecord)
    {
        jsCheckRecordMapper.insertJsCheckRecord(jsCheckRecord);
        //保存精神状况检查诊断支持记录
        List<JscheckSupportRecord> list = new ArrayList<>();
        for (int i=0;i<itemIds.length;i++){
            JscheckSupportRecord record = new JscheckSupportRecord();
            record.setCheckRecordId(jsCheckRecord.getId());
            record.setItemId(itemIds[i]);
            list.add(record);
        }
        jscheckSupportRecordService.insertJscheckSupportRecordByList(list);
        //更新学生训练记录
        trainRecord.setJsRecordId(jsCheckRecord.getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    /**
     * 修改精神状况检查记录
     * @param jsCheckRecord 精神状况检查记录
     * @param itemIds 项目ids
     */
    @Override
    public void updateJsCheckRecord(JsCheckRecord jsCheckRecord,Long[] itemIds)
    {
        //修改精神记录
        jsCheckRecordMapper.updateJsCheckRecord(jsCheckRecord);
        //修改精神项目诊断支持记录
        List<Long> itemList = new ArrayList<Long>(Arrays.asList(itemIds));
        JscheckSupportRecord supportRecord = new JscheckSupportRecord();
        supportRecord.setCheckRecordId(jsCheckRecord.getId());
        List<JscheckSupportRecord> list = jscheckSupportRecordService.selectJscheckSupportRecordList(supportRecord);
        for (JscheckSupportRecord before:list){
            //如果已有项目id不包含在集合中，删除该数据
            //如果id包含在集合中，将其从集合中删除，以便后续遍历数组
            if(!itemList.contains(before.getItemId())){
                jscheckSupportRecordService.deleteJscheckSupportRecordById(before.getId());
            }else{
                itemList.remove(before.getItemId());
            }
        }
        Long[] itemIds1 = itemList.toArray(new Long[]{});

        for (int i=0;i<itemIds1.length;i++){

            supportRecord.setItemId(itemIds1[i]);
            jscheckSupportRecordService.insertJscheckSupportRecord(supportRecord);
        }
    }

    /**
     * 批量删除精神状况检查记录
     * 
     * @param ids 需要删除的精神状况检查记录ID
     * @return 结果
     */
    @Override
    public int deleteJsCheckRecordByIds(Long[] ids)
    {
        return jsCheckRecordMapper.deleteJsCheckRecordByIds(ids);
    }

    /**
     * 删除精神状况检查记录信息
     * 
     * @param id 精神状况检查记录ID
     * @return 结果
     */
    @Override
    public int deleteJsCheckRecordById(Long id)
    {
        return jsCheckRecordMapper.deleteJsCheckRecordById(id);
    }
}
