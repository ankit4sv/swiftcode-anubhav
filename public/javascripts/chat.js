var app = angular.module('chatApp', ['ngMaterial']);

app.controller('chatController', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'Hello'
	},
        {
            'sender': 'BOT',
            'text': ' kya h be ???'
	},
        {
            'sender': 'USER',
            'text': 'Beta tumse naa ho payega'
	},
        {
            'sender': 'BOT',
            'text': 'Aise kaise be !'
	}

	];

});