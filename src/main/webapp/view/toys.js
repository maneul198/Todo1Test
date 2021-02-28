var toys = angular.module('toys', []);

toys.controller('ToyCRUDCtrl', ['$scope', 'ToyCRUDService',
    function ($scope, ToyCRUDService) {

        $scope.getToy = function () {
            var id = $scope.toy.id;
            ToyCRUDService.getToy($scope.toy.id)
                .then(function success(response) {
                        $scope.toy = response.data;
                        $scope.toy.id = id;
                        $scope.message = '';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.message = '';
                        if (response.status === 404) {
                            $scope.errorMessage = 'Toy not found!';
                        } else {
                            $scope.errorMessage = "Error gToy user!";

                        }
                    });
        }
        $scope.addToy = function () {
            if ($scope.toy != null && $scope.toy.publishingHouse) {
                ToyCRUDService.addToy($scope.toy.publishingHouse, $scope.toy.description)
                    .then(function success(response) {
                            $scope.message = 'Toy added!';
                            $scope.errorMessage = '';
                            $scope.getTotalToys();
                        },
                        function error(response) {
                            $scope.errorMessage = 'Error adding toy!';
                            $scope.message = '';
                        });
            } else {
                $scope.errorMessage = 'Please enter a name!';
                $scope.message = '';
            }
        }
        $scope.updateToy = function () {
            ToyCRUDService.updateToy($scope.toy.id,
                $scope.toy.description, $scope.toy.name)
                .then(function success(response) {
                        $scope.message = 'Toy data updated!';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.errorMessage = 'Error updating toy!';
                        $scope.message = '';
                    });
        }

        $scope.deleteToy = function () {
            ToyCRUDService.deleteToy($scope.toy.id)
                .then(function success(response) {
                        $scope.message = 'Toy deleted!';
                        $scope.toy = null;
                        $scope.errorMessage = '';
                        $scope.getTotalToys();
                    },
                    function error(response) {
                        $scope.errorMessage = 'Error deleting toy!';
                        $scope.message = '';
                    });
        }
        $scope.getAllToys = function () {
            ToyCRUDService.getAllToys()
                .then(function success(response) {
                        $scope.toys = response.data._embedded.toys;
                        $scope.message = '';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.message = '';
                        $scope.errorMessage = 'Error getting toys!';
                    });
        }
        $scope.getTotalToys = function () {
            ToyCRUDService.getAllToys()
                .then(function success(response) {
                    $scope.toysNumber = response.data._embedded.toys.length
                    $scope.message = '';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.message = '';
                    $scope.errorMessage = '';
                }
            )
        };
        $scope.init = function(){
            $scope.getTotalToys();
        };
        $scope.init();
    }
]);


toys.service('ToyCRUDService', ['$http', function ($http) {

    this.getToy = function getToy(toyId) {
        return $http({
            method: 'GET',
            url: 'toys/' + toyId
        });
    }

    this.addToy = function addToy(publishingHouse, description) {
        return $http({
            method: 'POST',
            url: 'toys',
            data: {
                description: description,
                publishingHouse: publishingHouse
            }
        });
    }
    this.updateToy = function updateToy(id, name, description) {
        return $http({
            method: 'PATCH',
            url: 'toys/' + id,
            data: {
                name: name,
                description: description
            }
        });
    }
    this.deleteToy = function deleteToy(id) {
        return $http({
            method: 'DELETE',
            url: 'toys/' + id
        })
    }
    this.getAllToys = function getAllToys() {
        return $http({
            method: 'GET',
            url: 'toys'
        });
    }
}]);