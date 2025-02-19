package com.aiocloud.onetable.console.nlp.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ybin
 */
public class TableMap {

    public static Map<String, String> map = new HashMap<>();

    static {
        map.put("序号","id");
        map.put("身份证号","citizenId");
        map.put("姓名","fullName");
        map.put("曾用名","formerName");
        map.put("国籍","nationality");
        map.put("性别","gender");
        map.put("民族","ethnicity");
        map.put("出生日期","birthDate");//改变不了
        map.put("籍贯省市县","hometownProvinceCityCounty");
        map.put("街路巷名称（居住）","streetandLaneNames");
        map.put("户号","AccountNumber");
        map.put("户籍地址","Registeredresidenceaddress");
        map.put("身份证地址","householdDetail");
        map.put("常用电话号码","phoneNumber");
        map.put("业务更新时间","businessUpdateTime");
        map.put("居住地址","ResidentialAddress");
        map.put("是否特困对象","isExtremelyPoorPerson");
        map.put("是否临时救助","isTemporaryAssistance");
        map.put("是否低保人员","isBasicLivingAllowancesPerson");
        map.put("是否低边人员","isBasicLivingAllowancesSidePerson");
        map.put("是否残疾人","isDisabled");
        map.put("残疾证号","disableCertNo");
        map.put("年龄","age");
        map.put("是否有医保","isHaveMedicalInsurance");
        map.put("是否有养老保险","isHaveEndowmentInsurance");
        map.put("是否有失业保险","isHaveUnemploymentInsurance");
        map.put("是否有工伤保险","isHaveEmploymentInjuryInsurance");
        map.put("是否有生育保险","isHaveBirthInsurance");
        map.put("所属村社区（居住）","belongCommunity");
        map.put("健康状况","healthSituation");
        map.put("是否是优抚对象","isScpmain");
        map.put("是否困难退役军人","isDisabledVeterans");
        map.put("所属乡镇名称（居住）","belongTownsVillagesAreaName");
    }
}
