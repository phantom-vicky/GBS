gbmUltraApp.directive('leadingSpace', function() {
    return {
        restrict: 'A',
        require: 'ngModel', // Require ngModel controller for data binding
        link: function(scope, element, attrs, ngModelCtrl) {
            // Watch for changes in the view value (user input)
            ngModelCtrl.$parsers.push(function(viewValue) {
                if (viewValue.startsWith(' ')) {
                    var trimmedValue = viewValue.trimStart();
                    ngModelCtrl.$setViewValue(trimmedValue); // Update model value
                    ngModelCtrl.$render(); // Update view
                    return trimmedValue; // Return trimmed value to pipeline
                }
                return viewValue; // Return original value if no leading spaces
            });
        }
    };
});