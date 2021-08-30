package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.cases.service.ICaseCheckItemService;
import com.qx.student.domain.FzCheckRecord;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.TgcheckSupportRecord;
import com.qx.student.service.IStudentTrainRecordService;
import com.qx.student.service.ITgcheckSupportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.TgCheckRecordMapper;
import com.qx.student.domain.TgCheckRecord;
import com.qx.student.service.ITgCheckRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 体格检查记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Service
public class TgCheckRecordServiceImpl implements ITgCheckRecordService
{
    @Autowired
    private TgCheckRecordMapper tgCheckRecordMapper;
    @Autowired
    private CasePatientItemMapper casePatientItemMapper;

    @Autowired
    private ITgcheckSupportRecordService tgcheckSupportRecordService;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    @Autowired
    private ICaseCheckItemService caseCheckItemService;

    /**
     * 查询体格检查记录
     * 
     * @param id 体格检查记录ID
     * @return 体格检查记录
     */
    @Override
    public TgCheckRecord selectTgCheckRecordById(Long id,Long patientId)
    {
        TgCheckRecord record = tgCheckRecordMapper.selectTgCheckRecordById(id);
        //根据id数组查询所有项目结果集合
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CasePatientItem> list = casePatientItemMapper.selectCasePatientItemByIds(patientId,itemIds);
        record.setPatientItem(list);
        return record;
    }

    @Override
    public List<CaseCheckItem> selectTgMissRecordById(Long id)
    {
        TgCheckRecord record = tgCheckRecordMapper.selectTgCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        int count=0;
        for (CaseCheckItem caseCheckItem:caseCheckItems){
            if (caseCheckItem.getPid()==1){count += 1;}
        }
        List<Long> allList = new ArrayList<Long>();
        //给allList添加所有辅助检查itemId
        if (count==0){
            allList.add(new Long(1));
        }
        for (int i =2;i<8;i++){
            allList.add(new Long(i));
        }
        allList.removeAll(longList);
        /*allList.add(new Long(2));
        allList.add(new Long(3));
        allList.add(new Long(4));
        allList.add(new Long(5));
        allList.add(new Long(6));
        allList.add(new Long(7));
        allList.removeAll(longList);*/
        /*Iterator<Long> it = allList.iterator();  //创建迭代器
        List<Long> missList =new ArrayList<>();
        while (it.hasNext()){ //循环遍历迭代器
            missList.add(it.next());
        }*/
        Long[] itemIdList =  allList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItemList = caseCheckItemService.selectCaseCheckItemByIds(itemIdList);
        return caseCheckItemList;
    }

    @Override
    public Double countTgScore(Long id){
        Double countTgScore =new Double(0);
        TgCheckRecord record = tgCheckRecordMapper.selectTgCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        int count=0;

        //pid为1 ==生命体征
        for (CaseCheckItem caseCheckItem:caseCheckItems){
            if (caseCheckItem.getPid()==1){
                count += 1;
            }
            if (caseCheckItem.getPid()==0&&caseCheckItem.getItemId()!=1){
                countTgScore += 1.0;
            }

        }
        if (count>0){
            countTgScore += 1.0;
        }
        return countTgScore;
    }
    /**
     * 查询体格检查记录列表
     * 
     * @param tgCheckRecord 体格检查记录
     * @return 体格检查记录
     */
    @Override
    public List<TgCheckRecord> selectTgCheckRecordList(TgCheckRecord tgCheckRecord)
    {
        return tgCheckRecordMapper.selectTgCheckRecordList(tgCheckRecord);
    }

    /**
     * 新增体格检查记录
     * @param tgCheckRecord 体格检查记录
     * @param itemIds 项目ids
     * @param trainRecord 学生训练记录
     * @return 结果
     */
    @Override
    public int insertTgCheckRecord(TgCheckRecord tgCheckRecord, Long[] itemIds, StudentTrainRecord trainRecord)
    {
        //保存体格检查记录
        tgCheckRecordMapper.insertTgCheckRecord(tgCheckRecord);
        //保存体格检查诊断支持记录
        List<TgcheckSupportRecord> list = new ArrayList<>();
        for (int i=0;i<itemIds.length;i++){
            TgcheckSupportRecord record = new TgcheckSupportRecord();
            record.setCheckRecordId(tgCheckRecord.getId());
            record.setItemId(itemIds[i]);
            list.add(record);
        }
        tgcheckSupportRecordService.insertTgcheckSupportRecordByList(list);
        //更新学生训练记录
        trainRecord.setTgRecordId(tgCheckRecord.getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    /**
     * 修改体格检查记录及其诊断支持记录
     * @param tgCheckRecord 体格检查记录
     * @param itemIds 项目ids
     */
    @Override
    public void updateTgCheckRecord(TgCheckRecord tgCheckRecord,Long[] itemIds)
    {
        //修改体格检查记录
        tgCheckRecordMapper.updateTgCheckRecord(tgCheckRecord);
        //修改体格项目诊断支持记录
        List<Long> itemList = new ArrayList<Long>(Arrays.asList(itemIds));
        TgcheckSupportRecord supportRecord = new TgcheckSupportRecord();
        supportRecord.setCheckRecordId(tgCheckRecord.getId());
        //查询原本记录中有哪些项目
        List<TgcheckSupportRecord> beforeList = tgcheckSupportRecordService.selectTgcheckSupportRecordList(supportRecord);
        for (TgcheckSupportRecord before:beforeList){
            //如果已有项目id不包含在集合中，删除该数据
            //如果id包含在集合中，将其从集合中删除，以便后续遍历数组
            if(!itemList.contains(before.getItemId())){
                tgcheckSupportRecordService.deleteTgcheckSupportRecordById(before.getId());
            }else{
                itemList.remove(before.getItemId());
            }
        }
        Long[] itemIds1 = itemList.toArray(new Long[]{});

        for (int i=0;i<itemIds1.length;i++){
            supportRecord.setItemId(itemIds1[i]);
            tgcheckSupportRecordService.insertTgcheckSupportRecord(supportRecord);
        }

    }

    /**
     * 批量删除体格检查记录
     * 
     * @param ids 需要删除的体格检查记录ID
     * @return 结果
     */
    @Override
    public int deleteTgCheckRecordByIds(Long[] ids)
    {
        return tgCheckRecordMapper.deleteTgCheckRecordByIds(ids);
    }

    /**
     * 删除体格检查记录信息
     * 
     * @param id 体格检查记录ID
     * @return 结果
     */
    @Override
    public int deleteTgCheckRecordById(Long id)
    {
        return tgCheckRecordMapper.deleteTgCheckRecordById(id);
    }
}
