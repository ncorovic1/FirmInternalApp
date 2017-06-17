(function () {
    var NwtWeb = angular.module('NwtWeb');

<<<<<<< Updated upstream
    NwtWeb.controller('documentsController', ['$rootScope', '$scope', '$location', 'documentService', '$http', '$window', function ($rootScope, $scope, $location, documentService, $http, $window) {
        if ($window.localStorage.token === '') $window.location.href = '/#/login';

        $scope.previewDocument = {
            id: 0,
            title: "",
            content: "",
            created_at: "",
            author_id: ""
        }
        $scope.newDocument = {
            id: 0,
            content: "",
            created_at: new Date(),
            modified_at: new Date(),
            title: "",
            author_id: 0
        };

        $scope.transferContent = function (document) {
            $scope.previewDocument.id = document.id;
            $scope.previewDocument.title = document.title;
            $scope.previewDocument.created_at = document.created_at;
            $scope.previewDocument.author_id = document.author_id;
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
            documentService.create("documents", $scope.newDocument, function (data) {
                    if (data) {
                        $scope.getDocuments();
                        alert("Document created");
                    }
                    else
                        alert("Error");
                })
        };
        $scope.getDocuments();
    
=======
    NwtWeb.controller('documentsController', ['$rootScope', '$scope', '$location', 'dataService', function ($rootScope, $scope, $location, dataService) {
        
>>>>>>> Stashed changes
    }]);

}());