import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailFormController {

    public Button btnSend;
    public TextField txtSender;
    public TextField txtRecipient;
    public TextArea txtMsg;


    public void initialize(){
        btnSend.setOnAction(event -> {
            sendEmail();
        });
    }

    private void sendEmail() {
        String sender = txtSender.getText();
        String recipient = txtRecipient.getText();
        String msg = txtMsg.getText();
        String pswd = "enkeevlbcbvpddur";

        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, pswd);
                    }
                });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject("This this the Subject");
            //num = String.format("%14d", otp);
            message.setText(msg);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }

}
