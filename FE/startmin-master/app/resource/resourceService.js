appreource.factory('get_resource',['$http',function($http){
  return $http.get('localhost:8080/resources')
  .success(function(data){
    return data;
  })
  .error(function(error){
    return error;
  });
}]);
