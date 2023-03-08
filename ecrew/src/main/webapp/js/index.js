// 控制元素的显示和隐藏
$(function () {
    //封装好的查询一级分类的方法
    firstSort();
    secondSort();
    search_goods();
    pageSize();
})
// function zhanshi(index) {
//     //发送ajax请求，拿到三级分类
//     $.get('/indexInfo/thirdSort',{},function (data) {
//         //循环data中的数据，添加li标签
//         var lis = $("#second").html();
//         for (let i = 0; i < data.length; i++) {
//             lis += '<li role="presentation"  class="yincang1"><a href="#">Men\'s Shoes</a></li>\n' +
//                 ' <li role="presentation"  class="yincang1"><a href="#">Women\'s Shoes</a></li>';
//         }
//         //添加完成之后，将所有的li标签，添加在ul后面
//         $("#second").html(lis);
//
//     });
//     // $(".yincang").toggleClass("hidden");
//     $(".yincang1").toggle("fast");
// };
//封装拿到一级分类的ajax请求
function firstSort(){
    //发送ajax请求，拿到一级分类
    $.get('/indexInfo/firstSort',{},function (data) {
        var lis = '';
        //循环data中的数据，添加li标签
        for (let i = 0; i < data.length; i++) {
            lis += '<li role="presentation" class="Fmenu" ><a href="#">'+data[i].name+'</a></li>';
        }
        //添加完成之后，将所有的li标签，添加在ul后面
        $("#first").html(lis);
        //当所有页面渲染完成之后，给索引为0的li标签，添加高亮
        $("#first > li").eq(0).addClass("active");
        //当所有的渲染都完成之后，调用点击切换高亮的方法，
        addAct();
    });
}
//封装拿到二级分类的ajax请求
function secondSort(sid) {
    //发送ajax请求，拿到二级分类
    $.get('/indexInfo/secondSort',{"sid":sid},function (data) {
        //循环data中的数据，添加li标签
        var lis = '';
        for (let i = 1; i <= data.length; i++) {
                lis += '<li  role="presentation" class="Smenu">\n' +
                    '                    <a id="show1" href="#">'+data[i-1].name+'\n' +
                    '                        <span class="caret sanjiao"></span>\n' +
                    '                    </a>\n' +
                    '                </li>  ';
        }
        //添加完成之后，将所有的li标签，添加在ul后面
        $("#second").html(lis);
        //页面上的所有内容渲染完毕之后，第一次加载页面的时候，将第一个li高亮
        $("#second > li").eq(0).addClass("active");
        //调用添加高亮的方法，当我们点击的时候做出相应
        addActive();
    });

}
//封装拿到商品和分页的ajax请求
function commodity(gid = 1,currentPage,pageSize) {
    //发送ajax请求，拿到商品信息
    $.get('/indexInfo/indexPagination',{"gid":gid,"currentPage":currentPage,"pageSize":pageSize},function (data) {
        //循环data中的数据，添加li标签
        var lis = '';
         for (let i = 0; i < data.list.length; i++) {
             lis+=`<li class="goods-detail" onclick="detail(${data.list[i].id});">
                    <img style="width: 275px; height: 290px" src="${data.list[i].pics[0].pic}">
                    <div style="display: none" id="hidden">${data.list[i].id}</div>
                    <div class="price clearfix">
                        <span>${data.list[i].price}</span>
                        <span><span style="color: red; font-size: 13px; font-weight: 400" >${data.list[i].payed}</span> + people pay</span>
                    </div>
                    <span class="description"><span>${data.list[i].title.substring(0,5)}</span>${data.list[i].title}</span>
                </li>`;
         }
         $("#count").html(data.totalCount);
         $("#page").html(data.totalPage);
         // 定义一个begin，作为分页条的起始页，可以用来判断如果当前页数为1，点击上一页的时候就重新跳转到1
         var begin = data.currentPage - 1;
         if (begin < 1){
             begin = 1;
         }
         var pagan = `<li>
                             <a href="javascript:commodity(${gid},1,${data.pageSize})" aria-label="Previous">
                                <span aria-hidden="true">首页</span>
                             </a>
                         </li><li>
                             <a href="javascript:commodity(${gid},${begin},${data.pageSize})" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                             </a>
                         </li>`;
         //优化一下导航条，做一个总共七条，前三，后四的导航条
        var start ;//循环遍历的初始值
        var finish ; //循环遍历的结束值
        if (data.totalPage < 7){
            // 如果总页数小于7，那首页就为1，末页就为总页数
            start = 1;
            finish = data.totalPage;
        } else {
            // 如果总页数大于7，那首页就等于当前页-3，末页就等于当前页+3
            start = data.currentPage - 3;
            finish = data.currentPage + 3;
            //特殊情况我们也需要判断一下，如果第一页小于一，就需要重置
            if (start < 1){
                start = 1;
                finish = start + 6;
            }
            //如果最后一页，也就是当前页 +3，如果大于总页数的话，也需要重置
            if (finish > data.totalPage){
                start = data.totalPage - 6;
                finish = data.totalPage;
            }
        }

        for (let i = start; i <= finish; i++) {
            if (data.currentPage == i){
                pagan += ` <li class="active"><a  href="javascript:commodity(${gid},${i},${data.pageSize})">${i}</a></li>`;
            } else {
                pagan += ` <li><a href="javascript:commodity(${gid},${i},${data.pageSize})">${i}</a></li>`;
            }

        }
        // 定义一个end，作为分页条的结束页，可以用来判断如果当前页数为页面总页数是，点击下一页的时候就重新跳转到总页数页
        var end = data.currentPage + 1;
        if (end >= data.totalPage){
            end = data.totalPage;
        }
        pagan += `<li>
                       <a href="javascript:commodity(${gid},${end},${data.pageSize})" aria-label="Next">
                         <span aria-hidden="true">&raquo;</span>
                         </a>
                        </li>
                            <li>
                            <a href="javascript:commodity(${gid},${data.totalPage},${data.pageSize})" aria-label="Next">
                                <span aria-hidden="true">末页</span>
                            </a>
                        </li>`
        //添加完成之后，将所有的li标签，添加在ul后面
         $("#commodity").html(lis);
         $("#pagan").html(pagan);
    });
}

// 发送ajax请求，进行商品查询
//拿到用户从页面上输入的数据
function search_goods(gid = 1,currentPage,pageSize,search) {
    $("#search_form").submit(function (){
        //表单的提交按钮中，return false，表单不提交
        return false;
    });
        //拿到用户从页面上输入的数据
        search = $("#search").val();
    $.post("indexInfo/search",{"search":search,gid,currentPage,pageSize},function (data) {
        if (data.list.length == 0){
            alert("抱歉,没有这个类型的数据")
        }
        //循环data中的数据，添加li标签
        var lis = '';
        for (let i = 0; i < data.list.length; i++) {
            lis+=`<li class="goods-detail" onclick="detail(${data.list[i].id});">
                    <img style="width: 275px; height: 290px" src="${data.list[i].pics[0].pic}">
                    <div style="display: none" id="hidden">${data.list[i].id}</div>
                    <div class="price clearfix">
                        <span>${data.list[i].price}</span>
                        <span><span style="color: red; font-size: 13px; font-weight: 400" >${data.list[i].payed}</span> + people pay</span>
                    </div>
                    <span class="description"><span>${data.list[i].title.substring(0,5)}</span>${data.list[i].title}</span>
                </li>`;
        }
        $("#count").html(data.totalCount);
        $("#page").html(data.totalPage);
        var begin = data.currentPage - 1;
        if (begin < 1){
            begin = 1;
        }
        var pagan = `<li>
                             <a href="javascript:search_goods(${gid},1,${data.pageSize},'${search}')" aria-label="Previous">
                                <span aria-hidden="true">首页</span>
                             </a>
                         </li><li>
                             <a href="javascript:search_goods(${gid},${begin},${data.pageSize},'${search}')" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                             </a>
                         </li>`;
        //优化一下导航条，做一个总共七条，前三，后四的导航条
        var start ;//循环遍历的初始值
        var finish ; //循环遍历的结束值
        if (data.totalPage < 7){
            start = 1;
            finish = data.totalPage;
        } else {
            start = data.currentPage - 3;
            finish = data.currentPage + 3;
            //如果第一页小于一，就需要重置
            if (start < 1){
                start = 1;
                finish = start + 6;
            }
            //如果最后一页，也就是当前页 +3，如果大于总页数的话，也需要重置
            if (finish > data.totalPage){
                start = data.totalPage - 6;
                finish = data.totalPage;
            }
        }

        for (let i = start; i <= finish; i++) {
            if (data.currentPage == i){
                pagan += ` <li class="active"><a  href="javascript:search_goods(${gid},${i},${data.pageSize},'${search}')">${i}</a></li>`;
            } else {
                pagan += ` <li><a href="javascript:search_goods(${gid},${i},${data.pageSize},'${search}')">${i}</a></li>`;
            }

        }
        var end = data.currentPage + 1;
        if (end >= data.totalPage){
            end = data.totalPage;
        }
        pagan += `<li>
                       <a href="javascript:search_goods(${gid},${end},${data.pageSize},'${search}')" aria-label="Next">
                         <span aria-hidden="true">&raquo;</span>
                         </a>
                        </li>
                            <li>
                            <a href="javascript:search_goods(${gid},${data.totalPage},${data.pageSize},'${search}')" aria-label="Next">
                                <span aria-hidden="true">末页</span>
                            </a>
                        </li>`
        //添加完成之后，将所有的li标签，添加在ul后面
        $("#commodity").html(lis);
        $("#pagan").html(pagan);
    })
};

function addAct(){
    var ins ;
    //获得li标签的点击事件，
    $(".Fmenu").click(function () {
        // 拿到点击的li的索引
         ins = ( $(this).index() + 1 );
         //给点击的这个li添加高亮，
        $("#first > li").eq(ins -1).addClass("active");
        //给其他的非点击的li删除高亮
        $("#first > li").not( $("#first li").eq(ins -1)).removeClass("active");
        //在给一级添加高亮的同时，通过ajax拿二级分类的数据
        secondSort(ins);
    });
}
function addActive(){
    var lis;
    // 拿到二级分类li标签，获得它的点击事件
    $(".Smenu").click(function (){
        //在点击事件中，拿到当前点击项的索引,用来当作参数传递，查询三级分类
        lis = $(this).index() + 1;
        //将点击的项设置为高亮
        $("#second > li").eq(lis - 1).addClass("active");
        //将除点击项之外的所有项都设置为非高亮
        $("#second > li").not( $("#second > li").eq(lis - 1) ).removeClass("active");
        //调用请求商品的方法，在次商品信息
        // commodity(lis,null,null);
        //拿到页面上的pageSize
        let $pageSize = $("#select").val();
        search_goods(lis,null,$pageSize,null);
    });
}
function pageSize() {
    $("#select").blur(function (){
        // commodity(null,null,$("#select").val());
        // 获取gid，每项二级分类的标识
        var index = $(this).parent().parent().prev().prev().find(".second-sort").children().find(".active").index() + 1;
        search_goods(index,null,$(this).val(),$("#search").val());
    });
}

//点击商品，跳转到商品详情页的方法
function detail(id) {
    //跳转到商品的详情页，在路径后面拼接参数
    location.href = "/detail.html?id="+id+""
}