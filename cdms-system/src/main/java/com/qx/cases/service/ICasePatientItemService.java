package com.qx.cases.service;

import com.qx.cases.domain.CasePatientItem;
import java.util.List;

/**
 * 案例病人和检查项目关联Service接口
 * 
 * @author aaa
 * @date 2021-05-27
 */
public interface ICasePatientItemService 
{
    /**
     * 查询某案例下的某项目
     * @param patientId
     * @param itemId
     * @return
     */
    public CasePatientItem selectCasePatientItemById(Long patientId,Long itemId);

    /**
     * 查询某案例下的某项目(核心)
     * @param patientId
     * @param itemId
     * @return
     */
    CasePatientItem selectCasePatientItemByCore(Long patientId, Long itemId);

    /**
     * 查询案例病人和检查项目关联列表
     * 
     * @param casePatientItem 案例病人和检查项目关联
     * @return 案例病人和检查项目关联集合
     */
    public List<CasePatientItem> selectCasePatientItemList(CasePatientItem casePatientItem);

    /**
     * 新增案例病人和检查项目关联
     * 
     * @param casePatientItem 案例病人和检查项目关联
     * @return 结果
     */
    public int insertCasePatientItem(CasePatientItem casePatientItem);

    /**
     * 修改案例病人和检查项目关联
     * 
     * @param casePatientItem 案例病人和检查项目关联
     * @return 结果
     */
    public int updateCasePatientItem(CasePatientItem casePatientItem);


    int deleteCasePatientItem(Long patientId, Long itemId);

    /**
     * 批量查询检查项目结果及意义
     * @param patientId
     * @param tgIds
     * @return
     */
    List<CasePatientItem> selectCasePatientItemByIds(Long patientId,Long[] tgIds);
}
