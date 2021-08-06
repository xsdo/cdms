package com.qx.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 项目对象 app_project
 * 
 * @author patient
 * @date 2020-03-03
 */
public class AppProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectSn;

    /** 项目来源 */
    @Excel(name = "项目来源")
    private String projectSource;

    /** 招标方式:(公开招标(0)、邀标(1)、单一来源(2)) */
    @Excel(name = "招标方式:(公开招标(0)、邀标(1)、单一来源(2))")
    private String biddingType;

    /** 费用来源 */
    @Excel(name = "费用来源")
    private String costSource;

    /** 项目类型(工程项目(0)、采购类项目(1)、基建项目(2)) */
    @Excel(name = "项目类型(工程项目(0)、采购类项目(1)、基建项目(2))")
    private String projectType;

    /** 采购方式(集中采购(0)、分散采购(1)) */
    @Excel(name = "采购方式(集中采购(0)、分散采购(1))")
    private String purchaseType;

    /** 概况 */
    @Excel(name = "概况")
    private String generalSituation;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private Long deptId;

    private String deptName;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long dutyUserId;

    private String userName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 预算费用 */
    @Excel(name = "预算费用")
    private String budgetMoney;

    /** 验收文档 */
    @Excel(name = "验收文档")
    private String checkFile;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 立项时间 */
    @Excel(name = "立项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectTime;

    /** 预计完成时间 */
    @Excel(name = "预计完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date predictTime;

    /** 状态（0进行中、1已验收） */
    @Excel(name = "状态", readConverterExp = "0=进行中、1已验收")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectSn(String projectSn) 
    {
        this.projectSn = projectSn;
    }

    public String getProjectSn() 
    {
        return projectSn;
    }
    public void setProjectSource(String projectSource) 
    {
        this.projectSource = projectSource;
    }

    public String getProjectSource() 
    {
        return projectSource;
    }
    public void setBiddingType(String biddingType) 
    {
        this.biddingType = biddingType;
    }

    public String getBiddingType() 
    {
        return biddingType;
    }
    public void setCostSource(String costSource) 
    {
        this.costSource = costSource;
    }

    public String getCostSource() 
    {
        return costSource;
    }
    public void setProjectType(String projectType) 
    {
        this.projectType = projectType;
    }

    public String getProjectType() 
    {
        return projectType;
    }
    public void setPurchaseType(String purchaseType) 
    {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseType() 
    {
        return purchaseType;
    }
    public void setGeneralSituation(String generalSituation) 
    {
        this.generalSituation = generalSituation;
    }

    public String getGeneralSituation() 
    {
        return generalSituation;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDutyUserId(Long dutyUserId) 
    {
        this.dutyUserId = dutyUserId;
    }

    public Long getDutyUserId() 
    {
        return dutyUserId;
    }
    public void setBudgetMoney(String budgetMoney) 
    {
        this.budgetMoney = budgetMoney;
    }

    public String getBudgetMoney() 
    {
        return budgetMoney;
    }
    public void setCheckFile(String checkFile) 
    {
        this.checkFile = checkFile;
    }

    public String getCheckFile() 
    {
        return checkFile;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setProjectTime(Date projectTime) 
    {
        this.projectTime = projectTime;
    }

    public Date getProjectTime() 
    {
        return projectTime;
    }
    public void setPredictTime(Date predictTime) 
    {
        this.predictTime = predictTime;
    }

    public Date getPredictTime() 
    {
        return predictTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("projectSn", getProjectSn())
            .append("projectSource", getProjectSource())
            .append("biddingType", getBiddingType())
            .append("costSource", getCostSource())
            .append("projectType", getProjectType())
            .append("purchaseType", getPurchaseType())
            .append("generalSituation", getGeneralSituation())
            .append("deptId", getDeptId())
            .append("dutyUserId", getDutyUserId())
            .append("budgetMoney", getBudgetMoney())
            .append("checkFile", getCheckFile())
            .append("orderNum", getOrderNum())
            .append("projectTime", getProjectTime())
            .append("predictTime", getPredictTime())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
