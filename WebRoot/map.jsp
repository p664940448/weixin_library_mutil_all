<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ page import="com.netmax.library.model.service.SettingService" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		</style>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZVX8fql0h9SYfelS6aiRY41jPvvzQuzS"></script>
	</head>
	<body>
		<div id="allmap">
		</div>
	</body>
	<%
	HashMap result=new HashMap();
	String mapId=request.getParameter("mapId");
    result=SettingService.getMapInfo(mapId);
    String lat=result.get("mapLat").toString();
    String lon=result.get("mapLon").toString();
    String mapName=result.get("mapName").toString();
    String defaultCity=result.get("mapDefaultCity").toString();
    
	%>
	<script>
	    var mapName="<%=mapName%>";
	    var lat=<%=lat%>;
	    var lon=<%=lon%>;
	    var defaultCity="<%=defaultCity%>";
	    
		var map = new BMap.Map("allmap");
	    var point = new BMap.Point(lon,lat);
		// 百度地图API功能		
		map.centerAndZoom(point,16);
		map.setCurrentCity(defaultCity); 
		map.enableScrollWheelZoom(); 
		var marker = new BMap.Marker(point); // 创建点
		map.addOverlay(marker);            //增加点
	</script>
</html>
