// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
//angular.module('wechat', ['ionic', 'wechat.controllers', 'wechat.routes',
//    'wechat.services', 'wechat.directives', 'monospaced.elastic'
//])

angular.module('wechat', ['ionic', 'wechat.controllers', 'wechat.routes','ngCordova'
])

.config(['$ionicConfigProvider', function($ionicConfigProvider) {

    $ionicConfigProvider.tabs.position('bottom'); // other values: top

}])



.run(function($ionicPlatform, $rootScope, $location, $timeout, $ionicHistory, $cordovaToast,$http) {

    var url = "";
    if (ionic.Platform.isAndroid()) {
        url = "/android_asset/www/";
    }
    
    /**
    // if (localStorage.getItem("messageID") === null) {
        $http.get(url + "data/json/messages.json").then(function(response) {
            // localStorageService.update("messages", response.data.messages);
            //messageService.init(response.data.messages);
        });
        $http.get(url + "data/json/friends.json").then(function(response){
            //console.log(response.data.results);
        });
    // }
    
    **/
    $ionicPlatform.ready(function() {

        //初始化插件
        
    	console.log("初始化完成")
    	/*
        if (window.cordova && window.cordova.plugins.Keyboard) {
            cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        }
        if (window.StatusBar) {
            StatusBar.styleDefault();
        }
        */
        
        //双击退出
        $ionicPlatform.registerBackButtonAction(function (e) {
            //判断处于哪个页面时双击退出
        	var urlpath=$location.path();
        	//alert(urlpath);
            if (urlpath == '/tab/home') {
                if ($rootScope.backButtonPressedOnceToExit) {
                    ionic.Platform.exitApp();
                } else {
                    $rootScope.backButtonPressedOnceToExit = true;
                    showToast1('再按一次退出系统');
                    setTimeout(function () {
                        $rootScope.backButtonPressedOnceToExit = false;
                    }, 2000);
                }
            }
            else if ($ionicHistory.backView()) {
                $ionicHistory.goBack();
            } else {
                $rootScope.backButtonPressedOnceToExit = true;
                showToast1('再按一次退出系统');
                setTimeout(function () {
                    $rootScope.backButtonPressedOnceToExit = false;
                }, 2000);
            }
            e.preventDefault();
            return false;
        }, 101);
    });
});


function showToast1(msg, duration) {    
    duration = isNaN(duration) ? 1000 : duration;    
    var m = document.createElement('div');    
    m.innerHTML = msg;    
    m.style.cssText = "width:60%; min-width:150px; background:#000; opacity:0.5; height:40px; color:#fff; line-height:40px; text-align:center; border-radius:5px; position:fixed; top:70%; left:20%; z-index:999999; font-weight:bold;";    
    document.body.appendChild(m);    
    setTimeout(function() {    
        var d = 0.5;    
        m.style.webkitTransition = '-webkit-transform ' + d    
                + 's ease-in, opacity ' + d + 's ease-in';    
        m.style.opacity = '0';    
        setTimeout(function() {    
            document.body.removeChild(m)    
        }, d * 1000);    
    }, duration);    
}  
