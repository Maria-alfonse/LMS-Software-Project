package com.example.project.service;

import com.example.project.model.Admin;

public interface AdminService {
    Boolean addAdmin(Admin admin);

    Boolean deleteAdmin(int id);

    Admin getAdmin(int id);
}
