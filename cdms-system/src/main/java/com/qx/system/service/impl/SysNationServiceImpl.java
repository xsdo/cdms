package com.qx.system.service.impl;

import com.qx.system.domain.SysNation;
import com.qx.system.mapper.SysNationMapper;
import com.qx.system.service.ISysNationService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 民族Service业务层处理
 * 
 * @author qx
 * @date 2020-08-10
 */
@Service
public class SysNationServiceImpl implements ISysNationService
{
    @Autowired
    private SysNationMapper sysNationMapper;

    /**
     * 查询民族
     * 
     * @param id 民族ID
     * @return 民族
     */
    @Override
    public SysNation selectSysNationById(Long id)
    {
        return sysNationMapper.selectSysNationById(id);
    }

    /**
     * 查询民族列表
     * 
     * @param sysNation 民族
     * @return 民族
     */
    @Override
    public List<Map<String,String>> selectSysNationList(SysNation sysNation)
    {
        List<SysNation> list=sysNationMapper.selectSysNationList(sysNation);

        List<Map<String,String>> mapList=new ArrayList<>();
        for (SysNation s:list) {
            Map<String,String> map=new LinkedMap<>();
            map.put("dictLabel",s.getNation());
            map.put("dictValue",s.getId()+"");
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 新增民族
     * 
     * @param sysNation 民族
     * @return 结果
     */
    @Override
    public int insertSysNation(SysNation sysNation)
    {
        return sysNationMapper.insertSysNation(sysNation);
    }

    /**
     * 修改民族
     * 
     * @param sysNation 民族
     * @return 结果
     */
    @Override
    public int updateSysNation(SysNation sysNation)
    {
        return sysNationMapper.updateSysNation(sysNation);
    }

    /**
     * 批量删除民族
     * 
     * @param ids 需要删除的民族ID
     * @return 结果
     */
    @Override
    public int deleteSysNationByIds(Long[] ids)
    {
        return sysNationMapper.deleteSysNationByIds(ids);
    }

    /**
     * 删除民族信息
     * 
     * @param id 民族ID
     * @return 结果
     */
    @Override
    public int deleteSysNationById(Long id)
    {
        return sysNationMapper.deleteSysNationById(id);
    }
}
