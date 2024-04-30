package br.com.alura.adopetstore.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnviadorEmail {

    private final JavaMailSender emailSender;

    public EnviadorEmail(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void enviarEmail(String assunto, String destinatario, String textoEmail) {
        try {
//            Código para envio de email real
//            var email = new SimpleMailMessage();
//            email.setFrom("adopet@email.com.br");
//            email.setSubject(assunto);
//            email.setTo(destinatario);
//            email.setText(textoEmail);
//            emailSender.send(email);
            System.out.println("Enviando email!");
            System.out.println(textoEmail);

            //Simulando demora de 3 segundos para enviar email
            Thread.sleep(3000);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email!", e);
        }
    }
}
