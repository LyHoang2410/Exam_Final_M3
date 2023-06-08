package com.example.examfinal.service;

import com.example.examfinal.DAO.DepartmentDAO;
import com.example.examfinal.model.Department;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DepartmentService {
private final DepartmentDAO departmentDAO;
private static DepartmentService departmentService;

    private DepartmentService() {
        departmentDAO = new DepartmentDAO();
    }

    public static DepartmentService getInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentService();
        }
        return departmentService;
    }

    public List<Department> showAll() {
        return departmentDAO.findAll();
    }

    public Department getById(int id) {
        return departmentDAO.findById(id);
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if (id != null) {
            int idUpdate = Integer.parseInt(id);
            departmentDAO.updateDepartment(new Department(idUpdate, name));
        } else {
            departmentDAO.addDepartment(new Department(name));
        }
    }
    public void deleteById(int id) {
        departmentDAO.deleteById(id);
    }

    public boolean checkById(int id) {
        Department department = departmentService.getById(id);
        return department != null;
    }
}
