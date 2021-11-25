package bg.stoyank.footballtix.email;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {
    public String buildRegistrationConfirmationEmail(String recipient, String link) {
        return buildEmail("Confirm your email",
                recipient,
                new String[]{
                        "Thank you for registering.",
                        "Please click on the below link to activate your account:",
                        buildLink(link, "Activate now"),
                        "See you soon"});
    }

    private String buildEmail(String title, String recipient, String[] body) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#444444\"><span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span><table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tbody><tr><td width=\"100%\" height=\"53\" bgcolor=\"#444444\"><table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\"><tbody><tr><td width=\"70\" bgcolor=\"#444444\" valign=\"middle\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td style=\"padding-left:10px\"></td><td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\"><span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">" + title + "</span></td></tr></tbody></table></a></td></tr></tbody></table></td></tr></tbody></table><table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\"><tbody><tr><td width=\"10\" height=\"10\" valign=\"middle\"></td><td><table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td bgcolor=\"#89df8f\" width=\"100%\" height=\"10\"></td></tr></tbody></table></td><td width=\"10\" valign=\"middle\" height=\"10\"></td></tr></tbody></table><table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\"><tbody><tr><td height=\"30\"><br></td></tr><tr><td width=\"10\" valign=\"middle\"><br></td><td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#444444\">Hi " + recipient + ",</p>" + buildBody(body) + "</td><td width=\"10\" valign=\"middle\"><br></td></tr><tr><td height=\"30\"><br></td></tr></tbody></table><div class=\"yj6qo\"></div><div class=\"adL\"></div></div>";
    }

    private String buildLink(String link, String text) {
        return "<blockquote style=\\\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\\\"><p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#444444\\\"> <a href=\"" + link + "\">" + text + "</a> </p></blockquote>";
    }

    private String buildParagraph(String text) {
        return "<p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#444444\\\">\"" + text + "</p>";
    }

    private String buildBody(String[] listOfItems) {
        StringBuilder body = new StringBuilder();
        for (String paragraph : listOfItems) {
            body.append("<p>").append(paragraph).append("</p>");
        }
        return body.toString();
    }
}
