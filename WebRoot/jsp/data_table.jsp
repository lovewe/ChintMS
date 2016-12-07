<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>数据表</title>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
   <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
   <link href="css/style.css" rel="stylesheet" />
   <link href="css/style_responsive.css" rel="stylesheet" />
   <link href="css/style_default.css" rel="stylesheet" id="style_color" />

   <link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
   <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
   <!-- BEGIN HEADER -->
   <div id="header" class="navbar navbar-inverse navbar-fixed-top">
       <!-- BEGIN TOP NAVIGATION BAR -->
       <div class="navbar-inner">
           <div class="container-fluid">
               <!-- BEGIN LOGO -->
               <a class="brand" href="index.html">
                   <img src="img/logo.png" alt="Admin Lab" />
               </a>
               <!-- END LOGO -->
               <!-- BEGIN RESPONSIVE MENU TOGGLER -->
               <a class="btn btn-navbar collapsed" id="main_menu_trigger" data-toggle="collapse" data-target=".nav-collapse">
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="arrow"></span>
               </a>
               <!-- END RESPONSIVE MENU TOGGLER -->
               <div id="top_menu" class="nav notify-row">
                   <!-- BEGIN NOTIFICATION -->
                   <ul class="nav top-menu">
                       <!-- BEGIN SETTINGS -->
                       <li class="dropdown">
                           <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="#" data-original-title="Settings">
                               <i class="icon-cog"></i>
                           </a>
                       </li>
                       <!-- END SETTINGS -->
                       <!-- BEGIN INBOX DROPDOWN -->
                        <li class="dropdown" id="header_inbox_bar">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="icon-envelope-alt"></i>
                                <span class="badge badge-important">1</span>
                            </a>
                            <ul class="dropdown-menu extended inbox">
                                <li>
                                    <p>您有新消息！</p>
                                </li>
                                <li>
                                    <a href="#">
                                        <span class="photo"><img src="./img/avatar-mini.png" alt="avatar" /></span>
                  <span class="subject">
                  <span class="from">张三</span>
                  <span class="time">刚刚</span>
                  </span>
                  <span class="message">
                      你好，王总，报表已更新！
                  </span>
                                    </a>
                                </li>
                             
                                <li>
                                    <a href="#">查看全部</a>
                                </li>
                            </ul>
                        </li>
                        <!-- END INBOX DROPDOWN -->
            <!-- BEGIN NOTIFICATION DROPDOWN -->
            <li class="dropdown" id="header_notification_bar">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">

              <i class="icon-bell-alt"></i>
              <span class="badge badge-warning">7</span>
              </a>
              <ul class="dropdown-menu extended notification">
                <li>
                  <p>您有7条通知！</p>
                </li>
                <li>
                  <a href="#">
                  <span class="label label-important"><i class="icon-bolt"></i></span>
                  Server #3 overloaded.
                  <span class="small italic">34分钟</span>
                  </a>
                </li>
                <li>
                  <a href="#">
                  <span class="label label-warning"><i class="icon-bell"></i></span>
                  Server #10 not respoding.
                  <span class="small italic">1小时</span>
                  </a>
                </li>
                                <li>
                                    <a href="#">
                                        <span class="label label-important"><i class="icon-bolt"></i></span>
                                        Database overloaded 24%.
                                        <span class="small italic">4小时</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span class="label label-success"><i class="icon-plus"></i></span>
                                        New user registered.
                                        <span class="small italic">刚刚</span>
                                    </a>
                                </li>
                <li>
                  <a href="#">
                  <span class="label label-info"><i class="icon-bullhorn"></i></span>
                  Application error.
                  <span class="small italic">10分钟</span>
                  </a>
                </li>
                <li>
                  <a href="#">查看全部通知！</a>
                </li>
              </ul>
            </li>
            <!-- END NOTIFICATION DROPDOWN -->

          </ul>
                </div>
                    <!-- END  NOTIFICATION -->
                <div class="top-nav ">
                    <ul class="nav pull-right top-menu" >
                        <!-- BEGIN SUPPORT -->
                        <li class="dropdown mtop5">

                            <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="#" data-original-title="聊天">
                                <i class="icon-comments-alt"></i>
                            </a>
                        </li>
                        <li class="dropdown mtop5">
                            <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="#" data-original-title="帮助">
                                <i class="icon-headphones"></i>
                            </a>
                        </li>
                        <!-- END SUPPORT -->
            <!-- BEGIN USER LOGIN DROPDOWN -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="img/avatar1_small.jpg" alt="">
                                <span class="username">王全</span>
              <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="icon-user"></i>个人中心</a></li>
                <li><a href="#"><i class="icon-tasks"></i> 我的任务</a></li>
                <li><a href="#"><i class="icon-calendar"></i> 日历</a></li>
                <li class="divider"></li>
                <li><a href="login.html"><i class="icon-key"></i> 退出</a></li>
              </ul>
            </li>
            <!-- END USER LOGIN DROPDOWN -->
          </ul>
          <!-- END TOP NAVIGATION MENU -->
        </div>
      </div>
    </div>
    <!-- END TOP NAVIGATION BAR -->
  </div>
  <!-- END HEADER -->
  <!-- BEGIN CONTAINER -->
  <div id="container" class="row-fluid">
    <!-- BEGIN SIDEBAR -->
    <div id="sidebar" class="nav-collapse collapse">
      <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
      <div class="sidebar-toggler hidden-phone"></div>
      <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

      <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
      <div class="navbar-inverse">
        <form class="navbar-search visible-phone">
          <input type="text" class="search-query" placeholder="Search" />
        </form>
      </div>
      <!-- END RESPONSIVE QUICK SEARCH FORM -->
      <!-- BEGIN SIDEBAR MENU -->
      <ul class="sidebar-menu">
        <li class="has-sub">
          <a href="javascript:;" class="">
              <span class="icon-box"> <i class="icon-dashboard"></i></span>仪表盘
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">
                        <li><a class="" href="index.html">销售趋势</a></li>
                        <li><a class="" href="index_2.html">财务状况</a></li>

                    </ul>
        </li>
     
        <li class="has-sub active">
          <a href="javascript:;" class="">
          <span class="icon-box"><i class="icon-cogs"></i></span> 组件
          <span class="arrow"></span>
          </a>
          <ul class="sub">
                        <li><a class="" href="calendar.html">日历</a></li>
                        <li class="active"><a class="" href="data_table.html">数据表</a></li>
                    
                        <li><a class="" href="charts.html">视觉图</a></li>
                        <li><a class="" href="messengers.html">消息</a></li>
                        <li><a class="" href="gallery.html"> 图库</a></li>
                    </ul>
        </li>
                <li class="has-sub">
                    <a href="javascript:;" class="">
                        <span class="icon-box"><i class="icon-tasks"></i></span>样式
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">
                      
                        <li><a class="" href="form_wizard.html">向导</a></li>
                
                        <li><a class="" href="dropzone.html">文件上传</a></li>
                    </ul>
                </li>
        <li class="has-sub">
                 <a href="javascript:;" class="">
                 <span class="icon-box"><i class="icon-fire"></i></span> 图标
                 <span class="arrow"></span>
                 </a>
                 <ul class="sub">
                    <li><a class="" href="font_awesome.html">字体</a></li>
                      <li><a class="" href="glyphicons.html">字形图标</a></li>
                 </ul>
              </li>
        <li class="has-sub">
          <a href="javascript:;" class="">
          <span class="icon-box"><i class="icon-map-marker"></i></span>地图
          <span class="arrow"></span>
          </a>
          <ul class="sub">
            <li><a class="" href="maps_google.html"> 谷歌地图</a></li>
            <li><a class="" href="maps_vector.html"> 矢量图</a></li>
          </ul>
        </li>
        <li class="has-sub">
          <a href="javascript:;" class="">
          <span class="icon-box"><i class="icon-file-alt"></i></span> 样本页
          <span class="arrow"></span>
          </a>
                    <ul class="sub">
                        <li><a class="" href="blank.html">空白页</a></li>
                        <li><a class="" href="sidebar_closed.html">侧栏关闭页</a></li>
                        <li><a class="" href="coming_soon.html">订阅</a></li>
                        <li><a class="" href="blog.html">博客</a></li>
                        <li><a class="" href="about_us.html">关于我们</a></li>
                        <li><a class="" href="contact_us.html">联系我们</a></li>
                    </ul>
        </li>
                <li class="has-sub">
                    <a href="javascript:;" class="">
                        <span class="icon-box"><i class="icon-glass"></i></span> 扩展
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">
                        <li><a class="" href="lock.html">锁屏</a></li>
                        <li><a class="" href="profile.html">简介</a></li>
                        <li><a class="" href="invoice.html">发票联</a></li>
                        <li><a class="" href="pricing_tables.html">定价表</a></li>
                        <li><a class="" href="faq.html">FAQ</a></li>
                        <li><a class="" href="404.html">404错误</a></li>
                        <li><a class="" href="500.html">500错误</a></li>
                    </ul>
                </li>
        <li><a class="" href="login.html"><span class="icon-box"><i class="icon-user"></i></span> 登录页</a></li>
      </ul>
      <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div id="main-content">
      <!-- BEGIN PAGE CONTAINER-->
      <div class="container-fluid">
        <!-- BEGIN PAGE HEADER-->
        <div class="row-fluid">
          <div class="span12">
            <!-- BEGIN THEME CUSTOMIZER-->
            <div id="theme-change" class="hidden-phone">
              <i class="icon-cogs"></i>
              <span class="settings">
                                <span class="text">皮肤:</span>
                                <span class="colors">
                                    <span class="color-default" data-style="default"></span>
                                    <span class="color-gray" data-style="gray"></span>
                                    <span class="color-purple" data-style="purple"></span>
                                    <span class="color-navy-blue" data-style="navy-blue"></span>
                                </span>
              </span>
            </div>
            <!-- END THEME CUSTOMIZER-->
                  <!-- BEGIN PAGE TITLE & BREADCRUMB-->     
                  <h3 class="page-title">
                     数据表
                     <small>完整的数据表组件</small>
                  </h3>
                   <ul class="breadcrumb">
                       <li>
                           <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                       </li>
                       <li>
                           <a href="#">组件</a> <span class="divider">&nbsp;</span>
                       </li>
                       <li><a href="#">数据表</a><span class="divider-last">&nbsp;</span></li>
                   </ul>
                  <!-- END PAGE TITLE & BREADCRUMB-->
               </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->          
            <div class="row-fluid">
               <div class="span6">
                  <!-- BEGIN SAMPLE TABLE widget-->
                  <div class="widget">
                     <div class="widget-title">
                        <h4><i class="icon-reorder"></i>简单表</h4>
                        <span class="tools">
                        <a href="javascript:;" class="icon-chevron-down"></a>
                        <a href="javascript:;" class="icon-remove"></a>
                        </span>
                     </div>
                     <div class="widget-body">
                        <table class="table table-hover">
                           <thead>
                              <tr>
                                 <th>#</th>
                                 <th>姓</th>
                                 <th>名</th>
                                 <th class="hidden-phone">用户名</th>
                                 <th>状态</th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr>
                                 <td>1</td>
                                 <td>Rafiqul</td>
                                 <td>Islam</td>
                                 <td class="hidden-phone">dk123</td>
                                 <td><span class="label label-success">Approved</span></td>
                              </tr>
                              <tr>
                                 <td>2</td>
                                 <td>Mosaddek</td>
                                 <td>Hossain</td>
                                 <td class="hidden-phone">mos123</td>
                                 <td><span class="label label-info">Pending</span></td>
                              </tr>
                              <tr>
                                 <td>3</td>
                                 <td>Dulal</td>
                                 <td>Khan</td>
                                 <td class="hidden-phone">lorem</td>
                                 <td><span class="label label-warning">Suspended</span></td>
                              </tr>
                              <tr>
                                 <td>4</td>
                                 <td>Sumon</td>
                                 <td>Ahmed</td>
                                 <td class="hidden-phone">ispum</td>
                                 <td><span class="label label-danger">Blocked</span></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
                  <!-- END SAMPLE TABLE widget-->
               </div>
                <div class="span6">
                    <!-- BEGIN SAMPLE TABLE widget-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>条纹表</h4>
                        <span class="tools">
                        <a href="javascript:;" class="icon-chevron-down"></a>
                        <a href="javascript:;" class="icon-remove"></a>
                        </span>
                        </div>
                        <div class="widget-body">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>姓</th>
                                    <th>名</th>
                                    <th class="hidden-phone">用户名</th>
                                    <th>状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Rafiqul</td>
                                    <td>Islam</td>
                                    <td class="hidden-phone">dk123</td>
                                    <td><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Mosaddek</td>
                                    <td>Hossain</td>
                                    <td class="hidden-phone">mos123</td>
                                    <td><span class="label label-info">Pending</span></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Dulal</td>
                                    <td>Khan</td>
                                    <td class="hidden-phone">lorem</td>
                                    <td><span class="label label-warning">Suspended</span></td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Sumon</td>
                                    <td>Ahmed</td>
                                    <td class="hidden-phone">ispum</td>
                                    <td><span class="label label-danger">Blocked</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE widget-->
                </div>
            </div>
            <div class="row-fluid">
               <div class="span12">
                    <!-- BEGIN BORDERED TABLE widget-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>邻接表</h4>
                        <span class="tools">
                        <a href="javascript:;" class="icon-chevron-down"></a>
                        <a href="javascript:;" class="icon-remove"></a>
                        </span>
                        </div>
                        <div class="widget-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>姓</th>
                                    <th>名</th>
                                    <th class="hidden-phone">用户名</th>
                                    <th>状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Rafiqul</td>
                                    <td>Islam</td>
                                    <td class="hidden-phone">dk123</td>
                                    <td><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Mosaddek</td>
                                    <td>Hossain</td>
                                    <td class="hidden-phone">mos123</td>
                                    <td><span class="label label-info">Pending</span></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Dulal</td>
                                    <td>Khan</td>
                                    <td class="hidden-phone">lorem</td>
                                    <td><span class="label label-warning">Suspended</span></td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Sumon</td>
                                    <td>Ahmed</td>
                                    <td class="hidden-phone">ispum</td>
                                    <td><span class="label label-danger">Blocked</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END BORDERED TABLE widget-->
                </div>
            </div>
            <div class="row-fluid">
               <div class="span6">
                  <!-- BEGIN SAMPLE TABLE widget-->
                  <div class="widget">
                     <div class="widget-title">
                        <h4><i class="icon-cogs"></i>推进表</h4>
                        <span class="tools">
                        <a href="javascript:;" class="icon-chevron-down"></a>
                        <a href="javascript:;" class="icon-remove"></a>
                        </span>
                     </div>
                     <div class="widget-body">
                        <table class="table table-striped table-bordered table-advance table-hover">
                           <thead>
                              <tr>
                                 <th><i class="icon-briefcase"></i> Company</th>
                                 <th class="hidden-phone"><i class="icon-user"></i> Contact</th>
                                 <th><i class="icon-shopping-cart"></i> Total</th>
                                 <th></th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr>
                                 <td class="highlight">
                                    <div class="success"></div>
                                    <a href="#">KLK</a>
                                 </td>
                                 <td class="hidden-phone"> Rafiq</td>
                                 <td>2560.60$</td>
                                 <td><a href="#" class="btn mini purple"><i class="icon-edit"></i> Edit</a></td>
                              </tr>
                              <tr>
                                 <td class="highlight">
                                    <div class="info"></div>
                                    <a href="#">BGF</a>
                                 </td>
                                 <td class="hidden-phone">Mosaddek </td>
                                 <td>560.60$</td>
                                 <td><a href="#" class="btn mini black"><i class="icon-trash"></i> Delete</a></td>
                              </tr>
                              <tr>
                                 <td class="highlight">
                                    <div class="important"></div>
                                    <a href="#">ABC</a>
                                 </td>
                                 <td class="hidden-phone">Dulal Khan</td>
                                 <td>3460.60$</td>
                                 <td><a href="#" class="btn mini purple"><i class="icon-edit"></i> Edit</a></td>
                              </tr>
                              <tr>
                                 <td class="highlight">
                                    <div class="warning"></div>
                                    <a href="#">DEF</a>
                                 </td>
                                 <td class="hidden-phone">DKMOS </td>
                                 <td>2560.60$</td>
                                 <td><a href="#" class="btn mini blue"><i class="icon-share"></i> Share</a></td>
                              </tr>
                              <tr>
                                  <td class="highlight">
                                      <div class="important"></div>
                                      <a href="#">ABC</a>
                                  </td>
                                  <td class="hidden-phone">Sumon Ahmed</td>
                                  <td>3460.60$</td>
                                  <td><a href="#" class="btn mini purple"><i class="icon-edit"></i> Edit</a></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
                  <!-- END SAMPLE TABLE widget-->
               </div>
               <div class="span6">
                  <!-- BEGIN SAMPLE TABLE widget-->            
                  <div class="widget">
                     <div class="widget-title">
                        <h4><i class="icon-cogs"></i>推进表</h4>
                        <span class="tools">
                        <a href="javascript:;" class="icon-chevron-down"></a>
                        <a href="javascript:;" class="icon-remove"></a>
                        </span>
                     </div>
                     <div class="widget-body">
                        <table class="table table-striped table-bordered table-advance table-hover">
                           <thead>
                              <tr>
                                 <th><i class="icon-briefcase"></i> From</th>
                                 <th class="hidden-phone"><i class="icon-question-sign"></i> Descrition</th>
                                 <th><i class="icon-bookmark"></i> Total</th>
                                 <th></th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr>
                                 <td><a href="#">Pixel Ltd</a></td>
                                 <td class="hidden-phone">Server hardware purchase</td>
                                 <td>52560.10$ <span class="label label-success label-mini">Paid</span></td>
                                 <td><a href="#" class="btn mini green-stripe">View</a></td>
                              </tr>
                              <tr>
                                 <td>
                                    <a href="#">
                                    Smart House
                                    </a>  
                                 </td>
                                 <td class="hidden-phone">Office furniture purchase</td>
                                 <td>5760.00$ <span class="label label-warning label-mini">Pending</span></td>
                                 <td><a href="#" class="btn mini blue-stripe">View</a></td>
                              </tr>
                              <tr>
                                 <td>
                                    <a href="#">
                                    FoodMaster Ltd
                                    </a>
                                 </td>
                                 <td class="hidden-phone">Company Anual Dinner Catering</td>
                                 <td>12400.00$ <span class="label label-success label-mini">Paid</span></td>
                                 <td><a href="#" class="btn mini blue-stripe">View</a></td>
                              </tr>
                              <tr>
                                 <td>
                                    <a href="#">
                                    WaterPure Ltd
                                    </a>
                                 </td>
                                 <td class="hidden-phone">Payment for Jan 2013</td>
                                 <td>610.50$ <span class="label label-danger label-mini">Overdue</span></td>
                                 <td><a href="#" class="btn mini red-stripe">View</a></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
                  <!-- END SAMPLE TABLE widget-->
               </div>
            </div>
            <!-- BEGIN ADVANCED TABLE widget-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN EXAMPLE TABLE widget-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>管理表</h4>
                            <span class="tools">
                                <a href="javascript:;" class="icon-chevron-down"></a>
                                <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <table class="table table-striped table-bordered" id="sample_1">
                            <thead>
                                <tr>
                                    <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
                                    <th>Username</th>
                                    <th class="hidden-phone">Email</th>
                                    <th class="hidden-phone">Points</th>
                                    <th class="hidden-phone">Joined</th>
                                    <th class="hidden-phone"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>shuxer</td>
                                    <td class="hidden-phone"><a href="mailto:shuxer@gmail.com">shuxer@gmail.com</a></td>
                                    <td class="hidden-phone">120</td>
                                    <td class="center hidden-phone">12 Jan 2012</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>looper</td>
                                    <td class="hidden-phone"><a href="mailto:looper90@gmail.com">looper90@gmail.com</a></td>
                                    <td class="hidden-phone">120</td>
                                    <td class="center hidden-phone">12.12.2011</td>
                                    <td class="hidden-phone"><span class="label label-warning">Suspended</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>userwow</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@yahoo.com">userwow@yahoo.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>user1wow</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-inverse">Blocked</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>restest</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>foopl</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">19.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>weep</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">19.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>coop</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">19.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>pppol</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">19.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>test</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">19.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>userwow</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-inverse">Blocked</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>test</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>goop</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>weep</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">15.11.2011</td>
                                    <td class="hidden-phone"><span class="label label-inverse">Blocked</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>toopl</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">16.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>userwow</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">9.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-inverse">Blocked</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>tes21t</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">14.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>fop</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">13.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-warning">Suspended</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>kop</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">17.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>vopl</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">19.11.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>userwow</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-inverse">Blocked</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>wap</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">12.12.2012</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>test</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">19.12.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>toop</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">17.12.2010</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td>weep</td>
                                    <td class="hidden-phone"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>
                                    <td class="hidden-phone">20</td>
                                    <td class="center hidden-phone">15.11.2011</td>
                                    <td class="hidden-phone"><span class="label label-success">Approved</span></td>
                                </tr>
                                </tbody>
                        </table>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE widget-->
                </div>
            </div>

            <!-- END ADVANCED TABLE widget-->

            <!-- END PAGE CONTENT-->
         </div>
         <!-- END PAGE CONTAINER-->
      </div>
      <!-- END PAGE -->
   </div>
   <!-- END CONTAINER -->
   <!-- BEGIN FOOTER -->
   <div id="footer">
       2015 &copy; 数字正泰-Chint Data.
      <div class="span pull-right">
         <span class="go-top"><i class="icon-arrow-up"></i></span>
      </div>
   </div>
   <!-- END FOOTER -->
   <!-- BEGIN JAVASCRIPTS -->
   <!-- Load javascripts at bottom, this will reduce page load time -->
   <script src="js/jquery-1.8.3.min.js"></script>
   <script src="assets/bootstrap/js/bootstrap.min.js"></script>   
   <script src="js/jquery.blockui.js"></script>
   <!-- ie8 fixes -->
   <!--[if lt IE 9]>
   <script src="js/excanvas.js"></script>
   <script src="js/respond.js"></script>
   <![endif]-->   
   <script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
   <script type="text/javascript" src="assets/data-tables/jquery.dataTables.js"></script>
   <script type="text/javascript" src="assets/data-tables/DT_bootstrap.js"></script>
   <script src="js/scripts.js"></script>
   <script>
      jQuery(document).ready(function() {       
         // initiate layout and plugins
         App.init();
      });
   </script>
</body>
<!-- END BODY -->
</html>

