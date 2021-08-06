package com.qx.common.enums.equip;


/**
 * 新增方式
 * @author suhp
 *
 */
public enum EquipAddType {
	
	RK(1, "入库"), DGR(2, "到个人"), DDW(3, "到单位"), GGSS(4, "公共设施");
	
	private final Integer code;
    private final String info;

    EquipAddType(Integer code, String info)
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
