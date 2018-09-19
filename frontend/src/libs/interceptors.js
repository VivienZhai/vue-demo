import Vue from 'vue';
import Config from '@/config/config';
var qs = require('qs');
var timer1,timer2;

function requestInterceptors(config){
	if(config.method === 'post') {
		config.data = qs.stringify(config.data);
	}
	return config;
}

function requestError(error){
	alert("服务器内部异常,请稍候再试");
    return Promise.reject(error);
}

function responseInterceptors(response){
    var data = response.data;
    if(data instanceof Object){
    	var code = data["code"];
    	if(code == "10106"){
			clearTimeout(timer1);
            timer1 = setTimeout(function(){
                if(confirm("会话已失效，是否重新登录")){
                    window.location.href = Config.loginPage;
                }
			},500)
    	}
    }
    return response;
}

function responseError(error){
    clearTimeout(timer2);
    timer2 = setTimeout(function(){
        alert("服务器内部异常,请稍候再试");
    },500)
    return Promise.reject(error);
}

export default {
	requestInterceptors:requestInterceptors,
	responseInterceptors:responseInterceptors,
	requestError:requestError,
	responseError:responseError
}
