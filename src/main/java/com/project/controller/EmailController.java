package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.EmailDetails;
import com.project.service.EmailService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/mail")
public class EmailController {
	
	
	@Autowired private EmailService emailService;
	 
    // Sending a simple Email
    @PostMapping("/sendMail")
    public Boolean
    sendMail(@RequestBody EmailDetails details)
    {
        Boolean status
            = emailService.sendSimpleMail(details);
 
        return status;
    }
    
    
    @PutMapping("/sendTableMail/{tableid}/{key}")
    public Boolean
    sendTableMail(@RequestBody EmailDetails details,@PathVariable("tableid") Long tableid,@PathVariable("key") String key)
    {
        Boolean status
            = emailService.sendTableMail(key, tableid, details);
 
        return status;
    }
    
 
    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
        @RequestBody EmailDetails details)
    {
        String status
            = emailService.sendMailWithAttachment(details);
 
        return status;
    }

}
