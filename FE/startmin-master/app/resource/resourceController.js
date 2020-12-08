appreource.controller('resourceController',  ['$scope', '$http', function($scope, $http){
  $scope.searchName   = '';
  $scope.sho;
  var request = {
      method: 'GET',
      url: 'http://localhost:8090/resources/',
  };

  $http(request)
  .then(function successCallback(response){
    $scope.resources = response.data;

    $scope.paging();
   },function errorCallback(response){
      alert("Error. Try Again!" + response.data);
   });

   $scope.paging = function(){

     $scope.viewby = 10;
     $scope.totalItems = $scope.resources.length;
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




   $scope.addResource = function(){
     var resourceName = document.getElementById("resourceName").value;
     var resource = {};
     resource.resourceName = resourceName;
     console.log(JSON.stringify(resource));
     if(resourceName != ""){
       $http(
         {
           method: 'POST',
           url: 'http://localhost:8090/resources/',
           data: JSON.stringify(resource),
           headers: {
            "Content-Type" : "application/json; charset=UTF-8"
          },
         }
       )
       .then(function successCallback(response){
         $scope.resources.push(response.data);
        alert("Resource has added successfully");
        window.location.reload(true);

        },function errorCallback(response){
           alert("Error. Try Again!" + JSON.stringify(response.data));
        });
     }
   };

}]);
