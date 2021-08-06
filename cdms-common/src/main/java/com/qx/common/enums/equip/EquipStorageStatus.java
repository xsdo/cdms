package com.qx.common.enums.equip;


/**
 * 装备库存状态
 * @author 15110
 *
 */
public enum EquipStorageStatus {
	
	ZC(0, "正常"), CK(1, "出库");

    private final Integer code;
    private final String info;

    EquipStorageStatus(Integer code, String info)
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
