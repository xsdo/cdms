package com.qx.student.domain;

import com.qx.cases.domain.CaseQuestion;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 病史采集问题支持记录对象 history_support_record
 * 
 * @author aaa
 * @date 2021-06-17
 */

public class HistorySupportRecord implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 病史采集记录ID */
    @Excel(name = "病史采集记录ID")
    private Long historyRecordId;

    /** 问题ID */
    @Excel(name = "问题ID")
    private Long questionId;

    /** 支持状态（0支持 1不支持） */
    @Excel(name = "支持状态", readConverterExp = "0=支持,1=不支持")
    private String support;

    private CaseQuestion question;

    public CaseQuestion getQuestion() {
        return question;
    }

    public void setQuestion(CaseQuestion question) {
        this.question = question;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHistoryRecordId(Long historyRecordId) 
    {
        this.historyRecordId = historyRecordId;
    }

    public Long getHistoryRecordId() 
    {
        return historyRecordId;
    }
    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }
    public void setSupport(String support) 
    {
        this.support = support;
    }

    public String getSupport() 
    {
        return support;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("historyRecordId", getHistoryRecordId())
            .append("questionId", getQuestionId())
            .append("support", getSupport())
            .toString();
    }
}
