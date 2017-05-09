(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {

        $scope.loadUsers = function () {
            $scope.waitUsers = true;
            dataService.list("users",
                function(data) {
                    if (data) {
                        $scope.users = data;
                        $scope.waitUsers = false;
                    } else {
                        notificationsConfig.error("Error!");
                    }
                });
        };

    }]);

}());