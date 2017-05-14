(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'teamService', function ($rootScope, $scope, $location, teamService) {

        $scope.loadUsers = function () {
            $scope.waitUsers = true;
            teamService.list("teams",
                function(data) {
                    if (data) {
                        $scope.teams = data;
                        $scope.waitUsers = false;
                    } else {
                        notificationsConfig.error("Error!");
                    }
                });
        };
        $scope.loadUsers();
    }]);

}());