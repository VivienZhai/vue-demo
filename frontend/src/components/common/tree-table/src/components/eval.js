/**
* @Author: jianglei
* @Date:   2017-10-12 12:06:49
*/
'use strict'
import Vue from 'vue'
export default function treeToArray(data, expandAll, parent, level) {
  var tmp = []
  Array.from(data).forEach(function(record) {
    var recordChildren = JSON.parse(JSON.stringify(record.children || null));
    if (record._expanded === undefined) {
      Vue.set(record, '_expand', expandAll)
    }
    var _level = 1
    if (level !== undefined && level !== null) {
      _level = level + 1;
        Vue.set(record, '_show', false);
    }else{
        Vue.set(record, '_show', true);
    }
    Vue.set(record, '_level', _level);
    Vue.set(record, 'parent', parent);

    tmp.push(record);
    if (recordChildren && recordChildren.length > 0) {
      record.children = true;
      var children = treeToArray(recordChildren, expandAll, true, _level)
      tmp = tmp.concat(children);
    }
  });
  return tmp
}
