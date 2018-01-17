package info.upump.wimmelbuch.model;

/**
 * Created by explo on 17.01.2018.
 */

public class Page {
    private long id;
    private long parentBookId;
    private int numberPage;
    private String imgTitle;

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

    public long getParentBookId() {
        return parentBookId;
    }

    public void setParentBookId(long parentBookId) {
        this.parentBookId = parentBookId;
    }
}
