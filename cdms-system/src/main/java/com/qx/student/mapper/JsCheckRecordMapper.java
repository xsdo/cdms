package com.qx.student.mapper;

import com.qx.student.domain.JsCheckRecord;
import java.util.List;

/**
 * 精神状况检查记录Mapper接口
 * 
 * @author aaa
 * @date 2021-06-07
 */
public interface JsCheckRecordMapper 
{
    /**
     * 查询精神状况检查记录
     * 
     * @param id 精神状况检查记录ID
     * @return 精神状况检查记录
     */
    public JsCheckRecord selectJsCheckRecordById(Long id);

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
     * @return 结果
     */
    public int insertJsCheckRecord(JsCheckRecord jsCheckRecord);

    /**
     * 修改精神状况检查记录
     * 
     * @param jsCheckRecord 精神状况检查记录
     * @return 结果
     */
    public int updateJsCheckRecord(JsCheckRecord jsCheckRecord);

    /**
     * 删除精神状况检查记录
     * 
     * @param id 精神状况检查记录ID
     * @return 结果
     */
    public int deleteJsCheckRecordById(Long id);

    /**
     * 批量删除精神状况检查记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJsCheckRecordByIds(Long[] ids);
}
