angular.module('meteostationApp', ['ui.bootstrap']);
angular.module('meteostationApp').controller('DatepickerDemoCtrl', function ($scope, $http, $filter) {
    $scope.today = function() {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.dateOptions = {
        formatYear: 'yy',
        minDate: '05/01/2015',
        startingDay: 1
    };

    $scope.formats =['dd/MM/yyyy', 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];

    $scope.getChooseDate = function(){
        $scope.postData = $filter('date')( $scope.dt, "yyyy-MM-dd");
        // $http.post('MeteoJSON.json', {postData: $scope.postData}).success(function(data){
        $http.post('http://localhost:8080//WebServer-1.0-SNAPSHOT/index', {postData: $scope.postData}).success(function(data){

            //$scope.temperature = data.temperatureChart;
            $scope.dataset = [{
                data: data.temperatureChart,
                label: " Temperature"
            }];
            $scope.TempValue = data.currentTemperature;
            $scope.PresValue = data.currentPressure;
            $scope.HumValue = data.currentHumidity;
            $scope.MaxTemperature = data.maxTemperature;
            $scope.MinTemperature = data.minTemperature;
            $scope.AvgTemperature = data.averageTemperature;
            $scope.AvgPressure = data.averagePressure;
            $scope.ArgHumidity = data.averageHumidity;
        })
    };
    $scope.getChooseDate();





});
var app = angular.module('meteostationApp');

app.controller('FlotCtrl', function($scope) {

   /* $http.get('MeteoJSON.json').success(function(data) {
        $scope.dataset = [{
            data: data.temperatureChart,
            label: " Temperature"
        }];
    });*/

    $scope.options = {
        colors: ['#a2d200'],
        series: {
            shadowSize: 0
        },
        legend: {
            show: true
        },
        xaxis: {
            font: {
                color: '#ccc'
            }
            //mode: "time",
            //timeformat: "%h"
        },
        yaxis: {
            font: {
                color: '#ccc'
            }
        },
        grid: {
            hoverable: true,
            clickable: true,
            borderWidth: 0,
            color: '#ccc'
        }
    };
});
app.controller('test', ['$scope', '$timeout', function($scope, $timeout) {
    $scope.animationTime = 10;
    //$scope.TempValue = $scope.currentTemperatute;
    $scope.TempMaxValue = 50;
    //$scope.PresValue = 700;
    $scope.PresMaxValue = 1000;
   // $scope.HumValue = 70;
    $scope.HumMaxValue = 100;
    $scope.gaugeType = 'donut';

    $scope.gaugeOptions = {
        lines: 12,
        // The number of lines to draw
        angle: 0.15,
        // The length of each line
        lineWidth: 0.44,
        // The line thickness
        pointer: {
            length: 0.9,
            // The radius of the inner circle
            strokeWidth: 0.035,
            // The rotation offset
            color: '#000000' // Fill color
        },
        limitMax: 'false',
        // If true, the pointer will not go past the end of the gauge
        colorStart: '#6FADCF',
        // Colors
        colorStop: '#8FC0DA',
        // just experiment with them
        strokeColor: '#eee',
        // to see which ones work best for you
        generateGradient: true
    };




}]);