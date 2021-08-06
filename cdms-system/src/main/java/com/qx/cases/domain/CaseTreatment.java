package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 治疗项目对象 case_treatment
 * 
 * @author aaa
 * @date 2021-06-01
 */
public class CaseTreatment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 治疗项目ID */
    private Long treatmentId;

    /** 治疗项目名称 */
    @Excel(name = "治疗项目名称")
    private String treatmentName;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long pid;

    private List<CaseTreatment> children = new ArrayList<CaseTreatment>();

    /** 父级治疗项目 **/
    private CaseTreatment pTreatment;


    public CaseTreatment getpTreatment() {
        return pTreatment;
    }

    public void setpTreatment(CaseTreatment pTreatment) {
        this.pTreatment = pTreatment;
    }

    public List<CaseTreatment> getChildren() {
        return children;
    }

    public void setChildren(List<CaseTreatment> children) {
        this.children = children;
    }

    public void setTreatmentId(Long treatmentId)
    {
        this.treatmentId = treatmentId;
    }

    public Long getTreatmentId() 
    {
        return treatmentId;
    }
    public void setTreatmentName(String treatmentName) 
    {
        this.treatmentName = treatmentName;
    }

    public String getTreatmentName() 
    {
        return treatmentName;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("treatmentId", getTreatmentId())
            .append("treatmentName", getTreatmentName())
            .append("pid", getPid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
