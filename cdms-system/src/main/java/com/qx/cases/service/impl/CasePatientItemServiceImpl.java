package com.qx.cases.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CasePatientItemMapper;
import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.service.ICasePatientItemService;

/**
 * 案例病人和检查项目关联Service业务层处理
 * 
 * @author aaa
 * @date 2021-05-27
 */
@Service
public class CasePatientItemServiceImpl implements ICasePatientItemService
{
    @Autowired
    private CasePatientItemMapper casePatientItemMapper;

    /**
     * 查询某案例下的某项目
     * @param patientId
     * @param itemId
     * @return
     */
    @Override
    public CasePatientItem selectCasePatientItemById(Long patientId,Long itemId)
    {
        return casePatientItemMapper.selectCasePatientItemById(patientId,itemId);
    }

    /**
     * 查询某案例下的某项目(核心)
     * @param patientId
     * @param itemId
     * @return
     */
    @Override
    public CasePatientItem selectCasePatientItemByCore(Long patientId,Long itemId)
    {
        return casePatientItemMapper.selectCasePatientItemByCore(patientId,itemId);
    }
    /**
     * 查询案例病人和检查项目关联列表
     * 
     * @param casePatientItem 案例病人和检查项目关联
     * @return 案例病人和检查项目关联
     */
    @Override
    public List<CasePatientItem> selectCasePatientItemList(CasePatientItem casePatientItem)
    {
        return casePatientItemMapper.selectCasePatientItemList(casePatientItem);
    }

    /**
     * 新增案例病人和检查项目关联
     * 
     * @param casePatientItem 案例病人和检查项目关联
     * @return 结果
     */
    @Override
    public int insertCasePatientItem(CasePatientItem casePatientItem)
    {
        return casePatientItemMapper.insertCasePatientItem(casePatientItem);
    }

    /**
     * 修改案例病人和检查项目关联
     * 
     * @param casePatientItem 案例病人和检查项目关联
     * @return 结果
     */
    @Override
    public int updateCasePatientItem(CasePatientItem casePatientItem)
    {
        return casePatientItemMapper.updateCasePatientItem(casePatientItem);
    }


    @Override
    public int deleteCasePatientItem(Long patientId, Long itemId) {
        return casePatientItemMapper.deleteCasePatientItem(patientId,itemId);
    }

    /**
     * 批量查询
     * @param tgIds
     * @return
     */
    @Override
    public List<CasePatientItem> selectCasePatientItemByIds(Long patientId,Long[] tgIds) {

        return casePatientItemMapper.selectCasePatientItemByIds(patientId,tgIds);
    }
}
