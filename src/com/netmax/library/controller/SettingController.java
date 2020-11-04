package com.netmax.library.controller;

import java.math.BigDecimal;

import com.jfinal.core.Controller;
import com.netmax.library.model.BBook;
import com.netmax.library.model.service.SettingService;

public class SettingController extends Controller {
   
	//通知
	public void  getNotices(){	   
	   String thePage=getPara("thePage");
       if(thePage==null || "".equals(thePage)){
    	   thePage="1";
       }else{
    	   //不是数字
    	   try{
    		   Integer.parseInt(thePage);
    	   }catch(Exception e){
    		   thePage="1";
    	   }
       }
       
       renderJson(SettingService.getNotices(Integer.parseInt(thePage)));
   }
	
	//公告详情
	public void noticeDetail(){
		String id=getPara("id");
		renderJson(SettingService.noticeDetail(new BigDecimal(id)));
	}
	
	//指南
	public void guid(){
		String kind=getPara("kind");
		System.out.print(kind);
		renderJson(SettingService.guid(kind));
	}
	
	//地图信息
	public void mapInfo(){
		renderJson(SettingService.mapInfo());

	}
	
	//主分馆地址信息
	public void mapInfoMulti(){
		renderJson(SettingService.mapInfoMulti());
	}
	
	//主分馆模式取某官信息
	public void getMapInfo(String mapId){
		renderJson(SettingService.getMapInfo(mapId));
	}
	
	//指南列表
	public void getGuid(){
		renderJson(SettingService.getGuid());
	}
}
