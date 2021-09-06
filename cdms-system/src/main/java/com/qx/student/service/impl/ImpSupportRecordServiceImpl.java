package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.service.ICaseImpDiagnosisService;
import com.qx.student.domain.ImpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.ImpSupportRecordMapper;
import com.qx.student.domain.ImpSupportRecord;
import com.qx.student.service.IImpSupportRecordService;

/**
 * 诊断依据支持记录Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-28
 */
@Service
public class ImpSupportRecordServiceImpl implements IImpSupportRecordService 
{
    @Autowired
    private ImpSupportRecordMapper impSupportRecordMapper;

    @Autowired
    private ICaseImpDiagnosisService caseImpDiagnosisService;

    /**
     * 查询诊断依据支持记录
     * 
     * @param id 诊断依据支持记录ID
     * @return 诊断依据支持记录
     */
    @Override
    public ImpSupportRecord selectImpSupportRecordById(Long id)
    {
        return impSupportRecordMapper.selectImpSupportRecordById(id);
    }

    /**
     * 查询诊断依据支持记录列表
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 诊断依据支持记录
     */
    @Override
    public List<ImpSupportRecord> selectImpSupportRecordList(ImpSupportRecord impSupportRecord)
    {
        return impSupportRecordMapper.selectImpSupportRecordList(impSupportRecord);
    }

    /**
     * 新增诊断依据支持记录
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 结果
     */
    @Override
    public int insertImpSupportRecord(ImpSupportRecord impSupportRecord)
    {
        return impSupportRecordMapper.insertImpSupportRecord(impSupportRecord);
    }

    @Override
    public int insertImpSupportByImpid(List<Long> basisIds, Long recordId, Long pimpId){
        List<ImpSupportRecord>list=new ArrayList<>();
        for (int i=0;i<basisIds.size();i++){
            ImpSupportRecord impSupportRecord= new ImpSupportRecord();
            impSupportRecord.setImpRecordId(recordId);
            impSupportRecord.setPimpId(pimpId);
            impSupportRecord.setBasisId(basisIds.get(i));
            list.add(impSupportRecord);
        }
        return impSupportRecordMapper.insertImpSupportRecordByList(list);
    }

    @Override
    public void insertImpSupport(ImpRecord impRecord, String supports){
        String[] ids = impRecord.getImpIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        String[] types = impRecord.getType().split(",");
        //获取主要诊断id
        Long impId=new Long(0);
        List<Long>basisIds=new ArrayList<>();
        for (int i =0;i<longList.size()&&i<types.length;i++) {
            if (types[i].equals("0")){
                impId=longList.get(i);
                caseImpDiagnosisService.getImpChildId(basisIds, longList.get(i));
            }
        }
        this.insertImpSupportByImpid(basisIds,impRecord.getId(),impId);
        //更新imp支持记录
        String[]basisIdList = supports.split(",");
        List<Long> list = Arrays.asList(basisIdList).stream().map(Long::parseLong).collect(Collectors.toList());
        ImpSupportRecord impSupportRecord =new ImpSupportRecord();
        impSupportRecord.setImpRecordId(impRecord.getId());
        impSupportRecord.setPimpId(impId);
        List<ImpSupportRecord>impSupportRecords =this.selectImpSupportRecordList(impSupportRecord);
            for (ImpSupportRecord impSupportRecord1:impSupportRecords){
                if (list.contains(impSupportRecord1.getBasisId())){
                    impSupportRecord1.setSupport("1");
                    this.updateImpSupportRecord(impSupportRecord1);
                }else {
                    impSupportRecord1.setSupport("0");
                    this.updateImpSupportRecord(impSupportRecord1);
                }
            }
    }

    @Override
    public void updataImpSupport(ImpRecord impRecord, String supports){
        String[] ids = impRecord.getImpIds().split(",");
        List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
        String[] types = impRecord.getType().split(",");
        //更新imp支持记录
        String[]basisIdList = supports.split(",");
        List<Long> list = Arrays.asList(basisIdList).stream().map(Long::parseLong).collect(Collectors.toList());
        //获取主要诊断id
        Long impId=new Long(0);
        List<Long>basisIds=new ArrayList<>();
        for (int i =0;i<longList.size()&&i<types.length;i++) {
            if (types[i].equals("0")){
                impId=longList.get(i);
                caseImpDiagnosisService.getImpChildId(basisIds, longList.get(i));
            }
        }
        ImpSupportRecord impSupport=new ImpSupportRecord();
        impSupport.setPimpId(impId);
        impSupport.setImpRecordId(impRecord.getId());
        List<ImpSupportRecord>impSupportRecordList =this.selectImpSupportRecordList(impSupport);
        if (impSupportRecordList!=null&&impSupportRecordList.size()>0){
            for (ImpSupportRecord impSupportRecord1:impSupportRecordList){
                if (list.contains(impSupportRecord1.getBasisId())){
                    impSupportRecord1.setSupport("1");
                    this.updateImpSupportRecord(impSupportRecord1);
                }else {
                    impSupportRecord1.setSupport("0");
                    this.updateImpSupportRecord(impSupportRecord1);
                }
            }
        }else {
            //新增支持表
            this.insertImpSupportByImpid(basisIds,impRecord.getId(),impId);

            ImpSupportRecord impSupportRecord =new ImpSupportRecord();
            impSupportRecord.setImpRecordId(impRecord.getId());
            impSupportRecord.setPimpId(impId);
            List<ImpSupportRecord>impSupportRecords =this.selectImpSupportRecordList(impSupportRecord);
            for (ImpSupportRecord impSupportRecord1:impSupportRecords){
                if (list.contains(impSupportRecord1.getBasisId())){
                    impSupportRecord1.setSupport("1");
                    this.updateImpSupportRecord(impSupportRecord1);
                }else {
                    impSupportRecord1.setSupport("0");
                    this.updateImpSupportRecord(impSupportRecord1);
                }
            }
        }
    }
    /**
     * 修改诊断依据支持记录
     * 
     * @param impSupportRecord 诊断依据支持记录
     * @return 结果
     */
    @Override
    public int updateImpSupportRecord(ImpSupportRecord impSupportRecord)
    {
        return impSupportRecordMapper.updateImpSupportRecord(impSupportRecord);
    }

    /**
     * 批量删除诊断依据支持记录
     * 
     * @param ids 需要删除的诊断依据支持记录ID
     * @return 结果
     */
    @Override
    public int deleteImpSupportRecordByIds(Long[] ids)
    {
        return impSupportRecordMapper.deleteImpSupportRecordByIds(ids);
    }

    /**
     * 删除诊断依据支持记录信息
     * 
     * @param id 诊断依据支持记录ID
     * @return 结果
     */
    @Override
    public int deleteImpSupportRecordById(Long id)
    {
        return impSupportRecordMapper.deleteImpSupportRecordById(id);
    }
}
