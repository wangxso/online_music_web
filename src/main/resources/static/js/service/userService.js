//服务层
app.service('userService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAllUser=function(){
		return $http.get('../user/findAllUser');
	};
	//查询实体
	this.findOne=function(id){
		return $http.get('../user/findOneUser?id='+id);
	};
	//增加 
	this.addUser=function(entity){
		return  $http.post('../user/addUser',entity);
	};

	this.login=function (loginUser) {
		return $http.get('../login?name='+loginUser.nickName+"&password="+loginUser.password);
	}
});
