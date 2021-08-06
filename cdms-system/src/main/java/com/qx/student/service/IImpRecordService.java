package com.qx.student.service;

import com.qx.student.domain.ImpRecord;
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.domain.vo.ImpRecordVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 诊断记录Service接口
 * 
 * @author aaa
 * @date 2021-06-18
 */
@Transactional
public interface IImpRecordService 
{
    /**
     * 查询诊断记录
     * 
     * @param id 诊断记录ID
     * @return 诊断记录
     */
    public ImpRecord selectImpRecordById(Long id);

    /**
     * 查询诊断记录列表
     * 
     * @param impRecord 诊断记录
     * @return 诊断记录集合
     */
    public List<ImpRecord> selectImpRecordList(ImpRecord impRecord);

    /**
     * 新增诊断记录
     * 
     * @param impRecordVo 诊断记录
     * @return 结果
     */
    public int insertImpRecord(ImpRecordVo impRecordVo);

    /**
     * 修改诊断记录
     * 
     * @param impRecordVo 诊断记录
     * @return 结果
     */
    public void updateImpRecord(ImpRecordVo impRecordVo);

    /**
     * 批量删除诊断记录
     * 
     * @param ids 需要删除的诊断记录ID
     * @return 结果
     */
    public int deleteImpRecordByIds(Long[] ids);

    /**
     * 删除诊断记录信息
     * 
     * @param id 诊断记录ID
     * @return 结果
     */
    public int deleteImpRecordById(Long id);
}
