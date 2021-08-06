package com.qx.cases.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CaseImpMapper;
import com.qx.cases.domain.CaseImp;
import com.qx.cases.service.ICaseImpService;

/**
 * 诊断库Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-01
 */
@Service
public class CaseImpServiceImpl implements ICaseImpService 
{
    @Autowired
    private CaseImpMapper caseImpMapper;

    /**
     * 查询诊断库
     * 
     * @param impId 诊断库ID
     * @return 诊断库
     */
    @Override
    public CaseImp selectCaseImpById(Long impId)
    {
        return caseImpMapper.selectCaseImpById(impId);
    }

    /**
     * 查询诊断库列表
     * 
     * @param caseImp 诊断库
     * @return 诊断库
     */
    @Override
    public List<CaseImp> selectCaseImpList(CaseImp caseImp)
    {
        return caseImpMapper.selectCaseImpList(caseImp);
    }

    /**
     * 新增诊断库
     * 
     * @param caseImp 诊断库
     * @return 结果
     */
    @Override
    public int insertCaseImp(CaseImp caseImp)
    {
        caseImp.setCreateTime(DateUtils.getNowDate());
        return caseImpMapper.insertCaseImp(caseImp);
    }

    /**
     * 修改诊断库
     * 
     * @param caseImp 诊断库
     * @return 结果
     */
    @Override
    public int updateCaseImp(CaseImp caseImp)
    {
        caseImp.setUpdateTime(DateUtils.getNowDate());
        return caseImpMapper.updateCaseImp(caseImp);
    }

    /**
     * 批量删除诊断库
     * 
     * @param impIds 需要删除的诊断库ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpByIds(Long[] impIds)
    {
        return caseImpMapper.deleteCaseImpByIds(impIds);
    }

    /**
     * 删除诊断库信息
     * 
     * @param impId 诊断库ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpById(Long impId)
    {
        return caseImpMapper.deleteCaseImpById(impId);
    }
}
