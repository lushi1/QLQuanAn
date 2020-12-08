appreource.controller('CategoryController',  ['$scope', '$http', function($scope, $http){
  $scope.searchName   = '';
  var request = {
      method: 'GET',
      url: 'http://localhost:8090/category/',
  };

  $http(request)
  .then(function successCallback(response){
    $scope.categories = response.data;

    $scope.paging();
   },function errorCallback(response){
      alert("Error. Try Again!" + response.data);
   });

   $scope.paging = function(){

     $scope.viewby = 10;
     $scope.totalItems = $scope.categories.length;
     $scope.currentPage = 1;
     $scope.itemsPerPage = $scope.viewby;
     $scope.maxSize = 5; //Number of pager buttons to show

     $scope.setPage = function (pageNo) {
       $scope.currentPage = pageNo;
     };

     $scope.pageChanged = function() {
       console.log('Page changed to: ' + $scope.currentPage);
     };

   $scope.setItemsPerPage = function(num) {
     $scope.itemsPerPage = num;
     $scope.currentPage = 1; //reset to first page
   }
   }




   $scope.addCategory = function(){
     var categoryName = document.getElementById("categoryName").value;
     var category = {};
     category.categoryName = categoryName;
     console.log(JSON.stringify(category));
     if(categoryName != ""){
       $http(
         {
           method: 'POST',
           url: 'http://localhost:8090/categories/',
           data: JSON.stringify(category),
           headers: {
            "Content-Type" : "application/json; charset=UTF-8"
          },
         }
       )
       .then(function successCallback(response){
         $scope.categories.push(response.data);
        alert("category has added successfully");
        window.location.reload(true);

        },function errorCallback(response){
           alert("Error. Try Again!" + JSON.stringify(response.data));
        });
     }
   };

}]);
