package com.qx.student.service.impl;

import java.util.List;


import com.qx.common.constant.UserConstants;
import com.qx.common.enums.UserStatus;
import com.qx.common.exception.BaseException;

import com.qx.common.exception.user.UserPasswordNotMatchException;
import com.qx.common.utils.DateUtils;

import com.qx.common.utils.StringUtils;
import com.qx.student.domain.vo.LoginStudent;
import com.qx.student.util.RedisCacheUtil;
import com.qx.student.util.SecurityUtil;
import com.qx.student.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.ZStudentMapper;
import com.qx.student.domain.ZStudent;
import com.qx.student.service.IZStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author aaa
 * @date 2021-05-18
 */
@Service
public class ZStudentServiceImpl implements IZStudentService 
{
    private static final Logger log = LoggerFactory.getLogger(ZStudentServiceImpl.class);

    @Autowired
    private ZStudentMapper zStudentMapper;

    @Autowired
    private TokenUtil tokenService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 查询学生
     * 
     * @param id 学生ID
     * @return 学生
     */
    @Override
    public ZStudent selectZStudentById(Long id)
    {
        return zStudentMapper.selectZStudentById(id);
    }

    /**
     * 查询学生列表
     * 
     * @param zStudent 学生
     * @return 学生
     */
    @Override
    public List<ZStudent> selectZStudentList(ZStudent zStudent)
    {
        return zStudentMapper.selectZStudentList(zStudent);
    }

    /**
     * 新增学生
     * 
     * @param zStudent 学生
     * @return 结果
     */
    @Override
    public int insertZStudent(ZStudent zStudent)
    {
        zStudent.setCreateTime(DateUtils.getNowDate());
        return zStudentMapper.insertZStudent(zStudent);
    }

    /**
     * 修改学生
     * 
     * @param zStudent 学生
     * @return 结果
     */
    @Override
    public int updateZStudent(ZStudent zStudent)
    {
        zStudent.setUpdateTime(DateUtils.getNowDate());
        return zStudentMapper.updateZStudent(zStudent);
    }

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的学生ID
     * @return 结果
     */
    @Override
    public int deleteZStudentByIds(Long[] ids)
    {
        return zStudentMapper.deleteZStudentByIds(ids);
    }

    /**
     * 删除学生信息
     * 
     * @param id 学生ID
     * @return 结果
     */
    @Override
    public int deleteZStudentById(Long id)
    {
        return zStudentMapper.deleteZStudentById(id);
    }

    @Override
    public int resetPwd(ZStudent student) {
        return zStudentMapper.updateZStudent(student);
    }

    @Override
    public int updateStuStatus(ZStudent student) {
        return zStudentMapper.updateZStudent(student);
    }

    @Override
    public ZStudent selectZStudentBySno(String sno) {
        return zStudentMapper.selectZStudentBySno(sno);
    }

    @Override
    public String checkSnoUnique(String sno) {
        int count = zStudentMapper.checkSnoUnique(sno);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    @Override
    public String login(String sno, String password) {


        LoginStudent loginStudent=new LoginStudent();
        ZStudent zStudent = zStudentMapper.selectZStudentBySno(sno);
        if (StringUtils.isNull(zStudent))
        {
            log.info("登录用户：{} 不存在.", sno);
            throw new UsernameNotFoundException("账号：" + sno + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(zStudent.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", sno);
            throw new BaseException("对不起，您的账号：" + sno + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(zStudent.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", sno);
            throw new BaseException("对不起，您的账号：" + sno + " 已被停用");
        }else if(SecurityUtil.matchesPassword(password,zStudent.getPassword())){
            loginStudent.setUser(zStudent);

        }else{
            throw new UserPasswordNotMatchException();

        }

        return tokenService.createToken(loginStudent);
    }

    /**
     * 重置密码
     * @param sno 学号
     * @param password 密码
     * @return
     */

    @Override
    public int resetStuPwd(String sno, String password) {
        return zStudentMapper.resetStuPwd(sno,password);
    }
}
