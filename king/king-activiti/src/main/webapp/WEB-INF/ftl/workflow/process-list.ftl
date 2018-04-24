<#assign ctx=request.contextPath />
<html>
<body>

 <link rel="stylesheet" href="${ctx!}/static/css/jquery-treetable/jquery.treetable.css">
 <link rel="stylesheet" href="${ctx}/static/css/jquery-treetable/jquery.treetable.theme.default.css">
<table class="table">
    <thead>
        <tr>
            <th>ProcessDefinitionId</th>
            <th>DeploymentId</th>
            <th>名称</th>
            <th>KEY</th>
            <th>版本号</th>
            <th>XML</th>
            <th>图片</th>
            <th>部署时间</th>
            <th>是否挂起</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <#if page.result!! >
         <#list obj as page.result>
	       <#assign process = obj[0] />
	       <#assign deployment =obj[1] />
             <tr>
                <td>${process.id!}</td>
                <td>${process.deploymentId!}</td>
                <td >
                	 ${process.name!}
                </td>
                <td >
                	 ${process.key!}
                </td>
                <td >
                	 ${process.version!}
                </td>
                <td >
                	<a target="_blank" href='${ctx }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName }</a>
                </td>
                <td >
                	<a target="_blank" href='${ctx }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName }</a>
                </td>
                <td >
                	${deployment.deploymentTime }
                </td>
                <td >
                	${process.suspended} |
                	<#if process.suspended ==true>
                		<a href="processdefinition/update/active/${process.id}">激活</a>
                	</#if>
                	<#if process.suspended == false>
                		<a href="processdefinition/update/suspend/${process.id}">挂起</a>
                	</#if>
                </td>
                <td >
                	<a href='${ctx }/workflow/process/delete?deploymentId=${process.deploymentId}'>删除</a>
                        <a href='${ctx }/workflow/process/convert-to-model/${process.id}'>转换为Model</a>
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