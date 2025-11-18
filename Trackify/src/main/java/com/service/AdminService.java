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

    public static class AdminNotFoundException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AdminNotFoundException(String message) {
            super(message);
        }
    }
 


    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Admin admin) {
        if (!adminRepository.existsById(admin.getId())) {
            throw new AdminNotFoundException("Admin with ID " + admin.getId() + " not found");
        }
        return adminRepository.save(admin);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin with ID " + id + " not found"));
    }


    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }


    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new AdminNotFoundException("Admin with ID " + id + " not found");
        }
        adminRepository.deleteById(id);
    }

    public boolean login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);

        if (admin == null) {
            throw new AdminNotFoundException("Admin with username '" + username + "' not found");
        }

        return admin.getPassword().equals(password);
    }
}

