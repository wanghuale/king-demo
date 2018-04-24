<#include "/common/global.ftl">
<html>
<head>
    <title>activiti 学习</title>
    <link rel="stylesheet" href="/vapstatics/bower_components/bootstrap/dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/vapstatics/bower_components/jquery-easyui/themes/default/easyui.css">

</head>
<body>

<iframe name="content" class="ui-layout-center"
        src="${ctx}/welcome" frameborder="0" scrolling="auto"></iframe>
<div class="ui-layout-north">欢迎学习activiti，</div>
<div class="ui-layout-south">
   title
</div>
<div class="ui-layout-west">
    功能菜单<br/>
    	 <a href="${ctx}/" target="content">工作流</a><br/>
    	 <a href="${ctx}/user/list" target="content">用户</a><br/>
    	 <a href="${ctx}/group/list" target="content">组</a><br/>
    	 <a href="${ctx}/workflow/list" target="content">流程定义和部署</a><br/>
    	 <a href="${ctx}/" target="content">定时器</a><br/>
    	 <a href="${ctx}/" target="content">请假流程</a><br/>
    	 <a href="${ctx}/" target="content">我的任务</a><br/>
    	 <a href="${ctx}/" target="content">动态表单</a><br/>
    	 <a href="${ctx}/" target="content">工作流</a><br/>
    	 <a href="${ctx}/" target="content">工作流</a><br/>
</div>


<script src="/vapstatics/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/vapstatics/bower_components/jquery-layout/jquery.layout-latest.min.js"></script>
<script>
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>