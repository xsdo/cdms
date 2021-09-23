package com.qx.student.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.cases.service.ICaseCheckItemService;
import com.qx.student.domain.*;
import com.qx.student.service.IFzcheckSupportRecordService;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.FzCheckRecordMapper;
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

    @Autowired
    private ICaseCheckItemService caseCheckItemService;
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

    @Override
    public List<CaseCheckItem> selectFzMissRecordById(Long id)
    {
        CaseCheckItem checkItem =new CaseCheckItem();
        checkItem.setCategory("2");
        checkItem.setIsMix("0");
        List<CaseCheckItem>caseCheckItemList=caseCheckItemService.selectCaseCheckItemList(checkItem);

        FzCheckRecord record = fzCheckRecordMapper.selectFzCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Iterator<CaseCheckItem>it =caseCheckItemList.iterator();
        while (it.hasNext()){
            CaseCheckItem caseCheckItemDe=it.next();
            if (longList.contains(caseCheckItemDe.getItemId())){
                it.remove();
            }
        }
        List<CaseCheckItem> caseCheckItems = caseCheckItemService.buildItemTree(caseCheckItemList);
        return caseCheckItems;
    }

    /*实验室检查（每个0.25分，共2分）pid77
    神经电生理检查（脑电图/脑诱发：1分，多导睡眠1分，共2分）pid78
    脑影像检查（选CT/MRI计1分，其他不计分，共1分）pid79
    其他（每个计1分，共4分）pid80 */
    @Override
    public Double countFzScore(Long id){
        Double countFzScore =new Double(0);
        FzCheckRecord record = fzCheckRecordMapper.selectFzCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItems=caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        int count78 =0;
        int count79 =0;
        for (CaseCheckItem caseCheckItem :caseCheckItems){
            if (caseCheckItem.getPid()==77){
                countFzScore+=0.25;
            }else if (caseCheckItem.getPid()==80){
                countFzScore+=1.0;
            }else if (caseCheckItem.getItemId()==45){ //多导id45
                countFzScore+=1.0;
            }else if (caseCheckItem.getItemId()==66||caseCheckItem.getItemId()==84){ //脑诱发66 脑电图84
                count78++;
            }else if (caseCheckItem.getItemId()==64||caseCheckItem.getItemId()==85){ //CT64 MRI85
                count79++;
            }
        }
        countFzScore+=(count78>0?1.0:0.0);
        countFzScore+=(count79>0?1.0:0.0);
        return countFzScore;
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
