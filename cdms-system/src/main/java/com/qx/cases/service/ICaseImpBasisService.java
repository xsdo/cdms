package com.qx.cases.service;

import com.qx.cases.domain.CaseImpBasis;
import java.util.List;

/**
 * 诊断依据Service接口
 * 
 * @author aaa
 * @date 2021-06-28
 */
public interface ICaseImpBasisService 
{
    /**
     * 查询诊断依据
     * 
     * @param id 诊断依据ID
     * @return 诊断依据
     */
    public CaseImpBasis selectCaseImpBasisById(Long id);

    /**
     * 查询诊断依据列表
     * 
     * @param caseImpBasis 诊断依据
     * @return 诊断依据集合
     */
    public List<CaseImpBasis> selectCaseImpBasisList(CaseImpBasis caseImpBasis);

    /**
     * 新增诊断依据
     * 
     * @param caseImpBasis 诊断依据
     * @return 结果
     */
    public int insertCaseImpBasis(CaseImpBasis caseImpBasis);

    /**
     * 修改诊断依据
     * 
     * @param caseImpBasis 诊断依据
     * @return 结果
     */
    public int updateCaseImpBasis(CaseImpBasis caseImpBasis);

    /**
     * 批量删除诊断依据
     * 
     * @param ids 需要删除的诊断依据ID
     * @return 结果
     */
    public int deleteCaseImpBasisByIds(Long[] ids);

    /**
     * 删除诊断依据信息
     * 
     * @param id 诊断依据ID
     * @return 结果
     */
    public int deleteCaseImpBasisById(Long id);
}
