(function () {
    var NwtWeb = angular.module('NwtWeb');

<<<<<<< Updated upstream
    NwtWeb.controller('usersController', ['$rootScope', '$scope', '$location', 'dataService', '$http', '$window', function ($rootScope, $scope, $location, dataService, $http, $window) {
        if ($window.localStorage.token === '') $window.location.href = '/#/login';
        $scope.editUser = {
            first_name: "",
            username: "",
            id: 0,
        }
        $scope.newUser= {
            id: 0,
            address: "",
            date_of_birth: 0,
            days_of_vacation: 24,
            email: "",
            first_name: "",
            gender: "",
            last_name:"",
            password: "",
            role: "",
            telephone: "",
            username: "",
            team_id: 0,
        };
        $scope.delUser = {
            id: 0,
            address: "",
            date_of_birth: 0,
            days_of_vacation: 24,
            email: "",
            first_name: "",
            gender: "",
            last_name: "",
            password: "",
            role: "",
            telephone: "",
            username: "",
            team_id: 0,
        };
        //console.log($scope.users);
        $scope.transferData = function (u) {
            $scope.editUser.id = u.id;
            $scope.editUser.first_name = u.first_name;
            $scope.editUser.username = u.username;
         //   console.log($scope.editUser);

        }
        $scope.loadUsers = function () {
            dataService.list("users",
                function(data) {
                    if (data) {
                        $scope.users = data;
                    }
                    else {
                        alert("Error!");
                    }
                });
        };
=======
    NwtWeb.controller('usersController',  ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        $scope.korisnici = ["Irma Karasoftic", "Amir Šabanović", "Nino Ćorović", "Amra Hadžiarapović"];
>>>>>>> Stashed changes
        $scope.addUser = function () {
            $scope.korisnici.push($scope.addMe);
        }
    }]);
}());
