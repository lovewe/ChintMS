<%@ page language="java" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String version = "20150413";%>

<script type="text/javascript">
var mozi = mozi || {};
mozi.contextPath = '<%=contextPath%>';
mozi.basePath = '<%=basePath%>';
mozi.version = '<%=version%>';
</script>
<link href="<%=contextPath%>/static/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/css/jquery.confirm.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/css/style.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/css/style_responsive.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/css/style_blue.css" rel="stylesheet" id="style_color" />
<link href="<%=contextPath%>/static/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
<link href="<%=contextPath%>/static/assets/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=contextPath%>/static/css/zTree.css" type="text/css">
<!-- BEGIN JAVASCRIPTS -->    
<!-- Load javascripts at bottom, this will reduce page load time -->
<script type="text/javascript" src="<%=contextPath%>/static/js/jquery-1.8.3.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/assets/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/jquery.blockui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/jquery.confirm.js" charset="utf-8"></script>
<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=contextPath%>/static/js/excanvas.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/respond.js" charset="utf-8"></script>
<![endif]-->
<script type="text/javascript" src="<%=contextPath%>/static/assets/chosen-bootstrap/chosen/chosen.jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/assets/uniform/jquery.uniform.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/scripts.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/util.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/jqutil.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath %>/static/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=contextPath %>/static/assets/data-tables/DT_bootstrap.js"></script>
<script type="text/javascript" src="<%=contextPath %>/static/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/assets/data-tables/jquery.dataTables.fnReloadAjax.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/jquery.ztree.excheck-3.5.js"></script>
<!-- END JAVASCRIPTS -->