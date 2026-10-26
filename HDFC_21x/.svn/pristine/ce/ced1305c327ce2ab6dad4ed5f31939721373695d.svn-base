gbmUltraApp.directive('noUnderscore', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.on('keypress', function(event) {
                var keyCode = event.which || event.keyCode;
                if (keyCode === 95) { // 95 is the ASCII code for underscore
                    event.preventDefault();
                }
            });
        }
    };
});