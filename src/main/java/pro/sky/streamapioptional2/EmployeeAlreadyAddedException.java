package pro.sky.streamapioptional2;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException(String massage) {
        super(massage);
    }
}
