package com.httpframe.httpframe;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class JsonUtil {
	
	
	 /**
     * 将json转化成map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object>   convertJsonStrToMap(String jsonStr){
        
        Map<String, Object> map = JSON.parseObject(
                jsonStr,new TypeReference<Map<String, Object>>(){} );
        
        return map;
    }
    /*
     * map转json
     */

    
    
}
