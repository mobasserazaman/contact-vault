package com.mobasserazaman;
import java.time.LocalDate;

public class Contact {

    private int id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthday;

    public Contact(String name, String email, String phone, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }

    public Contact(int id, String name, String email, String phone, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getBirthday() { return birthday; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Email: %s | Phone: %s | Birthday: %s",
            id, name, email, phone, birthday != null ? birthday.toString() : "N/A");
    } 
}
