package com.qx.cases.service.impl;



import com.qx.cases.domain.CaseImpDiagnosis;
import com.qx.cases.mapper.CaseImpDiagnosisMapper;
import com.qx.cases.service.ICaseImpDiagnosisService;
import com.qx.common.utils.DateUtils;
import com.qx.system.domain.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 治疗项目Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-01
 */
@Service
public class CaseImpDiagnosisServiceImpl implements ICaseImpDiagnosisService
{
    @Autowired
    private CaseImpDiagnosisMapper caseImpDiagnosisMapper;


    /**
     * 查询治疗项目
     * 
     * @param impId 治疗项目ID
     * @return 治疗项目
     */
    @Override
    public CaseImpDiagnosis selectCaseImpDiagnosisById(Long impId)
    {
        return caseImpDiagnosisMapper.selectCaseImpDiagnosisById(impId);
    }

    /**
     * 查询治疗项目列表
     * 
     * @param caseImpDiagnosis 治疗项目
     * @return 治疗项目
     */
    @Override
    public List<CaseImpDiagnosis> selectCaseImpDiagnosisList(CaseImpDiagnosis caseImpDiagnosis)
    {
        return caseImpDiagnosisMapper.selectCaseImpDiagnosisList(caseImpDiagnosis) ;
    }

    @Override
    public void getImpChildId(List<Long> childIds ,Long impId){
        if (this.hasChildById(impId)){
            List<CaseImpDiagnosis>caseImpDiagnoses=caseImpDiagnosisMapper.selectCaseImpDiagnosisByPid(impId);
            for (CaseImpDiagnosis caseImpDiagnosis :caseImpDiagnoses){
                if (this.hasChildById(caseImpDiagnosis.getImpId())){
                    this.getImpChildId(childIds,caseImpDiagnosis.getImpId());
                }else {
                    childIds.add(caseImpDiagnosis.getImpId());
                }
            }
        }else {
            childIds.add(impId);
        }

    }
    /**
     * 新增治疗项目
     * 
     * @param caseImpDiagnosis 治疗项目
     * @return 结果
     */
    @Override
    public int insertCaseImpDiagnosis(CaseImpDiagnosis caseImpDiagnosis)
    {
        caseImpDiagnosis.setCreateTime(DateUtils.getNowDate());
        return caseImpDiagnosisMapper.insertCaseImpDiagnosis(caseImpDiagnosis);
    }

    /**
     * 修改治疗项目
     * 
     * @param caseImpDiagnosis 治疗项目
     * @return 结果
     */
    @Override
    public int updateCaseImpDiagnosis(CaseImpDiagnosis caseImpDiagnosis)
    {
        caseImpDiagnosis.setUpdateTime(DateUtils.getNowDate());
        return caseImpDiagnosisMapper.updateCaseImpDiagnosis(caseImpDiagnosis);
    }

    /**
     * 批量删除治疗项目
     * 
     * @param impIds 需要删除的治疗项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpDiagnosisByIds(Long[] impIds)
    {
        return caseImpDiagnosisMapper.deleteCaseImpDiagnosisByIds(impIds);
    }

    /**
     * 删除治疗项目信息
     * 
     * @param impId 治疗项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpDiagnosisById(Long impId)
    {
        return caseImpDiagnosisMapper.deleteCaseImpDiagnosisById(impId);
    }
    /**
     * 构建前端所需要树结构
     *
     * @param imps 治疗列表
     * @return 树结构列表
     */

    @Override
    public List<CaseImpDiagnosis> buildImpTree(List<CaseImpDiagnosis> imps) {

        List<CaseImpDiagnosis> returnList = new ArrayList<>();

        for (Iterator<CaseImpDiagnosis> iterator = imps.iterator(); iterator.hasNext();)
        {
            CaseImpDiagnosis t = (CaseImpDiagnosis) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == 0)
            {
                recursionFn(imps, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = imps;
        }
        return returnList;
    }

    private void recursionFn(List<CaseImpDiagnosis> list, CaseImpDiagnosis t) {
        List<CaseImpDiagnosis> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CaseImpDiagnosis tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<CaseImpDiagnosis> it = childList.iterator();
                while (it.hasNext())
                {
                    CaseImpDiagnosis n = (CaseImpDiagnosis) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    private boolean hasChild(List<CaseImpDiagnosis> list, CaseImpDiagnosis tChild) {
        return getChildList(list, tChild).size() > 0 ? true : false;
    }

    private List<CaseImpDiagnosis> getChildList(List<CaseImpDiagnosis> list, CaseImpDiagnosis t) {
        List<CaseImpDiagnosis> tlist = new ArrayList<>();
        Iterator<CaseImpDiagnosis> it = list.iterator();
        while (it.hasNext())
        {
            CaseImpDiagnosis n = (CaseImpDiagnosis) it.next();
            if (n.getPid().longValue() == t.getImpId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param imps 治疗列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildImpTreeSelect(List<CaseImpDiagnosis> imps) {
        List<CaseImpDiagnosis> typeTrees = buildImpTree(imps);
        return typeTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    /**
     * 是否存在子项目          
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean hasChildById(Long id) {
        int result = caseImpDiagnosisMapper.hasChildById(id);
        return result > 0 ? true : false;
    }

    /**
     * 批量查询治疗项目
     * @param ids
     * @return
     */
    @Override
    public List<CaseImpDiagnosis> selectImpByIds(Long[] ids) {
        List<CaseImpDiagnosis> list = caseImpDiagnosisMapper.selectCaseImpDiagnosisByIds(ids);
        for(CaseImpDiagnosis imp : list){
            recursionFn(imp);
        }
        return list;
    }

    private void recursionFn(CaseImpDiagnosis imp) {

        if (0 != imp.getPid()) {
            //获取该类型的父级类型
            CaseImpDiagnosis pimp = caseImpDiagnosisMapper.selectCaseImpDiagnosisById(imp.getPid());
            imp.setPimp(pimp);
            recursionFn(pimp);
        }
    }
}
