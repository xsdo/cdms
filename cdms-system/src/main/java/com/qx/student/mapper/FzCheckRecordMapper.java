package com.qx.student.mapper;

import com.qx.student.domain.FzCheckRecord;
import java.util.List;

/**
 * 辅助检查记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
public interface FzCheckRecordMapper 
{
    /**
     * 查询辅助检查记录
     * 
     * @param id 辅助检查记录ID
     * @return 辅助检查记录
     */
    public FzCheckRecord selectFzCheckRecordById(Long id);

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
     * @return 结果
     */
    public int insertFzCheckRecord(FzCheckRecord fzCheckRecord);

    /**
     * 修改辅助检查记录
     * 
     * @param fzCheckRecord 辅助检查记录
     * @return 结果
     */
    public int updateFzCheckRecord(FzCheckRecord fzCheckRecord);

    /**
     * 删除辅助检查记录
     * 
     * @param id 辅助检查记录ID
     * @return 结果
     */
    public int deleteFzCheckRecordById(Long id);

    /**
     * 批量删除辅助检查记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFzCheckRecordByIds(Long[] ids);
}
