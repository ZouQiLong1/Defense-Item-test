<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>DASHMIN - Bootstrap Admin Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">

    <!-- Bootstrap的css文件 -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!--    bootstrap依赖jquery文件-->
    <script src="js/jquery-3.3.1/jquery-3.3.1/jquery-3.3.1.min.js"></script>

    <!--    bootstrap的js文件-->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            //删除选中的方法
            $("#delSelected").click(function () {
                //点击删除选中的时候，首先需要判断，用户有没有选中
                let $select = 0 ;  //定义一个变量，用来保存用户选中的条目数，也就是判断用户是否选中
                var choose = document.getElementsByClassName("delete");
                for (let i = 0; i < choose.length; i++) {
                    //如果有选中，数量直接+
                    if (choose[i].checked){
                        $select ++ ;
                        break; //只要有选中一条数据，就直接退出
                    }
                }
                //如果选中数量是否大于0
                if ($select > 0 ){
                    //用户有选中数
                    if (window.confirm("您确定要删除选中条目吗？")){
                        //用户点击确定，就提交表单
                        $("#form").submit();
                    }
                    //用户要是点击了不确定，就什么都不干
                }
                //要是用户选择的条数不大于0，也什么都不干
            });
            //全选和非全选的checkbox
            $("#all").click(function (){
                $(".delete").prop("checked",$("#all").prop("checked"));
            });
            //有一个未选中，就取消全选
            $(".delete").click(function () {
                notAll();
            });
            //新增用户
            $("#addUser").click(function (){
                location.href = "${pageContext.request.contextPath}/addUser.jsp"
            });
        });
        //删除用户数据的方法
        function deleteUser(id) {
            //用用户一个提示，让用户有更好的体验
            if (window.confirm("您确定要删除这条吗")){
                location.href = "${pageContext.request.contextPath}/deleteUserServlet?id="+id;
            }
            //如果用户点击取消，就不删除，不执行任何操作
        }
        //只要有一条数据没选中，就取消全选
        function notAll() {
            var choose = document.getElementsByClassName("delete");
            for (let i = 0; i < choose.length; i++) {
                //如果遍历的时候发现有一条没被选中，就将全选设置为非选中
                if (!choose[i].checked) {
                    $("#all").prop("checked", false);
                }
            }
        }
    </script>
</head>

<body>
    <div class="container-fluid position-relative bg-white d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Sidebar Start -->
        <div class="sidebar pe-4 pb-3">
            <nav class="navbar bg-light navbar-light">
                <a href="index.jsp" class="navbar-brand mx-4 mb-3">
                    <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>Ecrew</h3>
                </a>
                <div class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <img class="rounded-circle" src="${pageBean.list[0].manager.img}" alt="" style="width: 40px; height: 40px;">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                    <div class="ms-3">
                        <h6 class="mb-0" style="font-size: 18px;font-weight: 700">${pageBean.list[0].manager.username}</h6>
                    </div>
                </div>
                <div class="navbar-nav w-100">
                    <a href="${pageContext.request.contextPath}/indexInfoByPageServlet" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>用户管理</a>
                    <a href="${pageContext.request.contextPath}/goodsInfoByPageServlet" class="nav-item nav-link"><i class="fa fa-th me-2"></i>商品管理</a>
                    <a href="${pageContext.request.contextPath}/friendInfoByPageServlet" class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>盟友管理</a>
<%--                    <a href="manager.html" class="nav-item nav-link"><i class="fa fa-radiation me-2"></i>商家管理</a>--%>
                </div>
            </nav>
        </div>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                <a href="index.jsp" class="navbar-brand d-flex d-lg-none me-4">
                    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                </a>
                <a href="#" class="sidebar-toggler flex-shrink-0">
                    <i class="fa fa-bars"></i>
                </a>
                <form class="d-none d-md-flex ms-4" method="post" action="${pageContext.request.contextPath}/indexInfoByPageServlet">
                    账号：
                    <input class="form-control border-0" style="margin-left: 15px;" type="username" name="username" value="${condition.username[0]}">
                    <input class="form-control border-0" style="margin-left: 15px;" type="email" name="email" value="${condition.email[0]}">
                    <span style="display: inline-block; xmargin-left: 10px; margin-top: 8px;width: 200px; height: 40px; font-size: 16px;" >激活状态</span>
                    <select name="status" style="width: 50px; height: 30px;margin-top: 5px;margin-left: 15px;" id="">
                        <c:if test="${condition.status[0] == 'Y'}">
                            <option value="Y" selected>Y</option>
                            <option value="N">N</option>
                        </c:if>
                        <c:if test="${condition.status[0] != 'Y'}">
                            <option value="Y" >Y</option>
                            <option value="N" selected>N</option>
                        </c:if>
                    </select>
                    <button type="submit" style="margin-left: 15px!important;" class="btn btn-primary ms-2">Search</button>
                </form>
                <div class="navbar-nav align-items-center ms-auto">
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <img class="rounded-circle me-lg-2" src="${pageBean.list[0].manager.img}" alt="" style="width: 40px; height: 40px;">
                            <span class="d-none d-lg-inline-flex" style="font-size: 18px;font-weight: 700">${pageBean.list[0].manager.username}</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            <a href="#" class="dropdown-item">${pageBean.list[0].manager.identity == 0 ? '管理员' : '超级管理员'}</a>
                            <a href="#" class="dropdown-item">退出登录</a>
                        </div>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->
            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">用户列表</h6>
                        <button type="button" id="addUser" class="btn btn-primary ms-2" style="margin-right:-1300px">添加新用户</button>
                        <button type="button" id="delSelected" class="btn btn-primary ms-2">删除选中</button>
                    </div>
                    <div class="table-responsive">
                        <form id="form" action="${pageContext.request.contextPath}/deleteChooseServlet">
                        <table class="table table-striped text-start align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col"><input id="all" class="form-check-input" type="checkbox"></th>
                                    <th style="width: 50px;" scope="col">编号</th>
                                    <th scope="col">姓名</th>
                                    <th scope="col">账号</th>
                                    <th scope="col">密码</th>
                                    <th scope="col">是否可用</th>
                                    <th scope="col">邮箱</th>
                                    <th scope="col">激活码</th>
                                    <th scope="col">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageBean.list}" var="user" varStatus="s">
                                <tr>
                                    <td><input class="form-check-input delete" name="ids" type="checkbox" value="${user.id}"></td>
                                    <td style="display: none">${user.id}</td>
                                    <td>${s.count}</td>
                                    <td>${user.name}</td>
                                    <td>${user.username}</td>
                                    <td>${user.password}</td>
                                    <td>${user.status}</td>
                                    <td>${user.email}</td>
                                    <td></td>
<%--                                    注意:在jsp页面的跳转中,需要加上项目的虚拟路径--%>
                                    <td style="width: 150px;"><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/updateUserServlet?id=${user.id}">修改</a>
                                        <a class="btn btn-sm btn-primary" onclick="deleteUser(${user.id});" href="javascript:void(0)">删除</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </form>
                    </div>
                    <div>
                        注册用户数量 <span style="color: red">${pageBean.totalCount}</span>
                        总页数 <span style="color: red">${pageBean.totalPage}</span>
                    </div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination pagination-lg">
                            <c:if test="${pageBean.currentPage == 1}">
                                <li class="disabled">
                            </c:if>
                            <c:if test="${pageBean.currentPage != 1}">
                                 <li>
                            </c:if>

                                <a href="${pageContext.request.contextPath}/indexInfoByPageServlet?currentPage=${pageBean.currentPage - 1 }&email=${condition.email[0]}&status=${condition.status[0]}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${pageBean.totalPage}" var="i" >
                                <c:if test="${pageBean.currentPage == i}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/indexInfoByPageServlet?currentPage=${i}&email=${condition.email[0]}&status=${condition.status[0]}">${i}</a></li>
                                </c:if>
                                <c:if test="${pageBean.currentPage != i}">
                                    <li><a href="${pageContext.request.contextPath}/indexInfoByPageServlet?currentPage=${i}&email=${condition.email[0]}&status=${condition.status[0]}">${i}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${pageBean.currentPage == pageBean.totalPage}">
                                 <li class="disabled">
                            </c:if>
                            <c:if test="${pageBean.currentPage != pageBean.totalPage}">
                                <li>
                            </c:if>
                                <a href="${pageContext.request.contextPath}/indexInfoByPageServlet?currentPage=${pageBean.currentPage + 1 }&email=${condition.email[0]}&status=${condition.status[0]}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

            <!-- Footer Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light rounded-top p-4">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start">
                            &copy; <a href="#">Your Site Name</a>, All Right Reserved. 
                        </div>
                        <div class="col-12 col-sm-6 text-center text-sm-end">
                            <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                            Designed By <a href="https://htmlcodex.com">HTML Codex</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer End -->
        </div>
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/chart/chart.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>