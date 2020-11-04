<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
java.io.OutputStream os = null;
Object obj=request.getAttribute("pic");
if(obj==null){
	response.sendRedirect("/libraryServer/index/book.png");	
}else{
	byte[] pic=(byte[])obj;	
	response.setContentType("image/jpeg");
	ServletOutputStream sos = response.getOutputStream();  
	sos.write(pic);  
	sos.flush();  
	sos.close(); 
}
%>