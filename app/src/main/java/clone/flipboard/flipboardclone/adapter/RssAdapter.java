package clone.flipboard.flipboardclone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import clone.flipboard.flipboardclone.R;
import clone.flipboard.flipboardclone.model.RssItem;

/**
 * Created by mainguyen on 9/7/15.
 */
public class RssAdapter extends BaseAdapter {

    private final List<RssItem> mItems;
    private final Context mContext;

    public RssAdapter(Context context, List<RssItem> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.rss_item, null);
            holder = new ViewHolder();
            holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
            holder.itemImg = (ImageView) convertView.findViewById(R.id.itemImg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemTitle.setText(mItems.get(position).getTitle());
        Picasso.with(mContext).load(mItems.get(position).getImg()).into(holder.itemImg);
        return convertView;
    }

    static class ViewHolder {
        TextView itemTitle;
        ImageView itemImg;
    }
}
