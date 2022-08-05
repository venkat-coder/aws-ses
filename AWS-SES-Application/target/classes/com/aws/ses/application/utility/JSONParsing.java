package com.aws.ses.application.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JSONParsing {

	public static Map<String, Object>bodyToMap(String parentKey, JSONObject object) throws JSONException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);
			if(value instanceof JSONArray)
				//value = toList(key, (JSONObject)value);
	
			if(value instanceof JSONArray)
				value = bodyToMap(key, (JSONObject)value);
			
		}
		
		
		return null;
		
	}
}
