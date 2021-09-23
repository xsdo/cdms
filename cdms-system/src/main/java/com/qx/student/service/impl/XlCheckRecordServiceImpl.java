package com.qx.student.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.cases.service.ICaseCheckItemService;
import com.qx.student.domain.*;
import com.qx.student.mapper.XlCheckRecordMapper;
import com.qx.student.service.IStudentTrainRecordService;
import com.qx.student.service.IXlcheckSupportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.service.IXlCheckRecordService;

/**
 * 心理测量记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-09-09
 */
@Service
public class XlCheckRecordServiceImpl implements IXlCheckRecordService 
{

    @Autowired
    private XlCheckRecordMapper xlCheckRecordMapper;

    @Autowired
    private CasePatientItemMapper casePatientItemMapper;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    @Autowired
    private IXlcheckSupportRecordService xlcheckSupportRecordService;

    @Autowired
    private ICaseCheckItemService caseCheckItemService;

    /**
     * 查询心理测量记录
     * 
     * @param id 心理测量记录ID
     * @return 心理测量记录
     */
    /*@Override
    public XlCheckRecord selectXlCheckRecordById(Long id)
    {
        return xlCheckRecordMapper.selectXlCheckRecordById(id);
    }*/


    @Override
    public XlCheckRecord selectXLCheckRecordById(Long id, Long patientId)
    {
        XlCheckRecord record = xlCheckRecordMapper.selectXlCheckRecordById(id);
        //根据id数组查询所有项目结果集合
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CasePatientItem> list = casePatientItemMapper.selectCasePatientItemByIds(patientId,itemIds);
        record.setPatientItem(list);
        return record;
    }
    /**
     * 查询心理测量记录列表
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 心理测量记录
     */
    @Override
    public List<XlCheckRecord> selectXlCheckRecordList(XlCheckRecord xlCheckRecord)
    {
        return xlCheckRecordMapper.selectXlCheckRecordList(xlCheckRecord);
    }


    /**
     * 新增心理测量记录
     *
     * @param xlCheckRecord 心理测量记录
     * @param itemIds 项目ids
     * @param trainRecord 学生训练记录
     * @return 结果
     */
    @Override
    public int insertXlCheckRecord(XlCheckRecord xlCheckRecord, Long[] itemIds, StudentTrainRecord trainRecord)
    {
        xlCheckRecordMapper.insertXlCheckRecord(xlCheckRecord);
        //保存检查诊断支持记录
        List<XlcheckSupportRecord> list = new ArrayList<>();
        for (int i=0;i<itemIds.length;i++){
            XlcheckSupportRecord record = new XlcheckSupportRecord();
            record.setCheckRecordId(xlCheckRecord.getId());
            record.setItemId(itemIds[i]);
            list.add(record);
        }
        xlcheckSupportRecordService.insertXlcheckSupportRecordByList(list);
        //更新学生训练记录
        trainRecord.setXlRecordId(xlCheckRecord.getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    /**
     * 修改心理测量检查记录
     * @param xlCheckRecord 心理测量记录
     * @param itemIds 项目ids
     */
    @Override
    public void updateXlCheckRecord(XlCheckRecord xlCheckRecord, Long[] itemIds)
    {
        //修改心理测量记录
        xlCheckRecordMapper.updateXlCheckRecord(xlCheckRecord);
        //修改心理测量诊断支持记录
        List<Long> itemList = new ArrayList<Long>(Arrays.asList(itemIds));
        XlcheckSupportRecord supportRecord = new XlcheckSupportRecord();
        supportRecord.setCheckRecordId(xlCheckRecord.getId());
        List<XlcheckSupportRecord> list = xlcheckSupportRecordService.selectXlcheckSupportRecordList(supportRecord);
        for (XlcheckSupportRecord before:list){
            //如果已有项目id不包含在集合中，删除该数据
            //如果id包含在集合中，将其从集合中删除，以便后续遍历数组
            if(!itemList.contains(before.getItemId())){
                xlcheckSupportRecordService.deleteXlcheckSupportRecordById(before.getId());
            }else{
                itemList.remove(before.getItemId());
            }
        }
        Long[] itemIds1 = itemList.toArray(new Long[]{});

        for (int i=0;i<itemIds1.length;i++){

            supportRecord.setItemId(itemIds1[i]);
            xlcheckSupportRecordService.insertXlcheckSupportRecord(supportRecord);
        }
    }

    /**
     * 新增心理测量记录
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 结果
     */
    @Override
    public int insertXlCheckRecord(XlCheckRecord xlCheckRecord)
    {
        return xlCheckRecordMapper.insertXlCheckRecord(xlCheckRecord);
    }

    //获取错采项
    @Override
    public List<CaseCheckItem> selectXlWrongRecordById(Long id)
    {
        List<CaseCheckItem>caseCheckItemList=new ArrayList<>();
        XlCheckRecord record = xlCheckRecordMapper.selectXlCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>checkItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        Map<Long,String>map =new HashMap<>();
        for (CaseCheckItem caseCheckItem:checkItems){
            if (caseCheckItem.getIsMix()=="1"||caseCheckItem.getIsMix().equals("1")){
                caseCheckItemList.add(caseCheckItem);
                map.put(caseCheckItem.getPid(),"");
            }
        }
        for (Long itemId:map.keySet()){
            CaseCheckItem checkItemp=caseCheckItemService.selectCaseCheckItemById(itemId);
            caseCheckItemList.add(checkItemp);
        }
        List<CaseCheckItem> caseCheckItems = caseCheckItemService.buildItemTree(caseCheckItemList);
        return caseCheckItems;
    }

    //获取心理漏采项
    @Override
    public List<CaseCheckItem> selectXlMissRecordById(Long id){
        CaseCheckItem checkItem =new CaseCheckItem();
        checkItem.setCategory("3");
        checkItem.setIsMix("0");
        List<CaseCheckItem>caseCheckItemList=caseCheckItemService.selectCaseCheckItemList(checkItem);

        XlCheckRecord record = xlCheckRecordMapper.selectXlCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Iterator<CaseCheckItem>it =caseCheckItemList.iterator();
        while (it.hasNext()){
            CaseCheckItem caseCheckItemDe=it.next();
            if (longList.contains(caseCheckItemDe.getItemId())){
                it.remove();
            }
        }
        //获取错采项
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>checkItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        for (CaseCheckItem caseCheckItem:checkItems){
            if (caseCheckItem.getIsMix()=="1"||caseCheckItem.getIsMix().equals("1")){
                caseCheckItemList.add(caseCheckItem);
            }
        }
        List<CaseCheckItem> caseCheckItems = caseCheckItemService.buildItemTree(caseCheckItemList);
        return caseCheckItems;
    }
    //计算心理测量分数
    /*自评：必选一个得1分（SAS、SDS、SCL_90、AIS/PSIQ/EES），可选一个得0.5分（EPQ），选错一个扣1（Y-BOCS、HCL-32）【最高分4.5】
    47,48,49,68/69/70---------71---------72,73
    他评：必选一个得1分（HAMA、HAMD），可选一个得0.5分（PANSS），选错一个扣1（PSYRATS-AH、PSYRATS-D）【最高分2.5】
    50,51----------52---------74,75*/
    @Override
    public Double countXlScore (Long id){
        Double countXlScore =new Double(0);
        XlCheckRecord record =xlCheckRecordMapper.selectXlCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        int count =0;
        for (CaseCheckItem caseCheckItem:caseCheckItems){
            if (caseCheckItem.getIsMix()=="0"||caseCheckItem.getIsMix().equals("0")){
                if (caseCheckItem.getItemId()==68||caseCheckItem.getItemId()==69||caseCheckItem.getItemId()==70){
                    count++;
                }else if (caseCheckItem.getItemId()==71||caseCheckItem.getItemId()==52){
                    countXlScore+=0.5;
                }else {
                    countXlScore+=1.0;
                }
            }else {
                countXlScore-=1.0;
            }
        }
        countXlScore+=(count>0?1.0:0.0);
        return countXlScore;
    }
    /**
     * 修改心理测量记录
     * 
     * @param xlCheckRecord 心理测量记录
     * @return 结果
     */
    @Override
    public int updateXlCheckRecord(XlCheckRecord xlCheckRecord)
    {
        return xlCheckRecordMapper.updateXlCheckRecord(xlCheckRecord);
    }

    /**
     * 批量删除心理测量记录
     * 
     * @param ids 需要删除的心理测量记录ID
     * @return 结果
     */
    @Override
    public int deleteXlCheckRecordByIds(Long[] ids)
    {
        return xlCheckRecordMapper.deleteXlCheckRecordByIds(ids);
    }

    /**
     * 删除心理测量记录信息
     * 
     * @param id 心理测量记录ID
     * @return 结果
     */
    @Override
    public int deleteXlCheckRecordById(Long id)
    {
        return xlCheckRecordMapper.deleteXlCheckRecordById(id);
    }
}
