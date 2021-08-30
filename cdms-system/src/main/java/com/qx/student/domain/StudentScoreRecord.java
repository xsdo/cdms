package com.qx.student.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 学生训练分数对象 student_score_record
 * 
 * @author aaa
 * @date 2021-08-20
 */
public class StudentScoreRecord extends BaseEntity
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

    /** 记录ID */
    @Excel(name = "记录ID")
    private Long recordId;

    /** 病史采集分数 */
    @Excel(name = "病史采集分数")
    private Double historyScore;

    /** 体格检查分数 */
    @Excel(name = "体格检查分数")
    private Double tgScore;

    /** 精神检查分数 */
    @Excel(name = "精神检查分数")
    private Double jsScore;

    /** 辅助检查分数 */
    @Excel(name = "辅助检查分数")
    private Double fzScore;

    /** 诊断分数 */
    @Excel(name = "诊断分数")
    private Double impScore;

    /** 治疗分数 */
    @Excel(name = "治疗分数")
    private Double treatScore;

    /** 病历分数 */
    @Excel(name = "病历分数")
    private Double medicalScore;

    /** 总分 */
    @Excel(name = "总分")
    private Double sumScore;

    /** 打分状态（0未完成 1已完成） */
    @Excel(name = "打分状态", readConverterExp = "0=未完成,1=已完成")
    private String status;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updataTime;

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
    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setHistoryScore(Double historyScore) 
    {
        this.historyScore = historyScore;
    }

    public Double getHistoryScore() 
    {
        return historyScore;
    }
    public void setTgScore(Double tgScore) 
    {
        this.tgScore = tgScore;
    }

    public Double getTgScore() 
    {
        return tgScore;
    }
    public void setJsScore(Double jsScore) 
    {
        this.jsScore = jsScore;
    }

    public Double getJsScore() 
    {
        return jsScore;
    }
    public void setFzScore(Double fzScore) 
    {
        this.fzScore = fzScore;
    }

    public Double getFzScore() 
    {
        return fzScore;
    }
    public void setImpScore(Double impScore) 
    {
        this.impScore = impScore;
    }

    public Double getImpScore() 
    {
        return impScore;
    }
    public void setTreatScore(Double treatScore) 
    {
        this.treatScore = treatScore;
    }

    public Double getTreatScore() 
    {
        return treatScore;
    }
    public void setMedicalScore(Double medicalScore) 
    {
        this.medicalScore = medicalScore;
    }

    public Double getMedicalScore() 
    {
        return medicalScore;
    }
    public void setSumScore(Double sumScore) 
    {
        this.sumScore = sumScore;
    }

    public Double getSumScore() 
    {
        return sumScore;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setUpdataTime(Date updataTime) 
    {
        this.updataTime = updataTime;
    }

    public Date getUpdataTime() 
    {
        return updataTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stuId", getStuId())
            .append("patientId", getPatientId())
            .append("recordId", getRecordId())
            .append("historyScore", getHistoryScore())
            .append("tgScore", getTgScore())
            .append("jsScore", getJsScore())
            .append("fzScore", getFzScore())
            .append("impScore", getImpScore())
            .append("treatScore", getTreatScore())
            .append("medicalScore", getMedicalScore())
            .append("sumScore", getSumScore())
            .append("status", getStatus())
            .append("remarks", getRemarks())
            .append("createTime", getCreateTime())
            .append("updataTime", getUpdataTime())
            .toString();
    }
}
