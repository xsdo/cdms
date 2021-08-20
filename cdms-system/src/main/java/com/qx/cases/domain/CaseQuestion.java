package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 问诊问答对象 case_question
 * 
 * @author aaa
 * @date 2021-05-25
 */
public class CaseQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 问题 */
    @Excel(name = "案例患者ID")
    private Long patientId;

    /** 问题 */
    @Excel(name = "问题")
    private String question;

    /** 答案 */
    @Excel(name = "答案")
    private String answer;

    /** 所属类型 */
    @Excel(name = "所属类型")
    private Long typeId;

    /** 检查结果 */
    @Excel(name = "检查结果")
    private String result;

    /** 所属类型 */
    private CaseQuestionType type;

    /** 是否核心项目（1 核心项目 2非核心项目 0待定） */
    private Long isCore;

    public Long getIsCore() {
        return isCore;
    }

    public void setIsCore(Long isCore) {
        this.isCore = isCore;
    }

    public CaseQuestionType getType() {
        return type;
    }

    public void setType(CaseQuestionType type) {
        this.type = type;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQuestion(String question) 
    {
        this.question = question;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getQuestion()
    {
        return question;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
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
            .append("id", getId())
            .append("patientId", getPatientId())
            .append("question", getQuestion())
            .append("answer", getAnswer())
            .append("typeId", getTypeId())
            .append("result", getResult())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("isCore", getIsCore())
            .toString();
    }
}
