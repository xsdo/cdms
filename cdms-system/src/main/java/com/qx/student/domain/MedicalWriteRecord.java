package com.qx.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 病历书写对象 medical_write_record
 * 
 * @author aaa
 * @date 2021-06-25
 */
public class MedicalWriteRecord implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 病历书写记录ID */
    private Long id;

    /** 案例患者ID */
    @Excel(name = "案例患者ID")
    private Long patientId;

    /** 科别 */
    @Excel(name = "科别")
    private String department;

    /** 床号 */
    @Excel(name = "床号")
    private String bedNo;

    /** 住院号 */
    @Excel(name = "住院号")
    private String hospitalNo;

    /** 可靠程度 */
    @Excel(name = "可靠程度")
    private String reliable;

    /** 主诉 */
    @Excel(name = "主诉")
    private String chiefComplaint;

    /** 现病史 */
    @Excel(name = "现病史")
    private String hpi;

    /** 既往史 */
    @Excel(name = "既往史")
    private String pastHistory;

    /** 个人史 */
    @Excel(name = "个人史")
    private String personalHistory;

    /** 月经婚育史 */
    @Excel(name = "月经婚育史")
    private String mmch;

    /** 家族史 */
    @Excel(name = "家族史")
    private String familyHistory;

    /** 体格检查 */
    @Excel(name = "体格检查")
    private String tgCheck;

    /** 精神检查 */
    @Excel(name = "精神检查")
    private String jsCheck;

    /** 辅助检查 */
    @Excel(name = "辅助检查")
    private String fzCheck;

    /** 初步诊断 */
    @Excel(name = "初步诊断")
    private String primaryDiagnosis;

    /** 上级医师签名 */
    @Excel(name = "上级医师签名")
    private String superiorDoctor;

    /** 住院医师 */
    @Excel(name = "住院医师")
    private String residentDoctor;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setBedNo(String bedNo) 
    {
        this.bedNo = bedNo;
    }

    public String getBedNo() 
    {
        return bedNo;
    }
    public void setHospitalNo(String hospitalNo) 
    {
        this.hospitalNo = hospitalNo;
    }

    public String getHospitalNo() 
    {
        return hospitalNo;
    }
    public void setReliable(String reliable) 
    {
        this.reliable = reliable;
    }

    public String getReliable() 
    {
        return reliable;
    }
    public void setChiefComplaint(String chiefComplaint) 
    {
        this.chiefComplaint = chiefComplaint;
    }

    public String getChiefComplaint() 
    {
        return chiefComplaint;
    }
    public void setHpi(String hpi) 
    {
        this.hpi = hpi;
    }

    public String getHpi() 
    {
        return hpi;
    }
    public void setPastHistory(String pastHistory) 
    {
        this.pastHistory = pastHistory;
    }

    public String getPastHistory() 
    {
        return pastHistory;
    }
    public void setPersonalHistory(String personalHistory) 
    {
        this.personalHistory = personalHistory;
    }

    public String getPersonalHistory() 
    {
        return personalHistory;
    }
    public void setMmch(String mmch) 
    {
        this.mmch = mmch;
    }

    public String getMmch() 
    {
        return mmch;
    }
    public void setFamilyHistory(String familyHistory) 
    {
        this.familyHistory = familyHistory;
    }

    public String getFamilyHistory() 
    {
        return familyHistory;
    }
    public void setTgCheck(String tgCheck) 
    {
        this.tgCheck = tgCheck;
    }

    public String getTgCheck() 
    {
        return tgCheck;
    }
    public void setJsCheck(String jsCheck) 
    {
        this.jsCheck = jsCheck;
    }

    public String getJsCheck() 
    {
        return jsCheck;
    }
    public void setFzCheck(String fzCheck) 
    {
        this.fzCheck = fzCheck;
    }

    public String getFzCheck() 
    {
        return fzCheck;
    }
    public void setPrimaryDiagnosis(String primaryDiagnosis) 
    {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getPrimaryDiagnosis() 
    {
        return primaryDiagnosis;
    }
    public void setSuperiorDoctor(String superiorDoctor) 
    {
        this.superiorDoctor = superiorDoctor;
    }

    public String getSuperiorDoctor() 
    {
        return superiorDoctor;
    }
    public void setResidentDoctor(String residentDoctor) 
    {
        this.residentDoctor = residentDoctor;
    }

    public String getResidentDoctor() 
    {
        return residentDoctor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("patientId", getPatientId())
            .append("department", getDepartment())
            .append("bedNo", getBedNo())
            .append("hospitalNo", getHospitalNo())
            .append("reliable", getReliable())
            .append("chiefComplaint", getChiefComplaint())
            .append("hpi", getHpi())
            .append("pastHistory", getPastHistory())
            .append("personalHistory", getPersonalHistory())
            .append("mmch", getMmch())
            .append("familyHistory", getFamilyHistory())
            .append("tgCheck", getTgCheck())
            .append("jsCheck", getJsCheck())
            .append("fzCheck", getFzCheck())
            .append("primaryDiagnosis", getPrimaryDiagnosis())
            .append("superiorDoctor", getSuperiorDoctor())
            .append("residentDoctor", getResidentDoctor())
            .toString();
    }
}
