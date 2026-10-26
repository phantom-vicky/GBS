  (function() {

      'use strict';

      angular
          .module('gbmUltraApp')
          .directive('userLeftMenu', userLeftMenu);

      userLeftMenu.$inject = ['$location', 'userCommonService']

      function userLeftMenu(location, userCommonService) {
          return {
              restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
              scope: {
                  //@ reads the attribute value, = provides two-way binding, & works with functions
                  title: '@'
              },
              templateUrl: 'app/user_mgmt/common/leftmenu.html',
              link: function($scope, element, attrs) {
                  var path = "#!" + location.path();
                  var leftMenuItems = userCommonService.getLeftMenuItems();

                  if (leftMenuItems.add.display == true) {
                      $("#left-menu-add").css('display', 'block');
                      $("#left-menu-add a").attr('href', leftMenuItems.add.href);
                      if (path.indexOf('add') != -1 ) {
                          $("#left-menu-add a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-add").hide();
                  }

                  if (leftMenuItems.modify.display == true) {
                      $("#left-menu-modify").css('display', 'block');
                      $("#left-menu-modify a").attr('href', leftMenuItems.modify.href);
                      if (path.indexOf('modify') != -1 ) {
                          $("#left-menu-modify a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-modify").hide();
                  }

                  if (leftMenuItems.verify.display == true) {
                      $("#left-menu-verify").css('display', 'block');
                      $("#left-menu-verify a").attr('href', leftMenuItems.verify.href);
                      if (path.indexOf('verify') != -1 ) {
                          $("#left-menu-verify a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-verify").hide();
                  }

                  if (leftMenuItems.delete.display == true) {
                      $("#left-menu-delete").css('display', 'block');
                      $("#left-menu-delete a").attr('href', leftMenuItems.delete.href);
                      if (path.indexOf('delete') != -1 ) {
                          $("#left-menu-delete a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-delete").hide();
                  }

                  if (leftMenuItems.inquiry.display == true) {
                      $("#left-menu-inquiry").css('display', 'block');
                      $("#left-menu-inquiry a").attr('href', leftMenuItems.inquiry.href);
                      if (path.indexOf('inquiry') != -1 ) {
                          $("#left-menu-inquiry a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-inquiry").hide();
                  }

                  if (leftMenuItems.disable.display == true) {
                      $("#left-menu-disable").css('display', 'block');
                      $("#left-menu-disable a").attr('href', leftMenuItems.disable.href);
                      if (path.indexOf('disable') != -1 ) {
                          $("#left-menu-disable a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-disable").hide();
                  }

                  if (typeof(leftMenuItems.upload) == 'undefined') {
                      $("#left-menu-upload").hide();
                  } else {
                      if (leftMenuItems.upload.display == true) {
                          $("#left-menu-upload").css('display', 'block');
                          $("#left-menu-upload a").attr('href', leftMenuItems.upload.href);
                          if (path.indexOf('upload') != -1 ) {
                              $("#left-menu-upload a").addClass('leftMenuActive');
                          }
                      } else {
                          $("#left-menu-upload").hide();
                      }
                  }
                  //console.log($scope);
                  //console.log(leftMenuItems.inquiry.display);
              } //DOM manipulation
          };
      }

  })();