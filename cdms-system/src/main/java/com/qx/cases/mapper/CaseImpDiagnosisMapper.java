package com.qx.cases.mapper;

import com.qx.cases.domain.CaseImpDiagnosis;

import java.util.List;

/**
 * 项目Mapper接口
 *
 */
public interface CaseImpDiagnosisMapper
{

    public CaseImpDiagnosis selectCaseImpDiagnosisById(Long impId);


    public List<CaseImpDiagnosis> selectCaseImpDiagnosisByIds(Long[] impId);


    public List<CaseImpDiagnosis> selectCaseImpDiagnosisList(CaseImpDiagnosis caseImpDiagnosis);


    public int insertCaseImpDiagnosis(CaseImpDiagnosis caseImpDiagnosis);


    public int updateCaseImpDiagnosis(CaseImpDiagnosis caseImpDiagnosis);


    public int deleteCaseImpDiagnosisById(Long impId);


    public int deleteCaseImpDiagnosisByIds(Long[] impIds);

    int hasChildById(Long impId);
}
