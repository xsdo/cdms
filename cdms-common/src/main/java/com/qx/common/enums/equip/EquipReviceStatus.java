package com.qx.common.enums.equip;

public enum EquipReviceStatus {

	DCK(1, "已领取"), YLY(2, "退还");

    private final Integer code;
    private final String info;

    EquipReviceStatus(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
	
}
