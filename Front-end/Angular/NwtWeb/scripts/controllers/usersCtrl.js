(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('usersController',  ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        $scope.users =
        [
        {
            name: "Amra",
            id: 5,
        },
        {
            name: "Irma",
            id:3
        }
        ]
        console.log($scope.users);
        //$scope.loadUsers = function () {
        //    dataService.list("users", function (data) {
        //        if (data) {
        //            $scope.users = data;
        //        }
        //    })
        //};
    }]);
}());
