<#assign ctx=request.contextPath />
<html>
	 <script src="${ctx}/static/scripts/jquery/jquery.min.js"></script>
    <script src="${ctx}/static/scripts/jquery-validation/jquery.validate.js"></script>
<body>

    <form method="post" action="${ctx}/user/modify">

        <div class="form-group">
            <label name="username">登录名：</label>
            <input type="text" id="loginName" name="userId" value="${user.id!}"/>
        </div>

        <div class="form-group">
            <label path="password">密码：</label>
            <input type="password" id="password" name="password" value="${user.password!}"/>
        </div>
        <div class="form-group">
            <label path="firstName">firstName：</label>
            <input type="text" id="firstName" name="firstName" value="${user.firstName!}"/>
        </div>
        <div class="form-group">
            <label path="lastName">lastName：</label>
            <input type="text" id="lastName" name="lastName" value="${user.lastName!}"/>
        </div>
	<div class="form-group">
            <label path="email">邮箱：</label>
            <input type="text" id="email" name="email" value="${user.email!}"/>
        </div>



        <button type="submit">保存</button>

    </form>


    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>


</body>
</html>