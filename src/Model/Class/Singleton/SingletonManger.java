package src.Model.Class.Singleton;

import src.Model.Class.Customer;

public class SingletonManger {
    private static SingletonManger instance;
    private Customer loggedInUser;

    private SingletonManger() {}

    public static SingletonManger getInstance() {
        if (instance == null) {
            instance = new SingletonManger();
        }
        return instance;
    }

    public Customer getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Customer loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
