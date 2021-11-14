package activeUser;

public class activeUser {
    private String user_name;
    private String password;

    public activeUser(String name, String passwd){
        user_name = name;
        password = passwd;
    }

    void updateName(String newName){
        user_name = newName;
    }

    void updatePasswd(String newPasswd){
        password = newPasswd;
    }

    public String getName(){
        return user_name;
    }

    public String getPassword(){
        return password;
    }
}
