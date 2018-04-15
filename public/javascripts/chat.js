var app = angular.module('chatApp', ['ngMaterial']);

app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('purple')
        .accentPalette('green');
});

app.controller('chatController', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'Hello'
	},
        {
            'sender': 'BOT',
            'text': 'Hi, what can  i do for you ????'
	},
        {
            'sender': 'USER',
            'text': 'Wake me up after 15hrs'
	},
        {
            'sender': 'BOT',
            'text': 'Done !'
	}

	];

});