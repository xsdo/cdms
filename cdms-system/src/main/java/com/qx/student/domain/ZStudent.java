package com.qx.student.domain;

import com.qx.system.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 学生对象 z_student
 * 
 * @author aaa
 * @date 2021-05-18
 */
public class ZStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 学号/登录名 */
    @Excel(name = "学号/登录名")
    private String sno;

    /** 学生姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 班级 */
    @Excel(name = "班级")
    private String classNo;

    /** 密码 */
    private String password;

    /** 学院/系部 */
    @Excel(name = "学院/系部编号", type = Excel.Type.IMPORT , prompt="请根据学院/系部管理的编码填写")
    private Long deptId;

    /** 学院/系部对象 */
    @Excel(name = "学院/系部", targetAttr = "deptName", type = Excel.Type.EXPORT)
    private SysDept dept = new SysDept() ;
    /** 年级 */
    @Excel(name = "年级")
    private String grade;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 状态 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSno(String sno) 
    {
        this.sno = sno;
    }
    @NotBlank(message = "学生学号不能为空")
    @Size(min = 0, max = 20, message = "学号长度不能超过20个字符")
    public String getSno() 
    {
        return sno;
    }
    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stuName", getStuName())
            .append("sex", getSex())
            .append("classNo", getClassNo())
            .append("password", getPassword())
            .append("deptId", getDeptId())
            .append("grade", getGrade())
            .append("major", getMajor())
            .append("age", getAge())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("sno", getSno())
            .append("dept", getDept())
            .toString();
    }
}
