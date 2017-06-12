package cn.utils;



import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlProperties {
	
	private static Logger logger=LoggerFactory.getLogger(UrlProperties.class);
	private static HashMap<String, String> map=new HashMap<String, String>();
	private	static	Properties  properties=null;
	static{
		properties=new Properties();
		
		
		try {
			properties.load(UrlProperties.class.getResourceAsStream("../../url.properties"));
			Set<Entry<Object, Object>> entries=properties.entrySet();
			Iterator<Entry<Object, Object>> iterable=entries.iterator();
			while(iterable.hasNext())
			{
				Entry<Object, Object> entry=iterable.next();
				Object key=entry.getKey();
				Object value=entry.getValue();
				if(key!=null&&value!=null)
				{
					map.put(key.toString(), value.toString());
					
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static String getValue(String key)
	{
		
		return map.get(key);
		
	}
	
	

}
