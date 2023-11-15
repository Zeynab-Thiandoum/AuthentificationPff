package com.alibou.security.validerPaysan;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EnvoieMail {

    @Autowired
    private ValiderService validerService;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EnvoieMail(JavaMailSender javaMailSender, ValiderService validerService) {
        this.javaMailSender = javaMailSender;
        this.validerService = validerService;
    }

    @Async
    public void sendProjectValidationEmail(String adminEmail, String projectName) {
        String subject = "Validation de projet en attente";
        String content = "Bonjour Admin,\n\nLe projet '" + projectName + "' est en attente de validation. Veuillez cliquer sur le lien ci-dessous pour effectuer la validation :\n\nhttp://votre-site.com/validation-projet/" + projectName;

        sendEmail(adminEmail, subject, content);
    }

    public ValiderPaysan.StatusProjet validerProjett(ValiderPaysan validerPaysan) {
        return ValiderPaysan.StatusProjet.valider;
    }

    public void validerProjet(int paysanId) {
        // Récupérer le paysan par son ID (assurez-vous d'avoir une méthode pour cela dans votre service ou votre repository)
        ValiderPaysan validerPaysan = validerService.getPaysanById(paysanId);
        System.out.println(paysanId);
        System.out.println(validerService.getPaysanById(paysanId));

        // Vérifier si le paysan a été trouvé
        if (validerPaysan != null) {
            // Mettre à jour le statut du paysan
            validerPaysan.setStatus(ValiderPaysan.StatusProjet.valider);

            // Sauvegarder les modifications dans la base de données (si nécessaire)
             validerService.demandeProjet(validerPaysan);

        } else {
            // Gérer le cas où le paysan n'a pas été trouvé
            System.out.println("le status n'a pas changer");
//            return ValiderPaysan.StatusProjet.inconnu; // Ou une autre valeur appropriée
//        }
        }
    }

    private void sendEmail(String to, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content, false);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            // Gérer les exceptions liées à l'envoi de l'e-mail
            e.printStackTrace();
        }
    }
}
