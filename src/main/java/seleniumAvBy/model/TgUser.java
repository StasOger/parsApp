package seleniumAvBy.model;

public class TgUser {

    private String chatId;
    private String username;
    private String linkFiltr;

    @Override
    public String toString() {
        return "TgUser{" +
                "chatId='" + chatId + '\'' +
                ", username='" + username + '\'' +
                ", linkFiltr='" + linkFiltr + '\'' +
                '}';
    }

    public TgUser() {
    }

    public TgUser(String chatId, String username, String linkFiltr) {
        this.chatId = chatId;
        this.username = username;
        this.linkFiltr = linkFiltr;
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

    public String getLinkFiltr() {
        return linkFiltr;
    }

    public void setLinkFiltr(String linkFiltr) {
        this.linkFiltr = linkFiltr;
    }
}
