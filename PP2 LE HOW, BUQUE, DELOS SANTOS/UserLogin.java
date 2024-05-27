public class UserLogin {
    private String schoolID;
    private String password;

    public UserLogin(String schoolID, String password) {
        this.schoolID = schoolID;
        this.password = password;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return (schoolID.equals("2023-00770") && password.equals("123")) ||
               (schoolID.equals("2023-00797") && password.equals("123")) ||
               (schoolID.equals("2023-01335") && password.equals("123"));
    }
}
