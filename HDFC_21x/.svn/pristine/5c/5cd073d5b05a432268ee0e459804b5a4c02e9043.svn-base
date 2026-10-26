(function () {
	'use strict';

	angular
		.module('gbmUltraApp')
		.config(userMgmtConfig);

	userMgmtConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

	function userMgmtConfig($stateProvider, $ocLazyLoadProvider) {
		$stateProvider

			/* Login */
			.state('login', {
				url: '/login',
				templateUrl: 'app/login/views/login.html',
				controller: 'LoginController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/login/controllers/login.controller.js?v='+versionNum,
								'assets/js/config/auth/auth.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('logout', {
				url: '/logout',
				controller: 'LogoutController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/login/controllers/logout.controller.js?v='+versionNum]
						});
					}
				}
			})

			/* Permission */
			.state('permission', {
				abstract: true,
				url: '/permission',
				params: {
					views: ['add', 'modify', 'inquiry', 'delete'],
					permissions: ['USER_MGMT:PERMISSION:ADD', 'USER_MGMT:PERMISSION:MODIFY', 'USER_MGMT:PERMISSION:ENQUIRY', 'USER_MGMT:PERMISSION:DELETE']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/common/left-menu.directive.js',
								'app/user_mgmt/permission/permission.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('permission.add', {
				url: '/add',
				templateUrl: 'app/user_mgmt/permission/add_permission.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:PERMISSION:ADD'
				},
				controller: 'PermissionController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/permission/permission.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('permission.modify', {
				url: '/:id/modify',
				templateUrl: 'app/user_mgmt/permission/modify_permission.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:PERMISSION:MODIFY'
				},
				controller: 'PermissionController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/permission/permission.controller.js?v='+versionNum]
						});
					}
				}
			})

			.state('permission.list', {
				url: '/action/:action',
				templateUrl: 'app/user_mgmt/permission/permissions.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:PERMISSION:ENQUIRY'
				},
				controller: 'PermissionListController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/permission/permission-list.controller.js?v='+versionNum]
						});
					}
				}
			})
			/* User */
			.state('user', {
				abstract: true,
				url: '/user',
				params: {
					views: ['add', 'modify', 'inquiry', 'verify', 'delete'],
					permissions: ['USER_MGMT:USER:ADD', 'USER_MGMT:USER:MODIFY', 'USER_MGMT:USER:ENQUIRY', 'USER_MGMT:USER:VERIFY', 'USER_MGMT:USER:DELETE']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/common/left-menu.directive.js'
							]
						});
					}
				}
			})

			.state('user.list', {
				url: '/action/:action',
				templateUrl: 'app/user_mgmt/user/users.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:USER:ENQUIRY'
				},
				controller: 'UserListController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/user/user-list.controller.js?v='+versionNum,
								'app/user_mgmt/user/admin.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('user.list_details', {
				url: '/:action/:id/details',
				templateUrl: 'app/user_mgmt/user/user_details.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:USER:ENQUIRY'
				},
				controller: 'UserListController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/user/user-list.controller.js?v='+versionNum,
								'app/user_mgmt/user/admin.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('user.add', {
				url: '/add',
				templateUrl: 'app/user_mgmt/user/user_registration.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:USER:ADD'
				},
				controller: 'UserRegistrationController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/user/user-registration.controller.js?v='+versionNum,
								'app/user_mgmt/user/admin.service.js?v='+versionNum,
								'app/user_mgmt/common/compareto.directive.js',
							]
						});
					}
				}
			})

			.state('user.modify', {
				url: '/:id/modify',
				templateUrl: 'app/user_mgmt/user/modify_user.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:USER:MODIFY'
				},
				controller: 'AdminController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/user/admin.controller.js?v='+versionNum,
								'app/user_mgmt/user/admin.service.js?v='+versionNum,
								'app/user_mgmt/role/role.service.js?v='+versionNum
							]
						});
					}
				}
			})
			.state('user.modify_edit', {
				url: '/:id/modify/edit',
				templateUrl: 'app/user_mgmt/user/modify_user_edit.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:USER:MODIFY'
				},
				controller: 'AdminController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/user/admin.controller.js?v='+versionNum,
								'app/user_mgmt/user/admin.service.js?v='+versionNum,
								'app/user_mgmt/role/role.service.js?v='+versionNum
							]
						});
					}
				}
			})
			.state('user.roles', {
				url: '/:id/roles',
				templateUrl: 'app/user_mgmt/user/manage_roles.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:USER:MODIFY'
				},
				controller: 'AdminController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/user/admin.controller.js?v='+versionNum,
								'app/user_mgmt/user/admin.service.js?v='+versionNum,
								'app/user_mgmt/role/role.service.js?v='+versionNum
							]
						});
					}
				}
			})

			/* Role */
			.state('role', {
				abstract: true,
				url: '/role',
				params: {
					views: ['add', 'modify', 'verify', 'inquiry', 'delete'],
					permissions: ['USER_MGMT:ROLE:ADD', 'USER_MGMT:ROLE:MODIFY', 'USER_MGMT:ROLE:VERIFY', 'USER_MGMT:ROLE:ENQUIRY', 'USER_MGMT:ROLE:DELETE']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/common/left-menu.directive.js',
								'app/user_mgmt/role/role.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('role.add', {
				url: '/add',
				templateUrl: 'app/user_mgmt/role/add_role.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE:ADD'
				},
				controller: 'RoleController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role/role.controller.js?v='+versionNum,
								'app/user_mgmt/permission/permission.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('role.modify', {
				url: '/:id/modify',
				templateUrl: 'app/user_mgmt/role/edit_role.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE:MODIFY'
				},
				controller: 'RoleController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role/role.controller.js?v='+versionNum,
								'app/user_mgmt/permission/permission.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('role.list', {
				url: '/action/:action',
				templateUrl: 'app/user_mgmt/role/roles.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE:ENQUIRY'
				},
				controller: 'RoleListController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role/role-list.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('role.list_details', {
				url: '/:action/:id/details',
				templateUrl: 'app/user_mgmt/role/role_details.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE:ENQUIRY'
				},
				controller: 'RoleListController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role/role-list.controller.js?v='+versionNum]
						});
					}
				}
			})

			.state('role.permissions', {
				url: '/:id/permissions',
				templateUrl: 'app/user_mgmt/role/manage_permission.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE:MODIFY'
				},
				controller: 'RoleController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role/role.controller.js?v='+versionNum,
								'app/user_mgmt/permission/permission.service.js?v='+versionNum
							]
						});
					}
				}
			})


			/* Role Permission Mapping*/
			.state('role_permission_mapping', {
				abstract: true,
				url: '/role_permission_mapping',
				params: {
					views: ['add', 'modify', 'verify', 'inquiry'],
					permissions: ['USER_MGMT:ROLE_PERMISSION_MAPPING:ADD', 'USER_MGMT:ROLE_PERMISSION_MAPPING:MODIFY', 'USER_MGMT:ROLE_PERMISSION_MAPPING:VERIFY', 'USER_MGMT:ROLE_PERMISSION_MAPPING:ENQUIRY']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/common/left-menu.directive.js',
								'app/user_mgmt/role_permission_mapping/role-permission-mapping.service.js?v='+versionNum
							]
						});
					}
				}
			})



			.state('role_permission_mapping.list', {
				url: '/action/:action',
				templateUrl: 'app/user_mgmt/role_permission_mapping/role_permission_mapping_list.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE_PERMISSION_MAPPING:ENQUIRY'
				},
				controller: 'rolePermissionMappingController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role_permission_mapping/role-permission-mapping.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('role_permission_mapping.list_details', {
				url: '/:action/:id/:name/details',
				templateUrl: 'app/user_mgmt/role_permission_mapping/manage_role_permission_mapping.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE_PERMISSION_MAPPING:ENQUIRY'
				},
				controller: 'rolePermissionMappingController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role_permission_mapping/role-permission-mapping.controller.js?v='+versionNum]
						});
					}
				}
			})

			.state('role_permission_mapping.add', {
				url: '/add',
				templateUrl: 'app/user_mgmt/role_permission_mapping/add_role_permission_mapping.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE_PERMISSION_MAPPING:ADD'
				},
				controller: 'rolePermissionMappingController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role_permission_mapping/role-permission-mapping.controller.js?v='+versionNum,
								'app/user_mgmt/role_permission_mapping/role-permission-mapping.service.js?v='+versionNum
							]
						});
					}
				}
			})
			.state('role_permission_mapping.add_details', {
				url: '/add/details',
				templateUrl: 'app/user_mgmt/role_permission_mapping/add_role_permission_mapping_details.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE_PERMISSION_MAPPING:ADD'
				},
                params : {obj : null},
				controller: 'rolePermissionMappingController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role_permission_mapping/role-permission-mapping.controller.js?v='+versionNum
							]
						});
					}
				}
			})
			.state('role_permission_mapping.modify_details', {
				url: '/modify/details',
				templateUrl: 'app/user_mgmt/role_permission_mapping/modify_role_permission_mapping_details.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:ROLE_PERMISSION_MAPPING:MODIFY'
				},
                params : {obj : null},
				controller: 'rolePermissionMappingController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/role_permission_mapping/role-permission-mapping.controller.js?v='+versionNum
							]
						});
					}
				}
			})

            /* User Role Mapping*/
            .state('user_role_mapping', {
                abstract: true,
                url: '/user_role_mapping',
                params: {
                    views: ['add', 'modify', 'verify', 'inquiry'],
                    permissions: ['USER_MGMT:USER_ROLE_MAPPING:ADD', 'USER_MGMT:USER_ROLE_MAPPING:MODIFY', 'USER_MGMT:USER_ROLE_MAPPING:VERIFY', 'USER_MGMT:USER_ROLE_MAPPING:ENQUIRY']
                },
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: [
                                'app/user_mgmt/common/left-menu.directive.js',
                                'app/user_mgmt/user_role_mapping/user-role-mapping.service.js?v='+versionNum
                            ]
                        });
                    }
                }
            })



            .state('user_role_mapping.list', {
                url: '/action/:action',
                templateUrl: 'app/user_mgmt/user_role_mapping/user_role_mapping_list.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:USER_ROLE_MAPPING:ENQUIRY'
                },
                controller: 'userRoleMappingController',
                controllerAs: 'vm',
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/user_role_mapping/user-role-mapping.controller.js?v='+versionNum]
                        });
                    }
                }
            })


            .state('user_role_mapping.list_details', {
                url: '/:action/:id/:name/details',
                templateUrl: 'app/user_mgmt/user_role_mapping/manage_user_role_mapping.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:USER_ROLE_MAPPING:ENQUIRY'
                },
                controller: 'userRoleMappingController',
                controllerAs: 'vm',
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/user_role_mapping/user-role-mapping.controller.js?v='+versionNum]
                        });
                    }
                }
            })

            .state('user_role_mapping.add', {
                url: '/add',
                templateUrl: 'app/user_mgmt/user_role_mapping/add_user_role_mapping.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:USER_ROLE_MAPPING:ENQUIRY'
                },
                controller: 'userRoleMappingController',
                controllerAs: 'vm',
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/user_role_mapping/user-role-mapping.controller.js?v='+versionNum]
                        });
                    }
                }
            })
            .state('user_role_mapping.add_details', {
                url: '/add/details',
                templateUrl: 'app/user_mgmt/user_role_mapping/add_user_role_mapping_details.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:USER_ROLE_MAPPING:ENQUIRY'
                },
                params : {obj : null},
                controller: 'userRoleMappingController',
                controllerAs: 'vm',
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/user_role_mapping/user-role-mapping.controller.js?v='+versionNum]
                        });
                    }
                }
            })
            .state('user_role_mapping.modify_details', {
                url: '/modify/details',
                templateUrl: 'app/user_mgmt/user_role_mapping/modify_user_role_mapping_details.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:USER_ROLE_MAPPING:MODIFY'
                },
                params : {obj : null},
                controller: 'userRoleMappingController',
                controllerAs: 'vm',
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/user_role_mapping/user-role-mapping.controller.js?v='+versionNum]
                        });
                    }
                }
            })


			/* User Param */
            			.state('user_params', {
            				abstract: true,
            				url: '/user_params',
            				params: {
            					views: ['inquiry','modify', 'verify'],
            					permissions: ['USER_MGMT:USER_PARAMS:ENQUIRY','USER_MGMT:USER_PARAMS:MODIFY','USER_MGMT:USER_PARAMS:VERDEL']
            				},
            				resolve: {
            					load: function ($ocLazyLoad) {
            						return $ocLazyLoad.load({
            							files: [
            								'app/user_mgmt/common/left-menu.directive.js',
            								'app/user_mgmt/user_params/user-params.service.js?v='+versionNum
            							]
            						});
            					}
            				}
            			})

            			.state('user_params.list', {
            				url: '/action/:action',
            				templateUrl: 'app/user_mgmt/user_params/user_params.html',
            				data: {
            					authRequired: true,
            					requiredPermission: 'USER_MGMT:USER_PARAMS:ENQUIRY'
            				},
            				controller: 'UserParamController',
            				controllerAs: 'vm',
            				resolve: {
            					load: function ($ocLazyLoad) {
            						return $ocLazyLoad.load({
            							files: ['app/user_mgmt/user_params/user-params.controller.js?v='+versionNum]
            						});
            					}
            				}
            			})


                        .state('user_params.verify', {
                            url: '/action/verify',
                            templateUrl: 'app/user_mgmt/user_params/verify_delete_user_parameter_input.html',
                            data: {
                                authRequired: true,
                                requiredPermission: 'USER_MGMT:USER_PARAMS:VERDEL'
                            },
                            controller: 'UserParamController',
                            controllerAs: 'vm',
                            resolve: {
                                load: function ($ocLazyLoad) {
                                    return $ocLazyLoad.load({
                                        files: ['app/user_mgmt/user_params/user-params.controller.js?v=' + versionNum]
                                    });
                                }
                            }
                        })


			/* Reset Password */
			.state('reset_password', {
				url: '/reset_password',
				templateUrl: 'app/user_mgmt/reset_password/reset_password.html',
				controller: 'ResetPasswordController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/reset_password/reset-password.controller.js?v='+versionNum,
								'app/user_mgmt/reset_password/reset-password.service.js?v='+versionNum,
								'app/user_mgmt/common/compareto.directive.js'
							]
						});
					}
				}
			})

			.state('forbidden', {
				url: '/forbidden',
				templateUrl: 'app/unauthourized/unauthourized.html'
			})

			.state('locked_users', {
				abstract: true,
				url: '/locked_users',
				params: {
					views: ['modify', 'inquiry'],
					permissions: ['USER_MGMT:LOCKED_USERS:MODIFY', 'USER_MGMT:LOCKED_USERS:ENQUIRY']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/common/left-menu.directive.js',
								'app/user_mgmt/locked_users/locked-users.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('locked_users.list', {
				url: '/action/:action',
				templateUrl: 'app/user_mgmt/locked_users/locked_users.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:LOCKED_USERS:ENQUIRY'
				},
				controller: 'LockedUsersController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/locked_users/locked-users.controller.js?v='+versionNum]
						});
					}
				}
			})

			/* Sack User */
			.state('sack_user', {
				abstract: true,
				url: '/sack_user',
				params: {
					views: ['add'],
					permissions: ['NPS_ADMIN_FUNCTIONALITY:NPS_SACK_USER:ADD']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/common/left-menu.directive.js',
								'app/user_mgmt/sack_user/sack-user.service.js?v='+versionNum
							]
						});
					}
				}
			})

			.state('sack_user.add', {
				url: '/add',
				templateUrl: 'app/user_mgmt/sack_user/sack_user.html',
				data: {
					authRequired: true,
					requiredPermission: 'NPS_ADMIN_FUNCTIONALITY:NPS_SACK_USER:ADD'
				},
				controller: 'sackUserController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/sack_user/sack-user.controller.js?v='+versionNum]
						});
					}
				}
			})

			.state('admin_reset_password', {
				url: '/user/password/reset',
				templateUrl: 'app/user_mgmt/admin_reset_password/admin-reset-password.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:RESET_PASSWORD:MODIFY'
				},
				controller: 'AdminResetPasswordController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/admin_reset_password/admin-reset-password.controller.js?v='+versionNum,
								'app/user_mgmt/admin_reset_password/admin-reset-password.service.js?v='+versionNum,
								'app/user_mgmt/user/admin.service.js?v='+versionNum,
								'app/user_mgmt/common/compareto.directive.js'
							]
						});
					}
				}
			})

			/*report*/
			.state('report_menu_page', {
				url: '/report_menu_page',
				templateUrl: 'app/user_mgmt/report/report_menu_page.html',

			})

			/*Reports on Unsucessful login attempts*/
			.state('reports_on_unsucessful_login_attempts', {
				url: '/reports_on_unsucessful_login_attempts.html',
				templateUrl: 'app/user_mgmt/report/reports_on_unsucessful_login_attempts.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:REPORTS_ON_UNSUCESSFUL_LOGIN_ATTEMPTS:REPORT'
				},
				controller: 'ReportController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/report.controller.js?v='+versionNum,
								'app/user_mgmt/report/report.service.js?v='+versionNum
							]
						});
					}
				}
			})

//			Reports on Role Rights Report



//    Role Rights Report

 .state('role_rights_report', {
                abstract: true,
                url: '/role_rights_report',
                params: {
                   // views: ['inquiry'],
                    permissions: ['USER_MGMT:REPORT_ON_ROLE_RIGHTS:REPORT']
                },
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: [
                                'app/user_mgmt/report/role_rights_report/role_rights_report.service.js?v='+versionNum
                            ]
                        });
                    }
                }
            })


            .state('role_rights_report.inquiry', {
                url: '/action/inquiry',
                templateUrl: 'app/user_mgmt/report/role_rights_report/role_rights_report_input.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:REPORT_ON_ROLE_RIGHTS:REPORT'
                },
                controller: 'RoleRightReportController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/report/role_rights_report/role_rights_report.controller.js?v='+versionNum]
                        });
                    }
                }
            })
            .state('role_rights_report.inquiry_details', {
                url: '/action/inquiry_details',
                templateUrl: 'app/user_mgmt/report/role_rights_report/role_rights_report_details.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:REPORT_ON_ROLE_RIGHTS:REPORT'
                },
                params: {
                    obj: null
                },
                controller: 'RoleRightReportController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/report/role_rights_report/role_rights_report.controller.js?v='+versionNum]
                        });
                    }
                }
            })


//    population report

 .state('population_report', {
                abstract: true,
                url: '/population_report',
                params: {
                    views: ['inquiry'],
                    permissions: ['USER_MGMT:REPORT_ON_POPULATION:REPORT']
                },
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: [
                                'app/user_mgmt/report/Population_Report/report_on_population.service.js?v='+versionNum
                            ]
                        });
                    }
                }
            })


            .state('population_report.inquiry', {
                url: '/action/inquiry',
                templateUrl: 'app/user_mgmt/report/Population_Report/report_on_population_input.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:REPORT_ON_POPULATION:REPORT'
                },
                controller: 'PopulationReportController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/report/Population_Report/report_on_population.controller.js?v='+versionNum]
                        });
                    }
                }
            })
            .state('population_report.inquiry_details', {
                url: '/action/inquiry_details',
                templateUrl: 'app/user_mgmt/report/Population_Report/report_on_population_details.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'USER_MGMT:REPORT_ON_POPULATION:REPORT'
                },
                params: {
                    obj: null
                },
                controller: 'PopulationReportController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/report/Population_Report/report_on_population.controller.js?v='+versionNum]
                        });
                    }
                }
            })




			/*Report on Users logged in from more than one IP address during the day*/

			.state('reports_on_user_logged_in_more_than_one_ip_address_during_the_day', {
				url: '/user_logged_in_more_than_one_ip_address_during_the_day.html',
				templateUrl: 'app/user_mgmt/report/user_logged_in_more_than_one_ip_address_during_the_day.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:REPORTS_ON_USER_LOGGED_IN_MORE_THAN_ONE_IP_ADDRESS_DURING_THE_DAY:REPORT'
				},
				controller: 'ReportController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/report.controller.js?v='+versionNum,
								'app/user_mgmt/report/report.service.js?v='+versionNum
							]
						});
					}
				}
			})

			/*Report on Users logged in from more than one IP address during the day*/

			.state('report_on_list_of_user_related_activities_carried_out_on_a_day', {
				url: '/report_on_list_of_user_related_activities_carried_out_on_a_day.html',
				templateUrl: 'app/user_mgmt/report/report_on_list_of_user_related_activities_carried_out_on_a_day.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:REPORT_ON_LIST_OF_USER_RELATED_ACTIVITIES_CARRIED_OUT_ON_A_DAY:REPORT'
				},
				controller: 'ReportController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/report.controller.js?v='+versionNum,
								'app/user_mgmt/report/report.service.js?v='+versionNum
							]
						});
					}
				}
			})

			/*Report on Users logged in from more than one IP address during the day*/

			.state('report_on_user_sign_on_sign_off', {
				url: '/report_on_user_sign_on_sign_off.html',
				templateUrl: 'app/user_mgmt/report/report_on_user_sign_on_sign_off.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:REPORT_ON_USER_SIGN_ON_SIGN_OFF:REPORT'
				},
				controller: 'ReportController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/report.controller.js?v='+versionNum,
								'app/user_mgmt/report/report.service.js?v='+versionNum
							]
						});
					}
				}
			})

			/*Report on Dormant User-IDs */
			.state('report_on_dormant_user_ids', {
				url: '/report_on_dormant_user_ids.html',
				templateUrl: 'app/user_mgmt/report/report_on_dormant_user_ids.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:REPORT_ON_DORMANT_USER_IDS:REPORT'
				},
				controller: 'ReportController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/report.controller.js?v='+versionNum,
								'app/user_mgmt/report/report.service.js?v='+versionNum
							]
						});
					}
				}
			})

            /*audit report for dc parameterizations */
			.state('audit_report_for_dc_parameterizations', {
				abstract: true,
				url: '/audit_report_for_dc_parameterizations',
				params: {
					views: ['inquiry'],
					permissions: ['AUDIT_LOG_REPORT:AUDIT_REPORT_DC_PARAMETERIZATIONS:ENQUIRY']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.service.js?v='+versionNum
							]
						});
					}
				}
			})


			.state('audit_report_for_dc_parameterizations.list', {
				url: '/action/inquiry',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_DC_PARAMETERIZATIONS:ENQUIRY'
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				params: {
					obj: {
						screen: 'CPPC'
					}
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('audit_report_for_dc_parameterizations.list_detail', {
				url: '/action/inquiry/detail',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol_list.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_DC_PARAMETERIZATIONS:ENQUIRY'
				},
				params: {
					obj: null
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})

//			Population Report


			/* Audit report DC*/
			.state('audit_report_dc', {
				abstract: true,
				url: '/audit_report_dc',
				params: {
					views: ['inquiry'],
					permissions: ['AUDIT_LOG_REPORT:AUDIT_REPORT_AT_DC_LEVEL_FOR_ANY_SOL:ENQUIRY']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.service.js?v='+versionNum
							]
						});
					}
				}
			})


			.state('audit_report_dc.list', {
				url: '/action/inquiry',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_AT_DC_LEVEL_FOR_ANY_SOL:ENQUIRY'
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				params: {
					obj: {
						screen: 'DC'
					}
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('audit_report_dc.list_detail', {
				url: '/action/inquiry/detail',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol_list.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_AT_DC_LEVEL_FOR_ANY_SOL:ENQUIRY'
				},
				params: {
					obj: null
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})

			/* Audit report cppc*/
			.state('audit_report_cppc', {
				abstract: true,
				url: '/audit_report_cppc',
				params: {
					views: ['inquiry'],
					permissions: ['AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_HIGHER_LEVEL_BRANCHES:ENQUIRY']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.service.js?v='+versionNum
							]
						});
					}
				}
			})


			.state('audit_report_cppc.list', {
				url: '/action/inquiry',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_HIGHER_LEVEL_BRANCHES:ENQUIRY'
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				params: {
					obj: {
						screen: 'CPPC'
					}
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('audit_report_cppc.list_detail', {
				url: '/action/inquiry/detail',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol_list.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_HIGHER_LEVEL_BRANCHES:ENQUIRY'
				},
				params: {
					obj: null
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})

			/* Audit report pb*/
			.state('audit_report_pb', {
				abstract: true,
				url: '/audit_report_pb',
				params: {
					views: ['inquiry'],
					permissions: ['AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_BRANCH_LEVEL:ENQUIRY']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.service.js?v='+versionNum
							]
						});
					}
				}
			})


			.state('audit_report_pb.list', {
				url: '/action/inquiry',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_BRANCH_LEVEL:ENQUIRY'
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				params: {
					obj: {
						screen: 'PB'
					}
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('audit_report_pb.list_detail', {
				url: '/action/inquiry/detail',
				templateUrl: 'app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit_report_at_dc_level_for_any_sol_list.html',
				data: {
					authRequired: true,
					requiredPermission: 'AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_BRANCH_LEVEL:ENQUIRY'
				},
				params: {
					obj: null
				},
				controller: 'auditReportAtDcLevelForAnySolController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/audit_reports/audit_report_at_dc_level_for_any_sol/audit-report-at-dc-level-for-any-sol.controller.js?v='+versionNum]
						});
					}
				}
			})

            /*report_on_branch_wise_dept_wise_users */
			.state('report_on_branch_wise_dept_wise_users', {
				abstract: true,
				url: '/report_on_branch_wise_dept_wise_users',
				params: {
					views: ['inquiry'],
					permissions: ['USER_MGMT:REPORTS:REPORT']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/report/report_on_branch_wise_dept_wise_users/report_on_branch_wise_dept_wise_users.service.js?v='+versionNum
							]
						});
					}
				}
			})


			.state('report_on_branch_wise_dept_wise_users.inquiry', {
				url: '/action/inquiry',
				templateUrl: 'app/user_mgmt/report/report_on_branch_wise_dept_wise_users/report_on_branch_wise_dept_wise_users_input.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:REPORTS:REPORT'
				},
				controller: 'reportOnBranchWiseDeptWiseUsersController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/report_on_branch_wise_dept_wise_users/report_on_branch_wise_dept_wise_users.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('report_on_branch_wise_dept_wise_users.inquiry_details', {
				url: '/action/inquiry/detail',
				templateUrl: 'app/user_mgmt/report/report_on_branch_wise_dept_wise_users/report_on_branch_wise_dept_wise_users_details.html',
				data: {
					authRequired: true,
					requiredPermission: 'USER_MGMT:REPORTS:REPORT'
				},
				params: {
					obj: null
				},
				controller: 'reportOnBranchWiseDeptWiseUsersController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/report_on_branch_wise_dept_wise_users/report_on_branch_wise_dept_wise_users.controller.js?v='+versionNum]
						});
					}
				}
			})
           /*report_on_branch_wise_dept_wise_users ends */

           /*Access Control Matrix Report starts*/
			.state('Access_Control_Matrix_Report', {
				abstract: true,
				url: '/Access_Control_Matrix_Report',
				params: {
					views: ['inquiry'],
					permissions: ['GBM_ADMIN_REPORTS:ACCESS_CONTROL_MATRIX_REPORT:REPORT']
				},
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/user_mgmt/report/Access_Control_Matrix_Report/Access_Control_Matrix_Report.service.js?v='+versionNum
							]
						});
					}
				}
			})


			.state('Access_Control_Matrix_Report.inquiry', {
				url: '/action/inquiry',
				templateUrl: 'app/user_mgmt/report/Access_Control_Matrix_Report/Access_Control_Matrix_Report_input.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_ADMIN_REPORTS:ACCESS_CONTROL_MATRIX_REPORT:REPORT'
				},
				controller: 'accessControlMatrixReportController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/Access_Control_Matrix_Report/Access_Control_Matrix_Report.controller.js?v='+versionNum]
						});
					}
				}
			})


			.state('Access_Control_Matrix_Report.inquiry_details', {
				url: '/action/inquiry/detail',
				templateUrl: 'app/user_mgmt/report/Access_Control_Matrix_Report/Access_Control_Matrix_Report_detail.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_ADMIN_REPORTS:ACCESS_CONTROL_MATRIX_REPORT:REPORT'
				},
				params: {
					obj: null
				},
				controller: 'accessControlMatrixReportController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: ['app/user_mgmt/report/Access_Control_Matrix_Report/Access_Control_Matrix_Report.controller.js?v='+versionNum]
						});
					}
				}
			})
			/*Access Control Matrix Report ends*/


			.state('report_on_user', {
            				url: '/report_on_user.html',
            				templateUrl: 'app/user_mgmt/report/report_on_user_report.html',
            				data: {
            					authRequired: true,
            					requiredPermission: 'USER_MGMT:REPORT_ON_USER:REPORT'
            				},
            				controller: 'ReportController',
            				controllerAs: 'vm',
            				resolve: {
            					load: function ($ocLazyLoad) {
            						return $ocLazyLoad.load({
            							files: ['app/user_mgmt/report/report.controller.js?v='+versionNum,
            								'app/user_mgmt/report/report.service.js?v='+versionNum
            							]
            						});
            					}
            				}
            			})

            			.state('admin_activate_user', {
                                    				url: '/admin_activate_user.html',
                                    				templateUrl: 'app/user_mgmt/report/report_on_activate_user.html',
                                    				data: {
                                    					authRequired: true,
                                    					requiredPermission: 'ADMIN:ACTIVATE_USER'
                                    				},
                                    				controller: 'ReportController',
                                    				controllerAs: 'vm',
                                    				resolve: {
                                    					load: function ($ocLazyLoad) {
                                    						return $ocLazyLoad.load({
                                    							files: ['app/user_mgmt/report/report.controller.js?v='+versionNum,
                                    								'app/user_mgmt/report/report.service.js?v='+versionNum
                                    							]
                                    						});
                                    					}
                                    				}
                                    			})




      	/*User Activity Report starts*/
            .state('User_Activity_Report', {
                abstract: true,
                url: '/User_Activity_Report',
                params: {
                    views: ['inquiry'],
                    permissions: ['GBM_ADMIN_REPORTS:ACCESS_CONTROL_MATRIX_REPORT:REPORT']
                },
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: [
                                'app/user_mgmt/report/User_Activity_Report/User_Activity_Report.service.js?v='+versionNum
                            ]
                        });
                    }
                }
            })


            .state('User_Activity_Report.inquiry', {
                url: '/action/inquiry',
                templateUrl: 'app/user_mgmt/report/User_Activity_Report/User_Activity_Report_input.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_ADMIN_REPORTS:ACCESS_CONTROL_MATRIX_REPORT:REPORT'
                },
                controller: 'userActivityReportController',
                controllerAs: 'vm',
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/report/User_Activity_Report/User_Activity_Report.controller.js?v='+versionNum]
                        });
                    }
                }
            })


            .state('User_Activity_Report.inquiry_details', {
                url: '/action/inquiry/detail',
                templateUrl: 'app/user_mgmt/report/User_Activity_Report/User_Activity_Report_details.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_ADMIN_REPORTS:ACCESS_CONTROL_MATRIX_REPORT:REPORT'
                },
                params: {
                    obj: null
                },
                controller: 'userActivityReportController',
                controllerAs: 'vm',
                resolve: {
                    load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/user_mgmt/report/User_Activity_Report/User_Activity_Report.controller.js?v='+versionNum]
                        });
                    }
                }
            })

	/*User Activity Report ends*/


                     .state('usrCrtRleMpm', {
                         abstract: true,
                         url: '/usrCrtRleMpm',
                         params: {
                                views: ['add', 'modify', 'inquiry', 'verify'],
                                permissions: ['GBM_USER_MGMT:USR_CRT_RLE_MPG:ADD', 'GBM_USER_MGMT:USR_CRT_RLE_MPG:MODIFY', 'GBM_USER_MGMT:USR_CRT_RLE_MPG:ENQUIRY', 'GBM_USER_MGMT:USR_CRT_RLE_MPG:VERIFY']
                         },
                         resolve: {
                              load: function($ocLazyLoad) {
                                  return $ocLazyLoad.load({
                                      files: [
                                          'app/user_mgmt/common/left-menu.directive.js',
                                          'app/user_mgmt/common/compareto.directive.js',
                                          'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingService.js?v='+versionNum
                                      ]
                                  });
                              }
                          }
                      })
                     .state('usrCrtRleMpm.add', {
                      url: '/add',
                      templateUrl: 'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMapping_add.html',
                      data: {
                          authRequired: true,
                          requiredPermission: 'GBM_USER_MGMT:USR_CRT_RLE_MPG:ADD'
                      },
                      controller: 'userCreationRoleMappingController',
                      controllerAs: 'vm',
                      resolve: {
                          load: function($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                  files: ['app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingController.js?v='+versionNum]
                                    });
                          }
                      }
                  })
                     .state('usrCrtRleMpm.modify', {
                        url: '/action/modify',
                        templateUrl: 'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMapping_modify.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'GBM_USER_MGMT:USR_CRT_RLE_MPG:MODIFY'
                        },
                        controller: 'userCreationRoleMappingController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingController.js?v='+versionNum]
                                      });
                            }
                        }
                    })
                     .state('usrCrtRleMpm.modify_detail', {
                         url: '/action/modify/detail',
                         templateUrl: 'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMapping_modifyDetail.html',
                         data: {
                             authRequired: true,
                             requiredPermission: 'GBM_USER_MGMT:USR_CRT_RLE_MPG:MODIFY'
                         },
                         params : {obj : null},
                         controller: 'userCreationRoleMappingController',
                         controllerAs: 'vm',
                         resolve: {
                             load: function($ocLazyLoad) {
                                 return $ocLazyLoad.load({
                                     files:  ['app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingController.js?v='+versionNum]
                                 });
                             }
                         }
                     })
                     .state('usrCrtRleMpm.list', {
                                                     url: '/action/inquiry',
                                                     templateUrl: 'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMapping_inquiry.html',
                                                     data: {
                                                         authRequired: true,
                                                         requiredPermission: 'GBM_USER_MGMT:USR_CRT_RLE_MPG:ENQUIRY'
                                                     },
                                                     controller: 'userCreationRoleMappingController',
                                                     controllerAs: 'vm',
                                                     resolve: {
                                                         load: function($ocLazyLoad) {
                                                             return $ocLazyLoad.load({
                                                                 files: ['app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingController.js?v='+versionNum]
                                                                   });
                                                         }
                                                     }
                                                 })
                     .state('usrCrtRleMpm.list_detail', {
                          url: '/action/inquiry/detail',
                          templateUrl: 'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMapping_inquiryDetail.html',
                          data: {
                              authRequired: true,
                              requiredPermission: 'GBM_USER_MGMT:USR_CRT_RLE_MPG:ENQUIRY'
                          },
                          params : {obj : null},
                          controller: 'userCreationRoleMappingController',
                          controllerAs: 'vm',
                          resolve: {
                              load: function($ocLazyLoad) {
                                  return $ocLazyLoad.load({
                                      files:  ['app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingController.js?v='+versionNum]
                                  });
                              }
                          }
                      })
                     .state('usrCrtRleMpm.verify', {
                        url: '/action/verify',
                        templateUrl: 'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMapping_verify.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'GBM_USER_MGMT:USR_CRT_RLE_MPG:VERIFY'
                        },
                        controller: 'userCreationRoleMappingController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingController.js?v='+versionNum]
                                      });
                            }
                        }
                    })
                     .state('usrCrtRleMpm.verify_detail', {
                         url: '/action/verify/detail',
                         templateUrl: 'app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMapping_verifyDetail.html',
                         data: {
                             authRequired: true,
                             requiredPermission: 'GBM_USER_MGMT:USR_CRT_RLE_MPG:VERIFY'
                         },
                         params : {obj : null},
                         controller: 'userCreationRoleMappingController',
                         controllerAs: 'vm',
                         resolve: {
                             load: function($ocLazyLoad) {
                                 return $ocLazyLoad.load({
                                     files:  ['app/user_mgmt/usercreationandrolemapping/userCreationuserRoleMappingController.js?v='+versionNum]
                                 });
                             }
                         }
                     })



                     .state('unlockUsers', {
                         abstract: true,
                         url: '/unlockUsers',
                         params: {
                                views: ['modify', 'verify'],
                                permissions: [ 'GBM_USER_MGMT:UNL_USR:MODIFY', 'GBM_USER_MGMT:UNL_USR:VER/REJ']
                         },
                         resolve: {
                              load: function($ocLazyLoad) {
                                  return $ocLazyLoad.load({
                                      files: [
                                          'app/user_mgmt/common/left-menu.directive.js',
                                          'app/user_mgmt/common/compareto.directive.js',
                                          'app/user_mgmt/unlock_user/user-unlock-service.js?v='+versionNum
                                      ]
                                  });
                              }
                          }
                      })

                     .state('unlockUsers.list', {
                                              url: '/list',
                                              templateUrl: 'app/user_mgmt/unlock_user/user-unlock-modify.html',
//                                              data: {
//                                                  authRequired: true,
//                                                  requiredPermission: 'GBM_USER_MGMT:UNL_USR:MODIFY'
//                                              },
                                              controller: 'unlockUsersController',
                                              controllerAs: 'vm',
                                              resolve: {
                                                  load: function($ocLazyLoad) {
                                                      return $ocLazyLoad.load({
                                                          files: ['app/user_mgmt/unlock_user/user-unlock-controller.js?v='+versionNum]
                                                            });
                                                  }
                                              }
                                          })

                     .state('unlockUsers.modify', {
                        url: '/action/modify',
                        templateUrl: 'app/user_mgmt/unlock_user/user-unlock-modify.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'GBM_USER_MGMT:UNL_USR:MODIFY'
                        },
                        controller: 'unlockUsersController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/unlock_user/user-unlock-controller.js?v='+versionNum]
                                      });
                            }
                        }
                    })
                     .state('unlockUsers.verify', {
                        url: '/action/verify',
                        templateUrl: 'app/user_mgmt/unlock_user/user-unlock-verify.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'GBM_USER_MGMT:UNL_USR:VER/REJ'
                        },
                        controller: 'unlockUsersController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/unlock_user/user-unlock-controller.js?v='+versionNum]
                                      });
                            }
                        }
                    })


/*Active user starts*/
.state('active_user', {
//    abstract: true,
    url: '/active_user',
    params: {
        views: ['modify','verify'],
        permissions: ['USER_MGMT:ACTIVE_USER:MODIFY', 'USER_MGMT:ACTIVE_USER:VERIFY']
    },
    resolve: {
        load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
                files: [
                    'app/user_mgmt/common/left-menu.directive.js',
                    'app/user_mgmt/active_user/active_user.service.js?v=' + versionNum
                ]
            });
        }
    }
})

   .state('active_user.list', {
                                              url: '/list',
                                              templateUrl: 'app/user_mgmt/unlock_user/user-unlock-list.html',
//                                              data: {
//                                                  authRequired: true,
//                                                  requiredPermission: 'GBM_USER_MGMT:UNL_USR:MODIFY'
//                                              },
                                              controller: 'activeUserController',
                                              controllerAs: 'vm',
                                              resolve: {
                                                  load: function($ocLazyLoad) {
                                                      return $ocLazyLoad.load({
                                                          files: ['app/user_mgmt/active_user/active_user.controller.js?v='+versionNum]
                                                            });
                                                  }
                                              }
                                          })

.state('active_user.modify', {
    url: '/action/modify',
    templateUrl: 'app/user_mgmt/active_user/modify_active_user.html',
    data: {
        authRequired: true,
        requiredPermission: 'USER_MGMT:ACTIVE_USER:MODIFY'
    },
    params: { action: null },
    controller: 'activeUserController',
    controllerAs: 'vm',
    resolve: {
        load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
                files: ['app/user_mgmt/active_user/active_user.controller.js?v=' + versionNum]
            });
        }
    }
})

.state('active_user.verify', {
    url: '/action/verify',
    templateUrl: 'app/user_mgmt/active_user/verify_delete_active_user.html',
    data: {
        authRequired: true,
        requiredPermission: 'USER_MGMT:ACTIVE_USER:VERIFY'
    },
    controller: 'activeUserController',
    controllerAs: 'vm',
    resolve: {
        load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
                files: ['app/user_mgmt/active_user/active_user.controller.js?v=' + versionNum]
            });
        }
    }
})





                    .state('admin_activity_report', {
                        abstract: true,
                        url: '/admin_activity_report',
                        params: {
                            views: ['inquiry'],
                            permissions: ['USER_MGMT:ADMIN_ACTIVITY:REPORT']
                        },
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: [
                                        'app/user_mgmt/admin-activity-report/admin-activity-report-service.js?v='+versionNum
                                    ]
                                });
                            }
                        }
                    })
                    .state('admin_activity_report.inquiry', {
                        url: '/action/inquiry',
                        templateUrl: 'app/user_mgmt/admin-activity-report/admin-activity-report_input.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'USER_MGMT:ADMIN_ACTIVITY:REPORT'
                        },
                        controller: 'adminActivityReportController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/admin-activity-report/admin-activity-report-controller.js?v='+versionNum]
                                });
                            }
                        }
                    })
                    .state('admin_activity_report.inquiry_details', {
                        url: '/action/inquiry_details',
                        templateUrl: 'app/user_mgmt/admin-activity-report/admin-activity-report_details.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'USER_MGMT:ADMIN_ACTIVITY:REPORT'
                        },
                        params: {
                            obj: null
                        },
                        controller: 'adminActivityReportController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/admin-activity-report/admin-activity-report-controller.js?v='+versionNum]
                                });
                            }
                        }
                    })



                    .state('login_logout_report', {
                        abstract: true,
                        url: '/login_logout_report',
                        params: {
                            views: ['inquiry'],
                            permissions: ['USER_MGMT:LOGIN_LOGOUT:REPORT']
                        },
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: [
                                        'app/user_mgmt/login-logout-report/login-logout-report-service.js?v='+versionNum
                                    ]
                                });
                            }
                        }
                    })
                    .state('login_logout_report.inquiry', {
                        url: '/action/inquiry',
                        templateUrl: 'app/user_mgmt/login-logout-report/login-logout-report_input.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'USER_MGMT:LOGIN_LOGOUT:REPORT'
                        },
                        controller: 'loginLogoutReportController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/login-logout-report/login-logout-report-controller.js?v='+versionNum]
                                });
                            }
                        }
                    })
                    .state('login_logout_report.inquiry_details', {
                        url: '/action/inquiry_details',
                        templateUrl: 'app/user_mgmt/login-logout-report/login-logout-report_details.html',
                        data: {
                            authRequired: true,
                            requiredPermission: 'USER_MGMT:LOGIN_LOGOUT:REPORT'
                        },
                        params: {
                            obj: null
                        },
                        controller: 'loginLogoutReportController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/user_mgmt/login-logout-report/login-logout-report-controller.js?v='+versionNum]
                                });
                            }
                        }
                    })


	}
})();