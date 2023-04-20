package pro.sky.streamapioptional2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentConroller {
    private final DepartmentServiceImpl departmentServiceImpl;

    public DepartmentConroller(DepartmentServiceImpl departmentServiceImpl) {
        this.departmentServiceImpl = departmentServiceImpl;
    }
    @GetMapping(path = "/max-salary")

    public String employeeMaxSalary(@RequestParam(required = false, value = "departmentID") Integer department) {
        if (department == null)
            throw new RuntimeException("Данные введены не полностью");
        else
            return "Сотрудник департамента - " + department + " с максимальной зарплатой - " + String.valueOf(departmentServiceImpl.employeeMaxSalary(department));
    }

    @GetMapping(path = "/min-salary")
    public String employeeMinSalary(@RequestParam(required = false, value = "departmentID") Integer department) {
        return "Сотрудник департамента - " + department + " с минимальной зарплатой - " + String.valueOf(departmentServiceImpl.employeeMinSalary(department));
    }

    @GetMapping(path = "/all")
    public String employeePrint(@RequestParam(required = false, value = "departmentID") Integer department) {
        if (department == null) {
            return departmentServiceImpl.employeePrintAll().toString();
        } else {
            return departmentServiceImpl.employeePrintDepartment(department).toString();
        }
    }
}
