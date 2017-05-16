(function () {
    'use strict';

    var NwtWeb= angular.module("NwtWeb");

    NwtWeb.controller("datepickerController", ['$scope', function ($scope) {

        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];
        $scope.altInputFormats = ['M!/d!/yyyy'];

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.maxDate = null;
        $scope.minDate = new Date();
        $scope.maxDateEmp = new Date();
        $scope.minDateEmp = null;
        $scope.maxDateBirth = new Date();
        $scope.minDateBirth = null;
        $scope.maxDateRequest = null;
        $scope.minDateRequest = new Date('01/01/2010'),
            $scope.startDate = new Date();
        $scope.endDate = new Date();
        $scope.whenDate = new Date();

        $scope.disabled = function (date, mode) {
            return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
        };

        $scope.openUntil = function () {
            $scope.popupUntil.opened = true;
        };

        $scope.openStart = function () {
            $scope.popupStart.opened = true;
        };

        $scope.openSkip = function () {
            $scope.popupSkip.opened = true;
        };

        $scope.openFrom = function () {
            $scope.popupFrom.opened = true;
        };

        $scope.openTo = function () {
            $scope.popupTo.opened = true;
        };

        $scope.openEnd = function () {
            $scope.popupEnd.opened = true;
        };

        $scope.openEmp = function () {
            $scope.popupEmp.opened = true;
        };

        $scope.openBirth = function () {
            $scope.popupBirth.opened = true;
        };

        $scope.popupUntil = {
            opened: false
        };

        $scope.popupStart = {
            opened: false
        };

        $scope.popupSkip = {
            opened: false
        };

        $scope.popupFrom = {
            opened: false
        };

        $scope.popupTo = {
            opened: false
        };

        $scope.popupEnd = {
            opened: false
        };

        $scope.popupEmp = {
            opened: false
        };

        $scope.popupBirth = {
            opened: false
        };
    }]);
}());