<#assign ctx=request.contextPath />
<html>
<body>

当前在线人数 ${sesessionCount!}

<table class="table">
    <thead>
        <tr>
            <th>会话id</th>
            <th>用户名</th>
            <th>主机地址</th>
            <th>最后访问时间</th>
            <th>以强制退出</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <#if sessions??>
         <#list sessions as session>
            <tr>
                <td>${session.id!}</td>
                <td></td>
                <td>${session.host!}</td>
                <td>${session.lastAccessTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                <td>
                   
                </td>
                <td>
                    <a href="${ctx}/sessions/${session.id!}/forceLogout">删除</a>
                </td>
            </tr>
           </#list> 
        </#if>
    </tbody>
</table>

</body>
</html>