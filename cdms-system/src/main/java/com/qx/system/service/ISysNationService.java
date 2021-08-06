package com.qx.system.service;

import com.qx.system.domain.SysNation;

import java.util.List;
import java.util.Map;

/**
 * 民族Service接口
 * 
 * @author qx
 * @date 2020-08-10
 */
public interface ISysNationService 
{
    /**
     * 查询民族
     * 
     * @param id 民族ID
     * @return 民族
     */
    public SysNation selectSysNationById(Long id);

    /**
     * 查询民族列表
     * 
     * @param sysNation 民族
     * @return 民族集合
     */
    public List<Map<String,String>> selectSysNationList(SysNation sysNation);

    /**
     * 新增民族
     * 
     * @param sysNation 民族
     * @return 结果
     */
    public int insertSysNation(SysNation sysNation);

    /**
     * 修改民族
     * 
     * @param sysNation 民族
     * @return 结果
     */
    public int updateSysNation(SysNation sysNation);

    /**
     * 批量删除民族
     * 
     * @param ids 需要删除的民族ID
     * @return 结果
     */
    public int deleteSysNationByIds(Long[] ids);

    /**
     * 删除民族信息
     * 
     * @param id 民族ID
     * @return 结果
     */
    public int deleteSysNationById(Long id);
}
