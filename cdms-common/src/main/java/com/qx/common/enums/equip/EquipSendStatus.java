package com.qx.common.enums.equip;

public enum EquipSendStatus {
	
	DCK(1, "待出库"), DLY(2, "待领用"), YLY(3, "已领用");

    private final Integer code;
    private final String info;

    EquipSendStatus(Integer code, String info)
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
