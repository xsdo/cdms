package com.qx.common.enums;

public enum UserType {
	
	system("00", "系统用户"), mj("01", "民警"), fj("02", "辅警");
	
	private final String code;
    private final String info;

    UserType(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
	
}
