'use strict';

angular.module('crudApp').factory('ClothService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClothes: loadAllClothes,
                getAllClothes: getAllClothes,
                getCloth: getCloth,
                createCloth: createCloth,
                updateCloth: updateCloth,
                removeCloth: removeCloth
            };

            return factory;

            function loadAllClothes() {
                console.log('Fetching all clothes');
                var deferred = $q.defer();
                $http.get(urls.CLOTH_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all clothes');
                            $localStorage.clothes = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading clothes');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllClothes(){
                return $localStorage.clothes;
            }

            function getCloth(id) {
                console.log('Fetching Cloth with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CLOTH_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Cloth with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading CLth with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createCloth(cloth) {
                console.log('Creating Cloth');
                var deferred = $q.defer();
                $http.post(urls.CLOTH_SERVICE_API, cloth)
                    .then(
                        function (response) {
                            loadAllClothes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Cloth : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateCloth(cloth, id) {
                console.log('Updating Cloth with id '+id);
                var deferred = $q.defer();
                $http.put(urls.CLOTH_SERVICE_API + id, cloth)
                    .then(
                        function (response) {
                            loadAllClothes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Cloth with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeCloth(id) {
                console.log('Removing Cloth with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CLOTH_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClothes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Cloth with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);