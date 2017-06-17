(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('loginController', ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        $scope.login = {
            id: 0,
            username: "",
            password: "",
            name: "",
            isAdmin: false
        }
        $rootScope.loggedIn = false;
<<<<<<< Updated upstream

        $scope.loginCheck = function (un, pw) {
            var creds = { 'username': un, 'password': pw }
            $http.post('http://localhost:8085/login', JSON.stringify(creds), 
                            {
                                headers: {
                                    'Content-Type': 'text/plain',
                                },
                                transformResponse: undefined
                            })
                            .then(response => {
                                $http.defaults.headers.common.Authorization = response.headers('Authorization');
                                $window.localStorage.setItem("token", response.headers('Authorization'));
                                $rootScope.loggedIn = true;
                                $window.location.href = '/#/users';
                            }, error => {
                         console.log(error)});
           
        };
       
    function handleSuccess() {
        return { success: true };
    }

    function handleSuccessUsera(response) {
        return response.data;
    }

    function handleError(error) {
        return function () {
            return { success: false, message: error };
        };
    }

    $scope.sendResetPasswordEmail = function (email) {
        loginService.create("users", email,
            function (data) {
=======
        $rootScope.isAdmin = false;
        $rootScope.checkLogin = function () {
            dataService.create("login", $scope.login, function (data) {
>>>>>>> Stashed changes
                if (data) {
                    if (data.isAdmin) $rootScope.isAdmin = true;
                    else $rootScope.isAdmin = false;
                    $rootScope.loggedIn = true;
                    $location.url('/users');
                }
                else {
                    $rootScope.loggedIn = false;
                    alert("Login failed");
                }
            })
            console.log($rootScope.isAdmin);
            console.log($rootScope.loggedIn);
        }

        console.log($rootScope.isAdmin);
        console.log($rootScope.loggedIn);
    }]);

}());