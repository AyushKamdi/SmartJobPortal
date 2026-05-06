package com.SmartJobPortal.controller;

import com.SmartJobPortal.model.Complaint;
import com.SmartJobPortal.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepository;

    // --- 1. THE SUBMIT METHOD (You already have this, it works perfectly!) ---
    @PostMapping("/submit-complaint")
    public String submitComplaint(@ModelAttribute Complaint complaint, Principal principal) {
        complaint.setSubmittedBy(principal.getName());
        complaintRepository.save(complaint);
        return "redirect:/dashboard.html";
    }

    // --- 2. THE NEW FETCH METHOD ---
    // This secretly sends the database data to your HTML page when it asks for it!
    @GetMapping("/api/my-complaints")
    @ResponseBody
    public List<Complaint> getMyComplaints(Principal principal) {
        // Find all complaints submitted by the logged-in user
        return complaintRepository.findBySubmittedBy(principal.getName());
    }
}