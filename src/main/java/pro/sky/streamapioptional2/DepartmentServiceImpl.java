package pro.sky.streamapioptional2;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl {

    private EmployeeServiceImpl employeeServiceImpl;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    private final Map<String, Employee> employeeDataDep = new HashMap<>();

    public Optional<Employee> employeeMaxSalary(int department) {
        return employeeServiceImpl.employeeData.values().stream()
                .filter(employeeData -> employeeData.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary));
    }

    public Optional<Employee> employeeMinSalary(int department) {
        return employeeServiceImpl.employeeData.values().stream()
                .filter(employeeData -> employeeData.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary));
    }

    public List<Employee> employeePrintAll() {
        return employeeServiceImpl.employeeData.values().stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    public List<Employee> employeePrintDepartment(int department) {
        return employeeServiceImpl.employeeData.values().stream()
                .filter(employeeData -> employeeData.getDepartment() == department)
                .collect(Collectors.toList());
    }


}
