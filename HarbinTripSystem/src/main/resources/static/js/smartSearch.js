(function ($) {
    "use strict";
    var map = new BMapGL.Map("l-map");
    // 初始化地图,设置城市和地图级别。
    map.centerAndZoom("哈尔滨市", 12);

    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }

//建立一个自动完成的对象
    var acStart = new BMapGL.Autocomplete({
        "input": "startId",
        "location": map
    });
//鼠标放在下拉列表上的事件
    acStart.addEventListener("onhighlight", function (e) {
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchStartResultPanel").innerHTML = str;
    });


    var acEnd = new BMapGL.Autocomplete({
        "input": "endId",
        "location": map
    });
//鼠标放在下拉列表上的事件
    acEnd.addEventListener("onhighlight", function (e) {
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchEndResultPanel").innerHTML = str;
    });


    var myValue;
//鼠标点击下拉列表后的事件
    acStart.addEventListener("onconfirm", function (e) {
        var _value = e.item.value;
        myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
        G("searchStartResultPanel").innerHTML = "onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

        setPlaceStart();
    });

//鼠标点击下拉列表后的事件
    acEnd.addEventListener("onconfirm", function (e) {
        var _value = e.item.value;
        myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
        G("searchEndResultPanel").innerHTML = "onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

        setPlaceEnd();
    });

    function setPlaceStart() {
        //清除地图上所有覆盖物
        map.clearOverlays();

        function myFunStart() {
            //获取第一个智能搜索的结果
            var pp = local.getResults().getPoi(0).point;
            // 获取搜索结果的详细地址
            window.alert(local.getResults().getPoi(0).address)
            let address = local.getResults().getPoi(0).address;
            // 将获取到的地址填充到表单
            window.document.getElementById("addressStart").value = address
            map.centerAndZoom(pp, 18);
            //添加标注
            map.addOverlay(new BMapGL.Marker(pp));
        }

        //智能搜索
        var local = new BMapGL.LocalSearch(map, {
            onSearchComplete: myFunStart()
        });
        local.search(myValue);
    }

    function setPlaceEnd() {
        //清除地图上所有覆盖物
        map.clearOverlays();

        function myFun() {
            //获取第一个智能搜索的结果
            var pp = local.getResults().getPoi(0).point;
            // 获取搜索结果的详细地址
            window.alert(local.getResults().getPoi(0).address)
            let address = local.getResults().getPoi(0).address;
            // 将获取到的地址填充到表单
            window.document.getElementById("addressEnd").value = address
            map.centerAndZoom(pp, 18);
            //添加标注
            map.addOverlay(new BMapGL.Marker(pp));
        }

        //智能搜索
        var local = new BMapGL.LocalSearch(map, {
            onSearchComplete: myFun
        });
        local.search(myValue);
    }


})(jQuery);
