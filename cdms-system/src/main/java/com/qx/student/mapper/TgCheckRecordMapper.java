package com.qx.student.mapper;

import com.qx.student.domain.TgCheckRecord;
import java.util.List;

/**
 * 体格检查记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
public interface TgCheckRecordMapper 
{
    /**
     * 查询体格检查记录
     * 
     * @param id 体格检查记录ID
     * @return 体格检查记录
     */
    public TgCheckRecord selectTgCheckRecordById(Long id);

    /**
     * 查询体格检查记录列表
     * 
     * @param tgCheckRecord 体格检查记录
     * @return 体格检查记录集合
     */
    public List<TgCheckRecord> selectTgCheckRecordList(TgCheckRecord tgCheckRecord);

    /**
     * 新增体格检查记录
     * 
     * @param tgCheckRecord 体格检查记录
     * @return 结果
     */
    public int insertTgCheckRecord(TgCheckRecord tgCheckRecord);

    /**
     * 修改体格检查记录
     * 
     * @param tgCheckRecord 体格检查记录
     * @return 结果
     */
    public int updateTgCheckRecord(TgCheckRecord tgCheckRecord);

    /**
     * 删除体格检查记录
     * 
     * @param id 体格检查记录ID
     * @return 结果
     */
    public int deleteTgCheckRecordById(Long id);

    /**
     * 批量删除体格检查记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTgCheckRecordByIds(Long[] ids);
}
