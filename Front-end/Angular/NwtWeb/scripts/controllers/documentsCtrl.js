(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('documentsController', ['$rootScope', '$scope', '$location', 'documentService', '$http', '$window', function ($rootScope, $scope, $location, documentService, $http, $window) {
        if ($window.localStorage.token === '') $window.location.href = '/#/login';

        $scope.previewDocument = {
            id: 0,
            content: "",
            createdAt: "",
            modifiedAt: "",
            title: "",
            author: ""
        }
        $scope.newDocument = {
            id: 0,
            content: "",
            createdAt: new Date(),
            modifiedAt: new Date(),
            title: "",
            author: ""
        };

        $scope.transferContent = function (document) {
            $scope.previewDocument.id = document.id;
            $scope.previewDocument.title = document.title;
            $scope.previewDocument.createdAt = Date(document.createdAt);
            $scope.previewDocument.modifiedAt = document.modifiedAt;
            $scope.previewDocument.author= document.author;
            $scope.previewDocument.content = document.content;
            console.log($scope.previewDocument);

        }

        $scope.getDocuments = function () {
            documentService.list("documents", function (data) {
                    if (data) {
                        $scope.documents = data;
                    
                    } else {
                        alert("Error!");
                    }
            })
        };

        $scope.addDocument = function () {
            documentService.create("documents",$scope.newDocument.author,$scope.newDocument, function (data) {
                    if (data) {
                        $scope.getDocuments();
                        alert("Document created");
                    }
                })
        };
        $scope.getDocuments();

        $scope.removeDocument = function () {
            documentService.remove("documents", $scope.previewDocument.id, function (data) {
                if(data)
                {
                    alert("Document deleted");
                }

            })

        };
    
    }]);
}());

