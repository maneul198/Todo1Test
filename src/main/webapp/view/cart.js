var cart = angular.module('cart', []);

cart.controller('ItemCtrl', ['$scope', 'ItemService',
    function ($scope, ItemService) {
        $scope.buy = function () {
            ItemService.buy($scope.items)
        };
        $scope.getItems = function () {
            ItemService.getItems().then(function success(response) {
                $scope.items = response.data
            }, function error(response) {
                $scope.errorMessage = 'Error getting items';
            });
        };
        $scope.init = function () {
            $scope.getItems();
        };
        $scope.init();
    }
]);

cart.service('ItemService', ['$http',
    function ($http) {
        this.buy = function buy(id) {
            return $http({
                method: 'GET',
                url: 'item'
            });
        };
        this.getItems = function getItems(id) {
            return $http({
                method: 'GET',
                url: 'items'
            });
        };
    }
]);