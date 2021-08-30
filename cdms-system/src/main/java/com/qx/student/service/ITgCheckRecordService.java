package com.qx.student.service;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.TgCheckRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 体格检查记录Service接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Transactional
public interface ITgCheckRecordService 
{
    /**
     * 查询体格检查记录
     * 
     * @param id 体格检查记录ID
     * @return 体格检查记录
     */
    public TgCheckRecord selectTgCheckRecordById(Long id,Long patientId);

    List<CaseCheckItem> selectTgMissRecordById(Long id);

    Double countTgScore(Long id);

    /**
     * 查询体格检查记录列表
     * 
     * @param tgCheckRecord 体格检查记录
     * @return 体格检查记录集合
     */
    public List<TgCheckRecord> selectTgCheckRecordList(TgCheckRecord tgCheckRecord);

    /**
     * 新增体格检查记录
     * @param tgCheckRecord 体格检查记录
     * @param ids 项目ids
     * @param record 学生训练记录
     * @return 结果
     */
    public int insertTgCheckRecord(TgCheckRecord tgCheckRecord, Long[] ids, StudentTrainRecord record);

    /**
     * 修改体格检查记录及其诊断支持记录
     * @param tgCheckRecord 体格检查记录
     * @param ids 项目ids
     */
    public void updateTgCheckRecord(TgCheckRecord tgCheckRecord,Long[] ids);

    /**
     * 批量删除体格检查记录
     * 
     * @param ids 需要删除的体格检查记录ID
     * @return 结果
     */
    public int deleteTgCheckRecordByIds(Long[] ids);

    /**
     * 删除体格检查记录信息
     * 
     * @param id 体格检查记录ID
     * @return 结果
     */
    public int deleteTgCheckRecordById(Long id);
}
