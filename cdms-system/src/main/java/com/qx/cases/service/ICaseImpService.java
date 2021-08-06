package com.qx.cases.service;

import com.qx.cases.domain.CaseImp;
import java.util.List;

/**
 * 诊断库Service接口
 * 
 * @author aaa
 * @date 2021-06-01
 */
public interface ICaseImpService 
{
    /**
     * 查询诊断库
     * 
     * @param impId 诊断库ID
     * @return 诊断库
     */
    public CaseImp selectCaseImpById(Long impId);

    /**
     * 查询诊断库列表
     * 
     * @param caseImp 诊断库
     * @return 诊断库集合
     */
    public List<CaseImp> selectCaseImpList(CaseImp caseImp);

    /**
     * 新增诊断库
     * 
     * @param caseImp 诊断库
     * @return 结果
     */
    public int insertCaseImp(CaseImp caseImp);

    /**
     * 修改诊断库
     * 
     * @param caseImp 诊断库
     * @return 结果
     */
    public int updateCaseImp(CaseImp caseImp);

    /**
     * 批量删除诊断库
     * 
     * @param impIds 需要删除的诊断库ID
     * @return 结果
     */
    public int deleteCaseImpByIds(Long[] impIds);

    /**
     * 删除诊断库信息
     * 
     * @param impId 诊断库ID
     * @return 结果
     */
    public int deleteCaseImpById(Long impId);
}
