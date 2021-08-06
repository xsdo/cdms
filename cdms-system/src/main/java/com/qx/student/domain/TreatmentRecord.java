package com.qx.student.domain;

import com.qx.cases.domain.CaseTreatment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 治疗记录对象 treatment_record
 * 
 * @author aaa
 * @date 2021-06-08
 */
public class TreatmentRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 治疗记录ID */
    private Long id;

    /** 治疗内容ids */
    @Excel(name = "治疗内容ids")
    private String treatmentIds;

    /** 查询字段-治疗项目集合**/
    private List<CaseTreatment> treatment;

    public List<CaseTreatment> getTreatment() {
        return treatment;
    }

    public void setTreatment(List<CaseTreatment> treatment) {
        this.treatment = treatment;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTreatmentIds(String treatmentIds) 
    {
        this.treatmentIds = treatmentIds;
    }

    public String getTreatmentIds() 
    {
        return treatmentIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("treatmentIds", getTreatmentIds())
            .toString();
    }
}
