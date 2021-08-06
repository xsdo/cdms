package com.qx.system.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.system.mapper.SysAttachmentMapper;
import com.qx.system.domain.SysAttachment;
import com.qx.system.service.ISysAttachmentService;

/**
 * 附件Service业务层处理
 * 
 * @author patient
 * @date 2019-12-13
 */
@Service
public class SysAttachmentServiceImpl implements ISysAttachmentService 
{
    @Autowired
    private SysAttachmentMapper sysAttachmentMapper;

    /**
     * 查询附件
     * 
     * @param id 附件ID
     * @return 附件
     */
    @Override
    public SysAttachment selectSysAttachmentById(Long id)
    {
        return sysAttachmentMapper.selectSysAttachmentById(id);
    }

    /**
     * 查询附件列表
     * 
     * @param sysAttachment 附件
     * @return 附件
     */
    @Override
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.selectSysAttachmentList(sysAttachment);
    }

    /**
     * 新增附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    @Override
    public int insertSysAttachment(SysAttachment sysAttachment)
    {
        sysAttachment.setCreateTime(DateUtils.getNowDate());
        return sysAttachmentMapper.insertSysAttachment(sysAttachment);
    }

    /**
     * 修改附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    @Override
    public int updateSysAttachment(SysAttachment sysAttachment)
    {
        sysAttachment.setUpdateTime(DateUtils.getNowDate());
        return sysAttachmentMapper.updateSysAttachment(sysAttachment);
    }

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的附件ID
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentByIds(Long[] ids)
    {
        return sysAttachmentMapper.deleteSysAttachmentByIds(ids);
    }

    /**
     * 删除附件信息
     * 
     * @param id 附件ID
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentById(Long id)
    {
        return sysAttachmentMapper.deleteSysAttachmentById(id);
    }
}
