package clone.flipboard.flipboardclone.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import clone.flipboard.flipboardclone.R;
import clone.flipboard.flipboardclone.model.RssItem;
import clone.flipboard.flipboardclone.object.AfterStart;

/**
 * Created by mainguyen on 9/10/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RSSSearch";
    private static final String TABLE_NAME = "RssItem";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_LINK = "link";
    Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER," + KEY_TITLE + " TEXT,"
                + KEY_IMAGE + " INTEGER," + KEY_LINK + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(AfterStart mAfterStartItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, mAfterStartItem.getId());
        values.put(KEY_TITLE, mAfterStartItem.getTitle());
        values.put(KEY_IMAGE, mAfterStartItem.getImage());
        values.put(KEY_LINK, mAfterStartItem.getLink());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

//    public AfterStart getAfterStart(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
//                        KEY_TITLE, KEY_IMAGE, KEY_LINK }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        AfterStart mAfterStart = new AfterStart(context, cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(0)));
//        return mAfterStart;
//    }

//    public void initDefaultMetroItem(){
//        addData(new AfterStart(context, "General", R.drawable.selector_orange, "http://vnexpress.net/rss/tin-moi-nhat.rss", 1));
//        addData(new AfterStart(context,"Sport", R.drawable.selector_blue1, "http://vnexpress.net/rss/the-thao.rss", 2));
//        addData(new AfterStart(context,"Youth", R.drawable.selector_green2, "http://dantri.com.vn/nhipsongtre.rss", 3));
//        addData(new AfterStart(context, "Economy", R.drawable.selector_yellow, "http://vnexpress.net/rss/kinh-doanh.rss", 4));
//        addData(new AfterStart(context, "Politics", R.drawable.selector_green1, "http://dantri.com.vn/xa-hoi.rss", 5));
//        addData(new AfterStart(context, "Education", R.drawable.selector_blue3, "http://dantri.com.vn/giaoduc-khuyenhoc.rss", 6));
//        addData(new AfterStart(context, "Science", R.drawable.selector_pink, "http://vnexpress.net/rss/khoa-hoc.rss", 7));
//        addData(new AfterStart(context, "Health", R.drawable.selector_green3, "http://dantri.com.vn/suckhoe.rss", 8));
//        addData(new AfterStart(context, "Entertainment", R.drawable.selector_yellow, "http://vnexpress.net/rss/giai-tri.rss", 9));
//        addData(new AfterStart(context, "World", R.drawable.selector_blue4, "http://vnexpress.net/rss/the-gioi.rss", 10));
//
//
//    }
}
