package bsu.rfe.lavshuk.video.archive.util;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordUtil {
    public static boolean checkPassword(String requestPassword, String hashedPassword) {
        if (requestPassword == null || hashedPassword == null) {
            return false;
        }
        boolean res = BCrypt.checkpw(requestPassword,hashedPassword);
        return res;
    }

    public static String hash(String str) {
         String res = BCrypt.hashpw(str,BCrypt.gensalt());
         return res;
    }

}
