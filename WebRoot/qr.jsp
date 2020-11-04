<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="com.netmax.library.common.QRcode"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@ page trimDirectiveWhitespaces="true" %>
<% 
String content=(String)request.getParameter("content");
String kind=request.getParameter("kind");
if(kind==null){
	kind="barcode";
}
BufferedImage img=null;
QRcode qr=new QRcode();
if(kind.equals("qr")){
	img=qr.getQR(content, 147);	
}else{
	img=qr.getBarCode(content, 120);	
}


out.clear();
out = pageContext.pushBody();
response.setContentType("image/jpeg");

OutputStream outs = response.getOutputStream(); 
ImageIO.write(img, "jpg", outs);

%>
