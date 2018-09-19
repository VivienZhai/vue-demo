
function typeOf(obj) {
    var toString = Object.prototype.toString;
    var map = {
        '[object Boolean]'  : 'boolean',
        '[object Number]'   : 'number',
        '[object String]'   : 'string',
        '[object Function]' : 'function',
        '[object Array]'    : 'array',
        '[object Date]'     : 'date',
        '[object RegExp]'   : 'regExp',
        '[object Undefined]': 'undefined',
        '[object Null]'     : 'null',
        '[object Object]'   : 'object'
    };
    return map[toString.call(obj)];
}

// deepCopy
function deepCopy(data) {
    var t = typeOf(data);
    var o;

    if (t === 'array') {
        o = [];
    } else if ( t === 'object') {
        o = {};
    } else {
        return data;
    }

    if (t === 'array') {
        for (var i = 0; i < data.length; i++) {
            o.push(deepCopy(data[i]));
        }
    } else if ( t === 'object') {
        for (var i in data) {
            o[i] = deepCopy(data[i]);
        }
    }
    return o;
}

export {deepCopy};

