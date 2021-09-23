package com.qx.student.service.impl;

import java.util.Date;
import java.util.List;

import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.vo.MedicalWriteRecordVo;
import com.qx.student.service.IStudentTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.MedicalWriteRecordMapper;
import com.qx.student.domain.MedicalWriteRecord;
import com.qx.student.service.IMedicalWriteRecordService;

/**
 * 病历书写Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-25
 */
@Service
public class MedicalWriteRecordServiceImpl implements IMedicalWriteRecordService 
{
    @Autowired
    private MedicalWriteRecordMapper medicalWriteRecordMapper;

    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;
    /**
     * 查询病历书写
     * 
     * @param id 病历书写ID
     * @return 病历书写
     */
    @Override
    public MedicalWriteRecord selectMedicalWriteRecordById(Long id)
    {
        return medicalWriteRecordMapper.selectMedicalWriteRecordById(id);
    }

    /**
     * 查询病历书写列表
     * 
     * @param medicalWriteRecord 病历书写
     * @return 病历书写
     */
    @Override
    public List<MedicalWriteRecord> selectMedicalWriteRecordList(MedicalWriteRecord medicalWriteRecord)
    {
        return medicalWriteRecordMapper.selectMedicalWriteRecordList(medicalWriteRecord);
    }

    @Override
    public Double countMedicalScore(Long id){
        Double countMedicalScore =new Double(0);
        MedicalWriteRecord medicalWriteRecord = medicalWriteRecordMapper.selectMedicalWriteRecordById(id);
        //
        if (medicalWriteRecord!=null){
            if (medicalWriteRecord.getChiefComplaint()!=null&&!medicalWriteRecord.getChiefComplaint().equals("")){
                countMedicalScore+=(medicalWriteRecord.getChiefComplaint().contains("睡眠差")
                        ||medicalWriteRecord.getChiefComplaint().contains("夜眠差")
                        ||medicalWriteRecord.getChiefComplaint().contains("近半月")?0.2:0.0);
            }
            if (medicalWriteRecord.getHpi()!=null&&!medicalWriteRecord.getHpi().equals("")){
                String[]hpiWords={"入睡困难","早醒","打鼾","记忆力下降","注意力","下肢","情绪"};
                String[]yaoWords={"右佐匹克隆片","佐匹克隆片","艾司唑仑片","阿普唑仑片","镇静催眠药"};
                for (String hpiWord:hpiWords){
                    countMedicalScore+=(medicalWriteRecord.getHpi().contains(hpiWord)?0.05:0.0);
                }
                countMedicalScore+=(medicalWriteRecord.getHpi().contains("精力不足")
                        ||medicalWriteRecord.getHpi().contains("打盹")?0.05:0.0);
                countMedicalScore+=(medicalWriteRecord.getHpi().contains("记忆")
                        ||medicalWriteRecord.getHpi().contains("注意")?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getHpi(),yaoWords)?0.05:0.0);
            }
            if (medicalWriteRecord.getPastHistory()!=null&&!medicalWriteRecord.getPastHistory().equals("")){
                countMedicalScore+=(medicalWriteRecord.getPastHistory().contains("高血压")?0.2:0.0);
            }
            if (medicalWriteRecord.getPersonalHistory()!=null&&!medicalWriteRecord.getPersonalHistory().equals("")){
                String[]phWords={"性格","兴趣","人际","能力","已婚"};
                for (String phWord:phWords){
                    countMedicalScore+=(medicalWriteRecord.getPersonalHistory().contains(phWord)?0.08:0.0);
                }
            }
            if (medicalWriteRecord.getMmch()!=null&&!medicalWriteRecord.getMmch().equals("")){
                countMedicalScore+=(medicalWriteRecord.getMmch().contains("结婚")?0.1:0.0);
                countMedicalScore+=(medicalWriteRecord.getMmch().contains("女")?0.1:0.0);
            }
            if (medicalWriteRecord.getFamilyHistory()!=null&&!medicalWriteRecord.getFamilyHistory().equals("")){
                countMedicalScore+=(medicalWriteRecord.getFamilyHistory().contains("无")?0.2:0.0);
            }
            if (medicalWriteRecord.getTgCheck()!=null&&!medicalWriteRecord.getTgCheck().equals("")){
                String[]tgWords={"体温","脉搏","呼吸","血压"};
                for (String tgWord:tgWords){
                    countMedicalScore+=(medicalWriteRecord.getTgCheck().contains(tgWord)?0.1:0.0);
                }
                countMedicalScore+=(medicalWriteRecord.getTgCheck().contains("身高")
                        ||medicalWriteRecord.getTgCheck().contains("体重")?0.1:0.0);
            }
            if (medicalWriteRecord.getJsCheck()!=null&&!medicalWriteRecord.getJsCheck().equals("")){
                String[]jsWords={"意识","注意","接触","定向力","感知综合障碍","记忆","计算","判断","常识","自知力"};
                String[]gzWords={"感觉过敏","感觉减退","感觉倒错","内感性不适"};
                String[]sdWords={"思维奔逸","思维迟缓","思维贫乏","病理性赘述"};
                String[]lgWords={"思维松弛","思维破裂","思维不连贯","思维中断","思维云集"};
                String[]ljWords={"象征性思维","语词新作","逻辑倒错性思维","诡辩性"};
                String[]hdWords={"持续言语","重复言语","刻板言语","模仿言语"};
                String[]yzWords={"意志增强","意志减退","意志缺乏","意向倒错","矛盾意向"};
                String[]yyWords={"语量适中","语速适中","语调平和","未见自言自语"};
                String[]xwWords={"精神运动性兴奋","木僵","违拗症","被动服从","持续动作","刻板动作","模仿动作","作态","离奇古怿动作","强制性动作","强迫性动作"};
                for (String jsWord:jsWords){
                    countMedicalScore+=(medicalWriteRecord.getJsCheck().contains(jsWord)?0.05:0.0);
                }
                countMedicalScore+=(medicalWriteRecord.getJsCheck().contains("仪态")
                        ||medicalWriteRecord.getJsCheck().contains("衣着")?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),gzWords)?0.05:0.0);
                countMedicalScore+=(medicalWriteRecord.getJsCheck().contains("幻觉")
                        ||medicalWriteRecord.getJsCheck().contains("错觉")?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),sdWords)?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),lgWords)?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),ljWords)?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),hdWords)?0.05:0.0);
                countMedicalScore+=(medicalWriteRecord.getJsCheck().contains("妄想")
                        ||medicalWriteRecord.getJsCheck().contains("超价")
                        ||medicalWriteRecord.getJsCheck().contains("强迫")?0.1:0.0);
                countMedicalScore+=(medicalWriteRecord.getJsCheck().contains("烦")
                        ||medicalWriteRecord.getJsCheck().contains("不高兴")
                        ||medicalWriteRecord.getJsCheck().contains("高兴不起来")?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),yzWords)?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),yyWords)?0.05:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getJsCheck(),xwWords)?0.05:0.0);
                countMedicalScore+=(medicalWriteRecord.getJsCheck().contains("理解")
                        ||medicalWriteRecord.getJsCheck().contains("抽象概括")?0.05:0.0);
            }
            if (medicalWriteRecord.getXlCheck()!=null&&!medicalWriteRecord.getXlCheck().equals("")){
                String[]xl0Words={"SAS","焦虑自评量表","HAMA","汉密尔顿焦虑量表"};
                String[]xl1Words={"AIS","阿森斯失眠量表","PSQI","匹兹堡睡眠质量指数量表","EES","嗜睡评估量表"};
                String[]xl2Words={"SDS","抑郁自评量表","HAMD","汉密尔顿抑郁量表"};
                countMedicalScore+=(addCount(medicalWriteRecord.getXlCheck(),xl0Words)?0.2:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getXlCheck(),xl1Words)?0.2:0.0);
                countMedicalScore+=(addCount(medicalWriteRecord.getXlCheck(),xl2Words)?0.2:0.0);
            }
            if (medicalWriteRecord.getFzCheck()!=null&&!medicalWriteRecord.getFzCheck().equals("")){
                countMedicalScore+=(medicalWriteRecord.getFzCheck().contains("多导睡眠监测")?0.2:0.0);
                countMedicalScore+=(medicalWriteRecord.getFzCheck().contains("脑诱发电位")?0.2:0.0);
                countMedicalScore+=(medicalWriteRecord.getFzCheck().contains("近红外脑功能成像")?0.2:0.0);
            }
            if (medicalWriteRecord.getPrimaryDiagnosis()!=null&&!medicalWriteRecord.getPrimaryDiagnosis().equals("")){
                countMedicalScore+=(medicalWriteRecord.getPrimaryDiagnosis().contains("慢性失眠伴焦虑、抑郁状态")?0.2:0.0);
                countMedicalScore+=(medicalWriteRecord.getPrimaryDiagnosis().contains("贫血")?0.1:0.0);
                countMedicalScore+=(medicalWriteRecord.getPrimaryDiagnosis().contains("不宁腿综合征")?0.1:0.0);
                countMedicalScore+=(medicalWriteRecord.getPrimaryDiagnosis().contains("中度阻塞性睡眠呼吸暂停综合症")?0.1:0.0);
            }
        }
        return countMedicalScore;
    }
    private Boolean addCount(String med,String[] words){
        Boolean flag=false;
        for (String word:words){
            if (med.contains(word)){
                flag=true;
            }
        }
        return flag;
    }
    /**
     * 新增病历书写
     * 
     * @param recordVo 病历书写
     * @return 结果
     */
    @Override
    public int insertMedicalWriteRecord(MedicalWriteRecordVo recordVo)
    {
        //添加病历书写记录
        medicalWriteRecordMapper.insertMedicalWriteRecord(recordVo.getMedicalWriteRecord());
        //更新学生训练记录表中的数据
        StudentTrainRecord trainRecord = new StudentTrainRecord();
        trainRecord.setId(recordVo.getStudentTrainId());
        trainRecord.setMedicalRecordId(recordVo.getMedicalWriteRecord().getId());
        trainRecord.setEndTime(new Date());
        trainRecord.setStatus("1");
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    @Override
    public int insertMedicalWriteRecord(MedicalWriteRecord medicalWriteRecord, Long studentTrainId)
    {
        //添加病历书写记录
        medicalWriteRecordMapper.insertMedicalWriteRecord(medicalWriteRecord);
        //更新学生训练记录表中的数据
        StudentTrainRecord trainRecord = new StudentTrainRecord();
        trainRecord.setId(studentTrainId);
        trainRecord.setMedicalRecordId(medicalWriteRecord.getId());
        trainRecord.setEndTime(new Date());
        trainRecord.setStatus("1");
        return studentTrainRecordService.updateStudentTrainRecord(trainRecord);
    }

    /**
     * 修改病历书写
     * 
     * @param medicalWriteRecord 病历书写
     * @return 结果
     */
    @Override
    public int updateMedicalWriteRecord(MedicalWriteRecord medicalWriteRecord)
    {
        return medicalWriteRecordMapper.updateMedicalWriteRecord(medicalWriteRecord);
    }

    /**
     * 批量删除病历书写
     * 
     * @param ids 需要删除的病历书写ID
     * @return 结果
     */
    @Override
    public int deleteMedicalWriteRecordByIds(Long[] ids)
    {
        return medicalWriteRecordMapper.deleteMedicalWriteRecordByIds(ids);
    }

    /**
     * 删除病历书写信息
     * 
     * @param id 病历书写ID
     * @return 结果
     */
    @Override
    public int deleteMedicalWriteRecordById(Long id)
    {
        return medicalWriteRecordMapper.deleteMedicalWriteRecordById(id);
    }
}
