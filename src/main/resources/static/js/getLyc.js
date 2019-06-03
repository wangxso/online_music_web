function clientSideInclude(tagName, url) {
    var req = false;
    // Safari, Firefox, 及其他非微软浏览器
    if (window.XMLHttpRequest) {
        try {
            req = new XMLHttpRequest();
        } catch (e) {
            req = false;
        }
    } else if (window.ActiveXObject) {

        // For Internet Explorer on Windows
        try {
            req = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                req = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                req = false;
            }
        }
    }
    var element = document.getElementsByTagName(tagName);
    if (!element) {
        alert("函数clientSideInclude无法找到id " + id + "。" +
            "你的网页中必须有一个含有这个id的div 或 span 标签。");
        return;
    }
    if (req) {
        // 同步请求，等待收到全部内容
        req.open('GET', url, false);
        req.send(null);
        if (req.status == 404) {
            clientSideInclude(id, 'error.html')
        } else {
            element.innerHTML = req.responseText;
        }
    } else {
        element.innerHTML =
            "对不起，你的浏览器不支持" +
            "XMLHTTPRequest 对象。这个网页的显示要求" +
            "Internet Explorer 5 以上版本, " +
            "或 Firefox 或 Safari 浏览器，也可能会有其他可兼容的浏览器存在。";
    }
}

clientSideInclude("pre", "http://XXXXX.html");//页面中的一个div的id为includeHtml
