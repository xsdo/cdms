package com.qx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 附件对象 sys_attachment
 * 
 * @author patient
 * @date 2019-12-13
 */
public class SysAttachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    private Long id;

    /** 附件哈希 */
    @Excel(name = "附件哈希")
    private String hash;

    /** 原名称 */
    @Excel(name = "原名称")
    private String name;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String fileName;

    /** 附件大小 */
    @Excel(name = "附件大小")
    private Long size;

    /** 附件路径 */
    @Excel(name = "附件路径")
    private String path;

    /** 附件绝对路径 */
    @Excel(name = "附件绝对路径")
    private String absPath;

    /** 上传者 */
    @Excel(name = "上传者")
    private Long userid;

    /** 附件扩展子 */
    @Excel(name = "附件扩展子")
    private String ext;

    /** 附件类型，值范围为以下值：0:rest,1:img,2:video,3:pdf,4:music */
    @Excel(name = "附件类型，值范围为以下值：0:rest,1:img,2:video,3:pdf,4:music")
    private String type;

    /** 状态（0正常 1已删除） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=已删除")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHash(String hash) 
    {
        this.hash = hash;
    }

    public String getHash() 
    {
        return hash;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setSize(Long size) 
    {
        this.size = size;
    }

    public Long getSize() 
    {
        return size;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setAbsPath(String absPath) 
    {
        this.absPath = absPath;
    }

    public String getAbsPath() 
    {
        return absPath;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setExt(String ext) 
    {
        this.ext = ext;
    }

    public String getExt() 
    {
        return ext;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hash", getHash())
            .append("name", getName())
            .append("fileName", getFileName())
            .append("size", getSize())
            .append("path", getPath())
            .append("absPath", getAbsPath())
            .append("userid", getUserid())
            .append("ext", getExt())
            .append("type", getType())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
