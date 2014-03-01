// Auto-log uncaught JS errors
window.onerror = function(msg, url, line) {
    var img = new Image();
    img.src = "logJsErr.st?msg=" + encodeURIComponent(msg) + "&url=" + encodeURIComponent(url) + "&line=" + line + "&ran=" + Math.random();
    return true;
}