package ZN_Chocolate.DAO;

/**
 * Created by aplastunov on 25.10.15.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ZN_Chocolate.Model.User;
import ZN_Chocolate.Util.DbUtil;

public class UserDao {
    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users(username,password,guid,token,status) values (?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getGuid());
            preparedStatement.setString(4, user.getToken());
            preparedStatement.setString(5, user.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();

        }

    }

    public void deleteUser(String username) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where username=?");
            // Parameters start with 1
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set password = ?,guid = ?, token = ?, status = ? where username=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getGuid());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getStatus());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setGuid(rs.getString("guid"));
                user.setToken(rs.getString("token"));
                user.setStatus(rs.getString("status"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public User getUserByName(String username) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGuid(rs.getString("guid"));
                user.setToken(rs.getString("token"));
                user.setStatus(rs.getString("status"));

            }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }catch (java.lang.NullPointerException e) {
            e.printStackTrace();
            return null;

        }
        return user;
    }

}