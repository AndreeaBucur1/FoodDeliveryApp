package Classes;

public class Account {
    private int accountId;
    private String email;
    private String password;

    public Account(int accountId, String email, String password) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
