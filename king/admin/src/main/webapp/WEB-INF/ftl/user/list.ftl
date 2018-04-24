<#assign ctx=request.contextPath />
<html>
<body>

<@shiro.hasPermission name="user:create">
    <a href="${ctx}/user/create">用户新增</a><br/>
</@shiro.hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>用户名</th>
            <th>所属组织</th>
            <th>角色列表</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <#if users??>
         <#list users as user>
            <tr>
                <td>${user.loginName}</td>
                <td></td>
                <td><#list user.roles as role>
                	 ${role.alias},
                	</#list>
                </td>
                <td>
                    <@shiro.hasPermission name="user:update">
                        <a href="${ctx}/user/${user.id}/eidt">修改</a>
                    </@shiro.hasPermission>

                    <@shiro.hasPermission name="user:delete">
                        <a href="${ctx}/user/${user.id}/delete">删除</a>
                    </@shiro.hasPermission>

                    <@shiro.hasPermission name="user:update">
                        <a href="${ctx}/user/${user.id}/changePassword">改密</a>
                    </@shiro.hasPermission>
                </td>
            </tr>
           </#list> 
        </#if>
    </tbody>
</table>

</body>
</html>