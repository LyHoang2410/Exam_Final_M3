package com.example.examfinal.DAO;

import com.example.examfinal.DAO.connection.MyConnection;
import com.example.examfinal.model.Department;
import com.example.examfinal.model.Employee;
import com.example.examfinal.service.DepartmentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final Connection connection;
    private final DepartmentService departmentService = DepartmentService.getInstance();
    private final String SELECT_ALL = "select * from employee;";
    private final String SELECT_BY_ID = "select * from employee where id = ?;";
    private final String INSERT_INTO = "insert into employee(name,email,address,phone, salary, d_id) value (?,?,?,?,?,?);";
    private final String UPDATE_BY_ID = "update employee set name = ?,email=?,address=?,phone=?,salary=?,d_id=? where id = ?;";
    private final String DELETE_BY_ID = "delete from employee where id = ?";
    private final String SEARCH_BY_NAME = "select * from employee where name like ?;";

    public EmployeeDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Employee> findALl() {
        List<Employee> productList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            convertResultSetToList(productList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Employee findById(int id){
        Employee employee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String names = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("adress");
                String phone = resultSet.getString("phone");
                Double salary = resultSet.getDouble("salary");
                int departmentId = resultSet.getInt("c_id");
                Department department = departmentService.getById(departmentId);
                employee = new Employee(id, names, email, address, phone, salary, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void addEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
            preparedStatement.setLong(7, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> searchByName(String name) {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            convertResultSetToList(employeeList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void convertResultSetToList(List<Employee> employeeList, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            Double salary = resultSet.getDouble("salary");
            int departmentId = resultSet.getInt("d_id");
            Department department = departmentService.getById(departmentId);
            Employee employee = new Employee(id, name, email, address, phone, salary, department);
            employeeList.add(employee);
        }
    }
}
