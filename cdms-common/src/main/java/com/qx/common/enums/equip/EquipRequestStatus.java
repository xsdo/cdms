package com.qx.common.enums.equip;

/**
 * 装备领用状态
 * @author suhp
 *
 */
public enum EquipRequestStatus {
	
	XZ(1, "新增"), CX(2, "撤销"), CK(3, "出库"), LY(4, "领用");

    private final Integer code;
    private final String info;

    EquipRequestStatus(Integer code, String info)
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
