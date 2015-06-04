<!doctype html>
<html ng-app="meteostationApp">
<head>
    <script src="${pageContext.request.contextPath}/resources/angular/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ui-bootstrap-tpls.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/flot/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/gauge.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="${pageContext.request.contextPath}/resources/controllers/controller.js"></script>
    <script src="${pageContext.request.contextPath}/resources/directive/directive.js"></script>
</head>
<body>
<div class="container">
    <h1>Meteostation</h1>
    <div ng-controller="DatepickerDemoCtrl">
        <div class="row">
            <div class="col-md-3">
                <div style="display:inline-block; min-height:290px;">
                    <datepicker ng-model="dt" datepicker-options="dateOptions" class="well well-sm" ng-click="getChooseDate()" ></datepicker>
                </div>
            </div>
            <div class="col-md-3 indicators" ng-controller="test">
                <p>Temperature: {{TempValue}} &deg;C</p>
                <canvas gaugejs options="gaugeOptions" value="TempValue" max-value="TempMaxValue" animation-time="animationTime"></canvas>
            </div>
            <div class="col-md-3 indicators" ng-controller="test">
                <p>Pressure: {{PresValue}} mm Hg</p>
                <canvas gaugejs options="gaugeOptions" value="PresValue" max-value="PresMaxValue" animation-time="animationTime"></canvas>
            </div>
            <div class="col-md-3 indicators" ng-controller="test">
                <p>Humidity: {{HumValue}} %</p>
                <canvas gaugejs options="gaugeOptions" value="HumValue" max-value="HumMaxValue" animation-time="animationTime"></canvas>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <h3>Saint-Petersburg</h3>
                <p><em>{{dt | date:'fullDate' }}</em></p>
                <p><em>Max temperature: {{MaxTemperature}} &deg;C</em></p>
                <p><em>Min temperature: {{MinTemperature}} &deg;C</em></p>
                <p><em>Avr temperature: {{AvgTemperature}} &deg;C</em></p>
            </div>
            <div ng-controller="FlotCtrl" class="col-md-8">
                <flot dataset="dataset" options="options" height="280px" width="780px"></flot>
            </div>
        </div>
        <!--pre>Selected date is: <em>{{dt | date:'fullDate' }}</em></pre-->
    </div>
</div>
</body>
</html>