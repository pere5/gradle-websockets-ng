<html lang="en" ng-app="flowerApp">
<head>
    <meta charset="utf-8">
    <title>Flower bed</title>
    <link rel="stylesheet" href="/library/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="/stylesheets/flowerBed.css">
    <script src="/library/bower_components/angular/angular.js"></script>
    <script src="/library/bower_components/angular-route/angular-route.js"></script>
    <script src="/library/bower_components/sockjs/sockjs.js"></script>
    <script src="/library/bower_components/stomp-websocket/lib/stomp.js"></script>

    <script src="/javascripts/flowerApp.js"></script>
    <script src="/javascripts/SocketService.js"></script>
    <script src="/javascripts/flowerbedController.js"></script>
</head>
<body class="bodyBackground">
    <div class="container">
        <div class="row-fluid marginTop">
            <div ng-view></div>
        </div>
    </div>
</body>
</html>