package com.qx.student.mapper;

import com.qx.student.domain.ZStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生Mapper接口
 * 
 * @author aaa
 * @date 2021-05-18
 */
public interface ZStudentMapper 
{
    /**
     * 查询学生
     * 
     * @param id 学生ID
     * @return 学生
     */
    public ZStudent selectZStudentById(Long id);

    /**
     * 查询学生列表
     * 
     * @param zStudent 学生
     * @return 学生集合
     */
    public List<ZStudent> selectZStudentList(ZStudent zStudent);

    /**
     * 新增学生
     * 
     * @param zStudent 学生
     * @return 结果
     */
    public int insertZStudent(ZStudent zStudent);

    /**
     * 修改学生
     * 
     * @param zStudent 学生
     * @return 结果
     */
    public int updateZStudent(ZStudent zStudent);

    /**
     * 删除学生
     * 
     * @param id 学生ID
     * @return 结果
     */
    public int deleteZStudentById(Long id);

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteZStudentByIds(Long[] ids);

    /**
     * 根据学号查询学生
     * @param sno
     * @return
     */
    ZStudent selectZStudentBySno(String sno);

    int checkSnoUnique(String sno);

    /**
     * 重置密码
     * @param sno
     * @param password
     * @return
     */
    int resetStuPwd(@Param("sno") String sno, @Param("password") String password);
}
