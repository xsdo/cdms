package com.qx.cases.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseQuestion;
import com.qx.cases.domain.CaseQuestionType;
import com.qx.common.utils.DateUtils;
import com.qx.system.domain.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CaseTreatmentMapper;
import com.qx.cases.domain.CaseTreatment;
import com.qx.cases.service.ICaseTreatmentService;

/**
 * 治疗项目Service业务层处理
 * 
 * @author aaa
 * @date 2021-06-01
 */
@Service
public class CaseTreatmentServiceImpl implements ICaseTreatmentService 
{
    @Autowired
    private CaseTreatmentMapper caseTreatmentMapper;

    /**
     * 查询治疗项目
     * 
     * @param treatmentId 治疗项目ID
     * @return 治疗项目
     */
    @Override
    public CaseTreatment selectCaseTreatmentById(Long treatmentId)
    {
        return caseTreatmentMapper.selectCaseTreatmentById(treatmentId);
    }

    /**
     * 查询治疗项目列表
     * 
     * @param caseTreatment 治疗项目
     * @return 治疗项目
     */
    @Override
    public List<CaseTreatment> selectCaseTreatmentList(CaseTreatment caseTreatment)
    {
        return caseTreatmentMapper.selectCaseTreatmentList(caseTreatment);
    }

    /**
     * 新增治疗项目
     * 
     * @param caseTreatment 治疗项目
     * @return 结果
     */
    @Override
    public int insertCaseTreatment(CaseTreatment caseTreatment)
    {
        caseTreatment.setCreateTime(DateUtils.getNowDate());
        return caseTreatmentMapper.insertCaseTreatment(caseTreatment);
    }

    /**
     * 修改治疗项目
     * 
     * @param caseTreatment 治疗项目
     * @return 结果
     */
    @Override
    public int updateCaseTreatment(CaseTreatment caseTreatment)
    {
        caseTreatment.setUpdateTime(DateUtils.getNowDate());
        return caseTreatmentMapper.updateCaseTreatment(caseTreatment);
    }

    /**
     * 批量删除治疗项目
     * 
     * @param treatmentIds 需要删除的治疗项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseTreatmentByIds(Long[] treatmentIds)
    {
        return caseTreatmentMapper.deleteCaseTreatmentByIds(treatmentIds);
    }

    /**
     * 删除治疗项目信息
     * 
     * @param treatmentId 治疗项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseTreatmentById(Long treatmentId)
    {
        return caseTreatmentMapper.deleteCaseTreatmentById(treatmentId);
    }
    /**
     * 构建前端所需要树结构
     *
     * @param treatments 治疗列表
     * @return 树结构列表
     */

    @Override
    public List<CaseTreatment> buildTreatmentTree(List<CaseTreatment> treatments) {
        List<CaseTreatment> returnList = new ArrayList<>();
        for (Iterator<CaseTreatment> iterator = treatments.iterator(); iterator.hasNext();)
        {
            CaseTreatment t = (CaseTreatment) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == 0)
            {
                recursionFn(treatments, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = treatments;
        }
        return returnList;
    }

    private void recursionFn(List<CaseTreatment> list, CaseTreatment t) {
        List<CaseTreatment> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CaseTreatment tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<CaseTreatment> it = childList.iterator();
                while (it.hasNext())
                {
                    CaseTreatment n = (CaseTreatment) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    private boolean hasChild(List<CaseTreatment> list, CaseTreatment tChild) {
        return getChildList(list, tChild).size() > 0 ? true : false;
    }

    private List<CaseTreatment> getChildList(List<CaseTreatment> list, CaseTreatment t) {
        List<CaseTreatment> tlist = new ArrayList<>();
        Iterator<CaseTreatment> it = list.iterator();
        while (it.hasNext())
        {
            CaseTreatment n = (CaseTreatment) it.next();
            if (n.getPid().longValue() == t.getTreatmentId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param treatments 治疗列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildTreatmentTreeSelect(List<CaseTreatment> treatments) {
        List<CaseTreatment> typeTrees = buildTreatmentTree(treatments);
        return typeTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 是否存在子项目          
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean hasChildById(Long id) {
        int result = caseTreatmentMapper.hasChildById(id);
        return result > 0 ? true : false;
    }

    /**
     * 批量查询治疗项目
     * @param ids
     * @return
     */
    @Override
    public List<CaseTreatment> selectTreatmentByIds(Long[] ids) {
        List<CaseTreatment> list = caseTreatmentMapper.selectCaseTreatmentByIds(ids);
        for(CaseTreatment treatment : list){
            recursionFn(treatment);
        }
        return list;
    }

    private void recursionFn(CaseTreatment treatment) {

        if (0 != treatment.getPid()) {
            //获取该类型的父级类型
            CaseTreatment pTreatment = caseTreatmentMapper.selectCaseTreatmentById(treatment.getPid());
            treatment.setpTreatment(pTreatment);
            recursionFn(pTreatment);
        }
    }
}
