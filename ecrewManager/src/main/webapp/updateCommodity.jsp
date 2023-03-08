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

  <!-- Favicon -->
  <link href="img/favicon.ico" rel="icon">

  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Libraries Stylesheet -->
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

  <!-- Customized Bootstrap Stylesheet -->
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="css/style.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid position-relative" style="width:800px ;">
  <div style="background-color:rgba(245,171,171,0.91); margin-top: 50px; padding: 20px 50px; border-radius: 15px">
    <h1 style="text-align:center;" >添加商品</h1>
    <form action="${pageContext.request.contextPath}/updateCommodityInfoServlet">
      <input type="text" class="form-control" style="display: none" name="id" value="${commodity.id}">
      <div class="form-group" style="margin-top: 20px;">
        <label for="title" style="color: #000; margin-bottom: 10px;">商品名</label>
        <input type="text" class="form-control" id="title" name="title" value="${commodity.title}">
      </div>
      <div class="form-group" style="margin-top: 20px;">
        <label for="description" style="color: #000; margin-bottom: 10px;">商品描述</label>
        <input type="text" class="form-control" id="description" name="description" value="${commodity.description}">
      </div>
      <div class="form-group" style="margin-top: 20px;">
        <label for="price" style="color: #000; margin-bottom: 10px;">价格</label>
        <input type="text" class="form-control" id="price" name="price" value="${commodity.price}">
      </div>
      <div class="form-group" style="margin-top: 20px;">
        <label for="number" style="color: #000; margin-bottom: 10px;">交易量</label>
        <input type="text" class="form-control" id="number" disabled value="${commodity.payed}">
      </div>
      <input style="width: 250px; margin-left: 200px; margin-top: 30px; height: 50px; background-color:#ff002f; color: #fff;border: none;border-radius: 5px" type="submit" value="提交">
    </form>
  </div>
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