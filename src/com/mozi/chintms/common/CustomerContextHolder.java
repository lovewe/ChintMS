package com.mozi.chintms.common;

/**
 * 数据源适配
 * @author wx
 *
 */
public class CustomerContextHolder {
    public final static String DATA_SOURCE_DEFAULT = "dataSourceDefault";
    public final static String DATA_SOURCE_ASTRONERGY_BI_VIEW = "dataSourceAstronergyBiView";
    public final static String DATA_SOURCE_CHINT_BI_VIEW = "dataSourceChintBiView";
    public final static String DATA_SOURCE_CHINT_IC_VIEW = "dataSourceChintIcView";
    public final static String DATA_SOURCE_BI_BASIC =  "dataSourceBiBasic";
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);  
    }  
      
    public static String getCustomerType() {  
        return contextHolder.get();  
    }  
      
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  
}
