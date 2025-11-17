package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Admin;
import com.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // ---------- Custom Exception Inside This Class ----------
    public static class AdminNotFoundException extends RuntimeException {
        public AdminNotFoundException(String message) {
            super(message);
        }
    }
    // --------------------------------------------------------

    // Save admin
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Update admin
    public Admin updateAdmin(Admin admin) {
        if (!adminRepository.existsById(admin.getId())) {
            throw new AdminNotFoundException("Admin with ID " + admin.getId() + " not found");
        }
        return adminRepository.save(admin);
    }

    // Get admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin with ID " + id + " not found"));
    }

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Delete admin
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new AdminNotFoundException("Admin with ID " + id + " not found");
        }
        adminRepository.deleteById(id);
    }

    // Login
    public boolean login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);

        if (admin == null) {
            throw new AdminNotFoundException("Admin with username '" + username + "' not found");
        }

        return admin.getPassword().equals(password);
    }
}

