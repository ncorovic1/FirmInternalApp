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
            $http.post('http://localhost:8085/login', creds, 
                            {
                                headers: {
                                    'Content-Type': 'text/plain',
                                },
                                transformResponse: undefined
                            })
                            .then(response => {
                                console.log(response);
                                console.log(response.status);
                                console.log(response.headers);
                                console.log(response.headers.get('Authorization'));
                                $http.defaults.headers.common.Authorization = 'Bearer ' + data.data.access_token;
                                $cookies.put("access_token", data.data.access_token);
                            }, error => {
                         console.log(error)});
            //$http({
            //    method:'POST',
            //    url: 'http://localhost:8085/login',
            //    contentType: "application/json",
            //    data: angular.toJson({ username: 'irma', password: 'password' }, true),
            //    transformResponse:undefined,
            //}).then(function(result) {
            //    console.log(result.data);
            //    $scope.message = result;
            //}, function(error) {
            //    console.log(error);
            //});
		
        //    $window.location.href = '/users';
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
    $scope.login = function () {
        var response = $scope.loginCheck();
        $rootScope.token = response;
        $window.localStorage.setItem("authdata", $scope.login.username + response);
        $http.defaults.headers.common.Authorization = 'Bearer ' + response;

        $log.log(response);
        $rootScope.isLoggedIn = true;
        $location.path('/users');
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