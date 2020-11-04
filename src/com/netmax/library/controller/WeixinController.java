package com.netmax.library.controller;

import java.io.PrintWriter;
import java.util.Arrays;

import com.jfinal.core.Controller;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class WeixinController extends Controller{
	
    
    public void index(){
    	String sToken = "36KRwDPwq";//这个Token是随机生成，但是必须跟企业号上的相同
        String sCorpID = "wxf69b0ce157bf8fad";//这里是你企业号的CorpID
        String sEncodingAESKey = "nWqnHsIskqC4TcDUCVvxYhVu3Le5TruM0hdAIkpxVIQ";//这个EncodingAESKey是随机生成，但是必须跟企业号上的相同
        
         //微信加密签名
    	String sVerifyMsgSig=getPara("msg_signature");
    	String sVerifyTimeStamp=getPara("timestamp");
    	String sVerifyNonce=getPara("nonce");
    	String sVerifyEchoStr=getPara("echostr");
    	String sEchoStr;
    	
    	try{
	    	WXBizMsgCrypt wxcpt;
	    	wxcpt=new WXBizMsgCrypt(sToken,sEncodingAESKey,sCorpID);
	    	sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,sVerifyNonce, sVerifyEchoStr);
	    	System.out.println(sEchoStr);	    	
    	}catch(AesException e){
    		sEchoStr="ERR: "+e.getCode()+ "\n\n"; 
    		e.printStackTrace();
    	}
    	renderText(sEchoStr);
    }
    
    //订阅号
    public void index_2(){
    	String sToken = "1q2w3e4r";//这个Token是随机生成，但是必须跟企业号上的相同
        String sCorpID = "wx48223a696f6c582e";//这里是你企业号的CorpID
        String sEncodingAESKey = "U1OpCvnqb4qpxztAGrcSbpiwad922TYmKFRhk1W1SXL";//这个EncodingAESKey是随机生成，但是必须跟企业号上的相同
        
         //微信加密签名
    	String sVerifyMsgSig=getPara("signature");
    	String sVerifyTimeStamp=getPara("timestamp");
    	String sVerifyNonce=getPara("nonce");
    	String echostr=getPara("echostr");
    	String sEchoStr;
    	
    	try{

	    	String[] str = { sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce };
	       // Arrays.sort(str); // 字典序排序
	        //String bigStr = str[0] + str[1] + str[2];
	        // SHA1加密
	       // String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
	    	//System.out.println(digest);	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	renderText(echostr);
    }
    
}
