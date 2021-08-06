package com.qx.common.enums.equip;

/**
 * 装备状态
 * @author suhp
 *
 */
public enum EquipStatus {
	
	XG(1, "新购"), XZ(2, "闲置"), SYZ(3, "使用中"), WHZ(4, "维护中"), BF(5, "报废"),CKZ(6, "出库中");

    private final Integer code;
    private final String info;

    EquipStatus(Integer code, String info)
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
