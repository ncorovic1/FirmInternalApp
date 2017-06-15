(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'teamService', '$http', 'dataService', function ($rootScope, $scope, $location, teamService, $http, dataService) {

        $scope.tempTeamToAddUsers = 0;

        $scope.loadTeams = function () {
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
        $scope.loadTeams();
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
        $scope.getAllUsers = function (team) {
            dataService.list("users",
                function (data) {
                    if (data) {
                        $scope.tempUsers = data;
                        $scope.tempTeamToAddUsers = team;
                    }
                    else {
                        alert("Error!");
                    }
                });
        };
        $scope.getAllUsers();
        $scope.addUserToTeam = function (user) {
            user.team = $scope.tempTeamToAddUsers;
            dataService.update("users", user.id, user, function (data) {
                if (data) {
                    alert("User updated");
                }
                else
                    alert("Error");
            });
        };
    }]);

}());