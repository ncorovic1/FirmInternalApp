(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('vacationsController', ['$rootScope', '$scope', '$location', 'vacationService', function ($rootScope, $scope, $location, vacationService) {
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
            ]
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


        $scope.getVacations = function () {
            vacationService.list("vacations",
                function (data) {
                    if (data) {
                        $scope.vacations = data;
                    } else {
                        notificationsConfig.error(constants.notificationMessages.ERROR_ABSENCE_DAYS_NOT_LOADED);
                    }
                });
            //};

            //        function fetchEventTypes() {
            //            vacationsService.list("eventtypes",
            //                function (data) {
            //                    $scope.eventTypes = data;
            //                    $scope.eventDescriptions = data;
            //                });
            //        };

            //        $scope.createRequest = function () {
            //            dataService.create("events",
            //                $scope.requestItem,
            //                function (data) {
            //                    if (data) {
            //                        $scope.processingRequest = false;
            //                        if ($scope.requestItem.eventType === 12) {
            //                            notificationsConfig.success("Remote work successfully recorded");
            //                        } else {
            //                            notificationsConfig.success(constants.notificationMessages
            //                                .SUCCESS_ABSENCE_DAY_REQUEST);
            //                        }
            //                        fetchMyEvents();
            //                        $scope.clearRequest();
            //                    } else {
            //                        $scope.processingRequest = false;
            //                        notificationsConfig.error($rootScope.message);
            //                    }
            //                });
            //        }

            //        $scope.clearRequest = function () {
            //            $scope.requestItem = {
            //                id: 0,
            //                employeeId: $rootScope.currentUser.id,
            //                eventType: 0,
            //                startDate: null,
            //                endDate: null,
            //                note: "",
            //                approved: false,
            //                isCanceled: false
            //            }
            //            $scope.showDescription = false;
        };


        $scope.getVacations();


    }]);
}());

