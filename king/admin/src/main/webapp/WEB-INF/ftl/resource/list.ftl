<#assign ctx=request.contextPath />
<html>
<body>

 <link rel="stylesheet" href="${ctx!}/static/css/jquery-treetable/jquery.treetable.css">
 <link rel="stylesheet" href="${ctx}/static/css/jquery-treetable/jquery.treetable.theme.default.css">
<table class="table">
    <thead>
        <tr>
            <th>名称</th>
            <th>类型</th>
            <th>URL路径</th>
            <th>权限字符串</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <#if resources??>
         <#list resources as resource>
           <#assign isRootNode = resource.rootNode?string("true","false")>
            <#if isRootNode == 'true'>
             <tr data-tt-id='${resource.id}'>
            <#else>
             <tr data-tt-id='${resource.id}' data-tt-parent-id='${resource.parentId}'>
            </#if>
            
            
                <td>${resource.name!}</td>
                <td>${resource.text!}</td>
                <td >
                	 ${resource.url!},
                </td>
                <td >
                	 ${resource.permission!},
                </td>
                <td>
                	<@shiro.hasPermission name="resource:update">
                		<#if resource.type == 'menu'>
	                        <a href="${ctx}/resource/${resource.id}/create">增加子节点</a>
                		</#if>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="resource:update">
                        <a href="${ctx}/resource/${resource.id}/edit">修改</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="resource:delete">
                    	<#if isRootNode == 'false' >
	                        <a href="#" name='delete' resourId="${resource.id!}">删除</a>
                    	</#if>
                    </@shiro.hasPermission>

                </td>
            </tr>
           </#list> 
        </#if>
    </tbody>
</table>
	 <script src="${ctx}/static/scripts/jquery/jquery.min.js"></script>
<script src="${ctx}/static/scripts/jquery-treetable/jquery.treetable.js"></script>
<script>
	$(function(){
		$("table").treetable({ expandable: true }).treetable("expandNode", 1);
		$("a[name='delete']").click(function(){
		alert("dd");
			var resourId =$(this).attr("resourId");
			var url ="${ctx}/resource/"+resourId+"/delete";
			$.ajax({
			url:url,
			success:function(result){
				alert(result);
				if(result){
				alert(result);
					window.location="${ctx}/resource";
				}else{
					alert("删除失败！");
				}
			
			}
			
			});
		
		});
	});
</script>

</body>
</html>