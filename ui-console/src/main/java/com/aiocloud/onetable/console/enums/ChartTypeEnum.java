package com.aiocloud.onetable.console.enums;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 枚举类，表示不同的图表类型。
 */
public enum ChartTypeEnum {

    /**
     * 表格图表类型，对应code为0。
     */
    TABLE("0", "table"),
    
    /**
     * 柱状图图表类型，对应code为2。
     */
    BAR("1", "bar"),
    
    /**
     * 饼图图表类型，对应code为2。
     */
    PIE("2", "pie"),
    
    /**
     * 折线图图表类型，对应code为3。
     */
    LINE("3", "line");

    private String code;
    private String name;

    ChartTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据code获取对应的ChartType枚举。
     * @param code 图表类型的code
     * @return 对应的ChartType枚举，如果未找到则返回null
     */
    public static ChartTypeEnum fromCode(String code) {
        if(StringUtils.isNotBlank(code)) {
            for (ChartTypeEnum type : ChartTypeEnum.values()) {
                if (type.getCode().equalsIgnoreCase(code)) {
                    return type;
                }
            }
        }
        return null;
    }

    /**
     * 根据name获取对应的ChartType枚举。
     * @param name 图表类型的name
     * @return 对应的ChartType枚举，如果未找到则返回null
     */
    public static ChartTypeEnum fromName(String name) {
        if(StringUtils.isNotBlank(name)) {
            for (ChartTypeEnum type : ChartTypeEnum.values()) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return type;
                }
            }
        }
        return null;
    }
    /**
     * 根据code获取对应的name。
     * @param code 图表类型的code
     * @return 对应的name，如果未找到则返回null
     */
    public static String getNameByCode(String code) {
        ChartTypeEnum type = fromCode(code);
        return type != null ? type.getName() : null;
    }

    /**
     * 根据name获取对应的code。
     * @param name 图表类型的name
     * @return 对应的code，如果未找到则返回null
     */
    public static String getCodeByName(String name) {
        ChartTypeEnum type = fromName(name);
        return type != null ? type.getCode() : null;
    }
    @Override
    public String toString() {
        return "ChartType{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
