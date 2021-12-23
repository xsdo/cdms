package com.qx.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 论坛对象 case_forum
 * 
 * @author aaa
 * @date 2021-12-20
 */
public class CaseForum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 帖子id */
    private Long id;

    /** 帖子主题 */
    @Excel(name = "帖子主题")
    private String theme;

    /** 帖子内容 */
    @Excel(name = "帖子内容")
    private String content;

    /** 评论所属 */
    @Excel(name = "评论所属")
    private Long pid;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 所属类型（1学生论坛2教师答疑） */
    @Excel(name = "所属类型", readConverterExp = "1=学生论坛2教师答疑")
    private Integer forumType;

    /** 点赞 */
    @Excel(name = "点赞")
    private Long agreeNumber;

    /** 收藏 */
    @Excel(name = "收藏")
    private Long collectNumber;

    /** 0未删除1删除 */
    @Excel(name = "0未删除1删除")
    private Integer isDel;

    private List<CaseForum> caseForumList;

    public List<CaseForum> getCaseForumList() {
        return caseForumList;
    }

    public void setCaseForumList(List<CaseForum> caseForumList) {
        this.caseForumList = caseForumList;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTheme(String theme) 
    {
        this.theme = theme;
    }

    public String getTheme() 
    {
        return theme;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setForumType(Integer forumType) 
    {
        this.forumType = forumType;
    }

    public Integer getForumType() 
    {
        return forumType;
    }
    public void setAgreeNumber(Long agreeNumber) 
    {
        this.agreeNumber = agreeNumber;
    }

    public Long getAgreeNumber() 
    {
        return agreeNumber;
    }
    public void setCollectNumber(Long collectNumber) 
    {
        this.collectNumber = collectNumber;
    }

    public Long getCollectNumber() 
    {
        return collectNumber;
    }
    public void setIsDel(Integer isDel) 
    {
        this.isDel = isDel;
    }

    public Integer getIsDel() 
    {
        return isDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("theme", getTheme())
            .append("content", getContent())
            .append("pid", getPid())
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("forumType", getForumType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("agreeNumber", getAgreeNumber())
            .append("collectNumber", getCollectNumber())
            .append("isDel", getIsDel())
            .append("remark", getRemark())
            .toString();
    }
}
