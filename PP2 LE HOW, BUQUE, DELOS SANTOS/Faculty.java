public class Faculty extends UserLogin {
    public Faculty(String facultyID, String password) {
        super(facultyID, password);
    }

    
    public boolean isAuthenticated() {
        // Check if facultyID and password match the specified credentials
        return getSchoolID().equals("123") && getPassword().equals("123");
    }

    
}