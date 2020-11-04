package com.netmax.library.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.netmax.library.model.BReader;
import com.netmax.library.model.Recmsg;

public class LoginService {
	
	//登录验证
    public static boolean checkPwd(String userCode,String userPwd){
    	BReader reader= BReader.dao.findFirst("select * from b_reader where read_card=? and read_pw=?",userCode,userPwd);
    	if(reader==null){
    		return false;
    	}else{
    		return true;
    	}
    	
    }
    
    //支持多种id登录验证
    public static String checkPwdAll(String code,String userPwd){
    	String userCode="";
    	BReader reader= BReader.dao.findFirst("select * from b_reader where read_card=? and read_pw=?",code,userPwd);
    	if(reader!=null){
    		return reader.getReadCard();
    	}
    	
    	String checkEmail="F";
		String checkPhone="F";
		String checkId="F";
		
		String sql="select thevalue from app_config where thekind='app' and thekey='checkEmail' and flag='T' ";
		Record rs=Db.findFirst(sql);
		if(rs!=null && rs.getStr("thevalue").equals("T")){
			checkEmail="T";
		}
		
		String sql1="select thevalue from app_config where thekind='app' and thekey='checkPhone' and flag='T' ";
		Record rs1=Db.findFirst(sql1);
		if(rs1!=null && rs1.getStr("thevalue").equals("T")){
			checkPhone="T";
		}
		
		String sql2="select thevalue from app_config where thekind='app' and thekey='checkId' and flag='T' ";
		Record rs2=Db.findFirst(sql2);
		if(rs2!=null && rs2.getStr("thevalue").equals("T")){
			checkId="T";
		}
		
		
		if(checkEmail.equals("T")){
			sql="select * from b_reader where read_email=?";
			reader= BReader.dao.findFirst(sql,code);
			if(reader!=null){
				return reader.getReadCard();
			}
		}
		
		if(checkPhone.equals("T")){
			sql="select * from b_reader where read_mobile=?";
			reader= BReader.dao.findFirst(sql,code);
			if(reader!=null){
				return reader.getReadCard();
			}
		}
		
		if(checkId.equals("T")){
			sql="select * from b_reader where read_id=?";
			reader= BReader.dao.findFirst(sql,code);
			if(reader!=null){
				return reader.getReadCard();
			}
		}
		
    	return userCode;
    }
    
    //发送验证码，其实就是把数据存在数据库里
    public static void sendCheckcode(String phone,String checkcode){
    	Recmsg msg=new Recmsg();
    	msg.setPhone(phone);
    	msg.setChecknum(checkcode);
    	msg.save();
    }
    
    //修改密码
    public static HashMap<String,String> changePwdApp(String mphone,String password,String checkcode){
    	
    	HashMap result=new HashMap();
    	String sql="select count(*) as shu from b_reader where read_mobile=?";
    	int shu=Db.queryInt(sql,mphone);
    	if(shu<=0){
    		result.put("state", "ERROR");
    		result.put("message", "手机号码未注册！");
    		return result;
    	}
    	
    	sql="select count(*) as shu from recmsg where phone=? and checkcode=?";
    	int shu1=Db.queryInt(sql,mphone,checkcode);
    	if(shu1<=0){
    		result.put("state", "ERROR");
    		result.put("message", "非法请求！");
    		return result;
    	}
    	
		sql="update b_reader set read_pw=? where read_mobile=?";
		Db.update(sql,password,mphone);
		result.put("state", "OK");
		result.put("message", "密码修改成功！");
		return result;
	}
    
    /**
	 * 获取允许的登录方式
	 * @return
	 */
	public static HashMap getLoginKind(){
		HashMap result=new HashMap();
		
		String checkEmail="F";
		String checkPhone="F";
		String checkId="F";
		
		String sql="select thevalue from app_config where thekind='app' and thekey='checkEmail' and flag='T' ";
		Record rs=Db.findFirst(sql);
		if(rs!=null && rs.getStr("thevalue").equals("T")){
			checkEmail="T";
		}
		
		String sql1="select thevalue from app_config where thekind='app' and thekey='checkPhone' and flag='T' ";
		Record rs1=Db.findFirst(sql1);
		if(rs1!=null && rs1.getStr("thevalue").equals("T")){
			checkPhone="T";
		}
		
		String sql2="select thevalue from app_config where thekind='app' and thekey='checkId' and flag='T' ";
		Record rs2=Db.findFirst(sql2);
		if(rs2!=null && rs2.getStr("thevalue").equals("T")){
			System.out.print(rs2.getStr("thevalue"));
			checkId="T";
		}
		
		result.put("checkEmail", checkEmail);
		result.put("checkPhone", checkPhone);
		result.put("checkId", checkId);
		
		return result;
	}
	
	/**
	 * 获取验证码
	 * @param mphone
	 * @return
	 */
	public static String getCheckcode(String mphone){
		String sql="select top 1 * from recmsg where phone=? and  DATEDIFF([second],rectime,getdate())<=600 order by rectime desc ";
		Record rs=Db.findFirst(sql,mphone);
		
		if(rs==null){
			return "";
		}else{
			return rs.getStr("checknum");
		}
	}
	
	/**
	 * 注册用户时发短信还是邮件
	 * @return
	 */
	public static HashMap getSendBy(){
		HashMap result=new HashMap();
		String sql="select theValue from app_config where thekind='app' and theKey='sendByEmailorPhone' ";
		Record rs=Db.findFirst(sql);
		
		if(rs==null){
			result.put("sendKind", "phone");
		}else{
			result.put("sendKind", rs.getStr("theValue"));
		}
		result.put("state", "OK");
		return result;
	}
}
