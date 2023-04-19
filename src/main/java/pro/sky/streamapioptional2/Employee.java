package pro.sky.streamapioptional2;

public class Employee {
    private String surname;
    private String name;
    private String patronymic;
    private String fullName;
    private int department;
    private int salary;
    private static int counter = 0;


    public Employee(String surname, String name, String patronymic, int department, int salary) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.department = department;
        this.salary = salary;
        this.fullName = getSurname() + getName() + getPatronymic();
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getDepartment() {
        return this.department;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return "ФИО: '" + getSurname() + '\''
                + getName() + '\''
                + getPatronymic() + '\'' +
                ", отдел - " + getDepartment() +
                ", зарплата, рублей - " + getSalary() +
                ";";
    }
}
