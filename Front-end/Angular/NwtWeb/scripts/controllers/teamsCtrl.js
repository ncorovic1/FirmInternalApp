(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'teamService', '$http', 'dataService', function ($rootScope, $scope, $location, teamService, $http, dataService) {
        
        $scope.loadUsers = function () {
            $scope.waitUsers = true;
            teamService.list("teams",
                function (data) {
                    if (data) {
                        $scope.teams = data;
                        $scope.waitUsers = false;
                    } else {
                        alert("Error!");
                    }
                });
        };
        $scope.loadUsers();
        $scope.getTeamMembers = function (id) {
            $scope.waitUsers = true;
            dataService.read("users/byteam", id, function (data) {
                if (data) {
                    $scope.tempTeam = data;
                }
                else {
                    notificationsConfig.error(constants.notificationMessages.ERROR_TEAMS_NOT_LOADED);
                }
            });
        };

    }]);

}());