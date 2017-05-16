(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('adminController', ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        $scope.loadVacations = function () {
            vacationService.list("vacations",
                function (data) {
                    if (data) {
                        $scope.vacations = data;
                    } else {
                        notificationsConfig.error("Error!");
                    }
                });
        };
    }]);

}());