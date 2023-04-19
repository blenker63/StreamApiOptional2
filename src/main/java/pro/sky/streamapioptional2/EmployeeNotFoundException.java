package pro.sky.streamapioptional2;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String massage) {
        super(massage);
    }
}
