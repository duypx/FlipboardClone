package clone.flipboard.flipboardclone.model;

import java.io.Serializable;

/**
 * Created by mainguyen on 9/9/15.
 */
public class RssItemInfo implements Serializable {
    private RssItem item;

    public RssItemInfo (RssItem rss_item) {
        this.item = rss_item;
    }
    public RssItem getRssItem() {
        return this.item;
    }
}
