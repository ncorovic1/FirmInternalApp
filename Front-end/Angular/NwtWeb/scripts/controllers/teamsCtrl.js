(function () {
    var NwtWeb = angular.module('NwtWeb');

<<<<<<< Updated upstream
    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'teamService', '$http', 'dataService', '$window', function ($rootScope, $scope, $location, teamService, $http, dataService, $window) {
        if ($window.localStorage.token === '') $window.location.href = '/#/login';

        $scope.tempTeamToAddUsers = 0;

        $scope.loadTeams = function () {
=======
    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {

        $scope.loadUsers = function () {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
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
=======
>>>>>>> Stashed changes
    }]);

}());