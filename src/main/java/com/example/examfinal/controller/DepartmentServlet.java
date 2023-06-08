package com.example.examfinal.controller;

import com.example.examfinal.service.DepartmentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DepartmentServlet", value = "/departments")
public class DepartmentServlet extends HttpServlet {
    private final DepartmentService departmentService = DepartmentService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createGet(request, response);

                break;
            case "update":
                updateGet(request, response);

                break;
            case "delete":
                delete(request, response);

                break;
            default:
                findAll(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(request, response);

                break;
            case "update":
                updatePost(request, response);

                break;
        }
    }
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/department/home.jsp");
        request.setAttribute("department", departmentService.showAll());
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/department/create.jsp");
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        departmentService.save(request);
        response.sendRedirect("/department");

    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (departmentService.checkById(id)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/department/update.jsp");
            request.setAttribute("department", departmentService.getById(id));
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (departmentService.checkById(id)) {
            departmentService.save(request);
            response.sendRedirect("/department");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        if (departmentService.checkById(id)) {
            departmentService.deleteById(id);
            response.sendRedirect("/department");

        } else {
            response.sendRedirect("/404.jsp");
        }
    }

}
