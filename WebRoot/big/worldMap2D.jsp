﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>全国2D</title>
		<base href="<%=basePath%>">
		<meta name="generator" content="editplus" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	</head>
<body>
<div id="box">
	<div id="loading">
		<h1>loading...</h1>
	</div>
	<div id="content">
		<a href="#">
			<img id="logo" src="big/images/norse-white.png" alt="Norse Corp">
		</a>
		<div id="info"></div>

		<div class="data box top-left gray-bg">
			<a href="#" class="toggle" data-target="#left-table-container">
				<div class="table-header">
					<h1>
						<span class="icon-open"></span>
						发货方
					</h1>
				</div>
			</a>
			<div id="left-table-container" class="table-container">
				<table id="left-data">
					<colgroup>
						<col class="bar-col" span="1" />
						<col class="total-count" span="1" />
						<col class="data-label-small" span="1" />
						<col class="data-label" span="1" />
					</colgroup>
					<tr>
						<th></th>
						<th>数量</th>
						<th><span class="icon-country"></span></th>
						<th>地区</th>
					</tr>
				</table>
			</div>
		</div>

		<div class="data box top-right gray-bg">
			<a href="#" class="toggle" data-target="#right-table-container">
				<div class="table-header">
					<h1>
						<span class="icon-open"></span>
						收货方						</h1>
				</div>
			</a>
			<div id="right-table-container" class="table-container">
				<table id="right-data">
					<colgroup>
						<col class="bar-col" span="1">
						<col class="total-count" span="1">
						<col class="data-label-small" span="1">
						<col class="data-label" span="1">
					</colgroup>
					<tr>
						<th></th>
						<th>数量</th>
						<th class="flag-col"><span class="icon-country"></span></th>
						<th>地区</th>
					</tr>
				</table>
			</div>
		</div>
		<div id="console" class="box gray-bg">
			<a href="#" class="toggle" data-target="#console-table-container">
				<div class="table-header">
					<h1 class="table-header">
						<span class="icon-open"></span>
						销售列表
					</h1>
				</div>
			</a>
			<div id="console-table-container">
				<table id="events-data">
					<colgroup class="second-level">
						<col class="timestamp-col" span="1">
						<col class="service-col" span="1">
						<col class="attacker-org-col left-border" span="1">
						<col class="attacker-ip-col" span="1">
						<col class="service-col left-border" span="1">
						<col class="attacker-ip-col left-border" span="1">
					</colgroup>
					<tr class="second-level">
						<th>时间</th>
						<th>所在地</th>
						<th>客户名称</th>
						<th>销售地区</th>
						<th>产品类型</th>
						<th>销售额（元）</th>
					</tr>
				</table>
			</div>
		</div>
		<div class="info-controls">
			<div class="gray-bg information info-btn">
				<span class="info-text icon-info"></span>
			</div>
			<div data-paused="false" class="gray-bg controls">
				<span class="icon-pause play-pause"></span>
			</div>
		</div>
	</div>
	
	<div id="drawer">
		<div id="drawer-content" class="blue-bg">
			<div id="drawer-inner">
				<div id="drawer-left">
					<script type="text/javascript">
						var cpro_id = "u1789765";
					</script>
					<script src="#" type="text/javascript"></script>			
					<div id="legend-container">
						<div id="particle-legend">
							<h4><span class="icon-open"></span> <p>每个粒子代表一次销售</p></h4>
							<p id="particle-legend-content"></p>
						</div>
						<div id="origin-legend">
							<h4><span class="icon-open"></span> <p>这是销售组织</p></h4>
							<p>
								<span class="icon-one"></span>
								<span class="icon-ten"></span>
								<span class="icon-twohundred"></span>
							</p>
						</div>
						<div id="country-legend">
							<h4><span class="icon-open"></span> <p>这是销售区域</p></h4>
							<p>
								<span class="icon-one" style="color: #1e1e1e;"></span>
								<span class="icon-five" style="color: #1e2637;"></span>
								<span class="icon-twentyfive" style="color: #1e2e50;"></span>
								<span class="icon-onehundred" style="color: #1e3565;"></span>
								<span class="icon-fivehundred" style="color: #1e3d7e;"></span>								
							</p>
						</div>						
					</div>
					<footer>
						<div style="display: none;">
							<script type="text/javascript">
								var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
								document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F0269746fd173966c3ed921112fd5a8c2' type='text/javascript'%3E%3C/script%3E"));
							</script>
						</div>			
					</footer>
				</div>
			</div>
		</div>
	</div>
	<div id="message-panel" class="data box">
		<div class="table-header gray-bg">
			<h3>Message Dialog</h3>
		</div>
		<div id="message-container" class="black-solid table-container">
			<span id="message-text"></span>
			<button id="close-button" class="bggray" onClick="hideMessage();">关闭</button>
		</div>
	</div>

	<img id="unknown-icon" src="big/images/unknown-poly.png" />
</div>
</body>
</html>