package clone.flipboard.flipboardclone.model;

import java.io.Serializable;

/**
 * Created by mainguyen on 9/9/15.
 */
public class NewsInfo implements Serializable {
    private RssItem news;

    public NewsInfo(RssItem news) {
        this.news = news;
    }

    public RssItem getNews() {
        return news;
    }
}
