gbmUltraApp.directive('allowOnlyNumbers', function() {
    return {
        restrict: 'A',
        link: function (scope, elm, attrs, ctrl) {  
            elm.on('keydown', function (event) {
                if (event.which == 64 || event.which == 16) {  
                    // to allow numbers  
                    return false;  
                } else if (event.which >= 48 && event.which <= 57) {  
                    // to allow numbers  
                    return true;  
                } else if (event.which >= 96 && event.which <= 105) {  
                    // to allow numpad number  
                    return true;  
                } else if ([8, 9, 13, 27, 37, 38, 39, 40, 46].indexOf(event.which) > -1) {  
                    // to allow backspace, enter, escape, arrows  
                    return true;  
                } else if(event.which == 110 || event.which == 190 ){
                	// to allow dot  
                    return true;  
                } else {  
                    event.preventDefault();  
                    // to stop others  
                    return false;  
                }  
            });  
        }  
    }  
});
gbmUltraApp.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);
gbmUltraApp.directive('validFile', function () {
    return {
        require: 'ngModel',
        link: function (scope, el, attrs, ngModel) {
            ngModel.$render = function () {
                ngModel.$setViewValue(el.val());
            };

            el.bind('change', function () {
                scope.$apply(function () {
                    ngModel.$render();
                });
            });
        }
    };
});
gbmUltraApp.filter('offset', function() {
	  return function(input, start) {
		    start = parseInt(start, 10);
		    return input.slice(start);
	  };
});
gbmUltraApp.directive('leftMenuDirective', ['$location', function(location) {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment
        scope: {
            //@ reads the attribute value, = provides two-way binding, & works with functions
            title: '@'
        },
        templateUrl: 'views/nps/leftmenu.html',
        link: function($scope, element, attrs) {
            var path = "#!" + location.path();

            if ($scope.$parent.leftMenu.add.display == true) {
                $("#left-menu-add").css('display', 'block');
                $("#left-menu-add a").attr('href', $scope.$parent.leftMenu.add.href);
                if (path == $scope.$parent.leftMenu.add.href) {
                    $("#left-menu-add a").addClass('leftMenuActive');
                }
            } else {
                $("#left-menu-add").hide();
            }

	    	if($scope.$parent.leftMenu.modify.display == true){
	    		$("#left-menu-modify").css('display', 'block');
	    		$("#left-menu-modify a").attr('href', $scope.$parent.leftMenu.modify.href);
	    		if(path == $scope.$parent.leftMenu.modify.href){
	    			$("#left-menu-modify a").addClass('leftMenuActive');
	    		}
	    	} else {
	    		$("#left-menu-modify").hide();
	    	}

	    	if($scope.$parent.leftMenu.verify.display == true){
	    		$("#left-menu-verify").css('display', 'block');
	    		$("#left-menu-verify a").attr('href', $scope.$parent.leftMenu.verify.href);
	    		if(path == $scope.$parent.leftMenu.verify.href){
	    			$("#left-menu-verify a").addClass('leftMenuActive');
	    		}
	    	} else {
	    		$("#left-menu-verify").hide();
	    	}

	    	if($scope.$parent.leftMenu.delete.display == true){
	    		$("#left-menu-delete").css('display', 'block');
	    		$("#left-menu-delete a").attr('href', $scope.$parent.leftMenu.delete.href);
	    		if(path == $scope.$parent.leftMenu.delete.href){
	    			$("#left-menu-delete a").addClass('leftMenuActive');
	    		}
	    	} else {
	    		$("#left-menu-delete").hide();
	    	}

	    	if($scope.$parent.leftMenu.inquiry.display == true){
	    		$("#left-menu-inquiry").css('display', 'block');
	    		$("#left-menu-inquiry a").attr('href', $scope.$parent.leftMenu.inquiry.href);
	    		if(path == $scope.$parent.leftMenu.inquiry.href){
	    			$("#left-menu-inquiry a").addClass('leftMenuActive');
	    		}
	    	} else {
	    		$("#left-menu-inquiry").hide();
	    	}

	    	if(typeof($scope.$parent.leftMenu.upload) == 'undefined'){
	    		$("#left-menu-upload").hide();
	    	} else {
		    	if($scope.$parent.leftMenu.upload.display == true){
		    		$("#left-menu-upload").css('display', 'block');
		    		$("#left-menu-upload a").attr('href', $scope.$parent.leftMenu.upload.href);
		    		if(path == $scope.$parent.leftMenu.upload.href){
		    			$("#left-menu-upload a").addClass('leftMenuActive');
		    		}
		    	} else {
		    		$("#left-menu-upload").hide();
		    	}
	    	}
	    	//console.log($scope);
    		//console.log($scope.$parent.leftMenu.inquiry.display);
	    } //DOM manipulation
	}
}]);


gbmUltraApp.directive('noticeMessageDirective', function() {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment
        scope: {
            //@ reads the attribute value, = provides two-way binding, & works with functions
            title: '@'
        },
        templateUrl: 'views/common/noticeMessage.html'
    }
});
gbmUltraApp.directive('stringToNumber', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attrs, ngModel) {
      ngModel.$parsers.push(function(value) {
        return '' + value;
      });
      ngModel.$formatters.push(function(value) {
        return parseFloat(value);
      });
    }
  };
});

gbmUltraApp.directive('topMenuDirective', function() {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment
        scope: {
            //@ reads the attribute value, = provides two-way binding, & works with functions
            title: '@',
            username: '=',
	        solDate: "=",
            menuLists: "="
        },
        templateUrl: 'views/nps/topmenu.html',
        controller: function ($scope, $rootScope) {
            /* page redirection */
            $scope.childPageRedirection = function(childMenuItem){
                $rootScope.childMenuParams = childMenuItem.children;
            }
        },
        link: function($scope, element, attrs) {
            $('ul.nav li.dropdown').hover(function() {
                $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
            }, function() {
                $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
            });
        } //DOM manipulation
    }
});

gbmUltraApp.directive("formOnChange", function($parse){
  return {
    require: "form",
    link: function(scope, element, attrs){
       var cb = $parse(attrs.formOnChange);
       element.on("change", function(){
          cb(scope);
       });
    }
  }
});

gbmUltraApp.directive('chars', function() {
    'use strict';
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function($scope, $elem, attrs, ctrl) {
            var regReplace,
                preset = {
                    'only-numbers': '0-9',
                        'numbers': '0-9\\s',
                        'numbers-slash': '0-9/',
                    'numbers-with-special-characters': '0-9-,/.-',
                    'float': '0-9.?',
                    'only-letters': 'A-Za-z_ ',
                    'only-letters-no-spsace': '^[A-Za-z]+$',
                    'only-letters-spsace': '^[A-Za-z\\s]+$',
                    'only-letters-dot': 'A-Za-z_ .',
                    'letters': 'A-Za-z_ \\s',
                    'email': '\\wÑñ@._ \\-',
                    'alpha-numeric': 'A-Za-z0-9\\s',
                    'latin-alpha-numeric': '\\w_ \\sÑñáéíóúüÁÉÍÓÚÜ',
                    'letters-with-special-characters': 'A-Za-z\\s-,/.()&',
                    'letters-with-numbers' : 'A-Za-z0-9',
                    'alpha-numeric-with-special-characters': '\\w_ \\s\\/\_-',
                    'number-with-colon' : '0-9:'
                },
                filter = preset[attrs.chars] || attrs.chars;
            $elem.on('input', function() {
                regReplace = new RegExp('[^' + filter + ']', 'ig');
                ctrl.$setViewValue($elem.val().replace(regReplace, ''));
                ctrl.$render();
            });
        }
    };
});

gbmUltraApp.directive("limitTo", [function() {
    return {
        restrict: "A",
        link: function(scope, elem, attrs) {
            var limit = parseInt(attrs.limitTo);
            angular.element(elem).on("keypress", function(evt) {
            	evt = (evt) ? evt : window.event;
            	var charCode = (evt.which) ? evt.which : evt.keyCode;
            	if(charCode > 31){
            		if (this.value.length == limit) evt.preventDefault();
            	}
            });
        }
    }
}]);

gbmUltraApp.filter("getLabelFromValuesArray",function() {

   return function(item,valuesArray){
       if(!item || !valuesArray || !valuesArray.length)
          return item;

       for(let valueObject of valuesArray) {
          if(item == valueObject.value) {
             return valueObject.label;
          }
       }

        return item;
    }
});

gbmUltraApp.directive('pdfViewerDirective', function() {
    return {
        restrict: 'E',
        scope: {
            blob: '=',
            fileName: '=',
            registerShowFn: '&'
        },
        templateUrl: 'views/pension/viewPdf.html',

        controller: function($scope, $rootScope) {
            $scope.downloadPdfFile = function() {
                saveAs($scope.blob, $scope.fileName);
            }

            function showViewerModal() {
                setTimeout(function() {
                    if ($scope.blob) {
                        showFile();
                        angular.element('#viewModal').modal('toggle');
                    }
                }, 1000);
            }

            function showFile() {
                // If absolute URL from the remote server is provided, configure the CORS
                // header on that server.
                var url = window.URL.createObjectURL($scope.blob);

                // Loaded via <script> tag, create shortcut to access PDF.js exports.
                var pdfjsLib = window['pdfjs-dist/build/pdf'];

                // The workerSrc property shall be specified.
                pdfjsLib.GlobalWorkerOptions.workerSrc = '../assets/js/pdf.worker.js';

                var pdfDoc = null,
                    pageNum = 1,
                    pageRendering = false,
                    pageNumPending = null,
                    scale = 0.85,
                    canvas = document.getElementById('the-canvas'),
                    ctx = canvas.getContext('2d');

                /**
                 * Get page info from document, resize canvas accordingly, and render page.
                 * @param num Page number.
                 */
                function renderPage(num) {
                    pageRendering = true;
                    // Using promise to fetch the page
                    pdfDoc.getPage(num).then(function(page) {
                        var viewport = page.getViewport({
                            scale: scale
                        });
                        canvas.height = viewport.height;
                        canvas.width = viewport.width;

                        // Render PDF page into canvas context
                        var renderContext = {
                            canvasContext: ctx,
                            viewport: viewport
                        };
                        var renderTask = page.render(renderContext);

                        // Wait for rendering to finish
                        renderTask.promise.then(function() {
                            pageRendering = false;
                            if (pageNumPending !== null) {
                                // New page rendering is pending
                                renderPage(pageNumPending);
                                pageNumPending = null;
                            }
                        });
                    });

                    // Update page counters
                    document.getElementById('page_num').textContent = num;
                }

                /**
                 * If another page rendering in progress, waits until the rendering is
                 * finised. Otherwise, executes rendering immediately.
                 */
                function queueRenderPage(num) {
                    if (pageRendering) {
                        pageNumPending = num;
                    } else {
                        renderPage(num);
                    }
                }

                /**
                 * Displays previous page.
                 */
                function onPrevPage() {
                    if (pageNum <= 1) {
                        return;
                    }
                    pageNum--;
                    queueRenderPage(pageNum);
                }
                document.getElementById('prev').addEventListener('click', onPrevPage);

                /**
                 * Displays next page.
                 */
                function onNextPage() {
                    if (pageNum >= pdfDoc.numPages) {
                        return;
                    }
                    pageNum++;
                    queueRenderPage(pageNum);
                }
                document.getElementById('next').addEventListener('click', onNextPage);

                /**
                 * Asynchronously downloads PDF.
                 */
                pdfjsLib.getDocument(url).promise.then(function(pdfDoc_) {
                    pdfDoc = pdfDoc_;
                    document.getElementById('page_count').textContent = pdfDoc.numPages;

                    // Initial/first page rendering
                    renderPage(pageNum);
                });
            }

            $scope.registerShowFn({
                dirFn: showViewerModal
            });

        }
    }
});

gbmUltraApp.directive('idleServiceDirective', function() {
    return {
        restrict: 'E',
        controller: function($scope, $rootScope, Idle, $uibModal, $state, authService) {

            $scope.$on('IdleStart', function() {
                closeModals();

                $scope.warning = $uibModal.open({
                    templateUrl: 'views/common/idleWarningView.html',
                    windowClass: 'modal-warning'
                });
            });

            $scope.$on('IdleEnd', function() {
                closeModals();
                authService.sendHeartBeat();
            });

            $scope.$on('IdleTimeout', function() {
                closeModals();
                $scope.timedout = $uibModal.open({
                    templateUrl: 'views/common/idleTimedoutView.html',
                    windowClass: 'modal-danger'
                });
                setTimeout(() => {
                    authService.logout(USER_LOGOUT_REASONS.SESSION_EXPIRED)
                        .then(
                            function(response) {
                                closeModals();
                                $state.go('login');
                            });
                }, 2000);
            });

            $scope.start = function() {
                closeModals();
                Idle.watch();
            };

            $scope.stop = function() {
                closeModals();
                Idle.unwatch();

            };

            function closeModals() {
                if ($scope.warning) {
                    $scope.warning.close();
                    $scope.warning = null;
                }

                if ($scope.timedout) {
                    $scope.timedout.close();
                    $scope.timedout = null;
                }
            }

            $rootScope.startIdleService = $scope.start;
            $rootScope.stopIdleService = $scope.stop;
        }
    }
});

gbmUltraApp.filter("getDescriptionFromCodeArray",function() {

   return function(item,valuesArray){
       if(!item || !valuesArray || !valuesArray.length)
          return item;

       for(let valueObject of valuesArray) {
          if(item == valueObject.subCode) {
             return valueObject.subCodeDescription;
          }
       }

       return item;
   }
});
gbmUltraApp.directive('validateUsername', function() {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            ngModelCtrl.$parsers.push(function(viewValue) {
                if (viewValue) {
                   if (viewValue.startsWith(' ')) {
                      var trimmedValue = viewValue.trimStart();
                       ngModelCtrl.$setViewValue(trimmedValue); // Update model value
                       ngModelCtrl.$render(); // Update view
                        return trimmedValue; // Return trimmed value to pipeline
                          }

                    // Check if the first character is numeric or contains leading spaces
                    if (/^\d/.test(viewValue) || /^\s/.test(viewValue) || /\s+/g.test(viewValue)) {
                        // If first character is numeric or contains leading spaces, sanitize input to remove them
                        viewValue = sanitizeInput(viewValue);
                        ngModelCtrl.$setViewValue(viewValue);
                        ngModelCtrl.$render();
                    }

                    // Ensure the rest of the characters are alphanumeric or apostrophes
                    viewValue = viewValue.replace(/[^A-Za-z0-9]/g, '');

                    // Return the sanitized value
                    return viewValue;
                }
                return viewValue;
            });


            function sanitizeInput(input) {
               return input.replace(/^\d+|\s+|[^\w]/g, '').replace(/\s+/g, '');
            }
        }
    };
});