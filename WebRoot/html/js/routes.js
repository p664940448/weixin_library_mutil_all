angular.module('wechat.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state("tab", {
            url: "/tab",
            cache:'false',
            abstract: true,
            templateUrl: "templates/tabs.html",
        })
        .state('tab.home', {
            url: '/home',
            cache:'false',
            views: {
                'tab-home': {
                    templateUrl: 'templates/tab_home.html',
                    controller: "homeCtrl"
                }
            }
        })
        .state('bookDetail', {
            url: '/bookDetail/:bookId',
            cache:'false',
            templateUrl: "templates/book_detail.html",
            controller: "bookDetailCtrl"
        })
        
        .state('bookDetail2', {
            url: '/bookDetail2/:bookId',
            cache:'false',
            templateUrl: "templates/book_detail2.html",
            controller: "bookDetailCtrl"
        })
        
        .state('bookDetailPrelend', {
            url: '/bookDetailPrelend/:bookId/:b_no/:state',
            cache:'false',
            templateUrl: "templates/book_detail_prelend.html",
            controller: "bookDetailPrelendCtrl"
        })
        
        .state('bookDetailYuelend', {
            url: '/bookDetailYuelend/:bookId/:bid/:state',
            cache:'false',
            templateUrl: "templates/book_detail_yuelend.html",
            controller: "bookDetailYuelendCtrl"
        })
        
        .state('searchResult', {
            url: '/searchResult/:title/:dutyer/:sid/:kind/:isbn/:pub_man/:pub_date/:pub_zhaiyao',
            cache:'false',
            templateUrl: "templates/search_result.html",
            controller: "searchResultCtrl"
        })
        
        .state('adDetail', {
            url: '/adDetail/:adId',
            cache:'false',
            templateUrl: "templates/ad_detail.html",
            controller: "adDetailCtrl"
        })
        
        .state('tab.mylib', {
            url: '/mylib',
            cache:'false',
            views: {
                'tab-mylib': {
                    templateUrl: 'templates/tab_mylib.html',
                    controller: "mylibCtrl"
                }
            }
        })
        
        .state('mylib_info', {
            url: '/mylib_info',
            cache:'false',
            templateUrl: "templates/mylib_info.html",
            controller: "mylibInfoCtrl"
        })
        
        .state('mylib_mylend', {
            url: '/mylib_mylend',
            cache:'false',
            templateUrl: "templates/mylib_mylend.html",
            controller: "mylibMylendCtrl"
        })
        
        .state('mylib_myoverlned', {
            url: '/mylib_myoverlned',
            cache:'false',
            templateUrl: "templates/mylib_myoverlend.html",
            controller: "mylibMyoverlendCtrl"
        })
        
        .state('mylib_myhistorylned', {
            url: '/mylib_myhistorylend',
            cache:'false',
            templateUrl: "templates/mylib_myhistorylend.html",
            controller: "mylibMyhistorylendCtrl"
        })
        
        .state('mylib_mycollect', {
            url: '/mylib_mycollect',
            cache:'false',
            templateUrl: "templates/mylib_myCollect.html",
            controller: "mylibCollectCtrl"
        })
        
        .state('mylib_preLend', {
            url: '/mylib_preLend',
            cache:'false',
            templateUrl: "templates/mylib_prelend.html",
            controller: "mylibPreLendCtrl"
        })
        
        .state('mylib_yueLend', {
            url: '/mylib_yueLend',
            cache:'false',
            templateUrl: "templates/mylib_yuelend.html",
            controller: "mylibYueLendCtrl"
        })
        
        .state('mylib_mycard', {
            url: '/mylib_mycard',
            cache:'false',
            templateUrl: "templates/mylib_mycard.html",
            controller: "mylibMycardCtrl"
        })
        
        .state('mylib_password', {
            url: '/mylib_password',
            cache:'false',
            templateUrl: "templates/mylib_password.html",
            controller: "mylibPasswordCtrl"
        })
        
        .state('setting_guid', {
            url: '/setting_guid',
            cache:'false',
            templateUrl: "templates/setting_guid.html",
            controller: "settingGuidCtrl"
        })
        
        .state('setting_guid_detail', {
            url: '/setting_guid_detail/:kind',
            cache:'false',
            templateUrl: "templates/setting_guid_detail.html",
            controller: "settingGuidDetailCtrl"
        })
        
        .state('setting_notice', {
            url: '/setting_notice',
            cache:'false',
            templateUrl: "templates/setting_notice.html",
            controller: "settingNoticeCtrl"
        })
        
        .state('setting_notice_detail', {
            url: '/setting_notice_detail/:id',
            cache:'false',
            templateUrl: "templates/setting_notice_detail.html",
            controller: "settingNoticeDetailCtrl"
        })
        
        .state('setting_map', {
            url: '/setting_map/:mapId',
            cache:'false',
            templateUrl: "templates/setting_map.html",
            controller: "settingMapCtrl"
        })
        
        .state('setting_mapindex', {
            url: '/setting_mapindex',
            cache:'false',
            templateUrl: "templates/setting_mapindex.html",
            controller: "settingMapIndexCtrl"
        })
        
        
        .state('tab.search', {
            url: '/search',
            cache:'false',
            views: {
                'tab-search': {
                    templateUrl: 'templates/tab_search.html',
                    controller: "searchCtrl"
                }
            },
        })
        .state('tab.setting', {
            url: '/setting',
            cache:'false',
            views: {
                'tab-setting': {
                    templateUrl: 'templates/tab_setting.html',
                    controller: "settingCtrl"
                }
            }
        })
        
        .state('login', {
            url: '/login',
            cache:'false',
            templateUrl: "templates/login.html",
            controller: "loginCtrl"
        })
        
        .state('regUser', {
            url: '/regUser',
            cache:'false',
            templateUrl: "templates/regUser.html",
            controller: "regUserCtrl"
        })
        
        .state('forgetPwd', {
            url: '/forgetPwd',
            cache:'false',
            templateUrl: "templates/forgetPwd.html",
            controller: "forgetPwdCtrl"
        })
        
        .state('forgetPwd1', {
            url: '/forgetPwd1/:mphone/:checkcode',
            cache:'false',
            templateUrl: "templates/forgetPwd1.html",
            controller: "forgetPwd1Ctrl"
        });
        
        
        

    $urlRouterProvider.otherwise("/tab/home");
});