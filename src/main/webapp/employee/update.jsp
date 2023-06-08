<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/8/2023
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <h1 style="text-align: center">Form update employee</h1>
    <form style="width: 600px; margin: auto"
          action="/employee?action=update&&id=${e.id}" method="post">

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name"
                   placeholder="Enter name" value="${e.name}">
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="text" class="form-control" id="price" name="price"
                   placeholder="Enter price" value="${e.age}">
        </div>

        <div>
            <label for="quantity" class="form-label">Quantity</label>
            <input type="text" class="form-control" id="quantity" name="quantity"
                   placeholder="Enter quantity" value="${e.date}">
        </div>

        <div class="mb-3">
            <label for="department" class="form-label">Department</label>
            <select class="form-select" id="department" name="department"
                    aria-label="Default select example">
                <option selected>--- Choice department ---</option>
                <c:forEach items="${department}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
        </div>


        <div class="mb-3">
            <button class="btn btn-primary" type="submit">Update</button>
            <a class="btn btn-secondary" href="/employees">Back to home</a>
        </div>

    </form>
</div>

</body>
</html>