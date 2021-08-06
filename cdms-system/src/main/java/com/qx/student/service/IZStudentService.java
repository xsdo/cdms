package com.qx.student.service;

import com.qx.student.domain.ZStudent;
import java.util.List;

/**
 * 学生Service接口
 * 
 * @author aaa
 * @date 2021-05-18
 */
public interface IZStudentService 
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
     * 批量删除学生
     * 
     * @param ids 需要删除的学生ID
     * @return 结果
     */
    public int deleteZStudentByIds(Long[] ids);

    /**
     * 删除学生信息
     * 
     * @param id 学生ID
     * @return 结果
     */
    public int deleteZStudentById(Long id);

    /**
     * 重置密码
     * @param student
     * @return
     */
    int resetPwd(ZStudent student);

    int updateStuStatus(ZStudent student);

    ZStudent selectZStudentBySno(String sno);

    String checkSnoUnique(String sno);

    String login(String sno, String password);

    /**
     * 重置密码
     * @param sno
     * @param password
     * @return
     */
    int resetStuPwd(String sno, String password);
}
