package com.project.service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.model.Columns;
import com.project.model.EmailDetails;
import com.project.repository.ColumnsRepo;
import com.project.repository.RowRepo;

@Service
@Component
public class EmailService {
	
	
	@Autowired private JavaMailSender javaMailSender;
	@Autowired RowRepo rowrepo;
	@Autowired ColumnsRepo columnrepo;
	 
    @Value("${spring.mail.username}") private String sender;
    
    public Boolean sendTableMail(String key,Long tableId,EmailDetails details) {
    	 System.out.println("//sendTableMail//");
    	Boolean status=false;
    	
    	try {
			List<Columns>columns=columnrepo.getColumnByTableIdAndType(tableId);
			List<Columns>Allcolumns=columnrepo.getColumnByTableIdForTable(tableId);
			List<String> reMsg = new ArrayList<String>();
			Columns col=columns.get(0);
			List<Long> list = new ArrayList<Long>();
			for (String s : key.split(","))
			    list.add(Long.parseLong(s));
			int index=0;
			for(Long rowKey:list) {
				JSONObject row=rowrepo.findByrowKey(rowKey).getRows();
				String recipient=(String) row.get(col.getField());
				details.setRecipient(recipient);
				
				// Replace Msg Body............
				// String msgBody =;
				 for(int j=0;j<list.size();j++) {
					 reMsg.add( details.getMsgBody());
				 }
				 
				String newMsgBody=replaceMsgBody(reMsg, row, Allcolumns,index);
				details.setMsgBody(newMsgBody);
				
				//............................
				
				status= sendSimpleMail(details);
				index++;
			
			  
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status=false;
		}
		
		//return true;
		return status;
    	
    	
    }
    
    public String replaceMsgBody(List<String> reMsg,JSONObject row,List<Columns>Allcolumns,int index) {
    	
       	String replaceMsg=reMsg.get(index);
    	System.out.println("msg Body +>"+replaceMsg);
    
       for(Columns suggestion:Allcolumns) {
			
			replaceMsg=replaceMsg.replaceAll("@"+suggestion.getField(),(String) row.get(suggestion.getField()));		
		}
		System.out.println("Replaced new Msg==> "+replaceMsg);
		return replaceMsg;
		
		
    	
    	
    }
    // Method 1
    // To send a simple email
    public Boolean sendSimpleMail(EmailDetails details)
    {
 
        // Try block to check for exceptions
        try {
 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            //return "Mail Sent Successfully...";
            return true;
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
           // return "Error while Sending Mail";
        	 return false;
        }
    }
 
    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            // Adding the attachment
            FileSystemResource file
                = new FileSystemResource(
                    new File(details.getAttachment()));
 
            mimeMessageHelper.addAttachment(
                file.getFilename(), file);
 
            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
 
        // Catch block to handle MessagingException
        catch (MessagingException e) {
 
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
    
   

}
