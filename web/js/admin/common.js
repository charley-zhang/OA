function getQueryString(name) {
    var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)")
    var array = window.location.search.substr(1).match(reg);
    if (array!=null){
        return decodeURIComponent(array[2])
    }
    return ""
}