(function () {
    var NwtWeb = angular.module('NwtWeb');

    NwtWeb.controller('documentsController', ['$rootScope', '$scope', '$location', 'documentService', function ($rootScope, $scope, $location, documentService) {
        $scope.previewDocument = {
            id:0,
            title: "",
            created_at: "",
            author_id: ""
        }
        $scope.documents =
       [
       {
           id: 1,
           title: "Skype directory",
           created_at: "2017-02-15",
           author_id: " Irma",
           content:"1234567890"
       },
       {
           id: 2,
           title: "Firm policy",
           created_at: "2017-05-30",
           author_id: " Amra ",
           content: "1234567890"
       }]
        $scope.newDocument = {
            id: 0,
            content:"",
            created_at: new Date(),
            modified_at: new Date(),
            title:"",
            author_id:""
        };
        $scope.transferContent = function (document) {
            $scope.previewDocument.id = document.id;
            $scope.previewDocument.title = document.title;
            $scope.previewDocument.created_at = document.created_at;
            $scope.previewDocument.author_id = document.author_id;
            $scope.previewDocument.content= document.content;
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

        $scope.addDocument = function () {
            dataService.create("documents",
            $scope.requestItem,
            function (data) {
                if (data) {
                    $scope.processingRequest = false;
                    if ($scope.requestItem.eventType === 12) {
                        notificationsConfig.success("Remote work successfully recorded");
                    } else {
                        notificationsConfig.success(constants.notificationMessages
                        .SUCCESS_ABSENCE_DAY_REQUEST);
                    }
                    fetchMyEvents();
                    $scope.clearRequest();
                } else {
                    $scope.processingRequest = false;
                    notificationsConfig.error($rootScope.message);
                }
            });
        }
    }]);

}());