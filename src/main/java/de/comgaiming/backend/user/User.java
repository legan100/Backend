package de.comgaiming.backend.user;

import de.comgaiming.backend.BackendApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class User {

    private String username;
    private String password;
    private String firstpassword;
    private String email;
    Group group = new Group();

    public User(){}

    public String getUsername() {
        return username;
    }

    public boolean isUsernameIsValid(String username) {
        if (username == null || username.length() < 3 || username.length() > 20) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9_]+$");
    }

    public boolean isUsernameAvailable(String username) {
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void setUsername(String username) {
        if (isUsernameIsValid(username)) {
            if (!isUserExists(username)) {
                this.username = username;
            } else {
                throw new IllegalArgumentException("Username is already taken");
            }
        } else {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public boolean isUserExists(String username) {
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String getFirstPassword() {
        return firstpassword;
    }

    public void setFirstpassword(String firstpassword) {
        this.firstpassword = firstpassword;
    }

    public String getPassword() {
        String pasword = "";
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT password FROM users WHERE username = ?");
            st.setString(1, String.valueOf(username));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pasword = rs.getString("password");
                System.out.println(pasword);
            }
            return pasword;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEmail(String email) {
    }

    public void createUser(String email){
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("INSERT INTO users (email,username,password) VALUES (?,?,?)");
            st.setString(1, String.valueOf(email));
            st.setString(2, String.valueOf(this.getUsername()));
            st.setString(3, String.valueOf(this.getFirstPassword()));
            st.executeUpdate();
            st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("insert into user_groups(user_id, group_id) VALUES(user_id, group_id)");
            st.setString(1, String.valueOf(this.getUsername()));
            //st.setString(2, String.valueOf(this.getUserID(eingabe)));
            //st.executeUpdate();
            sendLoginInformation(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserID(String eingabe){
        int userId = 0;
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT userid FROM users WHERE username = ?");
            st.setString(1, String.valueOf(username));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("userid");
                System.out.println(userId);
            }
            return userId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername(int id) throws SQLException{
        int userId = 0;
        String username = "";
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT username FROM users WHERE userid = ?");
            st.setString(1, String.valueOf(userId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
            }
            return username;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPassword(int id) throws SQLException{
        int userId = 0;
        String password = "";
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT password FROM users WHERE userid = ?");
            st.setString(1, String.valueOf(userId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                username = rs.getString("password");
            }
            return username;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPassword(String username) throws SQLException{
        String susername = "";
        String password = "";
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT password FROM users WHERE userid = ?");
            st.setString(1, String.valueOf(username));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                username = rs.getString("password");
            }
            return username;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmail(String username) {
        String susername = "";
        String email = "";
        try {
            PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("SELECT email FROM users WHERE userid = ?");
            st.setString(1, String.valueOf(username));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                email = rs.getString("password");
            }
            return email;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public boolean checkUsername(String username) throws SQLException{
        if(getUsername().equals(username)){
            BackendApplication.getLogger().logInfo("username is already in use");
            return false;
        }else{
            BackendApplication.getLogger().logInfo("username is not in use.");
            return true;
        }
    }

    public boolean checkEmail(String email) throws SQLException{
        if(getEmail(email).equals(username)){
            BackendApplication.getLogger().logInfo("Emailadress is already in use");
            return false;
        }else{
            BackendApplication.getLogger().logInfo("Emailadress is not in use.");
            return true;
        }
    }

    public void sendLoginInformation(String email){
        String host = "mail.mailseven24.de";
        final String user = "backend@comgaiming.de";
        final String passwordmail = "Ös%Öä&ö8gDPT~kAv";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // oder 465 für SSL
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, passwordmail);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Logindaten für das ACP");
            message.setText("BITTE AUF DIESE EMAIL NICHT ANTWORTEN \n\n\nHallo "+ this.getUsername() + ",\n\nwir freuen uns, dass wir dich bei uns im Team als "+this.getGroup().getGroupname()+" begrüßen zu dürfen. \n\nDamit wir gut zusammenarbeiten können, haben wir ein Admincontrolpanel entwickeln und werden dieses auch weiterentwickeln. \n\nDeine Logindaten dafür sind:\nName: "+this.getUsername()+"\nPasswort: "+this.getPassword()+"\nLink zum Admincontrolpanel: https://comgaiming.de/acp\n\nEine Datenschutzerklärung haben wir hier beigefügt.\n\nSolltest du einen Fehler gefunden haben, eine Idee haben oder Fragen haben, darfst du gerne legan100 (Nico) kontaktieren. \nViele Grüße\n\ndein Team vom ACP");

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String username){
        if(isUserExists(username)){
            String newPassword = BackendApplication.getInternalMethods().createPassword(16, "abcdefghijklmnopqrstuvwxyz","ABCDEFGHIJKLMNOPQRSTUVWXYZ","-.,;.-!0123456789");

            try {
                PreparedStatement st = BackendApplication.getDatabaseManager().getConnection().prepareStatement("UPDATE users SET password = ? WHERE username = ?");
                st.setString(1, newPassword);
                st.setString(2, username);
                st.executeUpdate();
                System.out.println("Password updated successfully for user: " + username);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String host = "mail.mailseven24.de";
            final String user = "backend@comgaiming.de";
            final String passwordmail = "Ös%Öä&ö8gDPT~kAv";
            final String recipient = this.getEmail(username);
            System.out.println(recipient);

            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "587"); // oder 465 für SSL
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, passwordmail);
                }
            });
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                System.out.println("Email: " + this.getEmail(username));
                message.setSubject("Neues Passwort für das ACP");
                message.setText("Hallo "+ this.getUsername() + ",\n\ndu hast gerade ein neues Passwort angefordert für deinen Zugang.\n\nDein neues Passwort lautet: "+newPassword+"\n\nViele Grüße \n\ndein Team vom ACP");
                Transport.send(message);
                System.out.println("Email sent successfully!");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public Group getGroup() {
        return group;
    }

    public void setId(long l) {
    }

    public void setName(String johnDoe) {
    }

    public String getName() {
        return "John Doe";
    }
}
