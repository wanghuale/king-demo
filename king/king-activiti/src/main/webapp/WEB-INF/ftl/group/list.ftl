<#assign ctx=request.contextPath />
<html>
<body>

    <a href="${ctx}/group/create">用户新增</a><br/>
<@shiro.hasPermission name="user:create">
</@shiro.hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>groupid</th>
            <th>name</th>
            <th>类型</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <#if list??>
         <#list list as group>
            <tr>
                <td>${group.id!}</td>
                <td>${group.name!}</td>
                <td>${group.type!}
                </td>
                <td>
                        <a href="${ctx}/group/${group.id!}/edit">修改</a>
                    <@shiro.hasPermission name="user:update">
                    </@shiro.hasPermission>

                        <a href="${ctx}/group/${group.id!}/delete">删除</a>
                    <@shiro.hasPermission name="user:delete">
                    </@shiro.hasPermission>

                </td>
            </tr>
           </#list> 
        </#if>
    </tbody>
</table>

</body>
</html>