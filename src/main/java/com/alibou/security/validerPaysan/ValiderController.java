package com.alibou.security.validerPaysan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demandeProjet")
public class ValiderController {

    @Autowired
    private ValiderService validerService;

    @Autowired
    private EnvoieMail envoieMail;

    @PostMapping("/ajout")
    public ValiderPaysan demnadeProjet(@RequestBody ValiderPaysan validerPaysan){
        return validerService.demandeProjet(validerPaysan);
    }

    @PostMapping("/valider")
    public void valider(ValiderPaysan validerPaysan){
        validerService.valider(validerPaysan);
    }


    @PostMapping("/validerStatus/{id}")
    public String validerStatus(@PathVariable int id){
        envoieMail.validerProjet(id);
        return "success";
    }

    @GetMapping("{id}")
    public ValiderPaysan findByID(@PathVariable int id){
        System.out.println(validerService.getPaysanById(id));
       return validerService.getPaysanById(id);
    }

    @PostMapping("/envoie-mail")
    public String envoieMail(@RequestBody String email,String nameProjet){
        envoieMail.sendProjectValidationEmail(email,nameProjet);
        return "Envoie reussi";
    }
}
