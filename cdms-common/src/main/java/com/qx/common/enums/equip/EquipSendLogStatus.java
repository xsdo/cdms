package com.qx.common.enums.equip;

/**
 * 发放记录枚举
 * @author suhp
 *
 */
public enum EquipSendLogStatus {
	
	ZC(0, "正常"), CH(1, "撤回");
	
	private final Integer code;
    private final String info;

    EquipSendLogStatus(Integer code, String info)
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
