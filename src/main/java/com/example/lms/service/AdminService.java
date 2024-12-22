package com.example.lms.service;

import com.example.lms.model.user_related.Admin;

import java.util.List;

public interface AdminService {

    //add
    Admin addAdmin(Admin admin);

    //display
    Admin getAdmin(Integer id);

    //delete
    void deleteAdmin(Integer id);


    void updateAdmin(Integer id,Admin admin);


    List<Admin>getAll();
}
