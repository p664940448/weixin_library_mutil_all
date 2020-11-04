package com.netmax.library.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.netmax.library.model.service.LoginService;
import com.netmax.library.model.service.MylibService;

public class loginController extends Controller {
	 public long timeout=7200*1000;  //accessToken 2小时超时
	//用户登录
	public void checkPwd(){
		HashMap result=new HashMap();		
		String userCode=getPara("userCode");
		String userPwd=getPara("userPwd");
		
		if(userCode==null || userPwd==null){
			//result.put("state", "F");
		}else{
			if(LoginService.checkPwd(userCode, userPwd)){
				setSessionAttr("userCode",userCode);
				result.put("state", "T");
				
				HashMap loginUser=new HashMap();
				loginUser.put("userCode", userCode);
				loginUser.put("createDate", new java.util.Date());
				String accessToken=getUUID();
				loginUser.put("accessToken", accessToken);
				addLoginUser(loginUser);
				
				result.put("accessToken", accessToken);
			}else{
				result.put("state", "F");
			}
		}
		//System.out.print(getSessionAttr("userCode"));
		renderJson(result);
	}
	
	//用户登录全ID验证
	public void checkPwdAll(){
		HashMap result=new HashMap();		
		String userCode=getPara("userCode");
		String userPwd=getPara("userPwd");
		
		if(userCode==null || userPwd==null){
			//result.put("state", "F");
		}else{
			String userCodeXX=LoginService.checkPwdAll(userCode, userPwd);
			System.out.print(userCodeXX+"===");
			if(!userCodeXX.equals("")){
				setSessionAttr("userCode",userCodeXX);
				result.put("state", "T");
				result.put("userCodeXX", userCodeXX);
				HashMap loginUser=new HashMap();
				loginUser.put("userCode", userCodeXX);
				loginUser.put("createDate", new java.util.Date());
				String accessToken=getUUID();
				loginUser.put("accessToken", accessToken);
				addLoginUser(loginUser);
				
				result.put("accessToken", accessToken);
			}else{
				result.put("state", "F");
			}
		}
		//System.out.print(getSessionAttr("userCode"));
		renderJson(result);
	}
	
	
	//判断是否登录
	public void isLogin(){
		HashMap result=new HashMap();
		if(getSessionAttr("userCode")==null){
			result.put("state", "F");
		}else{
			result.put("state", "T");
		}
		
		renderJson(result);
	}
	
	//退出登录
	public void logout(){
		String accessToken=getPara("accessToken");
		List<HashMap> userList=CacheKit.get("loginUsers", "userList");
		if(userList!=null){
			for(HashMap user : userList){
				if(accessToken.equals(user.get("accessToken").toString())){
					userList.remove(user);
					break;
				}
			}
		}
		CacheKit.put("loginUsers", "userList", userList);
		HashMap result=new HashMap();
		result.put("state", "T");
		renderJson(result);
	}
	
	/**
	 * 取UUID
	 */
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	
	/**
	 * 添加登录用户
	 * @param loginUser
	 */
	public static void addLoginUser(HashMap loginUser){
		List<HashMap> userList=CacheKit.get("loginUsers", "userList");
		if(userList==null){
			userList=new ArrayList();
			userList.add(loginUser);
		}else{
			//去除重复的userCode
			for(HashMap user: userList){
				if(user.get("userCode").toString().equals(loginUser.get("userCode").toString())){
					userList.remove(user);
					break;
				}
			}
			userList.add(loginUser);
		}
		CacheKit.put("loginUsers", "userList", userList);
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
	
	/**
	 * 注册用户
	 */
	public void regUser(){
		String mphone=getPara("mphone");
		String userName=getPara("userName");
		String idcard=getPara("idcard");
		String checkcode=getPara("checkcode");
		String email=getPara("email");
		String com=getPara("com");
		String address=getPara("address");
		String password=getPara("password");
		String message=MylibService.regUser(mphone, userName, idcard, email, address, com,password);
		HashMap result=new HashMap();
		if(message.equals("")){
			result.put("message", "注册成功");
			result.put("state", "OK");
		}else{
			result.put("message", message);
			result.put("state", "ERROR");
		}
		
		renderJson(result);
	}
	
	/**
	 * 发送注册码
	 */
	public void sendCheckcode(){
		String mphone=getPara("mphone");
		int checkCode=(int)((Math.random()*9+1)*100000);  //6个数字的验证码
		System.out.println("checkcode:"+checkCode);
		HashMap result=new HashMap();
		result.put("state", "OK");
		result.put("checkcode", checkCode);
		
		LoginService.sendCheckcode(mphone, checkCode+"");		
		renderJson(result);
	}
	
	/**
	 * 取注册码
	 */
	public void getCheckcode(){
		HashMap result=new HashMap();		
		String mphone=getPara("mphone");
		if(mphone==null){
			result.put("state", "ERROR");
			result.put("checkcode", "");
		}
		
		String checkcode=LoginService.getCheckcode(mphone);
		if(checkcode.equals("")){
			result.put("state", "ERROR");
			result.put("checkcode", "");
		}else{
			result.put("state", "OK");
			result.put("checkcode", checkcode);
		}
		renderJson(result);
	}
	
	/**
	 * 修改密码
	 * 
	 */
	public void changePwd(){
		String mphone=getPara("mphone");
		String password=getPara("password");
		String checkcode=getPara("checkcode");
		renderJson(LoginService.changePwdApp(mphone,password,checkcode));
		
	}
	
	/**
	 * 获取登录方式
	 */
	public void getLoginKind(){
		renderJson(LoginService.getLoginKind());
	}
	
	/**
	 * 注册时发短信还是发邮件
	 * 
	 */
	public void getSendBy(){
		renderJson(LoginService.getSendBy());
	}
}
