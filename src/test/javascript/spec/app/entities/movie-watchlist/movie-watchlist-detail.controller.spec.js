'use strict';

describe('Controller Tests', function() {

    describe('Movie_watchlist Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockMovie_watchlist, MockUser, MockMovie;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockMovie_watchlist = jasmine.createSpy('MockMovie_watchlist');
            MockUser = jasmine.createSpy('MockUser');
            MockMovie = jasmine.createSpy('MockMovie');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Movie_watchlist': MockMovie_watchlist,
                'User': MockUser,
                'Movie': MockMovie
            };
            createController = function() {
                $injector.get('$controller')("Movie_watchlistDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sandboxApp:movie_watchlistUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
