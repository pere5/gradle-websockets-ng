/**
 * Created by pere5 on 22/05/14.
 */


controllers.controller('flowerbedController', ['$scope', '$http', function ($scope, $http) {
    $scope.plantFlower = function () {
        $http({
            method: "post",
            url: "plantflower",
            params: {
                x: 1,
                y: 2
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
    }
}]);
