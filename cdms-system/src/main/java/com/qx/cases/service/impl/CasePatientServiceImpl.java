package com.qx.cases.service.impl;

import java.util.List;

import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CasePatientMapper;
import com.qx.cases.domain.CasePatient;
import com.qx.cases.service.ICasePatientService;

/**
 * 案例患者Service业务层处理
 * 
 * @author aaa
 * @date 2021-05-25
 */
@Service
public class CasePatientServiceImpl implements ICasePatientService 
{
    @Autowired
    private CasePatientMapper casePatientMapper;

    @Autowired
    private CasePatientItemMapper casePatientItemMapper;



    /**
     * 查询案例患者
     * 
     * @param id 案例患者ID
     * @return 案例患者
     */
    @Override
    public CasePatient selectCasePatientById(Long id)
    {
        return casePatientMapper.selectCasePatientById(id);
    }

    /**
     * 查询案例患者列表
     * 
     * @param casePatient 案例患者
     * @return 案例患者
     */
    @Override
    public List<CasePatient> selectCasePatientList(CasePatient casePatient)
    {
        return casePatientMapper.selectCasePatientList(casePatient);
    }

    /**
     * 新增案例患者
     * 
     * @param casePatient 案例患者
     * @return 结果
     */
    @Override
    public int insertCasePatient(CasePatient casePatient)
    {
        casePatient.setCreateTime(DateUtils.getNowDate());
        return casePatientMapper.insertCasePatient(casePatient);
    }

    /**
     * 修改案例患者
     * 
     * @param casePatient 案例患者
     * @return 结果
     */
    @Override
    public int updateCasePatient(CasePatient casePatient)
    {
        casePatient.setUpdateTime(DateUtils.getNowDate());
        return casePatientMapper.updateCasePatient(casePatient);
    }

    /**
     * 批量删除案例患者
     * 
     * @param ids 需要删除的案例患者ID
     * @return 结果
     */
    @Override
    public int deleteCasePatientByIds(Long[] ids)
    {
        return casePatientMapper.deleteCasePatientByIds(ids);
    }

    /**
     * 删除案例患者信息
     * 
     * @param id 案例患者ID
     * @return 结果
     */
    @Override
    public int deleteCasePatientById(Long id)
    {
        return casePatientMapper.deleteCasePatientById(id);
    }
}
