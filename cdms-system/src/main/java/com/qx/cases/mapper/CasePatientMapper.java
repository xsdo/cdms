package com.qx.cases.mapper;

import com.qx.cases.domain.CasePatient;
import java.util.List;

/**
 * 案例患者Mapper接口
 * 
 * @author aaa
 * @date 2021-05-25
 */
public interface CasePatientMapper 
{
    /**
     * 查询案例患者
     * 
     * @param id 案例患者ID
     * @return 案例患者
     */
    public CasePatient selectCasePatientById(Long id);

    /**
     * 查询案例患者列表
     * 
     * @param casePatient 案例患者
     * @return 案例患者集合
     */
    public List<CasePatient> selectCasePatientList(CasePatient casePatient);

    /**
     * 新增案例患者
     * 
     * @param casePatient 案例患者
     * @return 结果
     */
    public int insertCasePatient(CasePatient casePatient);

    /**
     * 修改案例患者
     * 
     * @param casePatient 案例患者
     * @return 结果
     */
    public int updateCasePatient(CasePatient casePatient);

    /**
     * 删除案例患者
     * 
     * @param id 案例患者ID
     * @return 结果
     */
    public int deleteCasePatientById(Long id);

    /**
     * 批量删除案例患者
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCasePatientByIds(Long[] ids);
}
