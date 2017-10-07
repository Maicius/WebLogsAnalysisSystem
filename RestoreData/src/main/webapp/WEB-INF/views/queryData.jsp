<%@ page import="com.sun.org.apache.xpath.internal.operations.Div" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>天津法院网站服务器日志分析</title>
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
                <a class="navbar-brand" href="#"><i class="fa fa-square-o "></i>&nbsp;数据查询</a>
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
        <h2 class="h-title">网站服务器日志数据统计分析</h2>
        <!--条件查询-->
        <div class="mg-top-20 panel panel-primary panel-body dateContainer">
            <div class="row">
                <div class="col-md-3">
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1"><i class="fa fa-calendar"></i></span>
                        <input type="text" id="calendarBegin" placeholder="选择要查看的日期" class="form-control">
                    </div>
                    <p class="h-title">起始日期</p>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <select id="courtSelect" name="courtSelect" class="selectpicker show-tick form-control"
                                mutiple title="选择要查询的法院" multiple data-live-search="true" data-actions-box="true">
                            <option value="200">市高级法院</option>
                            <option value="210">第一中级法院</option>
                            <option value="220">第二中级法院</option>
                            <option value="230">海事法院</option>
                            <option value="211">和平区法院</option>
                            <option value="212">南开区法院</option>
                            <option value="222">河西区法院</option>
                            <option value="221">河东区法院</option>
                            <option value="213">河北区法院</option>
                            <option value="214">红桥区法院</option>
                            <option value="22A">滨海新区法院</option>
                            <option value="223">塘沽审判区</option>
                            <option value="224">汉沽审判区</option>
                            <option value="225">大港审判区</option>
                            <option value="229">功能区审判区</option>
                            <option value="226">东丽区法院</option>
                            <option value="227">津南区</option>
                            <option value="215">西青区</option>
                            <option value="216">北辰区</option>
                            <option value="217">武清区</option>
                            <option value="219">宝坻区</option>
                            <option value="218">静海区</option>
                            <option value="228">宁河区</option>
                            <option value="21A">蓟县区</option>
                            <option value="132">铁路区</option>
                        </select>
                    </div>
                    <p class="h-title">法院</p>
                </div>
                <div class="col-md-1">
                    <button class="btn btn-primary" id="conditionQuery" disabled>查询</button>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>

        <!--数据展示-->
        <div class="row">
            <!--四个饼图.-->
            <div class="col-md-4 col-sm-4 col-xs-6 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body accssTime" id="accessChartQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>网站访问次数</p>-->
                    <!--</div>-->
                </div>
            </div>

            <div class="col-md-4 col-sm-4 col-xs-6 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body accssTime" id="totalFlowQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>产生总流量</p>-->
                    <!--</div>-->
                </div>
            </div>

            <div class="col-md-4 col-sm-4 col-xs-6 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body accssTime" id="totalIPQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>来访IP总数/p>-->
                    <!--</div>-->
                </div>
            </div>

            <div class="col-md-12 col-sm-12 col-xs-12 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body barChart" id="flowBarQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>流量和访问量变化柱状图(小时)</p>-->
                    <!--</div>-->
                </div>
            </div>

            <div class="col-md-12 col-sm-12 col-xs-12 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body seqChart" id="flowSeqQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>流量和访问量变化曲线图（秒）</p>-->
                    <!--</div>-->
                </div>
            </div>

            <div class="col-md-6 col-sm-6 col-xs-6 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body seqChart" id="URLRankQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>URL排名</p>-->
                    <!--</div>-->
                </div>
            </div>

            <div class="col-md-6 col-sm-6 col-xs-6 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body seqChart" id="IPRankQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>IP排名</p>-->
                    <!--</div>-->
                </div>
            </div>

            <div class="col-md-12 col-sm-12 col-xs-12 mg-top-20">
                <div class="panel panel-primary">
                    <div class="panel-body seqChart" id="PieChartQuery"></div>
                    <!--<div class="panel-footer back-footer-blue">-->
                    <!--<p>请求方法和状态码图</p>-->
                    <!--</div>-->
                </div>
            </div>
        </div>
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
<script type="text/javascript" src="../../assets/js/queryData.js" charset="UTF-8"></script>
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