<#assign ctx=request.contextPath />
<html>
	 <script src="/vapstatics/bower_components/jquery/dist/jquery.min.js"></script>
<body>
    <form method="post" action="${ctx}/group/modify">
        <div class="form-group">
            <label path="id">组id：</label>
            <input type="text" id="id" name="groupId" value=""/>
        </div>
        <div class="form-group">
            <label path="name">组名：</label>
            <input type="text" id="name" name="name" value=""/>
        </div>
        <div class="form-group">
            <label path="type">类型：</label>
            <input type="text" id="type" name="type" value=""/>
        </div>
        <button type="submit">保存</button>
    </form>

</body>
</html>