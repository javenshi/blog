/*
 * FileUtils。java
 *
 * Created Date: 2015年1月8日
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
 * @author ted.ma
 * @version  <br>
 * <p>文件上传解析工具类</p>
 */

public class FileUtils {
	/**
     * get postfix of the path
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (path == null || "".equals(path.trim())) {
            return "";
        }
        if (path.contains(".")) {
            return path.substring(path.lastIndexOf(".") + 1, path.length());
        }
        return "";
    }
    
}
