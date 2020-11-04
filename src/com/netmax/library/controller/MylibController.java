package com.netmax.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.netmax.library.model.service.BookService;
import com.netmax.library.model.service.LoginService;
import com.netmax.library.model.service.MylibService;

public class MylibController extends Controller {
    
    public long timeout=7200*1000;  //accessToken 2小时超时

	
	//判断是否登录
	public boolean isLogin(String accessToken){
		List<HashMap> userList=CacheKit.get("loginUsers", "userList");
		if(userList==null){
			return false;
		}else{
			boolean flag=false;
			for(HashMap user : userList){
				String tmp=user.get("accessToken").toString();
				if(tmp.equals(accessToken)){
					java.util.Date date1=(java.util.Date)user.get("createDate");
					java.util.Date now=new java.util.Date();
					if((now.getTime()- date1.getTime())>timeout){
						//超时
						flag=false;
					}else{
						//未超时
						flag=true;
					}
					break;
				}
			}
			return flag;
		}
	}
		
	//当前用户
	public String getCurrentUserCode(String accessToken){
		String userCode="";
		List<HashMap> userList=CacheKit.get("loginUsers", "userList");
		for(HashMap user : userList){
			String tmp=user.get("accessToken").toString();
			if(tmp.equals(accessToken)){
				java.util.Date date1=(java.util.Date)user.get("createDate");
				java.util.Date now=new java.util.Date();
				if((now.getTime()- date1.getTime())>timeout){
					//超时
					userCode="";
				}else{
					//未超时
					userCode=user.get("userCode").toString();
				}
				break;
			}
		}
		return userCode;
	}
	
	//是否登录
	public void checkLogin(){
		String accessToken=getPara("accessToken");
		HashMap result=new HashMap();
		if(isLogin(accessToken)){
			result.put("state", "T");
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//个人信息
	public void userInfo(){
		HashMap result=new HashMap();
		String accessToken=getPara("accessToken");
		if(isLogin(accessToken)){
			
			result.put("state", "T");
			result.put("userInfo", MylibService.userInfo(getCurrentUserCode(accessToken)));
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//获取逾期图书数量
	public void getLendExCount(){
		String userCode=getPara("userCode");
		HashMap result=new HashMap();
		result.put("lendExCount", MylibService.getLendExCount(userCode));
		renderJson(result);
	}
	
    //保存密码
	public void changePwd(){
		
		String password=getPara("password");
		String accessToken=getPara("accessToken");
		HashMap result=new HashMap();
		if(isLogin(accessToken)){
			result.put("state", "T");
			result.put("info", MylibService.changePwd(getCurrentUserCode(accessToken),password));
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//注销
	public void logout(){
		this.setSessionAttr("userCode", null);
		HashMap result=new HashMap();
		result.put("state", "T");
		renderJson(result);
	}
	
	//借阅列表
	public void lendList(){
		HashMap result=new HashMap();
		String accessToken=getPara("accessToken");
		if(isLogin(accessToken)){
			result.put("state", "T");
			result.put("lendList", MylibService.lendList(getCurrentUserCode(accessToken)));
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//逾期列表
	public void lendOverList(){
		HashMap result=new HashMap();
		String accessToken=getPara("accessToken");
		if(isLogin(accessToken)){
			result.put("state", "T");
			result.put("lendList", MylibService.lendOverList(getCurrentUserCode(accessToken)));
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//我的预借
	public void prelendList(){
		HashMap result=new HashMap();
		String accessToken=getPara("accessToken");
		if(isLogin(accessToken)){
			result.put("state", "T");
			result.put("lendList", MylibService.prelendList(getCurrentUserCode(accessToken)));
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//我的委托预约
	public void yuelendList(){
		HashMap result=new HashMap();
		String accessToken=getPara("accessToken");
		if(isLogin(accessToken)){
			result.put("state", "T");
			result.put("lendList", MylibService.yuelendList(getCurrentUserCode(accessToken)));
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//历史借阅
	public void lendHisList(){
		String thePage=getPara("thePage");
		if(thePage==null){
			thePage="1";
		}
		if(!isNumber(thePage)){
			thePage="1";
		}
		
		HashMap result=new HashMap();
		String accessToken=getPara("accessToken");
		if(isLogin(accessToken)){
			result.put("state", "T");
			result.put("lendList", MylibService.lendHisList(getCurrentUserCode(accessToken),thePage));
		}else{
			result.put("state", "F");			
		}		
		renderJson(result);
	}
	
	//预借处理，借鉴asp
	public void prelend(){
		String bookId=getPara("bookId");
		String accessToken=getPara("accessToken");
		
		System.out.println("userCode:"+getCurrentUserCode(accessToken));
		
		if(getCurrentUserCode(accessToken)==null){
			HashMap result=new HashMap();
			result.put("state", "nologin");
			renderJson(result);
		}else{
			renderJson(BookService.prelend(bookId,getCurrentUserCode(accessToken)));
		}
		
	}
	
	//取消预借
	public void canclePrelend(){
		String bookId=getPara("bookId");
		String b_no=getPara("b_no");
		String accessToken=getPara("accessToken");
		
		System.out.println("userCode:"+getCurrentUserCode(accessToken));
		
		if(getCurrentUserCode(accessToken)==null){
			HashMap result=new HashMap();
			result.put("state", "nologin");
			renderJson(result);
		}else{
			renderJson(BookService.canclePrelend(b_no,bookId,getCurrentUserCode(accessToken)));
		}
	}
	
	//取消委托预借
		public void cancleYuelend(){
			String bookId=getPara("bookId");
			String b_no=getPara("b_no");
			String accessToken=getPara("accessToken");
		
			if(getCurrentUserCode(accessToken)==null){
				HashMap result=new HashMap();
				result.put("state", "nologin");
				renderJson(result);
			}else{
				renderJson(BookService.cancleYuelend(b_no,bookId,getCurrentUserCode(accessToken)));
			}
		}
	
	//预约委托处理，借鉴asp
	public void yuelend(){
		String bookId=getPara("bookId");
		String accessToken=getPara("accessToken");
	
		if(getCurrentUserCode(accessToken)==null){
			HashMap result=new HashMap();
			result.put("state", "nologin");
			renderJson(result);
		}else{
			renderJson(BookService.yueLend(bookId,getCurrentUserCode(accessToken)));
		}
	}
	
	//我的收藏处理
	public void myStore(){
		String bookId=getPara("bookId");
		String accessToken=getPara("accessToken");
		if(getCurrentUserCode(accessToken)==null){
			HashMap result=new HashMap();
			result.put("state", "nologin");
			renderJson(result);
		}else{
			renderJson(BookService.myStore(bookId,getCurrentUserCode(accessToken)));
		}
	}
	
	
	
	//收藏列表
	public void myStoreList(){
		String accessToken=getPara("accessToken");
		renderJson(BookService.myStoreList(getCurrentUserCode(accessToken)));
	}
	
	//数字判断
	public boolean isNumber(String num){
		boolean result=true;
		try{
			Integer.parseInt(num);
		}catch(Exception e){
			result=false;
		}
		return result;
	}
	
	public void xujie(){
		String accessToken=getPara("accessToken");
		String lend_no=getPara("lend_no");

		renderJson(BookService.xujie(getCurrentUserCode(accessToken),lend_no));
	}
}
