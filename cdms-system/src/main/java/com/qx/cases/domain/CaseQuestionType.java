package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 问诊问答类型对象 case_question_type
 * 
 * @author aaa
 * @date 2021-05-25
 */
public class CaseQuestionType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 类型父级id */
    @Excel(name = "类型父级id")
    private Long pid;

    /** 子类型 */
    private List<CaseQuestionType> children = new ArrayList<CaseQuestionType>();

    public CaseQuestionType pType;


    public CaseQuestionType getpType() {
        return pType;
    }

    public void setpType(CaseQuestionType pType) {
        this.pType = pType;
    }

    public List<CaseQuestionType> getChildren() {
        return children;
    }

    public void setChildren(List<CaseQuestionType> children) {
        this.children = children;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }
    @NotBlank(message = "类型名称不能为空")
    @Size(min = 0, max = 30, message = "类型名称长度不能超过30个字符")
    public String getTypeName() 
    {
        return typeName;
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
            .append("id", getId())
            .append("typeName", getTypeName())
            .append("pid", getPid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
