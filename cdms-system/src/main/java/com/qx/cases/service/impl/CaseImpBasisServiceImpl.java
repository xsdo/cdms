package com.qx.cases.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CaseImpBasisMapper;
import com.qx.cases.domain.CaseImpBasis;
import com.qx.cases.service.ICaseImpBasisService;

/**
 * 诊断依据Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-28
 */
@Service
public class CaseImpBasisServiceImpl implements ICaseImpBasisService 
{
    @Autowired
    private CaseImpBasisMapper caseImpBasisMapper;

    /**
     * 查询诊断依据
     * 
     * @param id 诊断依据ID
     * @return 诊断依据
     */
    @Override
    public CaseImpBasis selectCaseImpBasisById(Long id)
    {
        return caseImpBasisMapper.selectCaseImpBasisById(id);
    }

    /**
     * 查询诊断依据列表
     * 
     * @param caseImpBasis 诊断依据
     * @return 诊断依据
     */
    @Override
    public List<CaseImpBasis> selectCaseImpBasisList(CaseImpBasis caseImpBasis)
    {
        return caseImpBasisMapper.selectCaseImpBasisList(caseImpBasis);
    }

    /**
     * 新增诊断依据
     * 
     * @param caseImpBasis 诊断依据
     * @return 结果
     */
    @Override
    public int insertCaseImpBasis(CaseImpBasis caseImpBasis)
    {
        caseImpBasis.setCreateTime(DateUtils.getNowDate());
        return caseImpBasisMapper.insertCaseImpBasis(caseImpBasis);
    }

    /**
     * 修改诊断依据
     * 
     * @param caseImpBasis 诊断依据
     * @return 结果
     */
    @Override
    public int updateCaseImpBasis(CaseImpBasis caseImpBasis)
    {
        caseImpBasis.setUpdateTime(DateUtils.getNowDate());
        return caseImpBasisMapper.updateCaseImpBasis(caseImpBasis);
    }

    /**
     * 批量删除诊断依据
     * 
     * @param ids 需要删除的诊断依据ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpBasisByIds(Long[] ids)
    {
        return caseImpBasisMapper.deleteCaseImpBasisByIds(ids);
    }

    /**
     * 删除诊断依据信息
     * 
     * @param id 诊断依据ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpBasisById(Long id)
    {
        return caseImpBasisMapper.deleteCaseImpBasisById(id);
    }
}
