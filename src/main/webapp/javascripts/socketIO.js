/**
 * Created by pere5 on 12/06/14.
 */

flowerApp.run(['$rootScope', function($rootScope) {
    $rootScope.socket = {};
    $rootScope.socket.flowerBed = [{name: 'hej'}];
    var socket = new SockJS('/flowerbed');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/flowerbed', function(flowerBedMessage){
            var flowerBed = JSON.parse(flowerBedMessage.body);
            if (! angular.equals($rootScope.socket.flowerBed, flowerBed)) {
                $rootScope.socket.flowerBed = flowerBed;
                $rootScope.$apply();
            }
        });
    });
}]);