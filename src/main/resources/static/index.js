angular.module('app', []).controller('indexPageController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/products/api/v1/'

    $scope.page = 0;

    $scope.filltable = function (page) {
        $http.get(contextPath + '?page=' + page)
            .then(function (response) {
                $scope.ProductList = response.data.products;
                $scope.TotalPages = response.data.totalPages;
                $scope.CurrentPage = response.data.currentPage;
                console.log($scope);
            });
    };



    $scope.removeProductById = function (id) {
      $http.get(contextPath + 'remove/' + parseInt(id))
          .then(function (reloadPage) {
              $scope.filltable();
          }
      )};

    $scope.filltable($scope.page);
});