(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('loginController', ['$rootScope', '$scope', '$http', '$location', '$window', 'loginService', '$httpParamSerializer', function ($rootScope, $scope, $http, $location, $window, loginService, $httpParamSerializer) {
        $scope.login = {
            id: 0,
            username: "",
            password: "",
            name: "",
            isAdmin: false
        }
        $scope.loginCheck = function (un, pw) {
            var creds = { 'username': 'irma', 'password': 'password' }
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
                if (data) {
                    alert("Reset password email sent!");
                } else {
                    alert("There was an error!");
                }
            });
    }
    $scope.cancel = function () {
        $scope.emailInput = "";
    }
}]);

}());