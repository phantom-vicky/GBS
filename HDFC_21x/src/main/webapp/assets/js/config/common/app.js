var gbmUltraApp = angular.module('gbmUltraApp', ['ngCookies', 'ui.router', 'oc.lazyLoad', 'ui.bootstrap', 'ui.utils', 'ng-duallist', 'angularUtils.directives.dirPagination','ngIdle','angularjs-crypto'])
    .constant('AppConstant', {
        env: 'prod'
    });


gbmUltraApp.config(function($urlRouterProvider, $stateProvider, $ocLazyLoadProvider, $httpProvider ,IdleProvider) {
    $urlRouterProvider.otherwise('/');

    $httpProvider.interceptors.push('appInterceptor');
    $httpProvider.interceptors.push('preventTemplateCache');

    $stateProvider

        .state('index', {
        url: '/',
        templateUrl: 'views/nps/welcome.html',
    })

     //Session Timeout - Total 30 minutes (28 min idle + 2 min warning)
     IdleProvider.idle(1680);   // 28 Minutes (1680 seconds) - Warning appears
     IdleProvider.timeout(120); // 2 Minutes warning before logout


});

gbmUltraApp.run(function($rootScope, $filter, $location, $cookies, $transitions, $window , AppConstant, authService) {
    //For Logging Out while unloading
    $window.onbeforeunload = function() {
          return "Do you want to leave?"
    }
    $window.onunload = function() {
        //&& $cookies.get('username') && //
        if($rootScope.globals && $rootScope.globals.currentUser){
            authService.logout(USER_LOGOUT_REASONS.BROWSER_WINDOW_CLOSED);
        }
    }

    if (AppConstant.env == 'prod' && $cookies.get('username') == undefined) {
        var restrictedPage = $.inArray($location.path(), ['/login']) === -1;
        if (restrictedPage) {
            authService.logout(USER_LOGOUT_REASONS.NORMAL_TYPE);
        }
    }

    /** AES Encryption **/
    $rootScope.base64Key = CryptoJS.enc.Base64.parse("u/Gu5posvwDsXUnV5Zaq4g==");
    $rootScope.iv = CryptoJS.enc.Base64.parse("5D9r9ZVzEYYgha93/aUK2w==");
    $rootScope.toEncrypt = function(encryptObj){
        let encrypted = CryptoJS.AES.encrypt(encryptObj, $rootScope.base64Key, { iv: $rootScope.iv });
        return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
    }

    $rootScope.formatDateToDDMMMYYYY = function(dateObj) {
       return $filter('date')(dateObj, "dd-MMM-yyyy");
    }

    $transitions.onBefore({}, function(transition) {
        // Heartbeat to keep session alive on navigation
        if($rootScope.globals && $rootScope.globals.currentUser){
            authService.sendHeartBeat();
        }

        var data = transition.to().data;
        // check if the state should be protected
        if (data != null && data.authRequired && !authService.isAuthorized(data.requiredPermission)) {
            // redirect to the 'login' state
            return transition.router.stateService.target('forbidden');
        }
    });

    $rootScope.$on('$locationChangeStart',
        function(event, next, current) {
            if (AppConstant.env == 'prod') {
                //$templateCache.removeAll();
                if ($cookies.get('username') == undefined) {
                    var loggedIn = $rootScope.globals ? $rootScope.globals.currentUser : undefined;
                    if (restrictedPage && !loggedIn) {
                        $location.path('/login');
                    }
                } else {
                    var globals = {};
                    globals.username = $cookies.get('username');
                    globals.solId = $cookies.get('solId');
                    globals.password = '';
                    //globals.userDomain = 'FINACLE';
                    globals.userDomain = '';

                    authService.login(globals)
                        .then(loginSuccessHandler,
                            function(response) {
                                $cookies.remove('username', {
                                    path: '/'
                                });
                            }
                        );

                    function loginSuccessHandler(response) {
                        $cookies.remove('username', {
                            path: '/'
                        });
                        var result = response.data.result;
                        globals.lastLoginTime = result.lastLoginTime;
                        globals.bankName = result.lastLoginTime;
                        authService.setGlobals(globals);
                        getPermissions();
                    }

                    function getPermissions() {
                        authService.getPermissions()
                            .then(
                                function(response) {
                                    authService.setPerms(response.data);
                                }
                            );
                    }
                }


            } else {
                $rootScope.globals = {};
                $rootScope.globals.currentUser = {
                    'test': 'test'
                }
            }
        });
    $rootScope.$on('$locationChangeSuccess', function () {
        differentiateOldValueNewValue();
        differentiateOldValueNewValueBla();
    });

});

gbmUltraApp.factory('appInterceptor', function($q, $state, $window, $rootScope) {
    var requestInterceptor = {
        request: function(request) {
            var customContentType = request.headers['Custom-Content-Type'];
            if(request.url.endsWith("/login"))
                 delete $rootScope.csrfToken;
            if($rootScope.csrfToken)
                request.headers['X-CSRF-TOKEN'] = $rootScope.csrfToken;
            var tempData = request.data;
            if (typeof tempData === 'object' && tempData && customContentType != 'upload') {
                request.data = [];
                var newRequest = JSON.stringify(tempData, function(key, value) {
                    if (typeof value === 'object' && value && !Array.isArray(value)) {
                        return Object.keys(value).reduce(function(tempData, k) {
                        if(k == 'encrypted_value' || k == 'password'|| k == 'file' || k == 'fileName' || k == 'subCodeDescription' || k == 'sortField' || k == 'username'){
                            tempData[k] = typeof(value[k]) === 'string' ? value[k] : value[k];
                        } else {
                            tempData[k] = typeof(value[k]) === 'string' ? value[k].toUpperCase() : value[k];
                        }
                            return tempData;
                        }, {});
                    }
                    return value;
                }, 2);

                request.data = newRequest;
                return request;
            } else {
                return request;
            }

        },

        response: function(response) {
            var tempData = response.data;
            if(response.config.url.endsWith("/login")) {
                let newToken = response.headers()["x-csrf-token"]
                if(newToken) {
                    $rootScope.csrfToken = newToken;
                }
            }
            if (typeof tempData === 'object' && tempData) {
                response.data = [];
                var newResponse = JSON.stringify(tempData, function(key, value) {
                    if (typeof value === 'object' && value && !Array.isArray(value)) {
                        return Object.keys(value).reduce(function(tempData, k) {
                            if(k == 'encrypted_value' || k == 'parameterDesc' || k == 'propertyName' || k == 'message' || k == "fileData"
                                || k == "fileName"|| k == "file" || k=="menuUrl" || k=="linkName"
                                || k== 'password' || k =='signature' || k == 'subCodeDescription'
                                || k == 'username'|| k=='DESCRIPTION'){
                               tempData[k] = typeof(value[k]) === 'string' ? value[k] : value[k];
                            } else {
                               tempData[k] = typeof(value[k]) === 'string' ? value[k].toUpperCase() : value[k];
                            }
                            return tempData;
                        }, {});
                    }

                    return value;
                }, 2);

                response.data = JSON.parse(newResponse);
                if (response.data && response.data.message ) {
                                    let msg = response.data.message || "";
                                    if (typeof msg === "string" && (msg.includes("ORA-") || msg.includes("PL/SQL"))) {
                                        response.data.message = "Database Business Exception please contact system administrator.";
                                    }
                                }
                return response;
            } else {
                return response;
            }
        },

        responseError: function(response) {
            if (typeof(response.data) == 'undefined' || response.data == '') {
                response.data = {};
    if(response.status == 403){
                               response.data.message = response.message = 'Your Session is Timed out';
                                $window.sessionStorage.removeItem('perms');
                                $rootScope.globals = {};
                                $state.go('login');
                            }
                            else{
                         response.data.message = response.message = "Something went Wrong!, Contact System Administrator";
                           }
                                }else if(response.data == null){
                 response.data = {};
                 response.data.message = response.message = "Server Down! Please try after sometimes.";
            }else if(response.data.message == "Access Denied" || response.data.message == "Forbidden" ){
               if(response.data.message == "Forbidden"){
                             response.data.message = 'Your Session is Timed out';
                             }
                 $window.sessionStorage.removeItem('perms');
                 $rootScope.globals = {};
                 $state.go('login');
            }

                         if(typeof(response.data.error) != 'undefined' ){

                                         if(response.data.error == "Forbidden"){
                                                                          response.data = {};
                                                                          response.data.message = 'Your Session is Timed out';
                                                                                                  $window.sessionStorage.removeItem('perms');
                                                                                                  $rootScope.globals = {};
                                                                                                  $state.go('login');
                                                                       }
                                         else{
                                         response.data = {};
                                         response.data.message = 'Access Denied';
                                         }

                          }
            if (typeof(response.data.result) != 'undefined' && response.data.result != '') {
                var jsonObj = response.data.result;
                for (var key in jsonObj) {
                    if (jsonObj.hasOwnProperty(key)) {
                        response.data.message = response.message = jsonObj[key];
                        break;
                    }
                }
            } else if (typeof(response.data.message) != 'undefined' && response.data.message != '') {
                response.data.message = response.message = response.data.message;
                if (response.status == 401) {
                    //$window.sessionStorage.removeItem('globals');
                    $window.sessionStorage.removeItem('perms');
                    $rootScope.globals = {};
                    $state.go('login');
                }
            } else {
                switch (response.status) {
                    case 401:
                        response.data = {};
                        response.data.message = response.message = "Access Denied";
                        //$window.sessionStorage.removeItem('globals');
                        $window.sessionStorage.removeItem('perms');
                        $rootScope.globals = {};
                        authService.logout(USER_LOGOUT_REASONS.NORMAL_TYPE);
                        //$state.go('login');
                        break;
                    case 500:
                        response.data.message = response.message = "Server error. Unable to process request.";
                        break;
                    case 422:
                        response.data.message = response.message = "Server side validation failed";
                        break;
                    case 400:
                        response.data.message = response.message = ((response.data.error)?response.data.error[0] : undefined) || response.data.errorMessage;
                        break;
                    case 404:
                        response.data.message = response.message = "Page not found";
                        break;
                    case 405:
                        response.data.message = response.message = "Method not allowed";
                        break;
                    default:
                        response.data.message = response.message = response.data.message;
                        break;
                };
            }
            return $q.reject(response);
        }
    };
    return requestInterceptor;
});


window.Swal = Swal.mixin({
  didOpen: (popup) => {
    makeDraggable(popup);
  }
});
function makeDraggable(el) {
  let isDown = false;
  let offset = [0, 0];

  el.style.position = "absolute";

  const dragHandle = el.querySelector('.swal2-icon') || el.querySelector('.swal2-title') || el;

  dragHandle.style.cursor = "move";

  dragHandle.addEventListener('mousedown', function (e) {
    isDown = true;
    offset = [
      el.offsetLeft - e.clientX,
      el.offsetTop - e.clientY
    ];
  });

  document.addEventListener('mouseup', function () {
    isDown = false;
  });

  document.addEventListener('mousemove', function (e) {
    if (isDown) {
      el.style.left = (e.clientX + offset[0]) + 'px';
      el.style.top = (e.clientY + offset[1]) + 'px';
    }
  });
}


gbmUltraApp.factory('preventTemplateCache', function($injector) {
    return {
      'request': function(config) {
        if (config.url.indexOf('views')!== -1 || config.url.indexOf('assets')!== -1) {
        /*config.url.indexOf('app') !== -1 ||*/
          config.url = config.url + '?v=' + versionNum;
        }
        return config;
      }
    }
});

