package com.qx.student.domain;

import com.qx.cases.domain.CaseQuestion;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 病史采集记录对象 history_taking_record
 * 
 * @author aaa
 * @date 2021-06-07
 */
public class HistoryTakingRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 问诊问题ids */
    @Excel(name = "问诊问题ids")
    private String questionIds;

    /** 查询字段-问题集合 **/
    private List<CaseQuestion> question;



    public List<CaseQuestion> getQuestion() {
        return question;
    }

    public void setQuestion(List<CaseQuestion> question) {
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
    public void setQuestionIds(String questionIds) 
    {
        this.questionIds = questionIds;
    }

    public String getQuestionIds() 
    {
        return questionIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionIds", getQuestionIds())
            .toString();
    }
}
