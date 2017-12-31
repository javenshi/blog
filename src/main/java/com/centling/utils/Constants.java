
/*
 * YZConstants.java
 *
 * Created Date: 2016年5月24日
 *				
 * Copyright (c)  Centling Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Centling Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centling Technologies Co., Ltd.
 */

package com.centling.utils;


/**
 * @author Jack Gao
 * @version  <br>
 * <p>静态常量</p>
 */

public class Constants {

	/**
	 * 设备状态常量类
	 * @author derrick.yang
	 * @version  <br>
	 * <p>类的描述</p>
	 */
	public static final class EquipStatus {

		public final static int EQUIP_REGISTER_STATUS_UNOK = 0;//设备未注册状态
		public final static int EQUIP_REGISTER_STATUS_OK = 1;//设备注册完成状态
		public final static String EQUIP_MAINTAINING = "2";//设备维修中
		public final static int EQUIP_SWITCHER = 3;//设备被换机
		//	public final static String EQUIP_REGISTER_BOSS_CHECKING = "6";//老板模式审核锁定
		public final static String EQUIP_SELF_CHECK_FALIURE = "3";//设备自检故障锁定

		public final static int EQUIP_TYPE_DESKTOP = 0; //台式设备
		public final static int EQUIP_TYPE_PORTABLE =1; //便携式设备
		public final static String EQUIP_TYPE_EMBEDED = "2"; //内嵌设备
		public final static String EQUIP_TYPE_DESKTOP_AND_EMBEDED = "3"; //台式设备&内嵌设备

		public final static String EQUIP_TYPE_ZD = "章盾";
		public final static String EQUIP_TYPE_YX = "信印";

		//设备维修状态 0正常状态,1维修中
		public final static Integer EQUIP_REPAIR_NORMAL = 0;
		public final static Integer EQUIP_REPAIR_REPAIRING = 1;
	}
	public static final class UserStatus {
		//用户正常使用状态
		public final static String User_STATUS_OK = "0";
		//用户停用状态
		public final static String User_STATUS_UNOK = "1";

	}

	/**
	 * 故障报修状态
	 */
	public static final class EquipFailureStatus {
		//未处理
		public final static Integer FAILURE_NOTHANDLE = 0;
		//已处理
		public final static Integer FAILURE_HANDLED = 1;
		//已维修完成
		public final static Integer FAILURE_FINISH = 2;
		//已撤回
		public static final Integer FAILURE_CANCEL = 3;
		/**
		 * 重复报修(设备多个报修,一个处理后,其余改成重复报修)
		 */
		public static final Integer FAILURE_REPEAT = 4;
	}

	/**
	 * 费用类型信息
	 */
	public static final class CostType {
		//延保费
		public final static Integer GUARANTEE = 1;
		//在线服务费
		public final static Integer ONLINE = 2;
		//其他
		public final static Integer OTHER = 3;

	}

	/**
	 * @author derrick.yang
	 * @version  <br>
	 * <p>时间格式常量</p>
	 */
	public static final class DateFormat {
		public final static String DATE_FORMAT = "yyyy-MM-dd";
		public final static String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	}

	public static final class DataFilter{
		public final static String DATA_FILTER_WHERE = "filterWhere";
	}

    public static final class TimerTask {
        public final static String COUNT_LIMIT_SEAL_APPLY = "excuteExpiredCountLimitSealApply";
        public final static String TIME_LIMIT_SEAL_APPLY = "excuteExpiredTimeLimitSealApply";
        public final static String BOSS_MODE = "excuteExpiredBossMode";
        public final static String TAKE_OUT = "excuteExpiredTakeOut";
        public final static String SEAL_REPLACE = "excuteExpiredSealReplace";
        public final static String REQ_IDENTIDIER_CLEAN = "excuteReqIdentiferClean";
        public final static String EXPORT_FILE_CLEAN = "excuteExpiredExportFileClean";
        public final static String TOKEN_OSS_CLIENT_CLEAN = "excuteTokenOSSClientClean";
    }
	/**
	 * 设备报修类型,0--自动报修,1--手动报修
	 */
	public static final class FailureType{
		public static final Integer AUTO = 0;
		public static final Integer MAN_MADE = 1;
	}
	public static final class NeedChangeResetKey{
		public static final String NEED="0";
		public static final String NO_NEED="1";
	}
	public static final class NeedChangeToDefulteKey{
		public static final String NEED="0";
		public static final String NO_NEED="1";
	}

	public static final  class NeedReplace{
		public static final String NO_NEED="-1";
		public static final String NEED="1";
	}

	/**
	 * 设备维修状态
	 * @author anne.zhang
	 *
	 */
	public static final class EquipRepairStatus {
		public final static String RECORD_TYPE_IMPORT = "0";
		public final static String RECORD_TYPE_FILL_IN = "1";

		/**
		 * 维修记录是否关闭,0--未关闭,1--已关闭
		 */
		public final static String RECORD_IS_CLOSED_NO = "0";
		public final static String RECORD_IS_CLOSED_YES = "1";
		public final static String RECORD_IS_CLOSED_WAIT = "2";
	}

	/**
	 * 需要更换的设备类型
	 * @author anne.zhang
	 *
	 */
	public static final class EquipNeedReplaceType {
		public final static String NEED_NOT_REPLACE = "-1";  //不需要更换
		public final static String NEED_REPLACE = "1";  //需要更换


		public final static String DESKTOP_REPLACE = "0";	//换章盾电机
		public final static String PORTABLE_REPLACE = "1";	//换信印
		public final static String EMBEDED_REPLACE = "2";	//换翻斗章
		public final static String DESKTOP_AND_EMBEDED_REPLACE = "3"; //换章盾电机和翻斗章


	}
}
