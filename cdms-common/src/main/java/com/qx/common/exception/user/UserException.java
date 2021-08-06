package com.qx.common.exception.user;

import com.qx.common.exception.BaseException;
import com.qx.common.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author suhp
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
