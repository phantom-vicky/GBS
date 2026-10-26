  (function() {

      'use strict';

      angular
          .module('gbmUltraApp')
          .directive('cbsLeftMenu', cbsLeftMenu);

      cbsLeftMenu.$inject = ['$location', 'cbsCommonService']

      function cbsLeftMenu(location, cbsCommonService) {
          return {
              restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
              scope: {
                  //@ reads the attribute value, = provides two-way binding, & works with functions
                  title: '@'
              },
              templateUrl: 'views/nps/leftmenu.html',
              link: function($scope, element, attrs) {
                  var path = "#!" + location.path();
                  var leftMenuItems = cbsCommonService.getLeftMenuItems();

                  if (leftMenuItems.add.display == true) {
                      $("#left-menu-add").css('display', 'block');
                      $("#left-menu-add a").attr('href', leftMenuItems.add.href);
                      if (path.indexOf('add') != -1 ) {
                          $("#left-menu-add a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-add").hide();
                  }

                   if (leftMenuItems.bulkadd.display == true) {
                      $("#left-menu-bulkadd").css('display', 'block');
                      $("#left-menu-bulkadd a").attr('href', leftMenuItems.bulkadd.href);
                      if (path.indexOf('bulkadd') != -1 ) {
                          $("#left-menu-bulkadd a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-bulkadd").hide();
                  }

                  if (leftMenuItems.download.display == true) {
                                        $("#left-menu-download").css('display', 'block');
                                        $("#left-menu-download a").attr('href', leftMenuItems.download.href);
                                        if (path.indexOf('download') != -1 ) {
                                            $("#left-menu-download a").addClass('leftMenuActive');
                                        }
                                    } else {
                                        $("#left-menu-download").hide();
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
                    if (leftMenuItems.bulkverify.display == true) {
                        $("#left-menu-bulkverify").css('display', 'block');
                        $("#left-menu-bulkverify a").attr('href', leftMenuItems.bulkverify.href);
                        if (path.indexOf('bulkverify') != -1 ) {
                            $("#left-menu-bulkverify a").addClass('leftMenuActive');
                        }
                    } else {
                        $("#left-menu-bulkverify").hide();
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
                  

                  if (leftMenuItems.disable.display == true) {
                      $("#left-menu-disable").css('display', 'block');
                      $("#left-menu-disable a").attr('href', leftMenuItems.disable.href);
                      if (path.indexOf('disable') != -1 ) {
                          $("#left-menu-disable a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-disable").hide();
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



                  if (leftMenuItems.upload.display == true) {
                      $("#left-menu-upload").css('display', 'block');
                      $("#left-menu-upload a").attr('href', leftMenuItems.upload.href);
                      if (path.indexOf('upload_feature') != -1 ) {
                          $("#left-menu-upload a").addClass('leftMenuActive');
                      }
                  } else {
                      $("#left-menu-upload").hide();
                  }

              
                  //console.log($scope);
                  //console.log(leftMenuItems.inquiry.display);
              } //DOM manipulation
          };
      }

  })();