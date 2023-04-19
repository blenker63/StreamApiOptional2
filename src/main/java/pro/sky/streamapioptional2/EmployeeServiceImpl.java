package pro.sky.streamapioptional2;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {
    private static int counter = 0;
    private int number = 10;

    //    private final Map<String, Employee> employeeData = new HashMap<>();
    Map<String, Employee> employeeData = new HashMap<>(Map.of(
            "ИвановИванИванович",
            new Employee(
                    "Иванов", "Иван", "Иванович", 1, 11_000),
            "ПетровПетрПетрович",
            new Employee("Петров", "Петр", "Петрович", 2, 15000),
            "СидоровСидорСидорович",
            new Employee("Сидоров", "Сидор", "Сидорович", 3, 13000),
            "ВасильевВасилийИванович",
            new Employee("Васильев", "Василий", "Иванович", 4, 16000),
            "СоловьевСтепанВасильевич",
            new Employee("Соловьев", "Степан", "Васильевич", 2, 22000),
            "СтепановГригорийМихайлович",
            new Employee("Степанов", "Григорий", "Михайлович", 5, 17000)
    ));

    public String startWork() {
        return "<b>Добро пожаловать</b>";

    }

    public Employee addEmployee(String surname, String name, String patronymic, int department, int salary) {

        var kay = surname + name + patronymic;
        var employee = new Employee(surname, name, patronymic, department, salary);
        if (counter >= number) {
            throw new EmployeeStoragelsFullException("Список переполнен");
        }
        if (employeeData.containsKey(kay)) {
            throw new EmployeeAlreadyAddedException(kay + " - такой сотрудник уже есть");

        }
        employeeData.put(employee.getFullName(), employee);
        counter++;
        System.out.println("Добавлен сотрудник - " + employee);
        return employee;
    }


    public Employee removeEmployee(String surname, String name, String patronymic) {
        var kay = surname + name + patronymic;
        var resultRemove = employeeData.remove(kay);
        if (resultRemove == null) {
            throw new EmployeeNotFoundException(kay + " - Сотрудник не найден");
        }
        System.out.println("Сотрудник удален - " + resultRemove);
        return resultRemove;
    }

    public Employee getEmployee(String surname, String name, String patronymic) {
        var kay = surname + name + patronymic;
        var resultGet = employeeData.get(kay);
        if (resultGet == null) {
            throw new EmployeeNotFoundException(kay + " - Сотрудник не найден");
        }
        System.out.println("Сотрудник найден -  " + resultGet);
        return resultGet;
    }


    public String printAllEmployee() {
        for (Employee value : employeeData.values()) {
            System.out.println(value);
        }
        return null;
    }

    public int getCurrentSize() {
        return counter;
    }

    public int sumSalary() {
        int sumSalary = 0;
        for (Employee value : employeeData.values()) {
            sumSalary = sumSalary + value.getSalary();
//            return sumSalary;
        }
        return sumSalary;
    }

//    public Optional<Employee> employeeDepartment() {
//
//    }

    public Optional<Employee> employeeMaxSalary() {
        return employeeData.values().stream()
                .max(Comparator.comparing(employeeData -> employeeData.getSalary()));
    }


    public Optional<Employee> employeeMinSalary() {
        return employeeData.values().stream()
                .min(Comparator.comparing(employeeData -> employeeData.getSalary()));
    }

    public String averageSalary() {
        float average = (float) sumSalary() / counter;
        String averageSalary = String.format("%.2f", average);
        return averageSalary;
    }

    public void employeeList() {
        int id = 0;
        for (Employee value : employeeData.values()) {
            id++;
            System.out.println("№ п/п " + id + "  " + value.getSurname() + "  " + value.getName() + "  "
                    + value.getPatronymic() + ";");
        }
    }
}
