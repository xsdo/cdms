package com.qx.student.domain;

import com.qx.cases.domain.CasePatientItem;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 体格检查记录对象 tg_check_record
 * 
 * @author aaa
 * @date 2021-06-07
 */
public class TgCheckRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 体格检查记录ID */
    private Long id;

    /** 检查项目ids */
    @Excel(name = "检查项目ids")
    private String itemIds;

    /** 查询字段-患者检查项目结果集合**/
    private List<CasePatientItem> patientItem;

    public List<CasePatientItem> getPatientItem() {
        return patientItem;
    }

    public void setPatientItem(List<CasePatientItem> patientItem) {
        this.patientItem = patientItem;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setItemIds(String itemIds) 
    {
        this.itemIds = itemIds;
    }

    public String getItemIds() 
    {
        return itemIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemIds", getItemIds())
            .toString();
    }
}
