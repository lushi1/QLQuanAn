myApp.controller("FoodDetailController",['$scope', '$http',function($scope, $http){
  $scope.resources = {};
  $scope.foodDetailsSelected = [];
  $scope.units = {};
  $scope.currentFood = {};
  $scope.resourceSelected = {};
  $scope.resourceSelectedIndex;


  $scope.getDetailFood = function(foodId){
    $http({
      method: 'GET',
      url: 'http://localhost:8090/food_details/' + foodId,
      headers: {
       "Content-Type" : "application/json; charset=UTF-8"
     },
    }
  )
  .then(function successCallback(response){
      $scope.foodDetailsSelected = response.data;
      console.log($scope.foodDetailsSelected.length);
   },function errorCallback(response){
      alert("Error. Try Again!");
   });
  }

  $scope.getResource = function(){
    $http(
      {
        method: 'GET',
        url: 'http://localhost:8090/resources/',
        headers: {
         "Content-Type" : "application/json; charset=UTF-8"
       },
      }
    )
    .then(function successCallback(response){
        $scope.resources = response.data;
     },function errorCallback(response){
        alert("Error. Try Again!");
     });
  };

  $scope.getUnits = function(){
    $http(
      {
        method: 'GET',
        url: 'http://localhost:8090/units/',
        headers: {
         "Content-Type" : "application/json; charset=UTF-8"
       },
      }
    )
    .then(function successCallback(response){
        $scope.units = response.data;
     },function errorCallback(response){
        alert("Error. Try Again!");
     });
  };

  $scope.removeResourceDetail = function(){
    if(!$.isEmptyObject($scope.resourceSelected)){
      $http(
        {
          method: 'DELETE',
          url: 'http://localhost:8090/food_details/'+$scope.resourceSelected.foodDetailId.foodId+'&'+$scope.resourceSelected.foodDetailId.resourceId,
          headers: {
           "Content-Type" : "application/json; charset=UTF-8"
         },
        }
      )
      .then(function successCallback(response){
        $scope.resourceSelected = {};
        $scope.foodDetailsSelected.splice($scope.resourceSelectedIndex,1);
        $("button[type='button']#btn-delete-resource").prop('disabled',true);
       },function errorCallback(response){
          alert("Error. Try Again!");
       });
    }

  }

  $scope.record = function(resourceSelected,index){
    $scope.resourceSelected = resourceSelected;
    $scope.resourceSelectedIndex = index;
    console.log(JSON.stringify(  $scope.resourceSelected));
  }

  $scope.openFoodDetail = function(food){
    $scope.getResource();
    $scope.getUnits();
    $scope.getDetailFood(food.foodId);
    $scope.currentFood = food;
  }




  $scope.foodDetails = {
    'qualatitation' : '',
    'unitId': ''
  };
  $scope.resource_id = {};


  $scope.addAFoodDetail = function(){
    console.log($scope.currentFoodId);
  $scope.resource_id = $scope.resource_id_selected;
  console.log($scope.resource_id);
  console.log($scope.foodDetails.resourceId);

  $scope.foodDetail = {
    'foodDetailId' : {
      "foodId": $scope.currentFood.foodId,
      "resourceId": $scope.resource_id
    },
    'quantitative': $scope.foodDetails.qualatitation,
        "unit": {
            "unitId": $scope.foodDetails.unitId,
        }
  };


    $http(
      {
        method: 'POST',
        url: 'http://localhost:8090/food_details/',
        data: JSON.stringify($scope.foodDetail),
        headers: {
         "Content-Type" : "application/json; charset=UTF-8"
       },
      }
    )
    .then(function successCallback(response){
      if(response.status==204){
        alert("Bạn đã thêm nguyên liệu này trước đó rồi! Xin hãy kiểm tra lại.");
      }
      else{
          //$scope.foodDetailsSelected.push(response.data);
          $scope.foodDetailsSelected = $scope.getDetailFood($scope.currentFood.foodId);
      }
     },function errorCallback(response){
        alert("Error. Try Again!" + JSON.stringify(response.data));
     });
  };
}]);
