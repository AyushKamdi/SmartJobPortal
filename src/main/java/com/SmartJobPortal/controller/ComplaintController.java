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

   
    @PostMapping("/submit-complaint")
    public String submitComplaint(@ModelAttribute Complaint complaint, Principal principal) {
        complaint.setSubmittedBy(principal.getName());
        complaintRepository.save(complaint);
        return "redirect:/dashboard.html";
    }


    @GetMapping("/api/my-complaints")
    @ResponseBody
    public List<Complaint> getMyComplaints(Principal principal) {
       
        return complaintRepository.findBySubmittedBy(principal.getName());
    }
}
