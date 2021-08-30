package com.qx.student.service;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.student.domain.FzCheckRecord;
import com.qx.student.domain.StudentTrainRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 辅助检查记录Service接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
@Transactional
public interface IFzCheckRecordService 
{
    /**
     * 查询辅助检查记录
     * 
     * @param id 辅助检查记录ID
     * @return 辅助检查记录
     */
    public FzCheckRecord selectFzCheckRecordById(Long id,Long patientId);

    List<CaseCheckItem> selectFzMissRecordById(Long id);

    FzCheckRecord selectFzMissRecordById(Long id, Long patientId);

//    Map<String,String> selectFzMissRecordById(Long id);

    Double countFzScore(Long id);

    /**
     * 查询辅助检查记录列表
     * 
     * @param fzCheckRecord 辅助检查记录
     * @return 辅助检查记录集合
     */
    public List<FzCheckRecord> selectFzCheckRecordList(FzCheckRecord fzCheckRecord);

    /**
     * 新增辅助检查记录
     * 
     * @param fzCheckRecord 辅助检查记录
     * @param itemIds 项目ids
     * @param trainRecord 学生训练记录
     * @return 结果
     */
    public int insertFzCheckRecord(FzCheckRecord fzCheckRecord, Long[] itemIds, StudentTrainRecord trainRecord);

    /**
     * 修改辅助检查记录
     * 
     * @param fzCheckRecord 辅助检查记录
     * @param itemIds 项目ids
     */
    public void updateFzCheckRecord(FzCheckRecord fzCheckRecord,Long[] itemIds);

    /**
     * 批量删除辅助检查记录
     * 
     * @param ids 需要删除的辅助检查记录ID
     * @return 结果
     */
    public int deleteFzCheckRecordByIds(Long[] ids);

    /**
     * 删除辅助检查记录信息
     * 
     * @param id 辅助检查记录ID
     * @return 结果
     */
    public int deleteFzCheckRecordById(Long id);
}
