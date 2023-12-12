# capstone
final proj for OOP

go to src -> G2_MiniGame


----------------------------
# MiniGames_MainMenu
  -> first frame na mo-show ig run sa Main
  -> contains: Log-In, Sign-Up, and Home (MiniGames navigation) Panels

# User Class
  -> an abstract class for users
  -> fields: String username, char [] password

# Player Class
  -> subclass of User
  -> additional fields: int wordleScore, int mazeScore, int takyanScore, boolean isBanned
  -> methods: getters and setters for each field
  -> to implement: comparators for each score

# HandlePlayers Class
  -> will handle all Players in this MiniGame Application
  -> fields: ArrayList <Player> playerlist
  -> methods: 
              void sort() - to be upgraded
              int handleLogIn(String username, char [] password)

  --- to be continued ---
