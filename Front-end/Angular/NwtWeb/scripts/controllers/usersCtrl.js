(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('usersController',  ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        $scope.editUser = {
            name: "",
            username: "",
            profileImg: "",
            id:0
        }
        $scope.users =
        [
        {
            name: "Amra",
            username: "amra_h",
            profileImg: "https://s-media-cache-ak0.pinimg.com/736x/cf/14/b7/cf14b78e20d69be326ff7a1302e9bd7d.jpg",
            id: 1,
        },
        {
            name: "Irma",
            username: "irma_k",
            profileImg: "http://www4.pictures.zimbio.com/mp/NUJvHdoYW7gx.jpg",
            id: 2,
        },
        {
            name: "Nino",
            username: "nino_c",
            profileImg: "http://i.dailymail.co.uk/i/pix/2014/02/11/article-2557079-1B65993E00000578-579_306x423.jpg",
            id: 3,
        },
        {
            name: "Amir",
            username: "amir_s",
            profileImg: "https://s-media-cache-ak0.pinimg.com/736x/70/b6/6d/70b66d7968e7f6578d315d564781fed8.jpg",
            id: 4,
        }
        ]
        //console.log($scope.users);
        $scope.transferData = function (user) {
            $scope.editUser.id = user.id;
            $scope.editUser.name = user.name;
            $scope.editUser.profileImg = user.profileImg;
            $scope.editUser.username = user.username;
            console.log($scope.editUser);

        }
        $scope.loadUsers = function () {
            dataService.list("users", function (data) {
                if (data) {
                    $scope.users = data;
                }
            })
        };
    }]);
}());
