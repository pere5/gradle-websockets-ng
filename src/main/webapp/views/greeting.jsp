<html lang="en" ng-app="phonecatApp">
<head>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
    <script src="bower_components/angular/angular.js"></script>
    <script src="js/controller.js"></script>
</head>
<body ng-controller="PhoneListCtrl">
    <h2 ng-click="phones[0].name = 'Per'">Hi ${name}!</h2>


    <ul>
        <li ng-repeat="phone in phones">
            {{phone.name}}
            <p>{{phone.snippet}}</p>
        </li>
    </ul>

    1 + 2 = {{1 + 2}}

</body>
</html>