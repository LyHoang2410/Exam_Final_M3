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
    <h1 style="text-align: center">Create Employee Form</h1>
    <form style="width: 600px; margin: auto" action="/employees?action=create" method="post">

        <div class="mb-3">
            <label for="name" class="form-label">NAME</label>
            <input type="text" class="form-control" id="name" name="name"><br>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">EMAIL</label>
            <input type="text" class="form-control" id="email" name="email"><br>
        </div>

        <div class="mb-3">
            <label for="address">ADDRESS</label>
            <input type="text" class="form-control" id="address" name="address"><br>
        </div>

        <div class="mb-3">
            <label for="phone">PHONE</label>
            <input type="text" class="form-control" id="phone" name="phone"><br>
        </div>

        <div class="mb-3">
            <label for="salary">SALARY</label>
            <input type="text" class="form-control" id="salary" name="salary"><br>
        </div>

        <div>
            <label for="department" class="form-label">DEPARTMENT</label>
            <select class="form-select" id="department" name="department"
                    aria-label="Default select example">
                <option selected>--- Choice category ---</option>
                <c:forEach items="${department}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <button class="btn btn-primary" type="submit">Create</button>
            <a class="btn btn-secondary" href="/employees">Back to Home</a>
        </div>
    </form>
</div>
</body>
</html>
