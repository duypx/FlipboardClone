package clone.flipboard.flipboardclone.model;

import java.io.Serializable;

/**
 * Created by mainguyen on 9/7/15.
 */
public class RssItem implements Serializable {

    private String title;
    private String link;
    private String img;
    private String thumbnail;
    private String description;


    public RssItem(){
        title = description = link = thumbnail = "";
    }

    public RssItem(String title, String link, String img) {
        this.title = title;
        this.link = link;
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImg() {
        return img;
    }
}
