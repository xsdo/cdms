package com.qx.system.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CaseImpDiagnosis;
import com.qx.cases.domain.CaseQuestionType;
import com.qx.cases.domain.CaseTreatment;
import com.qx.system.domain.AppCategory;
import com.qx.system.domain.AppRegion;


/**
 * Treeselect树结构实体类
 *
 * @author patient
 */
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 层级 */
    private String level;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect() {

    }

    public TreeSelect(AppCategory appCategory)
    {
        this.id = appCategory.getId();
        this.label = appCategory.getName();
        this.children = appCategory.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    public TreeSelect(AppRegion appRegion)
    {
        this.id = appRegion.getId();
        this.label = appRegion.getName();
        this.children = appRegion.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysDept dept)
    {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu menu)
    {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(CaseQuestionType type)
    {
        this.id = type.getId();
        this.label = type.getTypeName();
        this.children = type.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());

    }
    public TreeSelect(CaseCheckItem item)
    {
        this.id = item.getItemId();
        this.label = item.getItemName();
        this.children = item.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(CaseTreatment treatment) {
        this.id = treatment.getTreatmentId();
        this.label = treatment.getTreatmentName();
        this.children = treatment.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());

    }

    public TreeSelect(CaseImpDiagnosis imp) {
        this.id = imp.getImpId();
        this.label = imp.getImpName();
        this.children = imp.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }
}
