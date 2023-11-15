package com.alibou.security.validerPaysan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValiderService {

    @Autowired
    private ValiderRepository validerRepository;

    public ValiderPaysan demandeProjet(ValiderPaysan demandeProjet){
        return validerRepository.save(demandeProjet);
    }

    public List<ValiderPaysan> listeProjetEn_attente(){
        return validerRepository.findAll();
    }

    public ValiderPaysan valider(ValiderPaysan validerPaysan){
        validerPaysan.setStatus(ValiderPaysan.StatusProjet.valider);
        return validerPaysan;
    }


    public ValiderPaysan getPaysanById(Integer id){
        Optional<ValiderPaysan> validerOptional = validerRepository.findById(id);
        System.out.println(validerOptional.orElse(null));
        return validerOptional.orElse(null);
    }
}
