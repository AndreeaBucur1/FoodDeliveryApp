package Classes;

public class Employee {

    private int employeeId;
    private String lastName;
    private String firstName;
    private int age;
    private String email;

    public Employee(int employeeId, String lastName, String firstName, int age, String email)
    {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.email = email;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "Id employee : " + employeeId + '\n'+
                "Last name : " + lastName + '\n' +
                "First name : " + firstName + '\n' +
                "Age : " + age + '\n' +
                "Email : " + email ;
    }
}
