<#assign ctx=request.contextPath />
<html>
<head>
    <title>Shiro综合案例</title>
    <link rel="stylesheet" href="${ctx}/static/css/layout-default-latest.css">
</head>
<body>

<iframe name="content" class="ui-layout-center"
        src="${ctx}/welcome" frameborder="0" scrolling="auto"></iframe>
<div class="ui-layout-north">欢迎[<shiro:principal/>]学习Shiro综合案例，<@shiro.principal property="loginName"/> <@shiro.hasRole name="admin">adfasfd </@shiro.hasRole><a href="${ctx}/logout">退出</a></div>
<div class="ui-layout-south">
   title
</div>
<div class="ui-layout-west">
    功能菜单<br/>
    <#list menus! as m >
    	 <a href="${ctx}${m.url}" target="content">${m.name}</a><br/>
    </#list>
    
</div>


<script src="${ctx}/static/scripts/jquery/jquery-1.11.0.min.js"></script>
<script src="${ctx}/static/scripts/jquery-layout/jquery.layout-latest.min.js"></script>
<script>
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>