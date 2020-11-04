var url_server="/libraryServer";
//var url_server="http://127.0.0.1:8080/libraryServer";
//var url_server="http://zhongshan.bjshansi.com:8023/libraryServer";
angular.module('wechat.controllers', ['ngCordova','ionic'])

.controller('mylibCtrl', function($scope, $state,$http,$ionicLoading) {
	 
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	
	//***我的图书预判页****
 
    //激活前
    $scope.$on("$ionicView.beforeEnter",function(){
    	var userCode=window.localStorage.getItem("userCode");
    	var userPwd=window.localStorage.getItem("userPwd");
    	var accessToken=window.localStorage.getItem("accessToken");
    	if(userCode==null){
    		$ionicLoading.hide();
    		$state.go("login");
    	}else{
    		//判断是否登录
    		var url=url_server+"/mylib/checkLogin/";
    		var data={'foobar': new Date().getTime(),
    		           'accessToken':accessToken};
	    	$http.get(url,{params:data}).success(function(result) {
	    		if(result.state=="F"){
	    			//未登录，自动登录
	    			
	    			url=url_server+"/login/checkPwd/?userCode="+userCode+"&userPwd="+userPwd;
			    	data={'foobar': new Date().getTime()};
			    	$http.get(url,{params:data,cache:false}).success(function(result) {
			    		if(result.state=="F"){
			    			$ionicLoading.hide();
			    			$state.go("login");
			    		}else{
			    			window.localStorage.setItem("accessToken",result.accessToken);
			    			showInfo();
			    		}		   
					})
					
	    		}else{
	    			//已登录
	    			showInfo();
	    		}		   
			})    		
    	}
    });
    
  
    function showInfo(){
    	
    	//个人信息
    	var accessToken=window.localStorage.getItem("accessToken");
    	var url=url_server+"/mylib/userInfo/";
    	var data={'foobar': new Date().getTime(),'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    		if(result.state=="F"){
    			$ionicLoading.hide();
    			$state.go("login",{});
    		}else{
    			$ionicLoading.hide();
    			$scope.userInfo = result.userInfo;
    		}		   
		}).error(function(err){
			$ionicLoading.hide();
		})
    }
    
    $scope.logout=function(){
    	//本地数据清除
    	window.localStorage.setItem("userCode",null);
    	window.localStorage.setItem("userPwd",null);
    	var accessToken=window.localStorage.getItem("accessToken");
    	//服务器清除session
    	var url=url_server+"/login/logout/";
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    		window.localStorage.removeItem("userCode");
    		window.localStorage.removeItem("userPwd");
    		window.localStorage.removeItem("accessToken");
    		$state.go("tab.home",{});		   
		}) 
    }
})


.controller('homeCtrl', function($scope, $state,$http,$ionicLoading) {
	//载入
	// Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
    init();
    //***首页***
    function init(){
    	var url=url_server+"/home/newBooks";
    	var data={};
    	$http.get(url,{params:data}).success(function(result) {
		   $scope.newBooks = result;
		   $scope.tab="new";
		   $ionicLoading.hide();
		}).error(function(error){
			$ionicLoading.hide();
		});
    	
    	url="data/json/ad.json";
    	$http.get(url,{params:data}).success(function(result){
    		$scope.ad=result;
    	});
    }
    
    //新书
    $scope.getNewBooks=function(){
    	$ionicLoading.show();
    	var url=url_server+"/home/newBooks";
    	var data={};
    	$http.get(url,{params:data}).success(function(result) {
    	   $ionicLoading.hide();
		   $scope.newBooks = result;
		   $scope.tab="new";
		}).error(function(err){
			$ionicLoading.hide();
		})
    }
    
    //推荐图书
    $scope.getTuijianBooks=function(){
    	$ionicLoading.show();
    	var url=url_server+"/home/tuijianBooks";
    	var data={};
    	$http.get(url,{params:data}).success(function(result) {
    	   $ionicLoading.hide();
		   $scope.newBooks = result;
		   $scope.tab="tuijian";
		}).error(function(err){
		   $ionicLoading.hide();
		})
    }
    
    //热门图书
    $scope.getHotBooks=function(){
    	$ionicLoading.show();
    	var url=url_server+"/home/hotBooks";
    	var data={};
    	$http.get(url,{params:data}).success(function(result) {
    		$ionicLoading.hide();
		   $scope.newBooks = result;
		   $scope.tab="hot";
		}).error(function(err){
			$ionicLoading.hide();
		})
    }
    
    
    //图书明细
    $scope.bookDetils = function(bookId) {
        $state.go("bookDetail2", {
            "bookId": bookId
        });
    };
    
    //广告明细
    $scope.adDetail = function(adId) {
        $state.go("adDetail", {
            "adId": adId
        });
    };
    
    //查询
    $scope.search=function(){
    	$state.go("tab.search");
    }
})

.controller('bookDetailCtrl', function($scope, $state,$http,$stateParams,$ionicHistory,$ionicLoading) {
    //***图书简介***
    // Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	
    $scope.$on("$ionicView.beforeEnter",init());
    function init(){
    	var bookId= $stateParams.bookId;
    	var url=url_server+"/home/bookInfo/?bookId="+bookId;
    	var data={};
    	$http.get(url,{params:data}).success(function(result){
    		$ionicLoading.hide();
    		if(result.bookInfo.hasBook=="T"){
    			result.bookInfo.cmd="预借图书";
    		}else{
    			result.bookInfo.cmd="预约委托";
    		}

    		$scope.libList=result.libList;
    		$scope.bookInfo=result.bookInfo;
    	}).error(function(err){
    		$ionicLoading.hide();
    	});
    }
    
    //处理预借、委托
    $scope.deal=function(bookId){
    	chklogin(bookId);    	
    }
    
    //收藏
    $scope.myStore=function(bookId){
    	chklogin2(bookId); 
    }
    
    function chklogin(bookId){
    	var userCode=window.localStorage.getItem("userCode");
    	var userPwd=window.localStorage.getItem("userPwd");
    	var accessToken=window.localStorage.getItem("accessToken");
    	if(userCode==null){
    		$state.go("login");
    	}else{
    		//判断是否登录
    		var url=url_server+"/mylib/checkLogin/";
    		var data={'foobar': new Date().getTime(),
    		           'accessToken':accessToken};
	    	$http.get(url,{params:data}).success(function(result) {
	    		if(result.state=="F"){
	    			//未登录，自动登录
	    			
	    			url=url_server+"/login/checkPwd/?userCode="+userCode+"&userPwd="+userPwd;
			    	data={'foobar': new Date().getTime()};
			    	$http.get(url,{params:data,cache:false}).success(function(result) {
			    		if(result.state=="F"){
			    			$state.go("login");
			    		}else{
				            window.localStorage.setItem("accessToken",result.accessToken);
			    			toDeal(bookId);
			    		}		   
					})
					
	    		}else{
	    			//已登录
	    			toDeal(bookId);
	    		}		   
			})    		
    	}
    }


    function toDeal(bookId){
    	var accessToken=window.localStorage.getItem("accessToken");
    	if($scope.bookInfo.hasBook=="T"){
    		//预借
    		var url=url_server+"/mylib/prelend/?bookId="+bookId+"&accessToken="+accessToken;
    	    var data={};
    	    $http.get(url,{params:data}).success(function(result){
    	    	showToast(result.message);
    	    })
    	}else{
    		//委托
    		var url=url_server+"/mylib/yuelend/?bookId="+bookId+"&accessToken="+accessToken;
    	    var data={};
    	    $http.get(url,{params:data}).success(function(result){
    	    	showToast(result.message);
    	    })
    	}
    }
    
    function chklogin2(bookId){
    	var userCode=window.localStorage.getItem("userCode");
    	var userPwd=window.localStorage.getItem("userPwd");
    	var accessToken=window.localStorage.getItem("accessToken");
    	if(userCode==null){
    		$state.go("login");
    	}else{
    		//判断是否登录
    		var url=url_server+"/mylib/checkLogin/";
    		var data={'foobar': new Date().getTime(),
    		           'accessToken':accessToken};
	    	$http.get(url,{params:data}).success(function(result) {
	    		if(result.state=="F"){
	    			//未登录，自动登录
	    			
	    			url=url_server+"/login/checkPwd/?userCode="+userCode+"&userPwd="+userPwd;
			    	data={'foobar': new Date().getTime()};
			    	$http.get(url,{params:data,cache:false}).success(function(result) {
			    		if(result.state=="F"){
			    			$state.go("login");
			    		}else{
				            window.localStorage.setItem("accessToken",result.accessToken);
			    			toMyStore(bookId);
			    		}		   
					})
					
	    		}else{
	    			//已登录
	    			toMyStore(bookId);
	    		}		   
			})    		
    	}
    }
    
    function toMyStore(bookId){
    	var accessToken=window.localStorage.getItem("accessToken");
    	var url=url_server+"/mylib/myStore/?bookId="+bookId+"&accessToken="+accessToken;
	    var data={};
	    $http.get(url,{params:data}).success(function(result){
	    	showToast(result.message);
	    })
    }
    
    $scope.returnSearch=function(){
    	$ionicHistory.goBack();
    }
    
    $scope.goBack=function(){
    	$ionicHistory.goBack();
    }
})


.controller('bookDetailPrelendCtrl', function($scope, $state,$http,$stateParams,$ionicHistory,$ionicLoading) {
    //***图书预借处理***
    // Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	
    $scope.$on("$ionicView.beforeEnter",init());
    function init(){
    	var bookId= $stateParams.bookId;
    	var b_no= $stateParams.b_no;
    	var state= $stateParams.state;
    	var url=url_server+"/home/bookInfo/?bookId="+bookId;
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={accessToken:accessToken};
    	$http.get(url,{params:data}).success(function(result){
    		$ionicLoading.hide();
    		result.b_no=b_no
    		result.cmd="取消预借";
    		if(state=="有效"){
    			result.state=true;
    		}else{
    			result.state=false;
    		}
    		
    		$scope.bookInfo=result;
    	}).error(function(err){
    		$ionicLoading.hide();
    	});
    }
    
    
    $scope.cancle=function(b_no,bookId){
    	var url=url_server+"/mylib/canclePrelend/?bookId="+bookId+"&b_no="+b_no;
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={accessToken:accessToken};
    	$http.get(url,{params:data}).success(function(result){
    		$ionicLoading.hide();
    		$scope.bookInfo.state=false;
    		showToast(result.message);
    	}).error(function(err){
    		$ionicLoading.hide();
    	});
    }
    
    $scope.returnSearch=function(){
    	$ionicHistory.goBack();
    }
})

.controller('bookDetailYuelendCtrl', function($scope, $state,$http,$stateParams,$ionicHistory,$ionicLoading) {
    //***图书委托预借处理***
    // Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	
    $scope.$on("$ionicView.beforeEnter",init());
    function init(){
    	var bookId= $stateParams.bookId;
    	var bid= $stateParams.bid;
    	var state= $stateParams.state;
    	var url=url_server+"/home/bookInfo/?bookId="+bookId;
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={accessToken:accessToken};
    	$http.get(url,{params:data}).success(function(result){
    		$ionicLoading.hide();
    		result.bid=bid
    		result.cmd="取消委托预借";
    		if(state=="有效"){
    			result.state=true;
    		}else{
    			result.state=false;
    		}
    		
    		$scope.bookInfo=result;
    	}).error(function(err){
    		$ionicLoading.hide();
    	});
    }
    
    
    $scope.cancle=function(b_no,bookId){
    	var url=url_server+"/mylib/cancleYuelend/?bookId="+bookId+"&b_no="+b_no;
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={accessToken:accessToken};
    	$http.get(url,{params:data}).success(function(result){
    		$ionicLoading.hide();
    		$scope.bookInfo.state=false;
    		showToast(result.message);
    	}).error(function(err){
    		$ionicLoading.hide();
    	});
    }
    
    $scope.returnSearch=function(){
    	$ionicHistory.goBack();
    }
})

.controller('adDetailCtrl', function($scope, $state,$http,$stateParams) {
    //***广告简介***
    init();
    function init(){
    	var adId= $stateParams.adId;
    	var url="data/json/ad.json?adId"+adId;
    	var data={};
    	$http.get(url,{params:data}).success(function(result){
    		$scope.adInfo=result;
    	});
    }
})


.controller('searchCtrl', function($scope, $state,$ionicPopup,$http) {
	//***图书检索***
	init();
    $scope.onSwipeLeft = function() {
        $state.go("tab.home");
    };
    $scope.onSwipeRight = function() {
        $state.go("tab.mylib");
    };
    $scope.contacts_right_bar_swipe = function(e){
        console.log(e);
    };
    
    
    function init(){
    	$scope.searchObj={title:'',dutyer:'',sid:'',kind:'',isbn:'',pub_man:'',pub_date:'',pub_zhaiyao:''};
    	var url=url_server+"/search/getSchs";
    	var data={};
    	$http.get(url,{params:data}).success(function(result){
    		$scope.schs=result;
    	});
    }
    
    $scope.search=function(){
    	var searchObj=$scope.searchObj;
    	var sid=searchObj.sid;
    	if(sid==null){
    		sid="";
    	}

    	$state.go("searchResult", {
    		title : searchObj.title,
    		dutyer: searchObj.dutyer,
    		sid: searchObj.sid,
    		kind: searchObj.kind,
    		isbn: searchObj.isbn,
    		pub_man: searchObj.pub_man,
    		pub_date: searchObj.pub_date,
    		pub_zhaiyao: searchObj.pub_zhaiyao
        });
    }
    $scope.itemChange=function(item){
    	$scope.searchObj.kind=item;
    }
    
    //全部书籍
    $scope.showAllBook=function(){
    	$state.go("searchResult", {
    		title : '',
    		dutyer: '',
    		sid: '',
    		kind: '',
    		isbn: '',
    		pub_man: '',
    		pub_date: '',
    		pub_zhaiyao: ''
        });
    }
    //警告
	$scope.showAlert = function(title,template) {
		var alertPopup = $ionicPopup.alert({
			title: title,
			template: template
		});
		alertPopup.then(function(res) {
			console.log('Thank you for not eating my delicious ice cream cone');
		});
	};
})

.controller('searchResultCtrl', function($scope, $state,$stateParams,$http,$ionicLoading) {
	//***搜书结果***
	// Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	
	$scope.hasmore=false;
	thePage=1;
	
	//$scope.$on("$ionicView.beforeEnter",init());
	init();
    function init(){
    	thePage=1;
    	var title=$stateParams.title;
    	var dutyer=$stateParams.dutyer;
    	var sid =$stateParams.sid;
    	var kind =$stateParams.kind;
    	var isbn =$stateParams.isbn;
    	var pub_man =$stateParams.pub_man;
    	var pub_date =$stateParams.pub_date;
    	var pub_zhaiyao =$stateParams.pub_zhaiyao;
    	
    	var url=url_server+"/search/searchNew/?thePage="+thePage;
    	
    	var data={title : title,
        		dutyer: dutyer,
        		sid: sid,
        		kind: kind,
        		isbn: isbn,
        		pub_man: pub_man,
        		pub_date: pub_date,
        		pub_zhaiyao: pub_zhaiyao};
    	$http.get(url,{params:data}).success(function(result) {
    	   $ionicLoading.hide();
		   $scope.newBooks = result;
		   
		   if(result.length<10){			   
			  $scope.hasmore=false;			  
			  $scope.$broadcast('scroll.infiniteScrollComplete');
		   }else{
			   $scope.hasmore=true;
		   }
		}).error(function(err){
			$ionicLoading.hide();
		}).finally(function(){
			$scope.$broadcast('scroll.infiniteScrollComplete');
		})
    }
    
    //图书明细
    $scope.bookDetils = function(bookId) {
        $state.go("bookDetail2", {
            "bookId": bookId
        });
    };
    
    //上拉加载更多
	$scope.loadMore=function(){
		thePage=thePage+1;
		var title=$stateParams.title;
    	var dutyer=$stateParams.dutyer;
    	var sid =$stateParams.sid;
    	var kind =$stateParams.kind;
    	var isbn =$stateParams.isbn;
    	var pub_man =$stateParams.pub_man;
    	var pub_date =$stateParams.pub_date;
    	var pub_zhaiyao =$stateParams.pub_zhaiyao;
    	var url=url_server+"/search/searchNew/?thePage="+thePage;
    	
    	var data={title : title,
        		dutyer: dutyer,
        		sid: sid,
        		kind: kind,
        		isbn: isbn,
        		pub_man: pub_man,
        		pub_date: pub_date,
        		pub_zhaiyao: pub_zhaiyao};
    	$http.get(url,{params:data}).success(function(result) {
    		if(result){
    			for(i=0;i<result.length;i++){
    				$scope.newBooks.push(result[i]);
    			}
    			
				if(result.length<10){
				    $scope.hasmore=false;	
				    $scope.$broadcast('scroll.infiniteScrollComplete');
				}else{
					$scope.hasmore=true;
				}
        	}else{
        		$scope.hasmore=false;
        		$scope.$broadcast('scroll.infiniteScrollComplete');
        	}
		}).finally(function(){
			$scope.$broadcast('scroll.infiniteScrollComplete');
		})
	}
})

.controller('settingCtrl', function($scope, $state,$http) {
	//***设置主界面***
	init();
	function init(){
		var url=url_server+"/setting/mapInfo/";
		var data={};
		$http.get(url,{params:data}).success(function(result){
			$scope.mapName=result.mapName;
		})
	}
	
    $scope.onSwipeLeft = function() {
        $state.go("tab.mylib");
    };
    
    
})

.controller('mylibInfoCtrl', function($scope, $state,$http) {
	//***我的信息***
	// Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	init();
    function init(){
    	var accessToken=window.localStorage.getItem("accessToken");
    	var url=url_server+"/mylib/userInfo/";
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    		if(result.state=="F"){
    			$ionicLoading.hide();
    			$state.go("login",{});
    		}else{
    			$scope.userInfo = result.userInfo;
    			var url=url_server+"/qr.jsp?content="+result.userInfo.userCode;
    	        $scope.qrPic=url;
    	        $ionicLoading.hide();
    		}		   
		}) 
    }
})

.controller('mylibMylendCtrl', function($scope, $state,$http,$stateParams,$ionicLoading) {
	//***我的借阅***
	// Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	
	$scope.$on("$ionicView.beforeEnter",init());
    function init(){
    	var keyword=$stateParams.keyword;
    	var kind=$stateParams.kind;
    	var url=url_server+"/mylib/lendList/";
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    	   $ionicLoading.hide();
		   if(result.state=="F"){
    			$state.go("login",{});
    		}else{
    			$scope.bookList = result.lendList;
    		}
		})
    }
    
    $scope.relend=function(lend_no){
     	var url=url_server+"/mylib/xujie/?lend_no="+lend_no;
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    	   $ionicLoading.hide();
		   if(result.state=="F"){
    			$state.go("login",{});
    		}else{
    			showToast1('续借成功!');
    			init();
    		}
		})
    }
    
})

.controller('mylibMyoverlendCtrl', function($scope, $state,$http,$stateParams,$ionicLoading) {
	//***我的逾期图书***
	// Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
    $scope.$on("$ionicView.beforeEnter",init());
    function init(){
    	var keyword=$stateParams.keyword;
    	var kind=$stateParams.kind;
    	var url=url_server+"/mylib/lendOverList/";
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    	   $ionicLoading.hide();
		   if(result.state=="F"){
    			$state.go("login",{});
    		}else{
    			$scope.bookList = result.lendList;
    		}
		}).error(function(err){
			$ionicLoading.hide();
		})
    }
    
})

.controller('mylibMyhistorylendCtrl', function($scope, $state,$http,$stateParams,$ionicLoading) {
	//***我的历史借阅***
	// Setup the loader
	$ionicLoading.show({
	    content: 'Loading',
	    animation: 'fade-in',
	    showBackdrop: true,
	    maxWidth: 200,
	    showDelay: 0
	});
	$scope.thePage=1;
    $scope.$on("$ionicView.beforeEnter",init());   
    function init(){
    	var url=url_server+"/mylib/lendHisList/?thePage="+$scope.thePage;
        var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    	   $ionicLoading.hide();
		   if(result.state=="F"){
    			$state.go("login",{});
    		}else{
    			$scope.bookList = result.lendList;
    		}
		}).error(function(err){
			$ionicLoading.hide();
		})
    }
    
    //下拉加载更多
    $scope.loadMore=function(){
    	$scope.thePage++;
    	var keyword=$stateParams.keyword;
    	var kind=$stateParams.kind;
    	var url=url_server+"/mylib/lendHisList/?thePage="+$scope.thePage;
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
		   if(result.state=="F"){
    			$state.go("login",{});
    		}else{
    			//$scope.bookList = result.lendList;
    		}
		})
    }
    
})

.controller('mylibCollectCtrl', function($scope, $state,$http,$stateParams) {
	//***我的收藏***
    chklogin();
    function init(){
    	var keyword=$stateParams.keyword;
    	var kind=$stateParams.kind;
    	var url=url_server+"/mylib/myStoreList";
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
		   $scope.newBooks = result;
		})
    }
    
    function chklogin(){
    	var userCode=window.localStorage.getItem("userCode");
    	var userPwd=window.localStorage.getItem("userPwd");
    	var accessToken=window.localStorage.getItem("accessToken");
    	if(userCode==null){
    		$state.go("login");
    	}else{
    		//判断是否登录
    		var url=url_server+"/mylib/checkLogin/";
    		var data={'foobar': new Date().getTime(),
    		           'accessToken':accessToken};
	    	$http.get(url,{params:data}).success(function(result) {
	    		if(result.state=="F"){
	    			//未登录，自动登录
	    			
	    			url=url_server+"/login/checkPwd/?userCode="+userCode+"&userPwd="+userPwd;
			    	data={'foobar': new Date().getTime()};
			    	$http.get(url,{params:data,cache:false}).success(function(result) {
			    		if(result.state=="F"){
			    			$state.go("login");
			    		}else{
				            window.localStorage.setItem("accessToken",result.accessToken);
			    			init();
			    		}		   
					})
					
	    		}else{
	    			//已登录
	    			init();
	    		}		   
			})    		
    	}
    }
    
    $scope.bookDetils=function(bookId){
    	$state.go("bookDetail2", {
            "bookId": bookId
        });
    }
})

.controller('mylibPreLendCtrl', function($scope, $state,$http,$stateParams) {
	//***我的预借***
    chklogin();
    function prelendList(){
    	var keyword=$stateParams.keyword;
    	var kind=$stateParams.kind;
    	var url=url_server+"/mylib/prelendList";
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
		   $scope.bookList = result.lendList;
		})
    }
    
    function chklogin(){
    	var userCode=window.localStorage.getItem("userCode");
    	var userPwd=window.localStorage.getItem("userPwd");
    	var accessToken=window.localStorage.getItem("accessToken");
    	if(userCode==null){
    		$state.go("login");
    	}else{
    		//判断是否登录
    		var url=url_server+"/mylib/checkLogin/";
    		var data={'foobar': new Date().getTime(),
    		           'accessToken':accessToken};
	    	$http.get(url,{params:data}).success(function(result) {
	    		if(result.state=="F"){
	    			//未登录，自动登录
	    			
	    			url=url_server+"/login/checkPwd/?userCode="+userCode+"&userPwd="+userPwd;
			    	data={'foobar': new Date().getTime()};
			    	$http.get(url,{params:data,cache:false}).success(function(result) {
			    		if(result.state=="F"){
			    			$state.go("login");
			    		}else{
				            window.localStorage.setItem("accessToken",result.accessToken);
			    			prelendList();
			    		}		   
					})
					
	    		}else{
	    			//已登录
	    			prelendList();
	    		}		   
			})    		
    	}
    }
    
    $scope.bookDetils=function(bookId,b_no,state){
    	console.log("b_no",b_no);
    	$state.go("bookDetailPrelend", {
            bookId: bookId,
            b_no: b_no,
            state: state
        });
    }
})

.controller('mylibYueLendCtrl', function($scope, $state,$http,$stateParams) {
	//***我的委托预约***
    chklogin();
    function yuelendList(){
    	var keyword=$stateParams.keyword;
    	var kind=$stateParams.kind;
    	var url=url_server+"/mylib/yuelendList";
    	var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
		   $scope.bookList = result.lendList;
		})
    }
    
    function chklogin(){
    	var userCode=window.localStorage.getItem("userCode");
    	var userPwd=window.localStorage.getItem("userPwd");
    	var accessToken=window.localStorage.getItem("accessToken");
    	if(userCode==null){
    		$state.go("login");
    	}else{
    		//判断是否登录
    		var url=url_server+"/mylib/checkLogin/";
    		var data={'foobar': new Date().getTime(),
    		           'accessToken':accessToken};
	    	$http.get(url,{params:data}).success(function(result) {
	    		if(result.state=="F"){
	    			//未登录，自动登录
	    			
	    			url=url_server+"/login/checkPwd/?userCode="+userCode+"&userPwd="+userPwd;
			    	data={'foobar': new Date().getTime()};
			    	$http.get(url,{params:data,cache:false}).success(function(result) {
			    		if(result.state=="F"){
			    			$state.go("login");
			    		}else{
				            window.localStorage.setItem("accessToken",result.accessToken);
			    			yuelendList();
			    		}		   
					})
					
	    		}else{
	    			//已登录
	    			yuelendList();
	    		}		   
			})    		
    	}
    }
    
    $scope.bookDetils=function(bid,bookId,state){
    	$state.go("bookDetailYuelend", {
            "bookId": bookId,
            "bid":bid,
            "state":state
        });
    }
})

.controller('mylibMycardCtrl', function($scope, $state,$http,$stateParams) {
	//***我的电子借书证***
	init();
    function init(){
    	var accessToken=window.localStorage.getItem("accessToken");
    	var url=url_server+"/mylib/userInfo/";
    	var data={'accessToken':accessToken};
    	$http.get(url,{params:data}).success(function(result) {
    		if(result.state=="F"){
    			$state.go("login",{});
    		}else{
    			var url=url_server+"/qr.jsp?content="+result.userInfo.userCode;
    	        $scope.qrPic=url;
    	        $scope.userInfo=result.userInfo;
    		}		   
		})
    	
    }
})

.controller('mylibPasswordCtrl', function($scope, $state,$http,$stateParams,$ionicPopup) {
	//***修改密码***
    init();
    function init(){
    	var info={ "password":"",
    	           "rePassword":""
                 };
        $scope.info=info;
        
        //检查是否登录
        var url=url_server+"/mylib/checkLogin/";
        var accessToken=window.localStorage.getItem("accessToken");
    	var data={'accessToken':accessToken};
        $http.get(url,{params:data}).success(function(result) {
    		if(result.state=="F"){
    			$state.go("login",{});
    		}		   
		})
    }
    
    $scope.save=function(){
    	var password=$scope.info.password;
    	var rePassword=$scope.info.rePassword;
    	if(password.length<6 || password.length>12){
    		$scope.showAlert("提示","密码必须是6-12位字母或数字！")
    		return;
    	}
    	
    	if(password==rePassword){
    		var url=url_server+"/mylib/changePwd/";
    		var accessToken=window.localStorage.getItem("accessToken");
	    	var data={'password':password,'rePassword':password,'accessToken':accessToken};
	    	$http.get(url,{params:data}).success(function(result) {
	    		if(result.state=="F"){
	    			$state.go("login",{});
	    		}else{
	    			$scope.showAlert("提示","密码修改成功！");
	    		}		   
			})
    	}else{
    		$scope.showAlert("提示","两次输入的密码不相同！")
    		return;
    	}
    }
    
    //警告
	$scope.showAlert = function(title,template) {
		var alertPopup = $ionicPopup.alert({
			title: title,
			template: template
		});
		alertPopup.then(function(res) {
			console.log('Thank you for not eating my delicious ice cream cone');
		});
	};
})

.controller('settingGuidCtrl', function($scope, $state,$http,$stateParams) {
	//***服务指南***
	function init(){
		var url=url_server+"/setting/getGuid";
    	var data={};
    	$http.get(url,{params:data}).success(function(result) {
    		$scope.list=result;		   
		})
	}
	init();
    $scope.guid=function(kind){
    	$state.go("setting_guid_detail",{
    		kind:kind
    	});
    }
})

.controller('settingGuidDetailCtrl', function($scope, $state,$http,$stateParams) {
	//***服务指南明细***

    init();
    function init(){
		var url=url_server+"/setting/guid/?kind="+$stateParams.kind;
		var data={};
		$http.get(url,{params:data}).success(function(result){
			$scope.guid=result;
		});
	}
})

.controller('settingNoticeCtrl', function($scope, $state,$http,$stateParams) {
	//***通知***
	var thePage=1;
	init();
	function init(){
		var url=url_server+"/setting/getNotices/?thePage="+thePage;
		var data={};
		$http.get(url,{params:data}).success(function(result){
			$scope.notices=result;
		});
	}
    
    $scope.noticeDetail=function(id){
    	$state.go("setting_notice_detail",{
    		id:id}
    	);
    }
})

.controller('settingNoticeDetailCtrl', function($scope, $state,$http,$stateParams) {
	//***通知明细***
    init();
    function init(){
		var url=url_server+"/setting/noticeDetail/?id="+$stateParams.id;
		var data={};
		$http.get(url,{params:data}).success(function(result){
			$scope.notice=result;
		});
	}
})

.controller('settingMapIndexCtrl', function($scope, $state,$http,$stateParams,$sce) {
	//***主分馆地址列表***
	init();
    function init(){
		var url=url_server+"/setting/mapInfoMulti";
		var data={};
		$http.get(url,{params:data}).success(function(result){
			$scope.list=result;
		});
	}
    
    $scope.showMap=function(mapId){
    	$state.go("setting_map",{mapId:mapId});
    }
})

.controller('settingMapCtrl', function($scope, $state,$http,$stateParams,$sce) {
	//***地图***
	init();
	/*
    function init(){
    	var url=url_server+"/map.jsp";
    	$scope.mapurl=$sce.trustAsResourceUrl(url);
    }
    */
    
    function init(){
    	var mapId = $stateParams.mapId;
    	var url=url_server+"/map.jsp?mapId="+mapId;
    	$scope.mapurl=$sce.trustAsResourceUrl(url);
    }
})

.controller('tabsCtrl', function($scope, $state,$http,$stateParams,$sce) {
	$scope.outPage=function(){
		window.finish.finishCurrentPage();	   	
	}
})

.controller('loginCtrl', function($scope, $state,$http,$stateParams) {
	//***登录页面***
	init();
    $scope.login=function(){    	
    	var url=url_server+"/login/checkPwd/?userCode="+$scope.userInfo.userCode+"&userPwd="+$scope.userInfo.userPwd;
    	var data={'foobar': new Date().getTime()+Math.random()};
		$http.get(url,{params:data}).success(function(result,status,headers,config){
			if(result.state=="F"){
				showToast("用户名或者密码错误！");
			}else{
				//设置
				window.localStorage.setItem("userCode",$scope.userInfo.userCode);
				window.localStorage.setItem("userPwd",$scope.userInfo.userPwd);
				window.localStorage.setItem("accessToken",result.accessToken);
				//window.localStorage.setItem("cookie",headers);
				$state.go("tab.mylib");
			}
		});
    }
    function init(){
    	$scope.userInfo={
    		"userCode":"",
    		"userPwd":""
    	}
    }
    
    //忘记密码
    $scope.forgetPwd=function(){
    	$state.go("forgetPwd");
    }
})
.controller('forgetPwdCtrl', function($scope, $state,$http,$stateParams,$sce) {
   //忘记密码
   init();
   function init(){
   	   $scope.userInfo={
   	   	   "mphone":"",
   	   	   "checkcode":"",
   	   	   "checkcode1":""
   	   }
   }
   $scope.next=function(){
   	    var userInfo=$scope.userInfo;
   	    if(userInfo.mphone==""){
			showToast("请填写手机号码");
			return;
		}
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(!reg.test(userInfo.mphone)){
			showToast("请填写合法手机号码");
			return;
		}
		if(userInfo.checkcode==""){
			showToast("请填写验证码");
			return;
		}
		var mphone=userInfo.mphone;
		var checkcode=userInfo.checkcode;

		
   	    if(userInfo.checkcode==userInfo.checkcode1){
   	    	$state.go("forgetPwd1",{"mphone":userInfo.mphone});
   	    }else{
   	    	//alert("验证码不正确！");
   	    	showToast("验证码不正确！");
   	    	return;
   	    }
   }
   
   //发送验证码
	$scope.sendCheckCode=function(mphone){
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(!reg.test(mphone)){
			showToast("请填写合法手机号码");
			return;
		}
		var url=url_server+"/login/sendCheckcode/";
    	var data={"mphone":mphone};
		$http.get(url,{params:data}).success(function(result,status,headers,config){
			if(result.state=="OK"){
				$scope.userInfo.checkcode1=result.checkcode;
				showToast("验证码已发送到你的手机");
			}
		});
	}
})

.controller('forgetPwd1Ctrl', function($scope, $state,$http,$stateParams,$sce) {
	//重设密码
	init();
	function init(){
		var mphone=$stateParams.mphone;
		$scope.userInfo={
			"password":"",
			"password1":"",
			"mphone": mphone
		}
	}
	
	$scope.finish=function(){
		var userInfo=$scope.userInfo;
		var pwd=userInfo.password;
		if(pwd.length<6){
			showToast("密码至少6位字母或者数字");
			return;
		}
		var pwd1=userInfo.password1;
        if(!(pwd==pwd1)){
        	showToast("两次输入密码不一致！");
			return;
        }
        
		var url=url_server+"/login/changePwd/";
    	var data=$scope.userInfo;
		$http.get(url,{params:data}).success(function(result,status,headers,config){
			showToast(result.message);
			$state.go("login");
			
		});
	}
})
.controller('regUserCtrl', function($scope, $state,$http,$stateParams,$sce) {
	//***注册用***
	init();
	function init(){
		$scope.userInfo={
			"mphone":"",
			"userName":"",
			"idcard":"",
			"email":"",
			"address":"",
			"com":"",
			"checkcode":"",
			"checkcode1":"",
			"password":"",
			"password1":""
		}
	}
	
	$scope.reg=function(){
		userInfo=$scope.userInfo;
		
		if(userInfo.mphone==""){
			showToast("请填写手机号码");
			return;
		}
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(!reg.test(userInfo.mphone)){
			showToast("请填写合法手机号码");
			return;
		}
		
		if(userInfo.userName==""){
			showToast("请填写姓名");
			return;
		}
		
		if(userInfo.idcard==""){
			showToast("请填写身份证号");
			return;
		}
		if(!IdentityCodeValid(userInfo.idcard)){
			showToast("请填写合法身份证号");
			return;
		}
		
		if(userInfo.checkcode==""){
			showToast("请填写验证码");
			return;
		}
		
		var pwd=userInfo.password;
		if(pwd.length<6){
			showToast("密码至少6位字母或者数字");
			return;
		}
		var pwd1=userInfo.password1;
        if(!(pwd==pwd1)){
        	showToast("两次输入密码不一致！");
			return;
        }
		
		if(userInfo.checkcode!=userInfo.checkcode1){
			showToast("验证码不正确");
			return;
		}
		
		if(userInfo.email!=""){
			var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
			if(!pattern.test(userInfo.email)){
				showToast("请填写合法邮件");
			    return;
			}
		}
		
		
		
		//提交
		var url=url_server+"/login/regUser/";
    	var data=$scope.userInfo;
		$http.get(url,{params:data}).success(function(result,status,headers,config){
			if(result.state=="OK"){
				showToast("你的信息已提交审核，审核结果会短信通知您");
				$state.go("tab.home");
			}else{
				showToast(result.message);
			}
			
		});
	}
	
	//发送验证码
	$scope.sendCheckCode=function(mphone){
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(!reg.test(mphone)){
			showToast("请填写合法手机号码");
			return;
		}
		var url=url_server+"/login/sendCheckcode/";
    	var data={"mphone":mphone};
		$http.get(url,{params:data}).success(function(result,status,headers,config){
			if(result.state=="OK"){
				$scope.userInfo.checkcode1=result.checkcode;
				showToast("验证码已发送到你的手机");
			}
		});
	}
	
	
	//身份证验证
	function IdentityCodeValid(code) { 
            var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
            var tip = "";
            var pass= true;
            
            if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
                tip = "身份证号格式错误";
                pass = false;
            }
            
           else if(!city[code.substr(0,2)]){
                tip = "地址编码错误";
                pass = false;
            }
            else{
                //18位身份证需要验证最后一位校验位
                if(code.length == 18){
                    code = code.split('');
                    //∑(ai×Wi)(mod 11)
                    //加权因子
                    var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                    //校验位
                    var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                    var sum = 0;
                    var ai = 0;
                    var wi = 0;
                    for (var i = 0; i < 17; i++)
                    {
                        ai = code[i];
                        wi = factor[i];
                        sum += ai * wi;
                    }
                    var last = parity[sum % 11];
                    if(parity[sum % 11] != code[17]){
                        tip = "校验位错误";
                        pass =false;
                    }
                }
            }
            return pass;
    }
	
	
})



function showToast(msg, duration) {    
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




