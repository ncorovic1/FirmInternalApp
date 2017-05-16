(function() {
    var NwtWeb = angular.module('NwtWeb');
    NwtWeb.controller('vacationsController',
        [
            '$rootScope', '$scope', '$location', 'dataService', function($rootScope, $scope, $location, dataService) {

                $scope.requestItem = {
                    id: 0,
                    employeeId: $rootScope.currentUser.id,
                    eventType: 0,
                    startDate: null,
                    endDate: null,
                    note: "",
                    approved: false,
                    isCanceled: false
                };


                function fetchMyEvents() {
                    $scope.waitEvents = true;
                    dataService.read("events",
                        $rootScope.currentUser.id,
                        function(data) {
                            if (data) {
                                $scope.myEvents = data;
                                $scope.waitEvents = false;
                            } else {
                                notificationsConfig.error(constants.notificationMessages.ERROR_ABSENCE_DAYS_NOT_LOADED);
                            }
                        });
                };

                function fetchEventTypes() {
                    vacationsService.list("eventtypes",
                        function(data) {
                            $scope.eventTypes = data;
                            $scope.eventDescriptions = data;
                        });
                };

                $scope.createRequest = function() {
                    dataService.create("events",
                        $scope.requestItem,
                        function(data) {
                            if (data) {
                                $scope.processingRequest = false;
                                if ($scope.requestItem.eventType === 12) {
                                    notificationsConfig.success("Remote work successfully recorded");
                                } else {
                                    notificationsConfig.success(constants.notificationMessages
                                        .SUCCESS_ABSENCE_DAY_REQUEST);
                                }
                                fetchMyEvents();
                                $scope.clearRequest();
                            } else {
                                $scope.processingRequest = false;
                                notificationsConfig.error($rootScope.message);
                            }
                        });
                }

                $scope.clearRequest = function() {
                    $scope.requestItem = {
                        id: 0,
                        employeeId: $rootScope.currentUser.id,
                        eventType: 0,
                        startDate: null,
                        endDate: null,
                        note: "",
                        approved: false,
                        isCanceled: false
                    }
                    $scope.showDescription = false;
                };
            }
        ]);
});