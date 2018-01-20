package info.upump.wimmelbuch.model;

/**
 * Created by explo on 17.01.2018.
 */

public class Book {
    private long id;
    private String title;
    private int rate;
    private String imgTitle;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", imgTitle='" + imgTitle + '\'' +
                '}';
    }
}
