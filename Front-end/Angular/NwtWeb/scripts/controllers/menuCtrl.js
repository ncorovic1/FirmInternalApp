(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('menuController', ['$rootScope', '$scope', '$http', '$window', function ($rootScope, $scope, $http, $window) {

        $scope.deleteToken = function () {
            $http.defaults.headers.common.Authorization = null;
            $window.localStorage.token = '';
            $rootScope.loggedIn = false;
        };

        $scope.isAdmin = $window.localStorage.role == "ADMIN";

    }]);

}());