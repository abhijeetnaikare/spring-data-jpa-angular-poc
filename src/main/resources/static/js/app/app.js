
var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/spring-data-jpa-angular-poc',
    CLOTH_SERVICE_API : 'http://localhost:8080/spring-data-jpa-angular-poc/api/cloth/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ClothController',
                controllerAs:'ctrl',
                resolve: {
                    clothes: function ($q, ClothService) {
                        console.log('Load all clothes');
                        var deferred = $q.defer();
                        ClothService.loadAllClothes().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);