package com.example.examfinal.service;

import com.example.examfinal.DAO.EmployeeDAO;
import com.example.examfinal.model.Department;
import com.example.examfinal.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final DepartmentService departmentService;
    private static EmployeeService employeeService;

    private EmployeeService() {
        employeeDAO = new EmployeeDAO();
        departmentService = DepartmentService.getInstance();
    }

    public static EmployeeService getInstance() {
        if (employeeService == null) {
            employeeService = new EmployeeService();
        }
        return employeeService;
    }

    public List<Employee> getEmployee() {
        return employeeDAO.findALl();
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentId = Integer.parseInt(request.getParameter("department"));
        Department department = departmentService.getById(departmentId);
        if (id != null) {
            int idUpdate = Integer.parseInt(id);
            employeeDAO.updateEmployee(new Employee(idUpdate, name, email, address, phone, salary, department));
        } else {
            employeeDAO.addEmployee(new Employee(name, email, address, phone, salary, department));
        }
    }

    public Employee getById(int id) {
        return employeeDAO.findById(id);
    }

    public void deleteById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteById(id);
    }

    public List<Employee> searchByName(HttpServletRequest request) {
        String search = request.getParameter("search");
        return employeeDAO.searchByName(search);
    }

    public boolean checkById(int id) {
        Employee employee = employeeDAO.findById(id);
        return employee != null;
    }
}
