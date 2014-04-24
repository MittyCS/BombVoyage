/**
 * @author Adrian Chmielewski-Anders
 */

if (utils === undefined) {
    utils = {};
}

/**
 * A simple async get function
 * @param url the url to get
 * @param callback the function that is going to be called upon success
 * @param error optional, the function that is going to be called upon error
 */
utils.get = function(url, callback, error) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.onload = callback;
    if (error) {
        xhr.onerror = error;
    }
    xhr.send();
};

utils.post = function(url, params) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);

    if (typeof params === "object") {
        var builder = "";
        for (var prop in params) {
            if (params.hasOwnProperty(prop)) {
                builder += prop + "=" + params[prop] + "&";
            }
        }
        xhr.send(builder.substring(0, builder.length - 1));
    } else {
        xhr.send(params);
    }
};

utils.randNum = function(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
};

utils.randString = function() {
    return Math.floor(Math.random() * 0xffff).toString(16);
};
