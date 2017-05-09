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
            username: "amrausername",
            profileImg: "https://www.w3schools.com/bootstrap/cinqueterre.jpg",
            id: 5,
        },
        {
            name: "Irma",
            id:3
        }
        ]
        console.log($scope.users);
        $scope.transferData = function (user) {
            $scope.editUser.id = user.id;
            $scope.editUser.name = user.name;
            $scope.editUser.profileImg = user.profileImg;
            $scope.editUser.username = user.username;
            console.log($scope.editUser);

        }
        //$scope.loadUsers = function () {
        //    dataService.list("users", function (data) {
        //        if (data) {
        //            $scope.users = data;
        //        }
        //    })
        //};
    }]);
}());
