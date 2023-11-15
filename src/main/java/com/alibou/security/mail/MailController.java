package com.alibou.security.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send/{mail}")
    public String envoieMail(@PathVariable String mail, @RequestBody Mail mailStructure){

        mailService.envoieMail(mail, mailStructure);

        return "successfully sent the mail ";
    }

}
