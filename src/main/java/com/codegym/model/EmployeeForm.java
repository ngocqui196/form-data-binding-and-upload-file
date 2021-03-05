package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeForm {
    private int id;
    private String name;
    private String address;
    private MultipartFile avatar;

    public EmployeeForm() {
    }

    public EmployeeForm(int id, String name, String address,MultipartFile avatar) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}

