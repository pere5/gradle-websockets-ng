/**
 * Created by pere5 on 12/06/14.
 */

flowerApp.service('SocketService', function() {
    var socket = new SockJS('/flowerServer');
    var stompClient = Stomp.over(socket);

    this.connectTo = function (topic, scope) {
        stompClient.connect({}, function (frame) {
            stompClient.subscribe(topic, function (flowerBedMessage) {
                var flowerBed = JSON.parse(flowerBedMessage.body);
                if (!angular.equals(scope.socket.flowerBed, flowerBed)) {
                    scope.socket.flowerBed = flowerBed;
                    scope.$apply();
                }
            });
        });
    }
});