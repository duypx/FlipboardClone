package clone.flipboard.flipboardclone.rssReader;

import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

import clone.flipboard.flipboardclone.model.RssItem;

/**
 * Class cung cap ham xu ly rss cho end-user
 * 
 * @author Nguyen Duc Hieu
 */
public class RssParser {

	public ArrayList<RssItem> parseXML(InputStream is) {
		return parseXML(is, 0);
	}
	
	/**
	 * Hàm phân tích XML tổng quát
	 * 
	 * @param is
	 *            InputStream
	 * @param iTestNumber
	 *            integer
	 * @return list of RSSItemInfo
	 */
	public ArrayList<RssItem> parseXML(InputStream is, int iTestNumber) {
		ArrayList<RssItem> cartList = null;
		// Đối tượng đọc XML
		XMLReader xr = null;
		// Tạo đối tượng xử lý XML với chỉ số testMode
		RssXMLHandler myXMLHandler = new RssXMLHandler(iTestNumber);
		try {
			// Khởi tạo đối tượng đọc
			xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			// Thiết lập nội dung xử lý
			xr.setContentHandler(myXMLHandler);
			// Nguồn dữ liệu vào
			InputSource inStream = new InputSource(is);
			// Bắt đầu xử lý dữ liệu vào
			xr.parse(inStream);

		} catch (MySAXException e1) {
			Log.i("xml reader", e1.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("xml reader", "loi khi parse");
		}

		// Lay cac muc rss cua trang bao
		cartList = myXMLHandler.getCartList();

		return cartList;
	}

	/**
	 * Ham lay thong tin trang bao
	 * 
	 * @param is
	 *            InputStream
	 * @return NewsSourceInfo
	 */
	public RssItem getSourceInfo(InputStream is) {
		RssItem nsi = null;
		// Đối tượng đọc XML
		XMLReader xr = null;
		// Tạo đối tượng xử lý XML theo tuần tự của mình
		RssXMLNameHandler myXMLHandler = new RssXMLNameHandler();
		try {
			// Khởi tạo đối tượng đọc
			xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			// Thiết lập nội dung xử lý
			xr.setContentHandler(myXMLHandler);
			// Nguồn dữ liệu vào
			InputSource inStream = new InputSource(is);
			// Bắt đầu xử lý dữ liệu vào
			xr.parse(inStream);

		} catch (MySAXException e1) {
			Log.i("xml reader", e1.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("xml reader", "loi khi parse");
		}

		// Lay thong tin trang bao
		nsi = myXMLHandler.getSource();

		return nsi;
	}

}
