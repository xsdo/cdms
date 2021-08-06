package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.student.domain.FzcheckSupportRecord;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.service.IFzcheckSupportRecordService;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.FzCheckRecordMapper;
import com.qx.student.domain.FzCheckRecord;
import com.qx.student.service.IFzCheckRecordService;

/**
 * 辅助检查记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Service
public class FzCheckRecordServiceImpl implements IFzCheckRecordService 
{
    @Autowired
    private FzCheckRecordMapper fzCheckRecordMapper;
    @Autowired
    private CasePatientItemMapper casePatientItemMapper;
    @Autowired
    private IFzcheckSupportRecordService fzcheckSupportRecordService;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;
    /**
     * 查询辅助检查记录
     * 
     * @param id 辅助检查记录ID
     * @return 辅助检查记录
     */
    @Override
    public FzCheckRecord selectFzCheckRecordById(Long id,Long patientId)
    {
        FzCheckRecord record = fzCheckRecordMapper.selectFzCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CasePatientItem> list = casePatientItemMapper.selectCasePatientItemByIds(patientId,itemIds);
        record.setPatientItem(list);
        return record;
    }

    /**
     * 查询辅助检查记录列表
     * 
     * @param fzCheckRecord 辅助检查记录
     * @return 辅助检查记录
     */
    @Override
    public List<FzCheckRecord> selectFzCheckRecordList(FzCheckRecord fzCheckRecord)
    {
        return fzCheckRecordMapper.selectFzCheckRecordList(fzCheckRecord);
    }

    /**
     * 新增辅助检查记录
     *
     * @param fzCheckRecord 辅助检查记录
     * @param itemIds 项目ids
     * @param trainRecord 学生训练记录
     * @return 结果
     */
    @Override
    public int insertFzCheckRecord(FzCheckRecord fzCheckRecord, Long[] itemIds, StudentTrainRecord trainRecord)
    {
        fzCheckRecordMapper.insertFzCheckRecord(fzCheckRecord);
        //保存辅助检查诊断支持记录
        List<FzcheckSupportRecord> list = new ArrayList<>();
        for (int i=0;i<itemIds.length;i++){
            FzcheckSupportRecord record = new FzcheckSupportRecord();
            record.setCheckRecordId(fzCheckRecord.getId());
            record.setItemId(itemIds[i]);
            list.add(record);
        }
        fzcheckSupportRecordService.insertFzcheckSupportRecordByList(list);
        //更新学生训练记录
        trainRecord.setFzRecordId(fzCheckRecord.getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);

    }

    /**
     * 修改辅助检查记录
     *
     * @param fzCheckRecord 辅助检查记录
     * @param itemIds 项目ids
     */
    @Override
    public void updateFzCheckRecord(FzCheckRecord fzCheckRecord,Long[] itemIds)
    {
        //修改辅助检查记录
        fzCheckRecordMapper.updateFzCheckRecord(fzCheckRecord);
        //修改辅助项目诊断支持记录
        List<Long> itemList = new ArrayList<Long>(Arrays.asList(itemIds));
        FzcheckSupportRecord supportRecord = new FzcheckSupportRecord();
        supportRecord.setCheckRecordId(fzCheckRecord.getId());
        //查询原本记录中有哪些项目
        List<FzcheckSupportRecord> list = fzcheckSupportRecordService.selectFzcheckSupportRecordList(supportRecord);
        for (FzcheckSupportRecord record:list){
            //如果已有项目id不包含在集合中，删除该数据
            //如果id包含在集合中，将其从集合中删除，以便后续遍历数组
            if(!itemList.contains(record.getItemId())){
                fzcheckSupportRecordService.deleteFzcheckSupportRecordById(record.getId());
            }else{
                itemList.remove(record.getItemId());
            }
        }
        Long[] itemIds1 = itemList.toArray(new Long[]{});
        for (int i=0;i<itemIds1.length;i++){
            supportRecord.setItemId(itemIds1[i]);
            fzcheckSupportRecordService.insertFzcheckSupportRecord(supportRecord);
        }
    }

    /**
     * 批量删除辅助检查记录
     * 
     * @param ids 需要删除的辅助检查记录ID
     * @return 结果
     */
    @Override
    public int deleteFzCheckRecordByIds(Long[] ids)
    {
        return fzCheckRecordMapper.deleteFzCheckRecordByIds(ids);
    }

    /**
     * 删除辅助检查记录信息
     * 
     * @param id 辅助检查记录ID
     * @return 结果
     */
    @Override
    public int deleteFzCheckRecordById(Long id)
    {
        return fzCheckRecordMapper.deleteFzCheckRecordById(id);
    }
}
