/* 使用jquery读取本地的username，修改右上角用户名标签 */
$(function(){
	//从cookie中读取username
	var val=$.cookie("username");
	if(!val){
		location="login.html";
	}
	$(".dropdown-toggle").html('<i class="halflings-icon white user"></i> '+val+'&nbsp;&nbsp;<span class="caret"></span>');
	//修改注销的链接地址  找到 ul.dropdown-menu 下的最后一个li下的a标签，吧href改成logout.do
	$("ul.dropdown-menu li").last().children("a").attr('href', "admin/logout.do");
})


