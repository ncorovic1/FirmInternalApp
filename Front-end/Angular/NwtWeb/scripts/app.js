(function () {
    var NwtWeb = angular.module('NwtWeb', ['ngRoute']);

    NwtWeb.config(function($routeProvider) {

        $routeProvider
            .when("/login",
            {
                templateUrl: "views/login.html",
                controller: "loginController"
            })
            .when("/vacations",
            {
                templateUrl: "views/vacations.html",
                controller: "vacationsController"
            })
            .when("/teams",
            {
                templateUrl: "views/teams.html",
                controller: "teamsController"
            })
            .when("/documents",
            {
                templateUrl: "views/documents.html",
                controller: "documentsController"
            })
            .when("/users",
            {
                templateUrl: "views/users.html",
                controller: "usersController"
            })
            .otherwise({ redirectTo: "/login" });
    });
}());