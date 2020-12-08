var myApp = angular.module('myApp', ['angularUtils.directives.dirPagination']);

  myApp.controller('foodController', ['$scope', '$http', function($scope, $http) {

    $scope.itemsPerPage = 5;
    $scope.itemsPerPage2 = 10;
        $scope.setItemsPerPage = function(num) {

         $scope.itemsPerPage = num;
         $scope.itemsPerPage2 = num;
        $scope.currentPage = 1; //reset to first page
      };

      $scope.curentOrders = [];
      $scope.currentDetailsOrder = []

      $scope.reloadPage = function() {
        $http({
          method: 'GET',
          url: 'http://localhost:8090/food',

        }).then(function successCallback(response) {

          $scope.foods = response.data;

        }, function errorCallback(response) {

          alert("Error. Try Again!");

        });
    };

     $scope.setFile = function(element) {

           $scope.$apply(function($scope) {
               $scope.theFile = element.files[0];
               $scope.foodPicture = $scope.theFile.name;
               $scope.food.foodPicture = $scope.theFile.name;
           });
      };

        $scope.addFood = function() {

          $scope.foodCode = "";
          $scope.foodName = "";
          $scope.foodPrice = "";
          $scope.categoryID = "";
          $scope.foodPicture = "";
          $scope.foodStatus = "";

      };


    $scope.getOrderDetails = function(orderId){
      $scope.currentDetailsOrder = [];
      $scope.getTotalPrice = 0;
      $http({
        method: 'GET',
        url: 'http://localhost:8090/orderDetails/'+orderId,

      }).then(function successCallback(response) {

        $scope.currentDetailsOrder = response.data;
        for(var i = 0; i < $scope.currentDetailsOrder.length; i++){
          var count = $scope.currentDetailsOrder[i].amount;
          for(var j = i + 1; j < $scope.currentDetailsOrder.length; j++){
            if($scope.currentDetailsOrder[j].foodEntity.foodId == $scope.currentDetailsOrder[i].foodEntity.foodId){
              count++;
              $scope.currentDetailsOrder.splice(j,1);
              j--;
            }
          }
          $scope.currentDetailsOrder[i].amount = count;
          $scope.getTotalPrice = $scope.currentDetailsOrder[i].price * count + $scope.getTotalPrice;
        }

      }, function errorCallback(response) {

        alert("Error. Try Again!");

      });
    }


    $scope.deleteOrderDetails = function(food){
      $http({
        method: 'DELETE',
        url: 'http://localhost:8090/deleteOrderDetails/'+food.foodEntity.foodId+'/'+food.orderId,

      }).then(function successCallback(response) {

        $scope.getOrderDetails($scope.curentOrders[0].orderId);
      }, function errorCallback(response) {
      });
    }


    $scope.dateFrom;
    $scope.dateUntil;

    $scope.getOrderByDateCheckout = function(){
      var date = $scope.dateFrom.getFullYear()+'-'+($scope.dateFrom.getMonth()+1)+'-'+$scope.dateFrom.getDate();
      var date2 = $scope.dateUntil.getFullYear()+'-'+($scope.dateUntil.getMonth()+1)+'-'+$scope.dateUntil.getDate();
      $scope.totalPriceByDateCheckout = 0;
      if( (new Date(date).getTime() > new Date(date2).getTime()) || (new Date(date).getTime() == new Date(date2).getTime()))
      {
          alert("Wrong input, please re-enter Date");
      }
      else
      {
        $http({
          method: 'GET',
          url: 'http://localhost:8090/orderByDateCheckout/dateFrom='+date+'&dateUntil='+date2,
        }).then(function successCallback(response) {
          $scope.orderByDateCheckout = response.data;
          for(var i = 0; i < $scope.orderByDateCheckout.length; i++)
          {
            $http({
              method: 'GET',
              url: 'http://localhost:8090/orderDetails/'+$scope.orderByDateCheckout[i].orderId,

            }).then(function successCallback(response) {

              $scope.currentOrderDetails = response.data;
              for(var i = 0; i < $scope.currentOrderDetails.length; i++)
              {
                $scope.totalPriceByDateCheckout = $scope.currentOrderDetails[i].price + $scope.totalPriceByDateCheckout;
              }

            }, function errorCallback(response) {

              alert("Error. Try Again!");

            });
          }

        }, function errorCallback(response) {

          alert("Error. Try Again!");

        });
      }

    }




    $scope.getOrderId = function(table){
      $http({
        method: 'GET',
        url: 'http://localhost:8090/orderByIdAndStatus/id='+table.tableId+'&status=0',

      }).then(function successCallback(response) {

        $scope.curentOrders = response.data;
        $scope.getOrderDetails($scope.curentOrders[0].orderId);

      }, function errorCallback(response) {

        alert("Error. Try Again!");

      });
    }

    $scope.changeTab = function(table) {
      $scope.currentDetailsOrder = [];
      $(".tabbanan").removeClass("active");
      $(".tabmonan").addClass("active");
      $("#banan").removeClass("active in");
      $("#monan").addClass("active in");
      $scope.getOrderId(table);

    }
    $scope.createOrder = function(table) {

      //$http POST function
      $scope.orders = new Array();
      $scope.order = {};
      $scope.order.status = "0";
      $scope.order.staffCashier = "1";
      $scope.order.tableId = table.tableId;
      $(".tabbanan").removeClass("active");
      $(".tabmonan").addClass("active");
      $("#banan").removeClass("active in");
      $("#monan").addClass("active in");
      $http({

        method: 'POST',
        url: 'http://localhost:8090/createOrder/',
        data: $scope.order

      }).then(function successCallback(response) {

        $scope.orders.push(response.data);
        $scope.table = {};
        $scope.table.tableStatus = 1;

          $http({

            method: 'PUT',
            url: 'http://localhost:8090/updateTable/' + table.tableId,
            data: $scope.table

          }).then(function successCallback(response) {
            $scope.getTable();
            $scope.getOrderId(table);

          }, function errorCallback(response) {

          });

      }, function errorCallback(response) {

        alert("Error. while Booked table Try Again!");

      });



    };


    $http({
      method: 'GET',
      url: 'http://localhost:8090/food',

    }).then(function successCallback(response) {

      $scope.foods = response.data;

    }, function errorCallback(response) {

      alert("Error. Try Again!");

    });



    $http({
      method: 'GET',
      url: 'http://localhost:8090/category'

    }).then(function successCallback(response) {

      $scope.categorys = response.data;


    }, function errorCallback(response) {

      alert("Error. Try Again!");

    });
    $scope.getTable = function() {
      $http({
        method: 'GET',
        url: 'http://localhost:8090/table'

      }).then(function successCallback(response) {

        $scope.tables = response.data;


      }, function errorCallback(response) {

        alert("Error. Try Again!");

      });
    }
    $scope.getTable();

    $scope.order = new Array();
    $scope.Book = function(food, table) {
      var today = new Date();
      var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
      var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
      var dateTime = date+' '+time;
      var orderDetail = {
          "orderId": $scope.curentOrders[0].orderId,
          "amount": 1,
          "price": food.foodPrice,
          "dateTimeOrder": dateTime,
          "foodEntity": {"foodId": food.foodId}
      };

      $http({

        method: 'POST',
        url: 'http://localhost:8090/createOrderDetails/',
        data: JSON.stringify(orderDetail),
        headers: "Content-Type:application/json"

      }).then(function successCallback(response) {

        $scope.getOrderDetails($scope.curentOrders[0].orderId);

      }, function errorCallback(response) {

      });
    };

    $scope.editFood = function(food) {
      $scope.food = food;
      $scope.tempName = $scope.food.foodName;
      $scope.tempCode = $scope.food.foodCode;
      $scope.tempPrice = $scope.food.foodPrice;
      $scope.tempStatus = $scope.food.foodStatus;
      $scope.tempCategoryId = $scope.food.categoryId;
      $scope.tempPicture = $scope.food.foodPicture;
      $('#editFood').modal('show');
    };
    $scope.addResourceFood = function(food){
      $scope.food = food;
      angular.element(document.querySelector('[ng-controller="FoodDetailController"]')).scope().openFoodDetail(food);
    }

    //Change Food Status
    $scope.foodStatus1 = function() {
      $scope.food.foodStatus = "Đang bán";
    }
    $scope.foodStatus2 = function() {
      $scope.food.foodStatus = "Ngưng bán";
    }

    //Create New User
    $scope.createFood = function() {

      //$http POST function
      $http({

        method: 'POST',
        url: 'http://localhost:8090/createfood/',
        data: {
            "foodCode": $scope.foodCode,
            "foodName": $scope.foodName,
            "foodPrice": $scope.foodPrice,
            "foodPicture": $scope.foodPicture,
            "foodStatus": "Đang bán",
            "categoryID": $scope.categoryID
        }

      }).then(function successCallback(response) {

        $scope.foods.push(response.data);
        alert("Food has added Successfully");
        $scope.reloadPage();

      }, function errorCallback(response) {

        alert("Error. while added food Try Again!");

      });


    };

    $scope.payment = function() {
      var today = new Date();
      var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
      var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
      var dateTime = date+' '+time;

      if($scope.curentOrders.length > 0)
      {
        $http({

          method: 'PUT',
          url: 'http://localhost:8090/updateOrder/' + $scope.curentOrders[0].orderId,
          data:     {
            "tableId": $scope.curentOrders[0].tableId,
            "status": "1",
            "dateCheckout": dateTime

        }

        }).then(function successCallback(response) {
          $http({

            method: 'PUT',
            url: 'http://localhost:8090/updateTable/' + $scope.curentOrders[0].tableId,
            data:     {
                "tableStatus": "0",
            }

          }).then(function successCallback(response) {

            alert("Bill has Paid Successfully");
            window.location.reload(true);

          }, function errorCallback(response) {
          });

        }, function errorCallback(response) {

        });
      }
      else {
          alert("Please choose table to payment");
      }
    };


    //Update User
    $scope.updateFood = function() {


      //$http PUT function
      $http({

        method: 'PUT',
        url: 'http://localhost:8090/updatefood/' + $scope.food.foodId,
        data: {
           "categoryID": $scope.food.categoryID,
           "foodCode": $scope.food.foodCode,
           "foodName": $scope.food.foodName,
           "foodPrice": $scope.food.foodPrice,
           "foodPicture": $scope.food.foodPicture,
           "foodStatus": $scope.food.foodStatus
        }

      }).then(function successCallback(response) {

        alert("Food has updated Successfully");
        $scope.reloadPage();

      }, function errorCallback(response) {

        alert("Error. while updating food Try Again!");

      });

    };


    //Delete User
    $scope.deleteFood = function(food) {

      //$http DELETE function
      $http({

        method: 'DELETE',
        url: 'http://localhost:8090/deletefood/' + food.foodId

      }).then(function successCallback(response) {

        alert("Food has deleted Successfully");
        var index = $scope.foods.indexOf(food);
        $scope.foods.splice(index, 1);

      }, function errorCallback(response) {

        alert("Error. while deleting food Try Again!");

      });

    };


  }]);

  myApp.directive('ngConfirmClick', [
          function(){
              return {
                  link: function (scope, element, attr) {
                      var msg = attr.ngConfirmClick || "Are you sure?";
                      var clickAction = attr.confirmedClick;
                      element.bind('click',function (event) {
                          if ( window.confirm(msg) ) {
                              scope.$eval(clickAction)
                          }
                      });
                  }
              };
      }])
