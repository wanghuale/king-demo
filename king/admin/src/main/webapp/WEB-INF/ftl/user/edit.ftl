<#assign ctx=request.contextPath />
<html>
	 <script src="${ctx}/static/scripts/jquery/jquery.min.js"></script>
    <script src="${ctx}/static/scripts/jquery-validation/jquery.validate.js"></script>
<body>

    <form method="post" action="${ctx}/user/modify">
        <input type="hidden" id="id" name="id" value="${user.id!}"/>
        <input type="hidden" id="salt" name="salt" value="${user.salt!}"/>
        <input type="hidden" id="locked" name="locked" value="${user.locked?string("true","false")}"/>


        <div class="form-group">
            <label name="username">登录名：</label>
            <input type="text" id="loginName" name="loginName" value="${user.loginName!}"/>
        </div>

        <div class="form-group">
            <label path="password">密码：</label>
            <input type="password" id="password" name="password" value="${user.password!}"/>
        </div>

        <div class="form-group">
            <label path="organizationId">所属组织：</label>
            <input type="hidden" id="organizationId" name="organizationId"/>
            <input type="text" id="organizationName" name="organizationName" value="" readonly>
            <a id="menuBtn" href="#">选择</a>
        </div>


        <div class="form-group">
            <label >角色列表：</label>
            <select id="roleIds" name="roleIds" multiple="true">
               <#list roles as role>
               	  <#if user.roles??>
               		<#assign uroles = user.roles>
               			<#list uroles as keys>
	               			<#if keys.id! == role.id!>
			               		<option value="${role.id}" selected>${role.description}</option>
	               			<#else>	
								<option value="${role.id}" >${role.description}</option>               				
	               			</#if>
               			</#list>
               		<#else>
               			<option value="${role.id}" >${role.description}</option>  
               		</#if>	
               </#list>	
            </select>
            (按住shift键多选)
        </div>

        <button type="submit">保存</button>

    </form>


    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>


</body>
</html>