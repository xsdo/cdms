package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 案例患者对象 case_patient
 * 
 * @author aaa
 * @date 2021-05-25
 */
public class CasePatient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 患者ID */
    private Long id;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 症状 */
    @Excel(name = "症状")
    private String symptom;

    /** 就诊时间 */
    @Excel(name = "就诊时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date visitDate;

    /** 婚姻状况(0未婚,1已婚) */
    @Excel(name = "婚姻状况(0未婚,1已婚)")
    private String maritalStatus;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 职业 */
    @Excel(name = "职业")
    private String occupation;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String affiliation;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String home;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String telephone;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payment;

    /**  既往病史 */
    @Excel(name = "既往病史")
    private String anamnesis;

    /**  既往病史图片 */
    @Excel(name = "既往病史图片")
    private String anamnesisImg;

    /** 病史陈述者 */
    @Excel(name = "病史陈述者")
    private String declarant;

    /** 患者诊断 */
    @Excel(name = "患者诊断")
    private String diagnosis;

    /** 患者图片 */
    @Excel(name = "患者图片")
    private String avatar;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAnamnesisImg() {
        return anamnesisImg;
    }

    public void setAnamnesisImg(String anamnesisImg) {
        this.anamnesisImg = anamnesisImg;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getDeclarant() {
        return declarant;
    }

    public void setDeclarant(String declarant) {
        this.declarant = declarant;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setSymptom(String symptom) 
    {
        this.symptom = symptom;
    }

    public String getSymptom() 
    {
        return symptom;
    }
    public void setVisitDate(Date visitDate) 
    {
        this.visitDate = visitDate;
    }

    public Date getVisitDate() 
    {
        return visitDate;
    }
    public void setMaritalStatus(String maritalStatus) 
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() 
    {
        return maritalStatus;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setNativePlace(String nativePlace) 
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace() 
    {
        return nativePlace;
    }
    public void setOccupation(String occupation) 
    {
        this.occupation = occupation;
    }

    public String getOccupation() 
    {
        return occupation;
    }
    public void setAffiliation(String affiliation) 
    {
        this.affiliation = affiliation;
    }

    public String getAffiliation() 
    {
        return affiliation;
    }
    public void setHome(String home) 
    {
        this.home = home;
    }

    public String getHome() 
    {
        return home;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setPayment(String payment) 
    {
        this.payment = payment;
    }

    public String getPayment() 
    {
        return payment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("patientName", getPatientName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("symptom", getSymptom())
            .append("visitDate", getVisitDate())
            .append("maritalStatus", getMaritalStatus())
            .append("nation", getNation())
            .append("nativePlace", getNativePlace())
            .append("occupation", getOccupation())
            .append("affiliation", getAffiliation())
            .append("home", getHome())
            .append("telephone", getTelephone())
            .append("payment", getPayment())
            .append("anamnesis", getAnamnesis())
            .append("declarant", getDeclarant())
            .append("anamnesisImg", getAnamnesisImg())
            .append("diagnosis", getDiagnosis())
            .append("avatar", getAvatar())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
