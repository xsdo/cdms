package com.qx.cases.service;

import com.qx.cases.domain.CaseTreatment;
import com.qx.system.domain.TreeSelect;

import java.util.List;

/**
 * 治疗项目Service接口
 * 
 * @author aaa
 * @date 2021-06-01
 */
public interface ICaseTreatmentService 
{
    /**
     * 查询治疗项目
     * 
     * @param treatmentId 治疗项目ID
     * @return 治疗项目
     */
    public CaseTreatment selectCaseTreatmentById(Long treatmentId);

    /**
     * 查询治疗项目列表
     * 
     * @param caseTreatment 治疗项目
     * @return 治疗项目集合
     */
    public List<CaseTreatment> selectCaseTreatmentList(CaseTreatment caseTreatment);

    /**
     * 新增治疗项目
     * 
     * @param caseTreatment 治疗项目
     * @return 结果
     */
    public int insertCaseTreatment(CaseTreatment caseTreatment);

    /**
     * 修改治疗项目
     * 
     * @param caseTreatment 治疗项目
     * @return 结果
     */
    public int updateCaseTreatment(CaseTreatment caseTreatment);

    /**
     * 批量删除治疗项目
     * 
     * @param treatmentIds 需要删除的治疗项目ID
     * @return 结果
     */
    public int deleteCaseTreatmentByIds(Long[] treatmentIds);

    /**
     * 删除治疗项目信息
     * 
     * @param treatmentId 治疗项目ID
     * @return 结果
     */
    public int deleteCaseTreatmentById(Long treatmentId);

    /**
     * 构建前端所需要树结构
     *
     * @param treatments 治疗列表
     * @return 树结构列表
     */
    List<CaseTreatment> buildTreatmentTree(List<CaseTreatment> treatments);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param treatments 治疗列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildTreatmentTreeSelect(List<CaseTreatment> treatments);

    /**
     * 是否存在子项目
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildById(Long id);

    /**
     * 根据ids批量查询治疗项目
     * @param ids
     * @return
     */
    List<CaseTreatment> selectTreatmentByIds(Long[] ids);
}
