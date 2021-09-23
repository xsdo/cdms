package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.ibatis.annotations.Case;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 检查项目对象 case_check_item
 * 
 * @author aaa
 * @date 2021-05-26
 */
public class CaseCheckItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long itemId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;

    /** 父id */
    @Excel(name = "父id")
    private Long pid;

    /** 是否混杂项（0非 1混杂项） */
    @Excel(name = "检查类型", readConverterExp = "0非 1混杂项")
    private String isMix;

    /** 检查类型（0体格检查 1精神状况检查 2辅助检查） */
    @Excel(name = "检查类型", readConverterExp = "0=体格检查,1=精神状况检查,2=辅助检查")
    private String category;

    private List<CaseCheckItem> children = new ArrayList<CaseCheckItem>();

    private CasePatientItem patientItem = new CasePatientItem();


    public CasePatientItem getPatientItem() {
        return patientItem;
    }

    public void setPatientItem(CasePatientItem patientItem) {
        this.patientItem = patientItem;
    }

    public List<CaseCheckItem> getChildren() {
        return children;
    }

    public void setChildren(List<CaseCheckItem> children) {
        this.children = children;
    }

    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    @NotBlank(message = "项目名称不能为空")
    @Size(min = 0, max = 30, message = "项目名称长度不能超过30个字符")
    public String getItemName() 
    {
        return itemName;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }

    public String getIsMix() {
        return isMix;
    }

    public void setIsMix(String isMix) {
        this.isMix = isMix;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId", getItemId())
            .append("itemName", getItemName())
            .append("pid", getPid())
            .append("isMix", getIsMix())
            .append("category", getCategory())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
