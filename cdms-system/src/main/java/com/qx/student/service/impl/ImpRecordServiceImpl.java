package com.qx.student.service.impl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseImp;
import com.qx.cases.domain.CaseImpDiagnosis;
import com.qx.cases.service.ICaseImpDiagnosisService;
import com.qx.cases.service.ICaseImpService;
import com.qx.common.core.domain.AjaxResult;
import com.qx.student.domain.ImpSupportRecord;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.vo.ImpChooseVo;
import com.qx.student.domain.vo.ImpRecordVo;
import com.qx.student.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.ImpRecordMapper;
import com.qx.student.domain.ImpRecord;

/**
 * 诊断记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-18
 */
@Service
public class ImpRecordServiceImpl implements IImpRecordService 
{
    @Autowired
    private ImpRecordMapper impRecordMapper;
    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    @Autowired
    private IHistorySupportRecordService historySupportRecordService;

    @Autowired
    private ITgcheckSupportRecordService tgcheckSupportRecordService;

    @Autowired
    private IJscheckSupportRecordService jscheckSupportRecordService;

    @Autowired
    private IFzcheckSupportRecordService fzcheckSupportRecordService;

    @Autowired
    private ICaseImpService caseImpService;

    @Autowired
    private ICaseImpDiagnosisService caseImpDiagnosisService;

    @Autowired
    private IImpSupportRecordService impSupportRecordService;

    /**
     * 查询诊断记录
     * 
     * @param id 诊断记录ID
     * @return 诊断记录
     */
    @Override
    public ImpRecord selectImpRecordById(Long id)
    {
        return impRecordMapper.selectImpRecordById(id);
    }

    @Override
    public List<ImpChooseVo> getImpChooseVo(Long id){
        List<ImpChooseVo> impChooseVos =new ArrayList<>();
        ImpRecord impRecord=impRecordMapper.selectImpRecordById(id);
        String[] ids = impRecord.getImpIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        String[] types = impRecord.getType().split(",");
        for (int i =0;i<longList.size()&&i<types.length;i++){
                CaseImp caseImp =caseImpService.selectCaseImpById(longList.get(i));
                ImpChooseVo impChooseVo =new ImpChooseVo();
                impChooseVo.setImpType(types[i]);
                impChooseVo.setImpName(caseImp.getImpName());
                impChooseVos.add(impChooseVo);
        }
        return impChooseVos;
    }
    @Override
    public Map<String,String> getImpChoose(Long id){

        Map<String,String> map=new IdentityHashMap<String,String>();
        ImpRecord impRecord=impRecordMapper.selectImpRecordById(id);

        String[] ids = impRecord.getImpIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        String[] types = impRecord.getType().split(",");
        for (int i =0;i<longList.size()&&i<types.length;i++){
            if (types[i].equals("0")){
                CaseImp caseImp =caseImpService.selectCaseImpById(longList.get(i));
                map.put("zyImp",caseImp.getImpName());
            }
            if (types[i].equals("1")){
                CaseImp caseImp =caseImpService.selectCaseImpById(longList.get(i));
                map.put(new String("cyImp"),caseImp.getImpName());
            }
            if (types[i].equals("2")){
                CaseImp caseImp =caseImpService.selectCaseImpById(longList.get(i));
                map.put(new String("jbImp"),caseImp.getImpName());
            }
        }
        return map;
    }

    @Override
    public Double countImpScore(Long id){
        Double countScore =new Double(0);
        ImpRecord impRecord=impRecordMapper.selectImpRecordById(id);
        String[] ids = impRecord.getImpIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        String[] types = impRecord.getType().split(",");
        boolean flag =false;
        boolean flagA =true;
        boolean flagB =true;
        Double countScoreA =0.0;
        Double countScoreB =0.0;
        for (int i =0;i<longList.size()&&i<types.length;i++){
            //主要诊断
            if (longList.get(i)==5&&types[i].equals("0")){
                countScore += 15.0;
                flag = true;
            }
            //次要诊断--只要判断错误均不得分。
            if (types[i].equals("1")){
                if (longList.get(i)!=1&&longList.get(i)!=2&&longList.get(i)!=3&&longList.get(i)!=4){
                    flagA=false;
                }else {
                    countScoreA =2.5;
                }
            }
            //鉴别诊断--只要判断错误均不得分。
            if (types[i].equals("2")){
                if (longList.get(i)!=6&&longList.get(i)!=7&&longList.get(i)!=8&&longList.get(i)!=9){
                    flagB=false;
                }else {
                    countScoreB =2.5;
                }
            }
        }
        if (flag){
            if (flagA){countScore+=countScoreA;}
            if (flagB){countScore+=countScoreB;}
        }else {
            countScore=0.0;
        }

        return countScore;
    }


    /**
     * 查询诊断记录列表
     * 
     * @param impRecord 诊断记录
     * @return 诊断记录
     */
    @Override
    public List<ImpRecord> selectImpRecordList(ImpRecord impRecord)
    {
        return impRecordMapper.selectImpRecordList(impRecord);
    }


    @Override
    public int insertImpRecordN(ImpRecordVo impRecordVo)
    {
        //新增诊断数据
        impRecordMapper.insertImpRecord(impRecordVo.getImpRecord());

        //新增imp支持表
        if (impRecordVo.getImpRecord()!=null&&!impRecordVo.getSupports().equals("")&&impRecordVo.getSupports()!=null){

            impSupportRecordService.insertImpSupport(impRecordVo.getImpRecord(),impRecordVo.getSupports());
        }

        //更新病史采集问题支持记录
        if (impRecordVo.getHistorySupportRecordList()!=null && impRecordVo.getHistorySupportRecordList().size()>0){

            historySupportRecordService.updateHistorySupportRecordBatch(impRecordVo.getHistorySupportRecordList());
        }
        //更新体格检查项目支持记录
        if (impRecordVo.getTgcheckSupportRecordList()!=null && impRecordVo.getTgcheckSupportRecordList().size()>0){

            tgcheckSupportRecordService.updateTgcheckSupportRecordBatch(impRecordVo.getTgcheckSupportRecordList());
        }
        //更新精神检查项目支持记录
        if (impRecordVo.getJscheckSupportRecordList()!=null && impRecordVo.getJscheckSupportRecordList().size()>0){

            jscheckSupportRecordService.updateJscheckSupportRecordBatch(impRecordVo.getJscheckSupportRecordList());
        }
        //更新辅助检查项目支持记录
        if (impRecordVo.getFzcheckSupportRecordList()!=null && impRecordVo.getFzcheckSupportRecordList().size()>0){

            fzcheckSupportRecordService.updateFzcheckSupportRecordBatch(impRecordVo.getFzcheckSupportRecordList());
        }
        //更新诊断依据支持记录

        //更新学生训练记录
        StudentTrainRecord trainRecord = new StudentTrainRecord();
        trainRecord.setId(impRecordVo.getStudentTrainId());
        trainRecord.setImpRecordId(impRecordVo.getImpRecord().getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);

    }

    /**
     * 新增诊断记录
     * 
     * @param impRecordVo 诊断记录
     * @return 结果
     */
    @Override
    public int insertImpRecord(ImpRecordVo impRecordVo)
    {
        //新增诊断数据
        impRecordMapper.insertImpRecord(impRecordVo.getImpRecord());

        //更新病史采集问题支持记录
        if (impRecordVo.getHistorySupportRecordList()!=null && impRecordVo.getHistorySupportRecordList().size()>0){

            historySupportRecordService.updateHistorySupportRecordBatch(impRecordVo.getHistorySupportRecordList());
        }
        //更新体格检查项目支持记录
        if (impRecordVo.getTgcheckSupportRecordList()!=null && impRecordVo.getTgcheckSupportRecordList().size()>0){

            tgcheckSupportRecordService.updateTgcheckSupportRecordBatch(impRecordVo.getTgcheckSupportRecordList());
        }
        //更新精神检查项目支持记录
        if (impRecordVo.getJscheckSupportRecordList()!=null && impRecordVo.getJscheckSupportRecordList().size()>0){

            jscheckSupportRecordService.updateJscheckSupportRecordBatch(impRecordVo.getJscheckSupportRecordList());
        }
        //更新辅助检查项目支持记录
        if (impRecordVo.getFzcheckSupportRecordList()!=null && impRecordVo.getFzcheckSupportRecordList().size()>0){

            fzcheckSupportRecordService.updateFzcheckSupportRecordBatch(impRecordVo.getFzcheckSupportRecordList());
        }
        //更新诊断依据支持记录

        //更新学生训练记录
        StudentTrainRecord trainRecord = new StudentTrainRecord();
        trainRecord.setId(impRecordVo.getStudentTrainId());
        trainRecord.setImpRecordId(impRecordVo.getImpRecord().getId());
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);

    }
    @Override
    public void updateImpRecordN(ImpRecordVo impRecordVo)
    {
        //更新诊断记录
        impRecordMapper.updateImpRecord(impRecordVo.getImpRecord());

        //更新imp支持表
        if (impRecordVo.getImpRecord()!=null&&!impRecordVo.getSupports().equals("")&&impRecordVo.getSupports()!=null){

            impSupportRecordService.updataImpSupport(impRecordVo.getImpRecord(),impRecordVo.getSupports());
        }
        //更新病史采集问题支持记录
        if (impRecordVo.getHistorySupportRecordList()!=null && impRecordVo.getHistorySupportRecordList().size()>0){

            historySupportRecordService.updateHistorySupportRecordBatch(impRecordVo.getHistorySupportRecordList());
        }
        //更新体格检查项目支持记录
        if (impRecordVo.getTgcheckSupportRecordList()!=null && impRecordVo.getTgcheckSupportRecordList().size()>0){

            tgcheckSupportRecordService.updateTgcheckSupportRecordBatch(impRecordVo.getTgcheckSupportRecordList());
        }
        //更新精神检查项目支持记录
        if (impRecordVo.getJscheckSupportRecordList()!=null && impRecordVo.getJscheckSupportRecordList().size()>0){

            jscheckSupportRecordService.updateJscheckSupportRecordBatch(impRecordVo.getJscheckSupportRecordList());
        }
        //更新辅助检查项目支持记录
        if (impRecordVo.getFzcheckSupportRecordList()!=null && impRecordVo.getFzcheckSupportRecordList().size()>0){

            fzcheckSupportRecordService.updateFzcheckSupportRecordBatch(impRecordVo.getFzcheckSupportRecordList());
        }
        //更新诊断依据支持记录

    }
    /**
     * 修改诊断记录
     * 
     * @param impRecordVo 诊断记录
     * @return 结果
     */
    @Override
    public void updateImpRecord(ImpRecordVo impRecordVo)
    {
        //更新诊断记录
        impRecordMapper.updateImpRecord(impRecordVo.getImpRecord());

        //更新病史采集问题支持记录
        if (impRecordVo.getHistorySupportRecordList()!=null && impRecordVo.getHistorySupportRecordList().size()>0){

            historySupportRecordService.updateHistorySupportRecordBatch(impRecordVo.getHistorySupportRecordList());
        }
        //更新体格检查项目支持记录
        if (impRecordVo.getTgcheckSupportRecordList()!=null && impRecordVo.getTgcheckSupportRecordList().size()>0){

            tgcheckSupportRecordService.updateTgcheckSupportRecordBatch(impRecordVo.getTgcheckSupportRecordList());
        }
        //更新精神检查项目支持记录
        if (impRecordVo.getJscheckSupportRecordList()!=null && impRecordVo.getJscheckSupportRecordList().size()>0){

            jscheckSupportRecordService.updateJscheckSupportRecordBatch(impRecordVo.getJscheckSupportRecordList());
        }
        //更新辅助检查项目支持记录
        if (impRecordVo.getFzcheckSupportRecordList()!=null && impRecordVo.getFzcheckSupportRecordList().size()>0){

            fzcheckSupportRecordService.updateFzcheckSupportRecordBatch(impRecordVo.getFzcheckSupportRecordList());
        }
        //更新诊断依据支持记录

    }

    /**
     * 批量删除诊断记录
     * 
     * @param ids 需要删除的诊断记录ID
     * @return 结果
     */
    @Override
    public int deleteImpRecordByIds(Long[] ids)
    {
        return impRecordMapper.deleteImpRecordByIds(ids);
    }

    /**
     * 删除诊断记录信息
     * 
     * @param id 诊断记录ID
     * @return 结果
     */
    @Override
    public int deleteImpRecordById(Long id)
    {
        return impRecordMapper.deleteImpRecordById(id);
    }
}
