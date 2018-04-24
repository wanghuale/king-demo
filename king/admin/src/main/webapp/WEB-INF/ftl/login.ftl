<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>电商平台 - 用户登陆</title>
    <meta content="" name="keywords">
    <meta content="" name="description">
    <style type="text/css">
        body {
            _behavior: url(${S_URL}/static/styles/csshover.htc);
        }
    </style>
    <script>
        // <![CDATA[
if((window.navigator.appName.toUpperCase().indexOf("MICROSOFT")>=0)&&(document.execCommand))
try{
document.execCommand("BackgroundImageCache", false, true);
   }
catch(e){}
// ]]>
</script>
<![endif]-->
    <script>
        var COOKIE_PRE = '5BF5_';
        var _CHARSET = 'utf-8';
        var SITEURL = '${S_URL}';
    </script>
    <script src="${S_URL}/static/scripts/jquery/jquery.min.js"></script>
    <script src="${S_URL}/static/scripts/jquery-validation/jquery.validate.js"></script>
    </script>

    <style type="text/css">
        <!--
        .STYLE1 {
            color: #FF0000;
            font-weight: bold;
            font-size: 24px;
        }

        -->
    </style>

</head>
<body>

<div id="append_parent"></div>
<div id="ajaxwaitid"></div>



<style type="text/css">
    .public-top-layout, .head-search-bar, .head-user-menu, .public-nav-layout, .nch-breadcrumb-layout, #faq {
        display: none !important;
    }
    .public-head-layout {
        margin: 10px auto -10px auto;
    }
    .wrapper {
        width: 1000px;
    }
    #footer {
        border-top: none!important;
        padding-top: 30px;
    }
</style>

<div class="nc-login-layout">
    <div class="nc-login">
        <div class="nc-login-title">
            <h3>用户登录</h3>
        </div>
        <div id="demo-form-site" class="nc-login-content">
            <form class="bg" method="post" id="login_form" action="login">
            <#if error?exists>
                <label class="error"  style="padding-left: 100px;">${error}</label>
            </#if>
                <dl>
                    <dt>用户名</dt>
                    <dd style="min-height:54px;">
                        <input type="text" id="username" name="username" autocomplete="off" class="text">
                        <label></label>
                    </dd>
                </dl>
                <dl>
                    <dt>密&nbsp;&nbsp;&nbsp;码 </dt>
                    <dd style="min-height:54px;">
                        <input type="password" id="password" autocomplete="off" name="password" class="text">
                        <label></label>
                    </dd>
                </dl>
                <dl>
                    <dt>验证码</dt>
                    <dd style="min-height:54px;">
                        <input type="text" size="10" maxlength="4" id="captcha" class="text w50 fl" name="captcha">
                        <img border="0" class="fl ml5" id="codeimage" name="codeimage" src="${S_URL}/validationCodeServlet.png">
                        <a onclick="javascript:codeImage();"
                           class="ml5" href="javascript:void(0);">看不清，换一张</a>
                        <label></label>
                    </dd>
                </dl>
                <dl>
                    <dt>&nbsp;</dt>
                    <dd>
                        <input type="button" name="Submit" value="登&nbsp;&nbsp;&nbsp;录" class="submit">
                        <a href="${S_URL}/forget_password.html" class="forget">忘记密码？</a>
                    </dd>
                </dl>
            </form>
            <dl class="mt10 mb10">
                <dt>&nbsp;</dt>
                <dd>还不是本站会员？立即<a class="register" href="${S_URL}/register.html" title="">注册</a></dd>
            </dl>
        </div>
        <div class="nc-login-bottom"></div>
    </div>
</div>

<script>
    $(document).ready(function(){
        $('input[name="Submit"]').click(function(){
            if($("#login_form").valid()){
                $("#login_form").submit();
            } else{
                document.getElementById('codeimage').src = '${S_URL}/validationCodeServlet.png?t=' + Math.random();
            }
        });
        $("#login_form").validate({
            errorPlacement: function(error, element){
                var error_td = element.parent('dd');
                error_td.find('label').hide();
                error_td.append(error);
            },
            rules: {
                username: "required",
                password: "required"
                ,captcha : {
                    required : true,
                    minlength: 4,
                    remote   : {
                        url : '${S_URL}/check/captcha',
                        type: 'get',
                        data:{
                            captcha : function(){
                                return $('#captcha').val();
                            }
                        }
                    }
                }
            },
            messages: {
                username: "用户名不能为空",
                password: "密码不能为空"
                ,captcha : {
                    required : '验证码不能为空',
                    minlength: '验证码不能为空',
                    remote	 : '验证码错误'
                }
            }
        });
    });
    function codeImage(){
        $('#codeimage').attr("src",'${S_URL}/validationCodeServlet.png?t=' + Math.random());
    }
</script>



</body>
</html>