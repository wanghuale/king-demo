<#assign ctx=request.contextPath />
<html>
<body>

<@shiro.hasPermission name="role:create">
    <a href="${ctx}/role/create">用户角色</a><br/>
</@shiro.hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>拥有的资源</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <#if roles??>
         <#list roles as role>
            <tr>
                <td>${role.role!}</td>
                <td>${role.alias!}</td>
                <td style="width:500px"><#list role.resources! as resource>
                	 ${resource.name!},
                	</#list>
                </td>
                <td>
                    <@shiro.hasPermission name="role:update">
                        <a href="${ctx}/role/${role.id}/edit">修改</a>
                    </@shiro.hasPermission>

                    <@shiro.hasPermission name="role:delete">
                        <a href="${ctx}/role/${role.id}/delete">删除</a>
                    </@shiro.hasPermission>

                </td>
            </tr>
           </#list> 
        </#if>
    </tbody>
</table>

</body>
</html>