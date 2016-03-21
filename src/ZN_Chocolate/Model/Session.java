package ZN_Chocolate.Model;

/**
 * Created by aplastunov on 27.10.15.
 */
public class Session {
    private String sessionID;
    private String userID;

    public Session() {
        super();
    }

    public String getsessionID() {
        return sessionID;
    }

    public void setsessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getuserID() {
        return userID;
    }

    public void setuserID(String userID) {
        this.userID = userID;
    }

}
