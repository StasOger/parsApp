package seleniumAvBy.model;

public class Post {
    private String model;
    private String description;
    private String link;
    private String price;

    public Post() {
    }

    public Post(String model, String description, String link, String price) {
        this.model = model;
        this.description = description;
        this.link = link;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Post{" +
                "model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", price='" + price + '\'' +
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

    public String getPrice() {return price;}

    public void setPrice(String price) {this.price = price;}
}
