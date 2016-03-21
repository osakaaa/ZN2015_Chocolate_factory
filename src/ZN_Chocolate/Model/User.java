package ZN_Chocolate.Model;
/**
 * Created by aplastunov on 24.10.15.
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.UUID;
import java.security.*;

public class User {
    private int number;
    private String username;
    private String password;
    private String guid;
    private String token;
    private String status;


    public User(){

    }

    public User(String username, String password) {

        this.username = username;
        this.password = getHashString(password);
        this.guid = UUID.randomUUID().toString();
        this.token = "";
        this.status = "";

    }
    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getGuid() {return guid;}

    public void setGuid(String guid) {this.guid = UUID.fromString(guid).toString();}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {return status;}

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getHashString(String input){
        String hashtext = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(input.getBytes());
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashtext;    }
}
