package seleniumAvBy.model;

public class TgUser {

    private String chatId;
    private String username;

    public TgUser(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }

    public TgUser() {
    }

    @Override
    public String toString() {
        return "TgUser{" +
                "chatId='" + chatId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
