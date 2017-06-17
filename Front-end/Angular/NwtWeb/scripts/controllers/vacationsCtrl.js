(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('vacationsController', ['$rootScope', '$scope', '$location', 'vacationService', '$window', function ($rootScope, $scope, $location, vacationService, $window) {
        if ($window.localStorage.token === '') $window.location.href = '/#/login';
        $scope.vacationTypes =
        [
        {
            name: "Remote work",
            id: 1
        },
        {
            name: "Vacation",
            id: 2
        },
        {
            name: "Sick day",
            id: 3
        },
        ];
        $scope.addMonths = 0;
        var d = new Date();
        var month = new Array();
        month[0] = "January";
        month[1] = "February";
        month[2] = "March";
        month[3] = "April";
        month[4] = "May";
        month[5] = "June";
        month[6] = "July";
        month[7] = "August";
        month[8] = "September";
        month[9] = "October";
        month[10] = "November";
        month[11] = "December";
        $scope.selectedMonth = month[d.getMonth()];

        $scope.selectedType =
        {
            name: "Remote work",
            id: 1
        }
        $scope.requestItem = {
            id: 0,
            employeeId: 1,
            eventType: 0,
            startDate: new Date(),
            endDate: new Date(),
            note: "",
            approved: false,
            isCanceled: false
        };
        $scope.back = function () {
            $scope.addMonths = $scope.addMonths - 1;
            if (d.getMonth() + $scope.addMonths < 0) $scope.addMonths = 12 + $scope.addMonths;
            $scope.selectedMonth = month[d.getMonth() + $scope.addMonths];
        }
        $scope.next = function () {
            $scope.addMonths = $scope.addMonths + 1;
            if (d.getMonth() + $scope.addMonths >= 12) $scope.addMonths = $scope.addMonths - 12;
            $scope.selectedMonth = month[d.getMonth() + $scope.addMonths];
        }

        $scope.getVacations = function () {
            vacationService.list("vacations",
            function (data) {
                if (data) {
                    $scope.vacations = data;
                } else {
                    alert("Vacations not loaded.")
                }
            });
        };

        function fetchEventTypes() {
            vacationsService.list("eventtypes",
            function (data) {
                $scope.eventTypes = data;
                $scope.eventDescriptions = data;
            });
        };

        $scope.createRequest = function () {
            $scope.requestItem.vacationType = $scope.selectedType;
            vacationService.create("vacations", $scope.requestItem,
            function (data) {
                if (data) {
                    $scope.processingRequest = false;
                    if ($scope.requestItem.eventType === 12) {
                        alert(OK);
                    } else {
                        alert(NOK);
                    }
                    fetchMyEvents();
                    $scope.clearRequest();
                } else {
                    $scope.processingRequest = false;
                }
            });
        }

        $scope.clearRequest = function () {
            $scope.requestItem = {
                id: 0,
                employeeId: 0,
                eventType: 0,
                startDate: null,
                endDate: null,
                note: "",
                approved: false,
                isCanceled: false
            }
            $scope.showDescription = false;
        };

        $scope.getVacations();
    }]);
}());

