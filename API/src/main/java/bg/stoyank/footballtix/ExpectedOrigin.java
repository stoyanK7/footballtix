package bg.stoyank.footballtix;

import org.springframework.beans.factory.annotation.Value;

public class ExpectedOrigin {
    @Value("${expected-origin}")
    public static String value;
}
