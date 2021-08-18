package com.qx.cases.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseTreatment;
import com.qx.common.utils.DateUtils;
import com.qx.system.domain.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CaseImpLearningMapper;
import com.qx.cases.domain.CaseImpLearning;
import com.qx.cases.service.ICaseImpLearningService;

/**
 * 学习项目Service业务层处理
 * 
 * @author aaa
 * @date 2021-08-16
 */
@Service
public class CaseImpLearningServiceImpl implements ICaseImpLearningService 
{
    @Autowired
    private CaseImpLearningMapper caseImpLearningMapper;

    /**
     * 查询学习项目
     * 
     * @param learningId 学习项目ID
     * @return 学习项目
     */
    @Override
    public CaseImpLearning selectCaseImpLearningById(Long learningId)
    {
        return caseImpLearningMapper.selectCaseImpLearningById(learningId);
    }

    /**
     * 查询学习项目列表
     * 
     * @param caseImpLearning 学习项目
     * @return 学习项目
     */
    @Override
    public List<CaseImpLearning> selectCaseImpLearningList(CaseImpLearning caseImpLearning)
    {
        return caseImpLearningMapper.selectCaseImpLearningList(caseImpLearning);
    }

    /**
     * 新增学习项目
     * 
     * @param caseImpLearning 学习项目
     * @return 结果
     */
    @Override
    public int insertCaseImpLearning(CaseImpLearning caseImpLearning)
    {
        caseImpLearning.setCreateTime(DateUtils.getNowDate());
        return caseImpLearningMapper.insertCaseImpLearning(caseImpLearning);
    }

    /**
     * 修改学习项目
     * 
     * @param caseImpLearning 学习项目
     * @return 结果
     */
    @Override
    public int updateCaseImpLearning(CaseImpLearning caseImpLearning)
    {
        caseImpLearning.setUpdateTime(DateUtils.getNowDate());
        return caseImpLearningMapper.updateCaseImpLearning(caseImpLearning);
    }

    /**
     * 批量删除学习项目
     * 
     * @param learningIds 需要删除的学习项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpLearningByIds(Long[] learningIds)
    {
        return caseImpLearningMapper.deleteCaseImpLearningByIds(learningIds);
    }

    /**
     * 删除学习项目信息
     * 
     * @param learningId 学习项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseImpLearningById(Long learningId)
    {
        return caseImpLearningMapper.deleteCaseImpLearningById(learningId);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param
     * @return 树结构列表
     */

    @Override
    public List<CaseImpLearning> buildLearningTree(List<CaseImpLearning> learnings) {
        List<CaseImpLearning> returnList = new ArrayList<>();
        for (Iterator<CaseImpLearning> iterator = learnings.iterator(); iterator.hasNext();)
        {
            CaseImpLearning t = (CaseImpLearning) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == 0)
            {
                recursionFn(learnings, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = learnings;
        }
        return returnList;
    }

    private void recursionFn(List<CaseImpLearning> list, CaseImpLearning t) {
        List<CaseImpLearning> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CaseImpLearning tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<CaseImpLearning> it = childList.iterator();
                while (it.hasNext())
                {
                    CaseImpLearning n = (CaseImpLearning) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    private boolean hasChild(List<CaseImpLearning> list, CaseImpLearning tChild) {
        return getChildList(list, tChild).size() > 0 ? true : false;
    }

    private List<CaseImpLearning> getChildList(List<CaseImpLearning> list, CaseImpLearning t) {
        List<CaseImpLearning> tlist = new ArrayList<>();
        Iterator<CaseImpLearning> it = list.iterator();
        while (it.hasNext())
        {
            CaseImpLearning n = (CaseImpLearning) it.next();
            if (n.getPid().longValue() == t.getLearningId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildLearningTreeSelect(List<CaseImpLearning> learnings) {
        List<CaseImpLearning> typeTrees = buildLearningTree(learnings);
        return typeTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 是否存在子项目
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean hasChildById(Long id) {
        int result = caseImpLearningMapper.hasChildById(id);
        return result > 0 ? true : false;
    }

    /**
     * 批量查询治疗项目
     * @param ids
     * @return
     */
    @Override
    public List<CaseImpLearning> selectTreatmentByIds(Long[] ids) {
        List<CaseImpLearning> list = caseImpLearningMapper.selectCaseTreatmentByIds(ids);
        for(CaseImpLearning treatment : list){
            recursionFn(treatment);
        }
        return list;
    }

    private void recursionFn(CaseImpLearning learning) {

        if (0 != learning.getPid()) {
            //获取该类型的父级类型
            CaseImpLearning pTreatment = caseImpLearningMapper.selectCaseImpLearningById(learning.getPid());
            learning.setpLearning(pTreatment);
            recursionFn(pTreatment);
        }
    }
}
