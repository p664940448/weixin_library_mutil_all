package com.netmax.library.model.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JsonFormat {
	//将list里的字段名按map指定的方式替换，并返回json串
    public static List  format(List list,HashMap map){
    	
    	List resultList=new ArrayList();
    	for(int i=0;i<list.size();i++){
    		HashMap item=(HashMap)list.get(i);
    		HashMap resultItem=new HashMap();
    		Iterator iter = map.entrySet().iterator();
    		while (iter.hasNext()) {
    		Map.Entry entry = (Map.Entry) iter.next();
    		    Object key = entry.getKey();
    	        Object val = entry.getValue();
    	        if(item.get(val)!=null){
    	        	resultItem.put(key, item.get(val));
    	        }
    		}
    		resultList.add(resultItem);    		
    	}
    	return resultList;
    }
}
