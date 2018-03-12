package com.ee.ctp.enums.business;
/**
 * 
 * @author lyb
 * 2017年10月17日 下午7:44:55
 *
 */
public enum FtdcIdCardType {
	/**
	 * 组织机构代码
	 */
	THOST_FTDCICT_EID("0"),
	/**
	 * 中国公民身份证
	 */
	THOST_FTDCICT_IDCard("1"),
	/**
	 * 军官证
	 */
	THOST_FTDCICT_OfficerIDCard("2"),
	/**
	 * 警官证
	 */
	THOST_FTDCICT_PoliceIDCard("3"),
	/**
	 * 士兵证
	 */
	THOST_FTDCICT_SoldierIDCard("4"),
	/**
	 * 户口簿
	 */
	THOST_FTDCICT_HouseholdRegister("5"),
	/**
	 * 护照
	 */
	THOST_FTDCICT_Passport("6"),
	/**
	 * 台胞证
	 */
	THOST_FTDCICT_TaiwanCompatriotIDCard("7"),
	/**
	 * 回乡证
	 */
	THOST_FTDCICT_HomeComingCard("8"),
	/**
	 * 营业执照号
	 */
	THOST_FTDCICT_LicenseNo("9"),
	/**
	 * 税务登记号/当地纳税ID
	 */
	THOST_FTDCICT_TaxNo("A"),
	/**
	 * 港澳居民来往内地通行证
	 */
	THOST_FTDCICT_HMMainlandTravelPermit ("B"),
	/**
	 * 台湾居民来往大陆通行证
	 */
	THOST_FTDCICT_TwMainlandTravelPermit("C"),
	/**
	 * 驾照
	 */
	THOST_FTDCICT_DrivingLicense("D"),
	/**
	 * 当地社保ID
	 */
	THOST_FTDCICT_SocialID("F"),
	/**
	 * 当地身份证
	 */
	THOST_FTDCICT_LocalID("G"),
	/**
	 * 商业登记证
	 */
	THOST_FTDCICT_BusinessRegistration ("H"),
	/**
	 * 港澳永久性居民身份证
	 */
	THOST_FTDCICT_HKMCIDCard("I"),
	/**
	 * 人行开户许可证
	 */
	THOST_FTDCICT_AccountsPermits("J"),
	/**
	 * 其他证件
	 */
	THOST_FTDCICT_OtherCard("x");
	
	private String type;
	private FtdcIdCardType(String type) {
		this.type = type;
	}
	
	
	/**
	 * @return the type
	 */
	public String type() {
		return type;
	}


	public static FtdcIdCardType parseFrom(String type) {
		FtdcIdCardType retType = null;
		for (FtdcIdCardType t : FtdcIdCardType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
