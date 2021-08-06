package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 案例病人和检查项目关联对象 case_patient_item
 * 
 * @author aaa
 * @date 2021-05-27
 */
public class CasePatientItem
{

    /** 案例患者id */
    private Long patientId;

    /** 检查项目id */
    private Long itemId;

    /** 检查记录/结果 */
    @Excel(name = "检查记录/结果")
    private String records;

    /** 检查记录/结果 */
    @Excel(name = "检查记录/结果")
    private String recordsImg;

    /** 临床意义 */
    @Excel(name = "临床意义")
    private String significance;

    public void setPatientId(Long patientId)
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setRecords(String records) 
    {
        this.records = records;
    }

    public String getRecords() 
    {
        return records;
    }
    public void setSignificance(String significance) 
    {
        this.significance = significance;
    }

    public String getRecordsImg() {
        return recordsImg;
    }

    public void setRecordsImg(String recordsImg) {
        this.recordsImg = recordsImg;
    }

    public String getSignificance()
    {
        return significance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("patientId", getPatientId())
            .append("itemId", getItemId())
            .append("records", getRecords())
            .append("recordsImg", getRecordsImg())
            .append("significance", getSignificance())
            .toString();
    }
}
