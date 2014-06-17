<html lang="en" ng-app="flowerApp">
<head>
    <meta charset="utf-8">
    <title>Flower bed</title>
    <link rel="stylesheet" href="/library/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="/stylesheets/flowerBed.css">
    <script src="/library/bower_components/angular/angular.js"></script>
    <script src="/library/bower_components/sockjs/sockjs.js"></script>
    <script src="/library/bower_components/stomp-websocket/lib/stomp.js"></script>

    <script src="/javascripts/flowerApp.js"></script>
    <script src="/javascripts/SocketService.js"></script>
    <script src="/javascripts/flowerbedController.js"></script>
</head>
<body class="bodyBackground" ng-controller="flowerbedController">
    <div class="container">
        <div class="row-fluid marginTop">
            <div class="col-md-12">
                <div class="textColor" ng-repeat="flowerRow in socket.flowerBed">
                    <div ng-repeat="flower in flowerRow | filter:{empty: false}">
                        <div class="textSizeMedium">Name: {{flower.name}}, Family: {{flower.family}}</div>
                        <div class="textSizeSmall">Planted: {{flower.planted}} Age:{{flower.age}} Max age: {{flower.maxAge}} Height: {{flower.height}} Max height: {{flower.maxHeight}}</div>
                    </div>
                </div>
                <button ng-click="plantFlowerAnywhere()">plant anywhere</button>
                <button ng-click="weedFlowerAnywhere()">weed anywhere</button>
                <button ng-click="plantFlower()">plant on 1, 1</button>
                <button ng-click="weedFlower()">weed on 1, 1</button>
            </div>
        </div>
    </div>
</body>
</html>