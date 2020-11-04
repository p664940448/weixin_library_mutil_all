package com.netmax.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class WeixinServlet extends HttpServlet {
    /** 
     * 校验Token 
     * @param request 
     * @param response 
     */  
    @Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
          
        String sVerifyEchoStr = request.getParameter("echostr");  
        String sEchoStr=null; //需要返回的明文  
        try {  
        	String sToken = "36KRwDPwq";//这个Token是随机生成，但是必须跟企业号上的相同
            String sCorpID = "wxf69b0ce157bf8fad";//这里是你企业号的CorpID
            String sEncodingAESKey = "nWqnHsIskqC4TcDUCVvxYhVu3Le5TruM0hdAIkpxVIQ";//这个EncodingAESKey是随机生成，但是必须跟企业号上的相同
            
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);  
            String sVerifyMsgSig = request.getParameter("msg_signature");  
            String sVerifyTimeStamp = request.getParameter("timestamp");  
            String sVerifyNonce = request.getParameter("nonce");  
              
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,  
                    sVerifyNonce, sVerifyEchoStr);  
        } catch (AesException e1) {  
            sEchoStr="ERR: "+e1.getCode()+ "\n\n";  
            e1.printStackTrace();         
        }  
          
        response.getWriter().print(sEchoStr);  
        response.getWriter().flush();  

    }  
}
