package selenium;

public class Post {
    private String model;
    private String link;
    private String description;
    private String getDateOfCreate;

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "model='" + model + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", getDateOfCreate='" + getDateOfCreate + '\'' +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGetDateOfCreate() {
        return getDateOfCreate;
    }

    public void setGetDateOfCreate(String getDateOfCreate) {
        this.getDateOfCreate = getDateOfCreate;
    }
}
