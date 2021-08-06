package com.qx.cases.mapper;

import com.qx.cases.domain.CasePatientItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 案例病人和检查项目关联Mapper接口
 * 
 * @author aaa
 * @date 2021-05-27
 */
public interface CasePatientItemMapper 
{
    /**
     * 查询某案例下的某项目
     * @param patientId
     * @param itemId
     * @return
     */
    public CasePatientItem selectCasePatientItemById(@Param(value = "patientId") Long patientId,@Param(value = "itemId") Long itemId);

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

    /**
     * 删除病人-项目关联数据
     * @param patientId
     * @param itemId
     * @return
     */
    int deleteCasePatientItem(@Param(value = "patientId") Long patientId,@Param(value = "itemId") Long itemId);

    /**
     * 批量查询s
     * @param patientId
     * @param itemId
     * @return
     */
    List<CasePatientItem> selectCasePatientItemByIds(@Param(value = "patientId") Long patientId,@Param(value = "itemIds") Long[] itemIds);
}
