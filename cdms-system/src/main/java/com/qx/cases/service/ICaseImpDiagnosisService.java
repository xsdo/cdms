package com.qx.cases.service;

import com.qx.cases.domain.CaseImpDiagnosis;
import com.qx.system.domain.TreeSelect;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/12 14:58
 */
public interface ICaseImpDiagnosisService {
    CaseImpDiagnosis selectCaseImpDiagnosisById(Long impId);

    List<CaseImpDiagnosis> selectCaseImpDiagnosisList(CaseImpDiagnosis caseImpDiagnosis);

    int insertCaseImpDiagnosis(CaseImpDiagnosis caseImpDiagnosis);

    int updateCaseImpDiagnosis(CaseImpDiagnosis caseImpDiagnosis);

    int deleteCaseImpDiagnosisByIds(Long[] impIds);

    int deleteCaseImpDiagnosisById(Long impId);

    List<CaseImpDiagnosis> buildImpTree(List<CaseImpDiagnosis> imps);

    List<TreeSelect> buildImpTreeSelect(List<CaseImpDiagnosis> imps);


    boolean hasChildById(Long id);

    List<CaseImpDiagnosis> selectImpByIds(Long[] ids);
}
