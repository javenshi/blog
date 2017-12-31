package com.centling.utils;

import java.net.URLEncoder;

import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 *
 */
public class HeaderUtil {

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-yzstyleApp-alert", message);
        headers.add("X-yzstyleApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("增加成功", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("更新成功", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("删除成功", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
      
        	String utf8Message = URLEncoder.encode(defaultMessage);
	
        headers.add("X-yzstyleApp-error", utf8Message);
        headers.add("X-yzstyleApp-params", entityName);
        return headers;
    }
}
