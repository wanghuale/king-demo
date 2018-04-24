<#assign ctx=request.contextPath />
<html>
	 <script src="${ctx}/static/scripts/jquery/jquery.min.js"></script>
    <script src="${ctx}/static/scripts/jquery-validation/jquery.validate.js"></script>
<body>

    <form method="post" action="${ctx}/group/modify">

        <div class="form-group">
            <label name="groupId">组id：</label>
            <input type="text" id="groupId" name="groupId" value="${group.id!}"/>
        </div>

        <div class="form-group">
            <label path="name">组名：</label>
            <input type="text" id="name" name="name" value="${group.name!}"/>
        </div>
        <div class="form-group">
            <label path="type">类型：</label>
            <input type="text" id="type" name="type" value="${group.type!}"/>
        </div>

        <button type="submit">保存</button>

    </form>


    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>


</body>
</html>