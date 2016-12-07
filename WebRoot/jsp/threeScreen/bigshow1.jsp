<%@ page language="java" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String version = "20150413";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>大屏展示</title>
		<meta name="generator" content="editplus" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="keywords" content="大屏" />
		<meta http-equiv="description" content="éé¡¹" />
		<link rel="stylesheet" type="text/css" href="css/bigshow.css" />
		<script type="text/javascript">
			var basePath='<%=basePath%>';
		</script>
	</head>
	<body>
		<iframe src="<%=basePath%>/tableau/report.do?t=report107&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report108&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report109&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report110&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report111&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report112&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report113&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report114&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report115&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report116&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report117&userName=screenUser"></iframe>
		<iframe src="<%=basePath%>/tableau/report.do?t=report118&userName=screenUser"></iframe>
		<script type="text/javascript" src="../../big/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/bigshow.js"></script>
	</body>
</html>
