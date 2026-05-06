package com.SmartJobPortal.repository;

import com.SmartJobPortal.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    
    // This magic line lets us fetch all complaints submitted by a specific user!
    List<Complaint> findBySubmittedBy(String submittedBy);
}