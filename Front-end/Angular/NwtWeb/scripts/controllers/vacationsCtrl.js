(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('vacationsController', ['$rootScope', '$scope', '$location', 'vacationService', '$window', function ($rootScope, $scope, $location, vacationService, $window) {
        if ($window.localStorage.token === '') $window.location.href = '/#/login';
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
            user:
            {
                id: 0
            },
            vacationType:
                {
                    id: 0
                },
            beginDate: new Date(),
            endDate: new Date(),
        };
        $scope.back = function () {
            $scope.addMonths = $scope.addMonths - 1;
            if (d.getMonth() + $scope.addMonths < 0) $scope.addMonths = 12 + $scope.addMonths;
            $scope.selectedMonth = month[d.getMonth() + $scope.addMonths];
            $scope.getVacations();
        }
        $scope.next = function () {
            $scope.addMonths = $scope.addMonths + 1;
            if (d.getMonth() + $scope.addMonths >= 12) $scope.addMonths = $scope.addMonths - 12;
            $scope.selectedMonth = month[d.getMonth() + $scope.addMonths];
            $scope.getVacations();
        }
        $scope.approveVacation = function (vacation) {
            vacation.status = "APPROVED";
            vacationService.update("vacations", vacation.id, vacation,
            function (data) {
                if (data === "") {
                    alert("Vacation approved");
                } else {
                    $scope.processingRequest = false;
                }
            });
        }
        $scope.rejectVacation = function (vacation) {
            vacation.status = "REJECTED";
            vacationService.update("vacations", vacation.id, vacation,
            function (data) {
                if (data === "") {
                    alert("Vacation denied");
                } else {
                    $scope.processingRequest = false;
                }
            });
        }
        $scope.getVacations = function () {
            vacationService.list("vacations/bymonth/" + $scope.addMonths,
            function (data) {
                if (data) {
                    $scope.vacations = data;
                } else {
                    alert("Vacations not loaded.")
                }
            });
            fetchEventTypes();
        };

        function fetchEventTypes() {
            vacationService.list("vacationTypes",
            function (data) {
                $scope.vacationTypes = data;
                $scope.eventDescriptions = data;
            });
        };

        $scope.createRequest = function () {
            $scope.requestItem.user.id = $window.localStorage.currentUserId;
            $scope.requestItem.vacationType.id = $scope.selectedType.id;
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
                user:
                {
                    id: 0
                },
                vacationType:
                    {
                        id: 0
                    },
                beginDate: new Date(),
                endDate: new Date(),
            }
            $scope.showDescription = false;
        };

        $scope.getVacations();
    }]);
}());

