(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('documentsController', ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        $scope.documents =
        [
        {
            id: 1,
            type: "Pravilnik firme",
            author: "amra_h"
        },
        {
            id: 2,
            type: "Skype directory",
            author: "irma_k"
        },
        {
            id: 3,
            type: "Discipline procedures",
            author: "nino_c"
         
        },
        {
            id: 4,
            type: "Uposlenici",
            author: "amir_s"
        }
        ]
    
        $scope.loadUsers = function () {
            dataService.list("documents", function (data) {
                if (data) {
                    $scope.documents = data;
                }
            })
        };
    }]);

}());