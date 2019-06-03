 //控制层 
app.controller('userController' ,function($scope,$controller   ,userService){

    //读取列表数据绑定到表单中  
	$scope.findAllUser=function(){
		userService.findAllUser().success(
			function(response){
				$scope.list=response;
			}			
		);
	};

	//查询实体 
	$scope.findOneUser=function(id){
		userService.findOneUser(id).success(
			function(response){
				$scope.entity= response;
				return response;
			}
		);				
	};
	//保存
	$scope.save=function(){
		if($scope.entity.password==$scope.password){
			userService.addUser($scope.entity).success(function (response) {
				alert(response.message);
			})//增加
		}else{
			alert("两次密码不一致");
			$scope.password="";
			$scope.entity.password="";
		}
	};




});	
