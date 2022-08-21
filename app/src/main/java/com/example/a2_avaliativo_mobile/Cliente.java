package com.example.a2_avaliativo_mobile;

public class Cliente {

    String id;
    String firstName;
    String lastName;
    String email;
    String avatar;

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "Nome: " + firstName + ".\nSobrenome: " + lastName + ".\nE-mail: " + email;
    }
}
