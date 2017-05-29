(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('documentsController', ['$rootScope', '$scope', '$location', 'documentService', function ($rootScope, $scope, $location, documentService) {
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
           type: "Skype directory",
           author: "XY",
           content: "Neki sadržaj"
       },
       {
           id: 2,
           type: "Firm policy",
           author: "Amra",
           content: " OK "
       }]
        $scope.transferContent = function (document) {
            $scope.previewDocument.id = document.id;
            $scope.previewDocument.type = document.type;
            $scope.previewDocument.author = document.author;
            $scope.previewDocument.content = document.content;
            console.log($scope.previewDocument);

        }
        $scope.getDocuments = function () {
            documentService.list("documents", function (data) {
                if (data) {
                    $scope.documents = data;
                }
            })
        };
        $scope.getDocuments();

    }]);

}());