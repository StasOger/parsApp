package selenium.model;

public class Post {
    private String model;
    private String link;
    private String description;
    private String dateOfCreate;

    public Post(String model, String link, String description, String getDateOfCreate) {
    }

    @Override
    public String toString() {
        return "Post{" +
                "model='" + model + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", getDateOfCreate='" + dateOfCreate + '\'' +
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

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
