(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('teamsController', ['$rootScope', '$scope', '$location', 'teamService', 'userService', function ($rootScope, $scope, $location, teamService, userService) {
        $scope.users =
        [
        {
            name: "Irma",
            profileImg: "../images/avatars/1.jpg",
            id: 3
        },
        {
            name: "Amir",
            profileImg: "../images/avatars/2.jpg",
            id: 3
        },
        {
            name: "Nino",
            profileImg: "../images/avatars/3.jpg",
            id: 3
        },
        {
            name: "Amra",
            profileImg: "../images/avatars/4.jpg",
            id: 3
        }
        ]
        $scope.loadTeams = function () {
            $scope.waitUsers = true;
            teamService.list("teams", function(data) {
                    if (data) {
                        $scope.teams = data;
                        $scope.waitUsers = false;
                    } else {
                        notificationsConfig.error("Error!");
                    }
                });
        };

        //$scope.loadTeamUsers = function (id) {
        //    $scope.waitUsers = true;
        //    userService.list("users", function (data) {
        //        if (data) {
        //            $scope.teamUsers = data;
        //            $scope.waitUsers = false;
        //        } else {
        //            notificationsConfig.error("Error!");
        //        }
        //    });
        //};

        $scope.loadTeams();
    }]);

}());