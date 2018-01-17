package info.upump.wimmelbuch.model;

/**
 * Created by explo on 17.01.2018.
 */

public class Page {
    private long id;
    private long BookId;
    private int numberPage;
    private String imgTitle;
    private String imgPage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public long getBookId() {
        return BookId;
    }

    public void setBookId(long bookId) {
        this.BookId = bookId;
    }

    public String getImgPage() {
        return imgPage;
    }

    public void setImgPage(String imgPage) {
        this.imgPage = imgPage;
    }
}
