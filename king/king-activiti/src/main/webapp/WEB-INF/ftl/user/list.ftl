<#assign ctx=request.contextPath />
<html>


<body>
<link rel="stylesheet" href="/vapstatics/bower_components/easyui/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" href="/vapstatics/bower_components/easyui/jquery-easyui-1.5/themes/icon.css">
    <a href="${ctx}/user/create">用户新增</a><br/>
<@shiro.hasPermission name="user:create">
</@shiro.hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>用户id</th>
            <th>fristName</th>
            <th>lastName</th>
            <th>Email</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <#if list??>
         <#list list as user>
            <tr>
                <td>${user.id!}</td>
                <td>${user.firstName!}</td>
                <td>${user.lastName!}
                </td>
                <td>${user.email!}
                </td>
                <td>
                        <a href="${ctx}/user/${user.id!}/edit">修改</a>
                    <@shiro.hasPermission name="user:update">
                    </@shiro.hasPermission>

                        <a href="${ctx}/user/${user.id!}/delete">删除</a>
                    <@shiro.hasPermission name="user:delete">
                    </@shiro.hasPermission>
						<a href="#" name="groupId" userId="${user.id!}">绑定组</a>
                    <@shiro.hasPermission name="user:update">
                        <a href="${ctx}/user/${user.id!}/changePassword">改密</a>
                    </@shiro.hasPermission>
                </td>
            </tr>
           </#list> 
        </#if>
    </tbody>
</table>

<table id="goupId">
		
	
</table>
    
   <script src="/vapstatics/bower_components/jquery/dist/jquery.min.js"></script> 
   <script type="text/javascript" src="/vapstatics/bower_components/easyui/jquery-easyui-1.5/jquery.easyui.min.js"></script>	
<script>
	$(function(){
		$("a[name='groupId']").click(function(){
			var userId = $(this).attr("userId");
			var url = "${ctx}/user/"+userId+"/getGroupByUerId";
			
			$('#goupId').datagrid({
				url: url,
				method:'GET',
				title: '组',
				fit:false,
				toolbar :[{
		            text:'Add',
		            iconCls:'icon-add',
		            handler:function(){
						var chks = $('#goupId').datagrid("getChecked");
						var ids =[] ;
						$.each(chks,function(i,data){
						  ids.push(data.groupId);
						});
						var parms  ;
						if(ids.length >1){
							param =ids.join(",");
						}else{
							param = ids[0];
						}
						url = "${ctx}/user/"+userId+"/bindGroup";
						$.post(url,{ids:param},function(result){
							if(result.success){
								alert("操作成功！")
							}
						},"json");
						
					}
		        }],
		    	fitColumns:true,
				columns:[[
					{field:'ck',checkbox:true },
					{field:'groupId',title:'组id',width:40},
					{field:'groupName',title:'组名',width:50}
				]]
			});
			
		});
	});
	

</script>
</body>
</html>