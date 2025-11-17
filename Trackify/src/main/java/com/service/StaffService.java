package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CourierDetails;
import com.entity.Staff;
import com.repository.CourierDetailsRepository;
import com.repository.StaffRepository;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CourierDetailsRepository courierDetailsRepository;
    public static class StaffNotFoundException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public StaffNotFoundException(Long id) {
            super("Staff not found with id: " + id);
        }
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
    }

    public Staff updateStaff(Long id, Staff updatedStaff) {
        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));

        existing.setName(updatedStaff.getName());
        existing.setLocation(updatedStaff.getLocation());
        existing.setPassword(updatedStaff.getPassword());

        return staffRepository.save(existing);
    }

    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new StaffNotFoundException(id);
        }
        staffRepository.deleteById(id);
    }
    public List<CourierDetails> getAssignedParcels(Long staffId) {
        return courierDetailsRepository.findByStaffId(staffId);
    }

}
