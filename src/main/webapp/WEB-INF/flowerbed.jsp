<html lang="en" ng-app="flowerApp">
<head>
    <link rel="stylesheet" href="../library/bower_components/bootstrap/dist/css/bootstrap.css">
    <script src="/library/bower_components/angular/angular.js"></script>
    <script src="/library/bower_components/sockjs/sockjs.js"></script>
    <script src="/library/bower_components/stomp-websocket/lib/stomp.js"></script>

    <script src="/javascripts/app.js"></script>
    <script src="/javascripts/socketIO.js"></script>
    <script src="/javascripts/flowerbedController.js"></script>
</head>
<body ng-controller="flowerbedController">
    <h2 ng-click="phones[0].name = 'Per'">${name}</h2>

    <ul>
        <li ng-repeat="phone in phones">
            {{phone.name}}
            <p>{{phone.snippet}}</p>
        </li>
    </ul>
    1 + 2 = {{1 + 2}}

    <div ng-repeat="flowerBed in socket.flowerBed">
        {{flowerBed.name}}
    </div>
</body>
</html>