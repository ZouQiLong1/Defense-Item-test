// 控制元素的显示和隐藏
$(function () {
    $("#show1").click(function () {
        // $(".yincang").toggleClass("hidden");
        $(".yincang1").toggle("fast");
    });
    $("#show2").click(function () {
        $(".yincang2").toggleClass("hidden");
        // $(".yincang2").toggle("normal");
    });
    $("#show3").click(function () {
        $(".yincang3").toggleClass("hidden");
        // $(".yincang3").toggle("normal");
    });
    $("#show4").click(function () {
        $(".yincang4").toggleClass("hidden");
        // $(".yincang4").toggle("normal");
    });
})