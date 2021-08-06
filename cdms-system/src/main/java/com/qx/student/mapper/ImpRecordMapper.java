package com.qx.student.mapper;

import com.qx.student.domain.ImpRecord;
import java.util.List;

/**
 * 诊断记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-18
 */
public interface ImpRecordMapper 
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
     * @param impRecord 诊断记录
     * @return 结果
     */
    public int insertImpRecord(ImpRecord impRecord);

    /**
     * 修改诊断记录
     * 
     * @param impRecord 诊断记录
     * @return 结果
     */
    public int updateImpRecord(ImpRecord impRecord);

    /**
     * 删除诊断记录
     * 
     * @param id 诊断记录ID
     * @return 结果
     */
    public int deleteImpRecordById(Long id);

    /**
     * 批量删除诊断记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpRecordByIds(Long[] ids);
}
