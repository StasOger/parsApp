public class Post {
    private String title;
    private String detailsLink;
    private String author;
    private String authorDetailsLink;
    private String dateOfCreate;

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", details='" + detailsLink + '\'' +
                ", author='" + author + '\'' +
                ", authorDetailsLink='" + authorDetailsLink + '\'' +
                ", dateOfCreate='" + dateOfCreate + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String details) {
        this.detailsLink = details;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorDetailsLink() {
        return authorDetailsLink;
    }

    public void setAuthorDetailsLink(String authorDetailsLink) {
        this.authorDetailsLink = authorDetailsLink;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
