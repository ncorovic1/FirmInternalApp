(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('documentsController', ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        $scope.previewDocument = {
            id:0,
            type: "",
            author: "",
            content: ""
        }
        $scope.documents =
        [
        {
            id: 1,
            type: "Pravilnik firme",
            author: "amra_h",
            content:"LALALALALAL"
        },
        {
            id: 2,
            type: "Skype directory",
            author: "irma_k",
            content: ""
        },
        {
            id: 3,
            type: "Discipline procedures",
            author: "nino_c",
            content: ""
         
        },
        {
            id: 4,
            type: "Uposlenici",
            author: "amir_s",
            content: ""
        }
        ]
        $scope.transferContent = function (document) {
            $scope.previewDocument.id = document.id;
            $scope.previewDocument.type = document.type;
            $scope.previewDocument.author = document.author;
            $scope.previewDocument.content = document.content;
            console.log($scope.previewDocument);

        }
        $scope.loadUsers = function () {
            dataService.list("documents", function (data) {
                if (data) {
                    $scope.documents = data;
                }
            })
        };
    }]);

}());