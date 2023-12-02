package G2_Capstone;

public abstract class User{
    String username;
    char[] password;

    public User(String username, char[] password){
        this.username = username;
        this.password = password;
    }

    public User(){
        username = "Anon";
        password = new char[]{'1', '2','3','4','5','6','y','z'};
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

}
