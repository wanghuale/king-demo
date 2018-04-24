<#assign ctx=request.contextPath />
<html>
	 <script src="/vapstatics/bower_components/jquery/dist/jquery.min.js"></script>
<body>
    <form method="post" action="${ctx}/user/modify">
        <div class="form-group">
            <label path="id">登录名：</label>
            <input type="text" id="id" name="userId" value=""/>
        </div>
        <div class="form-group">
            <label path="password">密码：</label>
            <input type="password" id="password" name="password" value=""/>
        </div>
        <div class="form-group">
            <label path="firstName">firstName：</label>
            <input type="text" id="firstName" name="firstName" value=""/>
        </div>
        <div class="form-group">
            <label path="lastName">lastName：</label>
            <input type="text" id="lastName" name="lastName" value=""/>
        </div>
		<div class="form-group">
            <label path="email">邮箱：</label>
            <input type="text" id="email" name="email" value=""/>
        </div>
        <button type="submit">保存</button>

    </form>


    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>


</body>
</html>