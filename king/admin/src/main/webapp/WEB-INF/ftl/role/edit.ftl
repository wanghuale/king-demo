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

    <form method="post" action="${ctx}/role/modfiy">
        <input type="hidden" name="id" value="${role.id!}"/>


        <div class="form-group">
            <label >编码：</label>
            <input type="text"  name="role" value="${role.role!}"/>
        </div>

        <div class="form-group">
            <label >别名：</label>
            <input type="text"  name="alias" value="${role.alias!}"/>
        </div>

        <div class="form-group">
            <label path="resourceIds">拥有资源列表：</label>
            <input type="hidden" id="resourceIds" name="resourceIds"/>
            <input type="text" id="resourceIdsName" name="resourceIdsName" value="${role.resourceStr!}" readonly>
            <a id="menuBtn" href="#">选择</a>
        </div>


        

        <button type="submit">保存</button>

    </form>


    <div id="menuContent" class="menuContent" style="display:inline; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>

    <script>
    	$(function(){
    		var setting = {
                check: {
                    enable: true ,
                    chkboxType: { "Y": "", "N": "" }
                },
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                
                callback: {
                    onCheck: onCheck
                }
               
            };
            
            function onCheck(e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree"),
                        nodes = zTree.getCheckedNodes(true),
                        id = "",
                        name = "";
                nodes.sort(function compare(a,b){return a.id-b.id;});
                for (var i=0, l=nodes.length; i<l; i++) {
                    id += nodes[i].id + ",";
                    name += nodes[i].name + ",";
                }
                if (id.length > 0 ) id = id.substring(0, id.length-1);
                if (name.length > 0 ) name = name.substring(0, name.length-1);
                $("#resourceIds").val(id);
                $("#resourceIdsName").val(name);
            }
    		
    	function showMenu() {
                var cityObj = $("#resourceName");
                var cityOffset = $("#resourceName").offset();
                $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

                $("body").bind("mousedown", onBodyDown);
            }
            function hideMenu() {
                $("#menuContent").fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);
            }
            function onBodyDown(event) {
                if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                    hideMenu();
                }
            }
            $.ajax({
				type : "post",
				data:{"roleId":"${role.id!}"},
				url : "${ctx!}/resource/treeNode",
				success : function(result) {
					 $.fn.zTree.init($("#tree"), setting, result);
				}
			});

          });  
            
    </script>

</body>
</html>