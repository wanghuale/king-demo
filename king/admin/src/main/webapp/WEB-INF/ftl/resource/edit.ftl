<#assign ctx=request.contextPath />
<html>
<link rel="stylesheet"
	href="${ctx}/static/css/ztree/zTreeStyle.css"
	type="text/css">
	 <script src="${ctx}/static/scripts/jquery/jquery.min.js"></script>
    <script src="${ctx}/static/scripts/jquery-validation/jquery.validate.js"></script>
	<script src="${ctx}/static/scripts/ztree/jquery.ztree.core.js"></script>
	<script src="${ctx}/static/scripts/ztree/jquery.ztree.excheck.js"></script>
<body>

    <form method="post" action="${ctx}/resource/modfiy">
        <input type="hidden" name="id" value="${resource.id!}"/>
		<input type="hidden" name="parentId" value="${resource.parentId!}"/>

        <div class="form-group">
            <label >名称：</label>
            <input type="text"  name="name" value="${resource.name!}"/>
        </div>

        <div class="form-group">
            <label >类型：</label>
           <select name="type">
           		<#list types as type>
           			<option value="${type}">${type.text}</option>
           		</#list>
           </select> 
        </div>

        <div class="form-group">
            <label path="resourceIds">URL路径：</label>
            <input type="text"  name="url" value="${resource.url!}">
        </div>
        
		<div class="form-group">
            <label path="resourceIds">权限字符串：</label>
            <input type="text"  name="permission" value="${resource.permission!}">
        </div>

        

        <button type="submit">保存</button>

    </form>



    <script>
    	$(function(){
    		
         });   
    </script>

</body>
</html>