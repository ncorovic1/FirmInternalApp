(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('loginController', ['$rootScope', '$scope', '$http', '$location', '$window', 'loginService', function ($rootScope, $scope, $http, $location, $window, loginService) {
        $scope.login = {
            id: 0,
            username: "",
            password: "",
            name: "",
            isAdmin: false
        }
        $scope.loginCheck = function () {
            data = {"username": "irma", "password": "password"}
            var a = $http({
                    url: 'http://localhost:8085/login',
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: {"username":"irma", "password":"password"},
                    transformResponse: undefined
                }).then(function(response){
                    return response.data;
                });
            //loginService.create("login", function (data) {
            //    if (data) {
            //        $scope.users = data;
            //    }
            //})
        }
        $scope.login = function () {
            var response = $scope.loginCheck();
            $rootScope.token = response;
            $window.localStorage.setItem("authdata", $scope.login.username + response);
            $http.defaults.headers.common.Authorization = 'Bearer ' + response;

            $log.log(response);
            $rootScope.isLoggedIn = true;
            $location.path('/users');
        }
    }]);

}());