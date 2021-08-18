package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学习项目对象 case_imp_learning
 * 
 * @author aaa
 * @date 2021-08-16
 */
public class CaseImpLearning extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 诊断ID */
    private Long learningId;

    /** 诊断名称 */
    @Excel(name = "诊断名称")
    private String learningName;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long pid;

    /** 诊断信息 */
    @Excel(name = "诊断信息")
    private String result;

    private List<CaseImpLearning> children = new ArrayList<CaseImpLearning>();

    /** 父级治疗项目 **/
    private CaseImpLearning pLearning;

    public List<CaseImpLearning> getChildren() {
        return children;
    }

    public void setChildren(List<CaseImpLearning> children) {
        this.children = children;
    }

    public CaseImpLearning getpLearning() {
        return pLearning;
    }

    public void setpLearning(CaseImpLearning pLearning) {
        this.pLearning = pLearning;
    }

    public void setLearningId(Long learningId)
    {
        this.learningId = learningId;
    }

    public Long getLearningId() 
    {
        return learningId;
    }
    public void setLearningName(String learningName) 
    {
        this.learningName = learningName;
    }

    public String getLearningName() 
    {
        return learningName;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("learningId", getLearningId())
            .append("learningName", getLearningName())
            .append("pid", getPid())
            .append("result", getResult())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
