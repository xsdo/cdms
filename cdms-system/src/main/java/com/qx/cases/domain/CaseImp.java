package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 诊断库对象 case_imp
 * 
 * @author aaa
 * @date 2021-06-01
 */
public class CaseImp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 诊断ID */
    private Long impId;

    /** 诊断名称 */
    @Excel(name = "诊断名称")
    private String impName;

    /** 诊断依据 */
    @Excel(name = "诊断依据")
    private String impBasis;

    public void setImpId(Long impId) 
    {
        this.impId = impId;
    }

    public Long getImpId() 
    {
        return impId;
    }
    public void setImpName(String impName) 
    {
        this.impName = impName;
    }

    public String getImpName() 
    {
        return impName;
    }
    public void setImpBasis(String impBasis) 
    {
        this.impBasis = impBasis;
    }

    public String getImpBasis() 
    {
        return impBasis;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("impId", getImpId())
            .append("impName", getImpName())
            .append("impBasis", getImpBasis())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
