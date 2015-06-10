
/**
 * 这是AngularJS的Hello控制器。它和hello.html中的 <div ng-controller="Hello"> 区域对应。
 * @param $scope 控制器内的全局变量。可以携带数据和函数。
 * @param $http
 */
function Hello($scope, $http) {
	/*
	 * 这是一段通过网络请求JSON数据并且将JSON数据显示出来的代码。因对方服务器问题网络请求不成功。
	 */
//    $http.get('http://rest-service.guides.spring.io/greeting').
//        success(function(data) {
//            $scope.greeting = data;
//        });
	
//	$scope.greeting.id = 1;
//	$scope.greeting.content = "hello angularJS and spring boot";
	
	$scope.id = 1;
	$scope.content = "hello angularJS and spring boot";
}