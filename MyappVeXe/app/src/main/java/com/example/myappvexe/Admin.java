package com.example.myappvexe;

public class Admin extends BackActivity {
    private long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;

    public Admin(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFromStaff(Staff staff){
        if (staff !=null) {
            this.name = staff.getName();
            this.username = staff.getUsername();
            this.password = staff.getPassword();
            this.email = staff.getEmail();
            this.phone = staff.getPhone();
        }
    }

//    @Override
//    public String toString(){
//        return name + "\n" + email;
//    }
}
