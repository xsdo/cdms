package com.qx.cases.domain;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 治疗项目对象 case_imp_diagnosis
 * 

 */
public class CaseImpDiagnosis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 诊断ID */
    private Long impId;

    /** 诊断名称 */
    @Excel(name = "诊断名称")
    private String impName;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long pid;

    private List<CaseImpDiagnosis> children = new ArrayList<CaseImpDiagnosis>();

    /** 父级治疗项目 **/
    private CaseImpDiagnosis pimp;

    public CaseImpDiagnosis getPimp() {
        return pimp;
    }

    public void setPimp(CaseImpDiagnosis pimp) {
        this.pimp = pimp;
    }

    public List<CaseImpDiagnosis> getChildren() {
        return children;
    }

    public void setChildren(List<CaseImpDiagnosis> children) {
        this.children = children;
    }

    public Long getImpId() {
        return impId;
    }

    public void setImpId(Long impId) {
        this.impId = impId;
    }

    public String getImpName() {
        return impName;
    }

    public void setImpName(String impName) {
        this.impName = impName;
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
            .append("impId", getImpId())
            .append("impName", getImpName())
            .append("pid", getPid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
