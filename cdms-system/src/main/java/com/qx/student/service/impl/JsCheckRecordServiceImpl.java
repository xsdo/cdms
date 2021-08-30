package com.qx.student.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.cases.service.ICaseCheckItemService;
import com.qx.student.domain.*;
import com.qx.student.service.IJscheckSupportRecordService;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.JsCheckRecordMapper;
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

    @Autowired
    private ICaseCheckItemService caseCheckItemService;
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

    @Override
    public JsCheckRecord selectJsMissRecordById(Long id, Long patientId)
    {
        JsCheckRecord record = jsCheckRecordMapper.selectJsCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        int count7=0;
        for (CaseCheckItem caseCheckItem:caseCheckItems){
            if (caseCheckItem.getPid()==13){count1+=1;}
            if (caseCheckItem.getPid()==14){count2+=1;}
            if (caseCheckItem.getPid()==15||caseCheckItem.getPid()==28){count3+=1;}
            if (caseCheckItem.getPid()==16){count4+=1;}
            if (caseCheckItem.getPid()==17){count5+=1;}
            if (caseCheckItem.getPid()==18){count6+=1;}
            if (caseCheckItem.getPid()==19){count7+=1;}
        }

        List<Long> allList = new ArrayList<Long>();
        //给allList添加所有辅助检查itemId
        allList.add(new Long(13));
        allList.add(new Long(14));
        allList.add(new Long(15));
        allList.add(new Long(16));
        allList.add(new Long(17));
        allList.add(new Long(18));
        allList.add(new Long(19));
        List<Long> chooseList = new ArrayList<Long>();
        if (count1>0){allList.remove(new Long(13));}
        if (count2>0){allList.remove(new Long(14));}
        if (count3>0){allList.remove(new Long(15));}
        if (count4>0){allList.remove(new Long(16));}
        if (count5>0){allList.remove(new Long(17));}
        if (count6>0){allList.remove(new Long(18));}
        if (count7>0){allList.remove(new Long(19));}

//        boolean ret = allList.removeAll(longList);
        Iterator<Long> it = allList.iterator();  //创建迭代器
        List<Long> missList =new ArrayList<>();
        while (it.hasNext()){ //循环遍历迭代器
            missList.add(it.next());
        }
        Long[] itemIds1 =  missList.toArray(new Long[]{});
//        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CasePatientItem> list = casePatientItemMapper.selectCasePatientItemByIds(patientId,itemIds1);
        record.setPatientItem(list);
        return record;
    }

    @Override
    public List<CaseCheckItem> selectJsMissRecordById(Long id)
    {
        JsCheckRecord record = jsCheckRecordMapper.selectJsCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        //将itemid13-20属于精神治疗的父项目存入map
        Map<Long,String> map =new HashMap<>();
        for (int i=13;i<20;i++){
            map.put(new Long(i),"");
        }
        for (CaseCheckItem caseCheckItem:caseCheckItems) {
            map.remove(caseCheckItem.getPid());
            if (caseCheckItem.getPid()==28){
                map.remove(new Long(15));
            }
        }

        Iterator<Long> it = map.keySet().iterator();  //创建迭代器
        List<Long> missList =new ArrayList<>();
        while (it.hasNext()){ //循环遍历迭代器
            missList.add(it.next());
        }
        Long[] itemIdsMiss =  missList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItemList = caseCheckItemService.selectCaseCheckItemByIds(itemIdsMiss);
        return caseCheckItemList;
       /*
        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        int count7=0;
        for (CaseCheckItem caseCheckItem:caseCheckItems){
            if (caseCheckItem.getPid()==13){count1+=1;}
            if (caseCheckItem.getPid()==14){count2+=1;}
            if (caseCheckItem.getPid()==15||caseCheckItem.getPid()==28){count3+=1;}
            if (caseCheckItem.getPid()==16){count4+=1;}
            if (caseCheckItem.getPid()==17){count5+=1;}
            if (caseCheckItem.getPid()==18){count6+=1;}
            if (caseCheckItem.getPid()==19){count7+=1;}
        }
        List<Long> allList = new ArrayList<Long>();
        //给allList添加所有检查itemId
        if (count1==0){allList.add(new Long(13));}
        if (count2==0){allList.add(new Long(14));}
        if (count3==0){allList.add(new Long(15));}
        if (count4==0){allList.add(new Long(16));}
        if (count5==0){allList.add(new Long(17));}
        if (count6==0){allList.add(new Long(18));}
        if (count7==0){allList.add(new Long(19));}
        *//*Iterator<Long> it = allList.iterator();  //创建迭代器
        List<Long> missList =new ArrayList<>();
        while (it.hasNext()){ //循环遍历迭代器
            missList.add(it.next());
        }*//*
        Long[] itemIdsMiss =  allList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItemList = caseCheckItemService.selectCaseCheckItemByIds(itemIdsMiss);
        return caseCheckItemList;*/
    }
    @Override
    public Double countJsScore (Long id){
        Double countJsScore =new Double(0);
        JsCheckRecord record = jsCheckRecordMapper.selectJsCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        Map<Long,String> map =new HashMap<>();
        int count =0;
        for (CaseCheckItem caseCheckItem:caseCheckItems){
            if (caseCheckItem.getPid()==15||caseCheckItem.getPid()==28){
                count++;
            }else {
                map.put(caseCheckItem.getPid(),"");
            }
        }
        countJsScore=map.size()*1.5;
        if (count>0){ countJsScore += 1.5;}

        /*int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        int count7=0;
        //共7大方面，每个方面1.5分，共10.5分
        for (CaseCheckItem caseCheckItem:caseCheckItems){
            if (caseCheckItem.getPid()==13){count1+=1;}
            if (caseCheckItem.getPid()==14){count2+=1;}
            if (caseCheckItem.getPid()==15||caseCheckItem.getPid()==28){count3+=1;}
            if (caseCheckItem.getPid()==16){count4+=1;}
            if (caseCheckItem.getPid()==17){count5+=1;}
            if (caseCheckItem.getPid()==18){count6+=1;}
            if (caseCheckItem.getPid()==19){count7+=1;}
        }
        if (count1>0){countJsScore+=1.5;}
        if (count2>0){countJsScore+=1.5;}
        if (count3>0){countJsScore+=1.5;}
        if (count4>0){countJsScore+=1.5;}
        if (count5>0){countJsScore+=1.5;}
        if (count6>0){countJsScore+=1.5;}
        if (count7>0){countJsScore+=1.5;}*/
//        countJsScore =ids.length*1.5;
        return countJsScore;
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
