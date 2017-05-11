package com.nkang.kxmoment.service;

/**
 * Copyright 2010 Hewlett-Packard. All rights reserved. <br>
 * HP Confidential. Use is subject to license terms.
 */

//import com.siperian.sif.message.Record;

/**
 * Class StringUtils.java
 * @author Aaron Yuan (qing-fei.yuan@hp.com)
 * @since 2013-6-21
 */
public class StringUtils {

    public static Boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static String transferEmptyStringToNull(String str) {
        if (str == null || "".equals(str.trim())) {
            str = null;
        }
        return str;
    }
    
    public static Boolean codeLenght(String code){
    	if(code.length() >= 1 && code.length() <= 20){
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public static Boolean nameLength(String name){
    	if(name.length() >= 1 && name.length() <= 50){
    		return true;
    	}else {
    		return false;
    	}
    }

    public static Boolean descriptionLength(String desc){
    	if(desc.length() <= 500){
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public static Boolean rowidObjectLength(String rowidObject){
    	if(rowidObject.length() >= 1 && rowidObject.length() <= 14){
    		return true;
    	}else {
    		return false;
    	}
    }
    
/*    public static String getStringValue(Record aRecord, String fieldName) {
        String aValue = null;

        if (aRecord.getField(fieldName) != null && aRecord.getField(fieldName).getStringValue() != null) {
            aValue = aRecord.getField(fieldName).getStringValue().trim();
        }

        return aValue;
    }*/
}