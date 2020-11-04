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
	<script>
		var map = new BMap.Map("allmap");
	    var point = new BMap.Point(116.331398,39.897445);
		// 百度地图API功能		
		map.centerAndZoom(point,12);
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint("北京市海淀区上地10街", function(point){
			if (point) {
				map.centerAndZoom(point, 16);
				map.addOverlay(new BMap.Marker(point));
			}else{
				alert("您选择地址没有解析到结果!");
			}
		}, "北京市");
	</script>
</html>
