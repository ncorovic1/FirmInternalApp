(function () {
    var NwtWeb = angular.module('NwtWeb');

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
        $scope.addUser = function () {
           // if ($scope.newUser)
                dataService.create("users", $scope.newUser,function (data) {
                    if (data) {
                        $scope.loadUsers();
                        alert("User created");
                    }
                    else
                        alert("Error");
            });
        };
        $scope.getLocalUser = function () {
            $http.defaults.headers.common.Authorization = $window.localStorage.token;
            dataService.list("users/byusername/" + $window.localStorage.username,
                function (data) {
                    if (data) {
                        $window.localStorage.setItem("role", data.role);
                        $window.localStorage.setItem("currentUserId", data.id);
                    }
                    else {
                        alert("Not authenticated!");
                    }
                });
        }
        $scope.getLocalUser();

        $scope.loadUsers();
        //$scope.deleteUser = function () {
        //    $scope.delUser.id=user
        //    // if ($scope.newUser)
        //    dataService.remove("users", $scope.newUser.id, function (data) {
        //        if (data) {
        //            $scope.loadUsers();
        //            alert("User deleted");
        //        }
        //        else
        //            alert("Error");
        //    });
        //};
        $scope.editUser = function () {
            // if ($scope.newUser)
            dataService.update("users", $scope.newUser.id,$scope.newUser, function (data) {
                if (data) {
                    $scope.loadUsers();
                    alert("User updated");
                }
                else
                    alert("Error");
            });
        };



    }]);
}());
