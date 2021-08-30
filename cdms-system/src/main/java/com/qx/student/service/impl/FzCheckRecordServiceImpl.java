package com.qx.student.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.cases.service.ICaseCheckItemService;
import com.qx.student.domain.FzcheckSupportRecord;
import com.qx.student.domain.JsCheckRecord;
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
        FzCheckRecord record = fzCheckRecordMapper.selectFzCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        List<Long> allList = new ArrayList<Long>();
        //给allList添加所有辅助检查itemId
        allList.add(new Long(45));
        allList.add(new Long(46));
        allList.add(new Long(66));
        allList.add(new Long(67));
        allList.removeAll(longList);
        /*Iterator<Long> it = allList.iterator();  //创建迭代器
        List<Long> missList =new ArrayList<>();
        while (it.hasNext()){ //循环遍历迭代器
            missList.add(it.next());
        }*/
        Long[] itemIds =  allList.toArray(new Long[]{});
        List<CaseCheckItem>caseCheckItemList = caseCheckItemService.selectCaseCheckItemByIds(itemIds);
        return caseCheckItemList;
    }
    @Override
    public FzCheckRecord selectFzMissRecordById(Long id,Long patientId)
    {
//        Map<String,String> map =new HashMap<>();
        FzCheckRecord record = fzCheckRecordMapper.selectFzCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());

        /*if (!longList.contains(new Long(45))){ map.put("睡眠多导检测（PSG）","未检查");}
        if (!longList.contains(new Long(46))){ map.put("多次睡眠潜伏期实验（MSLT）","未检查");}
        if (!longList.contains(new Long(66))){ map.put("脑诱发电位","未检查");}
        if (!longList.contains(new Long(67))){ map.put("近红外脑功能成像","未检查");}

        return map;*/
        List<Long> allList = new ArrayList<Long>();
        //给allList添加所有辅助检查itemId
        allList.add(new Long(45));
        allList.add(new Long(46));
        allList.add(new Long(66));
        allList.add(new Long(67));
        boolean ret = allList.removeAll(longList);
        Iterator<Long> it = allList.iterator();  //创建迭代器
        List<Long> missList =new ArrayList<>();
        while (it.hasNext()){ //循环遍历迭代器
            missList.add(it.next());
        }
        Long[] itemIds =  missList.toArray(new Long[]{});
//        Long[] itemIds =  longList.toArray(new Long[]{});
        List<CasePatientItem> list = casePatientItemMapper.selectCasePatientItemByIds(patientId,itemIds);
        record.setPatientItem(list);
        return record;
    }

    @Override
    public Double countFzScore(Long id){
        Double countFzScore =new Double(0);
        FzCheckRecord record = fzCheckRecordMapper.selectFzCheckRecordById(id);
        String[] ids = record.getItemIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
//        Long[] itemIds =  longList.toArray(new Long[]{});
        if (longList.contains(new Long(45))){ countFzScore += 2.0;}
        if (longList.contains(new Long(46))){ countFzScore += 2.0;}
        if (longList.contains(new Long(66))){ countFzScore += 2.0;}
        if (longList.contains(new Long(67))){ countFzScore += 2.0;}
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
