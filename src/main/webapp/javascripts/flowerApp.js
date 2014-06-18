/**
 * Created by pere5 on 12/06/14.
 */

var flowerApp = angular.module('flowerApp', [
    'ngRoute',
    'controllers'
]);

var controllers = angular.module('controllers', []);

flowerApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/flowerBed', {
            templateUrl: '/views/flowerBed.html',
            controller: 'flowerbedController'
        }).
        otherwise({
            redirectTo: '/flowerBed'
        });
}]);