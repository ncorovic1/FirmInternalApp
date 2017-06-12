(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'teamService', '$http', function ($rootScope, $scope, $location, teamService, $http) {

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
    }]);

}());