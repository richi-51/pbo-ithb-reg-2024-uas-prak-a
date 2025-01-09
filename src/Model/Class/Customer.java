package src.Model.Class;

public class Customer {
    private int id_customer;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String email;


    // Constructor
    public Customer(int id_customer, String password, String name, String address, String phone, String email) {
        this.id_customer = id_customer;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }


    // getter and setter
    public int getId_customer() {
        return id_customer;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
