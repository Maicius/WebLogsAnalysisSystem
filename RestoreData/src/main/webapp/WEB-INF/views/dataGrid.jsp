<%@ page import="com.sun.org.apache.xpath.internal.operations.Div" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>网站服务器日志分析</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
</head>
<body>
<div class="wrapper">
    <!--navbar top-->
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="adjust-nav">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><i class="fa fa-square-o "></i>&nbsp;数据表格</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/queryCourtData.action"><i class="fa fa-search "></i>&nbsp;数据查询&nbsp;</a></li>
                    <li><a href="${pageContext.request.contextPath}/queryData.action"><i class="fa fa-area-chart"></i>&nbsp;数据一览&nbsp;</a></li>
                    <li><a href="${pageContext.request.contextPath}/queryDataGrid.action"><i class="fa fa-delicious "></i>&nbsp;数据表格&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
    </div>
    <footer class="footer navbar-fixed-bottom ">
        <div class="p-footer">
            <p>天津市高级人民法院@copyright</p>
        </div>
    </footer>
</div>


<script src="../../assets/js/jquery.min.js"></script>
<script src="../../assets/js/bootstrap.js"></script>
<script src="../../assets/js/echarts.common.js"></script>
<script src="../../assets/js/myCharts.js"></script>
<script src="../../assets/js/inputCheck.js"></script>
<script type="text/javascript" src="../../assets/js/bootstrapDatepickr-1.0.0.min.js" charset="UTF-8"></script>
<!-- Latest compiled and minified JavaScript -->
<script type="text/javascript" src="../../assets/js/bootstrap-select.js" charset="UTF-8"></script>

<script>
    $(document).ready(function () {
        $("#calendarBegin").bootstrapDatepickr({date_format: "Y-m-d"});
    });
    $(window).on('load', function () {
        $('#courtSelect').selectpicker({
            'selectedText': 'cat'
        });
    });
</script>

<link href="../../assets/css/bootstrapDatepickr-1.0.0.css" rel="stylesheet">
<link rel="stylesheet" href="../../assets/css/bootstrap.css" type="text/css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="../../assets/css/bootstrap-select.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
<!-- FONTAWESOME STYLES-->
<link rel="stylesheet" href="../../assets/css/font-awesome.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!--MY CUSTOM STYLE-->
<link href="../../assets/css/myStyle.css" rel="stylesheet"/>


</body>
</html>