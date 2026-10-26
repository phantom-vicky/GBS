(function() {
    'use strict';

    angular
        .module('gbmUltraApp')
        .config(CommonLinkConfig);

   CommonLinkConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function CommonLinkConfig($stateProvider, $ocLazyLoadProvider) {
        $stateProvider

         /* Common Menu */
        .state('dc_common_menu', {
            url: '/dc_common_menu',
            templateUrl: 'app/common/dc/dc_common_menu.html'
        })


         /* common_interest_code_maintence Start*/
          .state('common_interest_code_maintence', {
              abstract: true,
              url: '/common_interest_code_maintence',
              params: {
                  views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                  permissions: ['DC:INTEREST_RATE_CODE_MAINTENANCE:ADD','DC:INTEREST_RATE_CODE_MAINTENANCE:MODIFY','DC:INTEREST_RATE_CODE_MAINTENANCE:VERIFY','DC:INTEREST_RATE_CODE_MAINTENANCE:DELETE','DC:INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY']
              },
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: [
                              'app/dc/common/left-menu.directive.js',
                              'app/common/dc/common_interest_code_maintence/common_interest_code_maintence.service.js?v='+versionNum
                          ]
                      });
                  }
              }
          })


          .state('common_interest_code_maintence.add', {
              url: '/add',
              templateUrl: 'app/common/dc/common_interest_code_maintence/add_common_interest_code_maintence.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:ADD'
              },
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })

          .state('common_interest_code_maintence.modify', {
              url: '/action/modify',
              templateUrl: 'app/common/dc/common_interest_code_maintence/modify_common_interest_code_maintence.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:MODIFY'
              },
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })

            .state('common_interest_code_maintence.modify_detail', {
              url: '/action/modify/detail',
              templateUrl: 'app/common/dc/common_interest_code_maintence/modify_common_interest_code_maintence_detail.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:MODIFY'
              },
              params: {obj: null},
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })

           .state('common_interest_code_maintence.verify', {
              url: '/action/verify',
              templateUrl: 'app/common/dc/common_interest_code_maintence/verify_common_interest_code_maintence.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:VERIFY'
              },
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })

            .state('common_interest_code_maintence.verify_detail', {
              url: '/action/verify/detail',
              templateUrl: 'app/common/dc/common_interest_code_maintence/verify_common_interest_code_maintence_detail.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:VERIFY'
              },
              params: {obj: null},
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })

           .state('common_interest_code_maintence.delete', {
              url: '/action/delete',
              templateUrl: 'app/common/dc/common_interest_code_maintence/delete_common_interest_code_maintence.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:DELETE'
              },
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })

            .state('common_interest_code_maintence.delete_detail', {
              url: '/action/delete/detail',
              templateUrl: 'app/common/dc/common_interest_code_maintence/delete_common_interest_code_maintence_detail.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:DELETE'
              },
              params: {obj: null},
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })


          .state('common_interest_code_maintence.inquiry', {
              url: '/action/inquiry',
              templateUrl: 'app/common/dc/common_interest_code_maintence/inquiry_common_interest_code_maintence.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY'
              },
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })

          .state('common_interest_code_maintence.inquiry_detail', {
              url: '/action/inquiry/detail',
              templateUrl: 'app/common/dc/common_interest_code_maintence/inquiry_common_interest_code_maintence_detail.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY'
              },
              params: {obj: null},
              controller: 'interestRateCodeMaintenanceforInvestmentSchemesController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_interest_code_maintence/common_interest_code_maintence.controller.js?v='+versionNum]
                      });
                  }
              }
          })
          /* common_interest_code_maintence End*/

          /* Common TOC Sharing Maintenance Start*/
          .state('common_toc_sharing_maintenance', {
              abstract: true,
              url: '/common_toc_sharing_maintenance',
              params: {
                  views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                  permissions: ['DC:SCSS_TOC_SHARING:ADD','DC:SCSS_TOC_SHARING:MODIFY','DC:SCSS_TOC_SHARING:VERIFY','DC:SCSS_TOC_SHARING:DELETE','DC:SCSS_TOC_SHARING:ENQUIRY']
              },
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: [
                              'app/dc/common/left-menu.directive.js',
                              'app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.service.js?v='+versionNum
                          ]
                      });
                  }
              }
          })


          .state('common_toc_sharing_maintenance.add', {
              url: '/add',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/add_toc_sharing_maintenance.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:ADD'
              },
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

          .state('common_toc_sharing_maintenance.add_detail', {
              url: '/add/detail',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/add_toc_sharing_maintenance_details.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:ADD'
              },
              params : {obj : null},
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

          .state('common_toc_sharing_maintenance.modify', {
              url: '/action/modify',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/modify_toc_sharing_maintenance.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:MODIFY'
              },
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

            .state('common_toc_sharing_maintenance.modify_detail', {
              url: '/action/modify/detail',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/modify_toc_sharing_maintenance_details.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:MODIFY'
              },
              params: {obj: null},
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

           .state('common_toc_sharing_maintenance.verify', {
              url: '/action/verify',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/verify_toc_sharing_maintenance.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:VERIFY'
              },
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

            .state('common_toc_sharing_maintenance.verify_detail', {
              url: '/action/verify/detail',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/verify_toc_sharing_maintenance_details.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:VERIFY'
              },
              params: {obj: null},
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

           .state('common_toc_sharing_maintenance.delete', {
              url: '/action/delete',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/delete_toc_sharing_maintenance.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:DELETE'
              },
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

            .state('common_toc_sharing_maintenance.delete_detail', {
              url: '/action/delete/detail',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/delete_toc_sharing_maintenance_details.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:DELETE'
              },
              params: {obj: null},
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })


          .state('common_toc_sharing_maintenance.inquiry', {
              url: '/action/inquiry',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/inquiry_toc_sharing_maintenance.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:ENQUIRY'
              },
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })

          .state('common_toc_sharing_maintenance.inquiry_detail', {
              url: '/action/inquiry/detail',
              templateUrl: 'app/common/dc/common_toc_sharing_maintenance/inquiry_toc_sharing_maintenance_details.html',
              data: {
                  authRequired: true,
                  requiredPermission: 'DC:SCSS_TOC_SHARING:ENQUIRY'
              },
              params: {obj: null},
              controller: 'tocSharingMaintenanceController',
              controllerAs: 'vm',
              resolve: {
                  load: function($ocLazyLoad) {
                      return $ocLazyLoad.load({
                          files: ['app/common/dc/common_toc_sharing_maintenance/toc_sharing_maintenance.controller.js?v='+versionNum]
                      });
                  }
              }
          })
          /* Common TOC Sharing Maintenance End*/

          /* Level Master Start*/
            .state('common_level_master', {
                abstract: true,
                url: '/common_level_master',
                params: {
                    views: ['add', 'verify', 'delete', 'inquiry'],
                    permissions: ['DC:COMMON_LEVEL_MASTER:ADD','DC:COMMON_LEVEL_MASTER:VERIFY','DC:COMMON_LEVEL_MASTER:DELETE','DC:COMMON_LEVEL_MASTER:ENQUIRY']
                },
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: [
                                'app/dc/common/left-menu.directive.js',
                                'app/common/dc/common_level_master/level_master.service.js?v='+versionNum
                            ]
                        });
                    }
                }
            })


            .state('common_level_master.add', {
                url: '/add',
                templateUrl: 'app/common/dc/common_level_master/add_level_master.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'DC:COMMON_LEVEL_MASTER:ADD'
                },
                controller: 'levelMasterController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/common/dc/common_level_master/level_master.controller.js?v='+versionNum]
                        });
                    }
                }
            })

             .state('common_level_master.verify', {
                url: '/action/verify',
                templateUrl: 'app/common/dc/common_level_master/verify_level_master.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'DC:COMMON_LEVEL_MASTER:VERIFY'
                },
                controller: 'levelMasterController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/common/dc/common_level_master/level_master.controller.js?v='+versionNum]
                        });
                    }
                }
            })

             .state('common_level_master.delete', {
                url: '/action/delete',
                templateUrl: 'app/common/dc/common_level_master/delete_level_master.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'DC:COMMON_LEVEL_MASTER:DELETE'
                },
                controller: 'levelMasterController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/common/dc/common_level_master/level_master.controller.js?v='+versionNum]
                        });
                    }
                }
            })

            .state('common_level_master.inquiry', {
                url: '/action/inquiry',
                templateUrl: 'app/common/dc/common_level_master/inquiry_level_master.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'DC:COMMON_LEVEL_MASTER:ENQUIRY'
                },
                controller: 'levelMasterController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/common/dc/common_level_master/level_master.controller.js?v='+versionNum]
                        });
                    }
                }
            })

            /* level_master End*/

              /* Common TOC Slab Maintenance Start*/
              .state('common_toc_slab_maintenance', {
                  abstract: true,
                  url: '/common_toc_slab_maintenance',
                  params: {
                      views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                      permissions: ['DC:SCSS_TOC_SHARING:ADD','DC:SCSS_TOC_SHARING:MODIFY','DC:SCSS_TOC_SHARING:VERIFY','DC:SCSS_TOC_SHARING:DELETE','DC:SCSS_TOC_SHARING:ENQUIRY']
                  },
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: [
                                  'app/dc/cbdt/common/left-menu.directive.js',
                                  'app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.service.js?v='+versionNum
                              ]
                          });
                      }
                  }
              })


              .state('common_toc_slab_maintenance.add', {
                  url: '/add',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/add_toc_slab_maintenance.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:ADD'
                  },
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

              .state('common_toc_slab_maintenance.add_detail', {
                  url: '/add/detail',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/add_toc_slab_maintenance_detail.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:ADD'
                  },
                  params : {obj : null},
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

              .state('common_toc_slab_maintenance.modify', {
                  url: '/action/modify',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/modify_toc_slab_maintenance.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:MODIFY'
                  },
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

                .state('common_toc_slab_maintenance.modify_detail', {
                  url: '/action/modify/detail',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/modify_toc_slab_maintenance_detail.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:MODIFY'
                  },
                  params: {obj: null},
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

               .state('common_toc_slab_maintenance.verify', {
                  url: '/action/verify',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/verify_toc_slab_maintenance.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:VERIFY'
                  },
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

                .state('common_toc_slab_maintenance.verify_detail', {
                  url: '/action/verify/detail',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/verify_toc_slab_maintenance_detail.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:VERIFY'
                  },
                  params: {obj: null},
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

               .state('common_toc_slab_maintenance.delete', {
                  url: '/action/delete',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/delete_toc_slab_maintenance.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:DELETE'
                  },
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

                .state('common_toc_slab_maintenance.delete_detail', {
                  url: '/action/delete/detail',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/delete_toc_slab_maintenance_detail.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:DELETE'
                  },
                  params: {obj: null},
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })


              .state('common_toc_slab_maintenance.list', {
                  url: '/action/inquiry',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:ENQUIRY'
                  },
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })

              .state('common_toc_slab_maintenance.list_detail', {
                  url: '/action/inquiry/detail',
                  templateUrl: 'app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance_list.html',
                  data: {
                      authRequired: true,
                      requiredPermission: 'DC:SCSS_TOC_SHARING:ENQUIRY'
                  },
                  params: {obj: null},
                  controller: 'commonTocSlabMaintenanceController',
                  controllerAs: 'vm',
                  resolve: {
                      load: function($ocLazyLoad) {
                          return $ocLazyLoad.load({
                              files: ['app/common/dc/common_toc_slab_maintenance/toc_slab_maintenance.controller.js?v='+versionNum]
                          });
                      }
                  }
              })
             /* Common TOC Slab Maintenance End*/

             /*Scroll Number Maintenance*/
            .state('scroll_number_maintenance', {
             abstract: true,
             url: '/scroll_number_maintenance',
             params: {
                 views: ['add', 'modify', 'verify', 'inquiry'],
                 permissions: ['GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:ADD', 'GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:MODIFY', 'GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:VERIFY','GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:ENQUIRY']
             },
             resolve: {
                 load: function($ocLazyLoad) {
                     return $ocLazyLoad.load({
                         files: [
                             'app/dc/common/left-menu.directive.js',
                             'app/common/dc/scroll_number_maintenance/scroll_number_maintenance.service.js?v='+versionNum
                         ]
                     });
                 }
             }
            })
            .state('scroll_number_maintenance.add', {
                 url: '/add',
                 templateUrl: 'app/common/dc/scroll_number_maintenance/add_scroll_number_maintenance.html',
                    data: {
                     authRequired: true,
                     requiredPermission: 'GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:ADD'
                 },
                 controller: 'scrollNumberMaintenanceController',
                 controllerAs: 'vm',
                 resolve: {
                     load: function($ocLazyLoad) {
                         return $ocLazyLoad.load({
                             files: ['app/common/dc/scroll_number_maintenance/scroll_number_maintenance.controller.js?v='+versionNum]
                         });
                     }
                 }
                })
            .state('scroll_number_maintenance.modify', {
                 url: '/action/modify',
                 templateUrl: 'app/common/dc/scroll_number_maintenance/modify_scroll_number_maintenance.html',
                 data: {
                     authRequired: true,
                     requiredPermission: 'GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:MODIFY'
                 },
                 controller: 'scrollNumberMaintenanceController',
                 controllerAs: 'vm',
                 resolve: {
                     load: function($ocLazyLoad) {
                         return $ocLazyLoad.load({
                             files: ['app/common/dc/scroll_number_maintenance/scroll_number_maintenance.controller.js?v='+versionNum]
                         });
                     }
                 }
                })
                .state('scroll_number_maintenance.verify', {
                   url: '/action/verify',
                   templateUrl: 'app/common/dc/scroll_number_maintenance/verify_scroll_number_maintenance.html',
                   data: {
                       authRequired: true,
                       requiredPermission: 'GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:VERIFY'
                   },
                   controller: 'scrollNumberMaintenanceController',
                   controllerAs: 'vm',
                   resolve: {
                       load: function($ocLazyLoad) {
                           return $ocLazyLoad.load({
                               files: ['app/common/dc/scroll_number_maintenance/scroll_number_maintenance.controller.js?v='+versionNum]
                           });
                       }
                   }
                  })
                  .state('scroll_number_maintenance.inquiry', {
                      url: '/action/inquiry',
                      templateUrl: 'app/common/dc/scroll_number_maintenance/inquiry_scroll_number_maintenance.html',
                      data: {
                          authRequired: true,
                          requiredPermission: 'GBM_DC_ST:SCROLL_NUMBER_MAINTENANCE:ENQUIRY'
                      },
                      controller: 'scrollNumberMaintenanceController',
                      controllerAs: 'vm',
                      resolve: {
                          load: function($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                  files: ['app/common/dc/scroll_number_maintenance/scroll_number_maintenance.controller.js?v='+versionNum]
                              });
                          }
                      }
                     })



    }
})();
