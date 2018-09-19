import axios from 'axios';
import wrapper from 'axios-cache-plugin';

var qs = require('qs');

var util = {};
util.title = function (title) {
  title = title || 'lambo';
  window.document.title = title;
};
let instance = axios.create();
instance.defaults.headers.common['Content-Type'] = 'application/json;charset=UTF-8'

//axios-cache-plugin 以下请求结果将被缓存，刷新浏览器清空缓存
util.ajax = wrapper(instance, {
  maxCacheSize: 15
});
util.params = function (obj){
  return qs.stringify(obj);
};
util.isJson = function(arg){
  return typeof(arg) == "object" &&  Object.prototype.toString.call(arg).toLowerCase() == "[object object]" && !arg.length
};

export default util;
