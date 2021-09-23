package com.qx.student.service;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.student.domain.JsCheckRecord;
import com.qx.student.domain.StudentTrainRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 精神状况检查记录Service接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Transactional
public interface IJsCheckRecordService 
{
    /**
     * 查询精神状况检查记录
     * 
     * @param id 精神状况检查记录ID
     * @return 精神状况检查记录
     */
    public JsCheckRecord selectJsCheckRecordById(Long id,Long patientId);

    List<CaseCheckItem> selectJsWrongRecordById(Long id);

    List<CaseCheckItem> selectJsMissRecordById(Long id);

    Double countJsScore(Long id);

    /**
     * 查询精神状况检查记录列表
     * 
     * @param jsCheckRecord 精神状况检查记录
     * @return 精神状况检查记录集合
     */
    public List<JsCheckRecord> selectJsCheckRecordList(JsCheckRecord jsCheckRecord);

    /**
     * 新增精神状况检查记录
     *
     * @param jsCheckRecord 精神状况检查记录
     * @param ids 项目ids
     * @param record 学生训练记录
     * @return 结果
     */
    public int insertJsCheckRecord(JsCheckRecord jsCheckRecord, Long[] ids, StudentTrainRecord record);

    /**
     * 修改精神状况检查记录
     * @param jsCheckRecord 精神状况检查记录
     * @param ids 项目ids
     */
    public void updateJsCheckRecord(JsCheckRecord jsCheckRecord,Long[] ids);

    /**
     * 批量删除精神状况检查记录
     * 
     * @param ids 需要删除的精神状况检查记录ID
     * @return 结果
     */
    public int deleteJsCheckRecordByIds(Long[] ids);

    /**
     * 删除精神状况检查记录信息
     * 
     * @param id 精神状况检查记录ID
     * @return 结果
     */
    public int deleteJsCheckRecordById(Long id);
}
