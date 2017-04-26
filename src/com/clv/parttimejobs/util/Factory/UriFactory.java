package com.clv.parttimejobs.util.Factory;

public class UriFactory {

	/*
	 * 
	 * 
	 * 
	 * <-------------------网络请求接口---------------------->
	 * 
	 * 
	 * 
	 * 
	 */
	private final static String  questHead ="http://115.159.64.167:8080/haidaojobs/user";//请求头
	/*
	 * 
	 * 《------------------------------------兼职块-------------------------------------------》
	 * 
	 */
	// 获取兼职列表
	public static String getPartJobUrl() {
//		String url = "http://115.159.64.167:8080/ssm/user/parttimes/getPartTimeList";
		String url = questHead+"/parttimes/getPartTimeList";
		return url;
	}

	// 获取兼职详情
	public static String getPartJobInformationUrl() {
		String url = questHead+"/parttimes/getPartTimeInformation";
		return url;
	}

	// 兼职报名
	public static String getPartRegistrationUrl() {
		String url = questHead+"/parttimes/partTimeRegistration";
		return url;
	}

	//获取待审核兼职
	public static String getPendingListUrl() {
		String url = questHead+"/parttimes/getPendingList";
		return url;
	}
	/*
	 * 
	 * <------------------------------用户块----------------------------------------->
	 * 
	 */
	// 查询签到次数
	public static String getselectSignInUrl() {
		String url = questHead+"/my/selectSignIn";
		return url;
	}

	// 用户签到
	public static String getRetroactiveUrl() {
		String url = questHead+"/my/retroactive";
		return url;
	}

	// 查询礼包
	public static String getSelectSignInGiftUrl() {
		String url = questHead+"/my/selectSignInGift";
		return url;
	}
	
	//用户获取礼物
	public static String getSignInGiftUrl() {
		String url = questHead+"/my/getSignInGift";
		return url;
	}
	
	//用户身份信息填写
	public static String getIdentityAuthenticationUrl() {
		String url = questHead+"/resume/identity/identityAuthentication";
		return url;
	}
	
	// 查看用户提交信息
	public static String getSelectIdentityAuditConclusionUrl() {
		String url = questHead+"/resume/identity/selectIdentityAuditConclusion";
		return url;
	}
	
	//用户上传图片
	public static String getUploadCertificatePhotoUrl() {
		String url = questHead+"/resume/identity/uploadCertificatePhoto";
		return url;
	}
	//用户上传风采图片
	public static String getAddPhotoUrl() {
		String url = questHead+"/resume/photo/addPhoto";
		return url;
	}
	//遍历风采图片
	public static String getSelectPhotoUrl() {
		String url = questHead+"/resume/photo/selectPhoto";
		return url;
	}
	//删除风采图片
	public static String getDeletePhotoUrl() {
		String url = questHead+"/resume/photo/deletePhoto";
		return url;
	}
	//用户修改名字
	public static String getModifyUserNameUrl() {
		String url = questHead+"/modify/modifyUserName";
		return url;
	}
	//修改用户身高信息
	public static String getModifyUserHeightUrl() {
		String url = questHead+"/resume/identity/modifyHeight";
		return url;
	}
	//查询用户信息
	public static String getInformationUrl() {
		String url = questHead+"/resume/identity/getInformation";
		return url;
	}
	//获取用户技能
	public static String getSelectSkillUrl() {
		String url = questHead+"/resume/skill/selectSkill";
		return url;
	}
	//用户增加技能
	public static String getAddKillUrl() {
		String url = questHead+"/resume/skill/addSkill";
		return url;
	}
	//用户删除技能
	public static String getDeleteKillUrl() {
		String url = questHead+"/resume/skill/deleteSkill";
		return url;
	}
	//用户修改技能
	public static String getModifySkillUrl() {
		String url = questHead+"/resume/skill/modifySkill";
		return url;
	}
	/*
	 * 
	 * 《-------------------------------用户登录--------------------------------》
	 * 
	 */
	//用户登录
	public static String getSignInUrl() {
		String url = questHead+"/signIn";
		return url;
	}
	/*
	 * 
	 * 用户注册
	 * 
	 */
	//请求手机是否能用
	public static String getIsUserPhoneNoUrl() {
		String url = questHead+"/isUserPhoneNo";
		return url;
	}
	//获取验证码
	public static String getCodeUrl() {
		String url = questHead+"http://115.159.64.167:8080/ssm/code/getCode";//
		return url;
	}
	//检查验证码
	public static String getCheckCode() {
		String url = questHead+"http://115.159.64.167:8080/ssm/code/checkCode";
		return url;
	}
	//增加用户----注册
	public static String getAddUserUrl() {
		String url = questHead+"/addUser";
		return url;
	}
	//修改用户手机
	public static String getModifyUserPhoneUrl() {
		String url = questHead+"http://115.159.64.167:8080/ssm/user/modify/modifyUserPhone";
		return url;
	}
	//修改用户密码
	public static String getModifyUserPasswordUrl() {
		String url = questHead+"http://115.159.64.167:8080/ssm/user/modify/modifyUserPassword";
		return url;
	}
	/*
	 * 
	 * 
	 * 用户简历
	 * 
	 * 
	 */
	//获取用户头像
	public static String getHeadPortraitUrl() {
		String url = "http://115.159.64.167:8080/ssm/user/selectHeadPortraitURL";
		return url;
	}
	//修改用户头像
	public static String getModifyUserHeadPortraitUrl() {
		String url = "http://115.159.64.167:8080/ssm/user/modify/modifyUserHeadPortrait";
		return url;
	}
	
}
