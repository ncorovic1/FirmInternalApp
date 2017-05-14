(function () {
    var NwtWeb = angular.module('NwtWeb');
    NwtWeb.controller('vacationsController',  ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService)  {
        $scope.loadVacations = function () {
            $scope.waitUsers = true;
            vacationsService.list("vacations", function (data) {
                if (data) {
                    $scope.vacations = data;
                    $scope.waitUsers = false;
                } else {
                    notificationsConfig.error("Error!");
                }
            });
        };
        $scope.loadUsers();

    }]);
}());
