package com.qx.cases.mapper;

import com.qx.cases.domain.CaseTreatment;
import java.util.List;

/**
 * 治疗项目Mapper接口
 * 
 * @author aaa
 * @date 2021-06-01
 */
public interface CaseTreatmentMapper 
{
    /**
     * 查询治疗项目
     * 
     * @param treatmentId 治疗项目ID
     * @return 治疗项目
     */
    public CaseTreatment selectCaseTreatmentById(Long treatmentId);


    public List<CaseTreatment> selectCaseTreatmentByIds(Long[] treatmentId);

    /**
     * 查询治疗项目列表
     * 
     * @param caseTreatment 治疗项目
     * @return 治疗项目集合
     */
    public List<CaseTreatment> selectCaseTreatmentList(CaseTreatment caseTreatment);

    /**
     * 新增治疗项目
     * 
     * @param caseTreatment 治疗项目
     * @return 结果
     */
    public int insertCaseTreatment(CaseTreatment caseTreatment);

    /**
     * 修改治疗项目
     * 
     * @param caseTreatment 治疗项目
     * @return 结果
     */
    public int updateCaseTreatment(CaseTreatment caseTreatment);

    /**
     * 删除治疗项目
     * 
     * @param treatmentId 治疗项目ID
     * @return 结果
     */
    public int deleteCaseTreatmentById(Long treatmentId);

    /**
     * 批量删除治疗项目
     * 
     * @param treatmentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseTreatmentByIds(Long[] treatmentIds);

    int hasChildById(Long treatmentId);
}
