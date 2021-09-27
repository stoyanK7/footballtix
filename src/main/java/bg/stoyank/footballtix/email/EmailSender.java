package bg.stoyank.footballtix.email;

public interface EmailSender {
    void send(String to, String subject, String email);
}
