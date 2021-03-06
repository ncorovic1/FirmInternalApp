﻿(function () {
    var NwtWeb = angular.module('NwtWeb', ['ngRoute']);

    NwtWeb.config(function($routeProvider) {

        $routeProvider
            .when("/login",
            {
                templateUrl: "views/login.html",
                controller: "loginController"
            })
            .when("/vacationsRequest",
                {
                    templateUrl: "views/vacationRequest.html",
                    controller: "vacationsController"
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
            .when("/admin",
            {
                templateUrl: "views/admin.html",
                controller: "adminController"
            })
            .when("/users",
            {
                templateUrl: "views/users.html",
                controller: "usersController"
            })
            .when("/notFound",
            {
                templateUrl: "views/notFound.html",
            })
            .otherwise({ redirectTo: "/notFound" });
    });
}());