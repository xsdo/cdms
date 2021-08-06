package com.qx.student.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生训练记录对象 student_train_record
 * 
 * @author aaa
 * @date 2021-06-07
 */
public class StudentTrainRecord implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long stuId;

    /** 病人ID */
    @Excel(name = "病人ID")
    private Long patientId;

    /** 病史采集记录ID */
    @Excel(name = "病史采集记录ID")
    private Long historyRecordId;

    /** 体格检查记录ID */
    @Excel(name = "体格检查记录ID")
    private Long tgRecordId;

    /** 精神检查记录ID */
    @Excel(name = "精神检查记录ID")
    private Long jsRecordId;

    /** 辅助检查记录ID */
    @Excel(name = "辅助检查记录ID")
    private Long fzRecordId;

    /** 诊断记录ID */
    @Excel(name = "诊断记录ID")
    private Long impRecordId;

    /** 治疗记录ID */
    @Excel(name = "治疗记录ID")
    private Long treatRecordId;

    /** 病历书写ID */
    @Excel(name = "病历书写ID")
    private Long medicalRecordId;

    /** 训练状态（0未完成 1已完成） */
    @Excel(name = "训练状态", readConverterExp = "0=未完成,1=已完成")
    private String status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
    }

    public Long getStuId()
    {
        return stuId;
    }
    public void setPatientId(Long patientId)
    {
        this.patientId = patientId;
    }

    public Long getPatientId()
    {
        return patientId;
    }
    public void setHistoryRecordId(Long historyRecordId)
    {
        this.historyRecordId = historyRecordId;
    }

    public Long getHistoryRecordId()
    {
        return historyRecordId;
    }
    public void setTgRecordId(Long tgRecordId)
    {
        this.tgRecordId = tgRecordId;
    }

    public Long getTgRecordId()
    {
        return tgRecordId;
    }
    public void setJsRecordId(Long jsRecordId)
    {
        this.jsRecordId = jsRecordId;
    }

    public Long getJsRecordId()
    {
        return jsRecordId;
    }
    public void setFzRecordId(Long fzRecordId)
    {
        this.fzRecordId = fzRecordId;
    }

    public Long getFzRecordId()
    {
        return fzRecordId;
    }
    public void setImpRecordId(Long impRecordId)
    {
        this.impRecordId = impRecordId;
    }

    public Long getImpRecordId()
    {
        return impRecordId;
    }
    public void setTreatRecordId(Long treatRecordId)
    {
        this.treatRecordId = treatRecordId;
    }

    public Long getTreatRecordId()
    {
        return treatRecordId;
    }
    public void setMedicalRecordId(Long medicalRecordId)
    {
        this.medicalRecordId = medicalRecordId;
    }

    public Long getMedicalRecordId()
    {
        return medicalRecordId;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("stuId", getStuId())
                .append("patientId", getPatientId())
                .append("historyRecordId", getHistoryRecordId())
                .append("tgRecordId", getTgRecordId())
                .append("jsRecordId", getJsRecordId())
                .append("fzRecordId", getFzRecordId())
                .append("impRecordId", getImpRecordId())
                .append("treatRecordId", getTreatRecordId())
                .append("medicalRecordId", getMedicalRecordId())
                .append("status", getStatus())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .toString();
    }
}
