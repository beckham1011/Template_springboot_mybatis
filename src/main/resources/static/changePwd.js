
function userPasswordFormSubmit(){
	console.log('userPasswordFormSubmit');
	var oldCurrentPassword = ${password} ;
	var oldInputPassword = $.md5(value);
	
	if(oldCurrentPassword != oldInputPassword){
		alert("请正确填写您的旧密码!");
		return ;
	}
	
	var newPwd1 = $("#newPwd1") ;
	var newPwd2 = $("#newPwd2") ;
	if(newPwd1.lengt < 6 || newPwd2 < 6){
		alert("新密码长度请大于6位!");
		return ;
	}
	if(newPwd1 != newPwd1){
		alert("请保持两次新密码一致!");
		return ;
	}
	var newPwdAfterMd5 = $.md5(newPwd1) ;
	if(newPwdAfterMd5 == oldCurrentPassword){
		alert("新密码不能和老密码一样!");
		return ;
	}
	return ;
}



