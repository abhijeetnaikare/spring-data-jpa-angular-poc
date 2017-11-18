'use strict';

angular.module('crudApp').controller('ClothController',
    ['ClothService', '$scope',  function( ClothService, $scope) {

        var self = this;
        self.cloth = {};
        self.clothes=[];

        self.submit = submit;
        self.getAllClothes = getAllClothes;
        self.createCloth = createCloth;
        self.updateCloth = updateCloth;
        self.removeCloth = removeCloth;
        self.editCloth = editCloth;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.cloth.id === undefined || self.cloth.id === null) {
                console.log('Saving New Cloth', self.cloth);
                createCloth(self.cloth);
            } else {
                updateCloth(self.cloth, self.cloth.id);
                console.log('Cloth updated with id ', self.cloth.id);
            }
        }

        function createCloth(cloth) {
            console.log('About to create cloth');
            ClothService.createCloth(cloth)
                .then(
                    function (response) {
                        console.log('Cloth created successfully');
                        self.successMessage = 'Cloth created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.cloth={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Cloth');
                        self.errorMessage = 'Error while creating Cloth: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateCloth(cloth, id){
            console.log('About to update cloth');
            ClothService.updateCloth(cloth, id)
                .then(
                    function (response){
                        console.log('Cloth updated successfully');
                        self.successMessage='Cloth updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Cloth');
                        self.errorMessage='Error while updating Cloth '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeCloth(id){
            console.log('About to remove Cloth with id '+id);
            ClothService.removeCloth(id)
                .then(
                    function(){
                        console.log('Cloth '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing cloth '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllClothes(){
            return ClothService.getAllClothes();
        }

        function editCloth(id) {
            self.successMessage='';
            self.errorMessage='';
            ClothService.getCloth(id).then(
                function (cloth) {
                    self.cloth = cloth;
                },
                function (errResponse) {
                    console.error('Error while removing cloth ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.cloth={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);