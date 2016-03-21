package ZN_Chocolate.DAO;

import ZN_Chocolate.Model.Session;
import ZN_Chocolate.Model.User;
import ZN_Chocolate.Util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aplastunov on 27.10.15.
 */
public class SessionDao {
    private Connection connection;

    public SessionDao() {
        connection = DbUtil.getConnection();
    }

    public void addSession(Session session) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into sessions(sessionID,username) values (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, session.getsessionID());
            preparedStatement.setString(2, session.getuserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void deleteSession(String username) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from sessions where username=?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

     public Session getSessionByUser(String username) {
        Session session = new Session();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from sessions where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                session.setsessionID(rs.getString("sessionID"));
                session.setuserID(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }
}
