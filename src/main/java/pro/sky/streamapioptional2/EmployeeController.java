package pro.sky.streamapioptional2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee/departments")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping
    public String startWork() {
        return employeeServiceImpl.startWork();
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam(value = "surname", required = false) String surname,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "patronymic", required = false) String patronymic,
                              @RequestParam(value = "department", required = false) int department,
                              @RequestParam(value = "salary", required = false) int salary) {
        if (surname == null || name == null || patronymic == null || department < 1 || salary < 1)
            throw new RuntimeException("Данные введены не полностью");
        else
            return "Добавлен сотрудник: " + employeeServiceImpl.addEmployee(surname, name, patronymic, department, salary);
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam(required = false, value = "surname") String surname,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "patronymic", required = false) String patronymic) {
        if (surname == null || name == null || patronymic == null)
            throw new RuntimeException("Данные введены не полностью");
        else
            return "Удален сотрудник - " + employeeServiceImpl.removeEmployee(surname, name, patronymic);

    }

    @GetMapping(path = "/get")
    public String getEmployee(@RequestParam(required = false, value = "surname") String surname,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "patronymic", required = false) String patronymic) {
        return "Найден сотрудник: " + employeeServiceImpl.getEmployee(surname, name, patronymic);
    }

    @GetMapping(path = "/max-salary")

    public String employeeMaxSalary(@RequestParam(required = false, value = "departmentID") Integer department) {
        if (department == null)
            throw new RuntimeException("Данные введены не полностью");
        else
        return "Сотрудник департамента - " + department + " с максимальной зарплатой - " + String.valueOf(employeeServiceImpl.employeeMaxSalary(department));
    }

    @GetMapping(path = "/min-salary")
    public String employeeMinSalary(@RequestParam(required = false, value = "departmentID") Integer department) {
        return "Сотрудник департамента - " + department + " с минимальной зарплатой - " + String.valueOf(employeeServiceImpl.employeeMinSalary(department));
    }

    @GetMapping(path = "/all")
    public String employeePrint(@RequestParam(required = false, value = "departmentID") Integer department) {
        if (department == null) {
            return employeeServiceImpl.employeePrintAll().toString();
        } else {
            return employeeServiceImpl.employeePrintDepartment(department).toString();
        }
    }
}

