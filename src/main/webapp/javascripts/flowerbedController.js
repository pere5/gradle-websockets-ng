/**
 * Created by pere5 on 22/05/14.
 */


controllers.controller('flowerbedController', ['$scope', '$http', 'SocketService', function ($scope, $http, SocketService) {
    $scope.socket = {};
    $scope.socket.flowerBed = [[{name: 'hej', empty: false}]];
    SocketService.connectTo('/topic/flowerbed', $scope);

    $scope.plantFlower = function () {
        $http({
            method: "post",
            url: "plantFlower",
            params: {
                x: 1,
                y: 1
            },
            data: {
                name: 'Per',
                family: 'Eriksson',
                maxAge: 200,
                maxHeight: 300
            }
        }).success(
            function(flowerBed) {
                console.log('Flower planted.');
                $scope.socket.flowerBed = flowerBed;
            }
        );
    };

    $scope.plantFlowerAnywhere = function () {
        $http({
            method: "post",
            url: "plantFlowerAnywhere",
            data: {
                name: 'Per',
                family: 'Eriksson',
                maxAge: 200,
                maxHeight: 300
            }
        }).success(
            function(flowerBed) {
                console.log('Flower planted.');
                $scope.socket.flowerBed = flowerBed;
            }
        );
    };

    $scope.weedFlower = function () {
        $http({
            method: "post",
            url: "weedFlower",
            params: {
                x: 1,
                y: 1
            }
        }).success(
            function(flowerBed) {
                console.log('Flower weeded.');
                $scope.socket.flowerBed = flowerBed;
            }
        );
    };

    $scope.weedFlowerAnywhere = function () {
        $http({
            method: "post",
            url: "weedFlowerAnywhere"
        }).success(
            function(flowerBed) {
                console.log('Flower weeded.');
                $scope.socket.flowerBed = flowerBed;
            }
        );
    };
}]);
