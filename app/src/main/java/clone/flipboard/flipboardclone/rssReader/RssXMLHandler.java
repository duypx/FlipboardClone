package clone.flipboard.flipboardclone.rssReader;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clone.flipboard.flipboardclone.model.RssItem;

/**
 * Class trung gian, thua ke {@link DefaultHandler} dung de parse du lieu xml
 * Nhiem vu: Lay cac vu Rss item
 * 
 * @author Nguyen Duc Hieu
 */
public class RssXMLHandler extends DefaultHandler {

	int testNumber = 0;
	private int curItemNo = 0;

	boolean currentElement = false;
	String currentValue = "";

	RssItem productInfo = null;
	ArrayList<RssItem> cartList = null;

	// constructors
	public RssXMLHandler() {
		super();
	}

	public RssXMLHandler(int iTestNumber) {
		super();
		if (iTestNumber < 0)
			testNumber = 0;
		else
			testNumber = iTestNumber;
	}

	/**
	 * Ham tra ve items parse duoc
	 * 
	 * @return ArrayList of RssItemInfo
	 */
	public ArrayList<RssItem> getCartList() {
		return cartList;
	}

	// ham xu ly khi tag mo
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		if (qName.equals("rss")) {
			cartList = new ArrayList<RssItem>();
		} else if (qName.equals("item")) {
			productInfo = new RssItem();
			currentElement = true;
		}

		if (attributes.getLength() > 1 && currentElement == true) {
			String value = attributes.getValue("url");
			if (value != null)
				if (value.contains(".jpg"))
					productInfo.setThumbnail(attributes.getValue("url"));
		}

	}

	// ham xu ly khi tag dong
	@Override
	public void endElement(String uri, String localName, String qName)
			throws MySAXException {

		if (productInfo != null)
			if (qName.equalsIgnoreCase("title"))
				productInfo.setTitle(currentValue.trim());
			else if (qName.equalsIgnoreCase("description"))
				productInfo.setDescription(currentValue.trim());
			else if (qName.equalsIgnoreCase("link"))
				productInfo.setLink(currentValue.trim());
//			else if (qName.equalsIgnoreCase("pubDate"))
//				productInfo.setPubDate(currentValue.trim());
			else if (qName.equalsIgnoreCase("summaryimg")) // use for 24h
				productInfo.setThumbnail(currentValue.trim());
			else if (qName.equalsIgnoreCase("item")) {
				// now format cmplx desc
				String desc = productInfo.getDescription();
				String extractedImg = extractDesc(desc);
				if (extractedImg != null)
					productInfo.setThumbnail(extractedImg);
				// tien hanh chuan hoa link
				String curThumb = productInfo.getThumbnail();
				if (curThumb != null && !curThumb.equals(""))
					try {
						new URL(curThumb); // if not full url, need to concat
					} catch (MalformedURLException e1) {
						productInfo.setThumbnail(concatUrl(
								productInfo.getLink(), curThumb));
					}

				productInfo.setDescription(desc.replaceAll("<.+?>", ""));

				cartList.add(productInfo);
				currentElement = false;

				// xu ly so phan tu cho testmode
				if (testNumber > 0)
					if (++curItemNo >= testNumber)
						throw new MySAXException("Done testMode with "
								+ curItemNo + " items");
			}

		currentValue = "";
	}

	// ham xu ly khi duyet qua cac ky tu
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (currentElement) {
			currentValue = currentValue + new String(ch, start, length);
		}
	}

	// extract data from complex description
	private String extractDesc(String inDesc) {
		String output = null;
		output = getMatch("<\\s*img\\s*[^>]+src\\s*=\\s*(['\"]?)(.*?).jpg\\1",
				inDesc, 2);

		if (output != null)
			output += ".jpg";
		return output;
	}

	// 2 ham xu ly pattern
	private String getMatch(String patternString, String text, int groupIndex) {
		Pattern pattern = Pattern.compile(patternString,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		return getMatch(pattern, text, groupIndex);
	}

	private String getMatch(Pattern pattern, String text, int groupIndex) {
		if (text != null) {
			Matcher matcher = pattern.matcher(text);
			String match = null;
			while (matcher.find()) {
				match = matcher.group(groupIndex);
				break;
			}
			return match;
		} else {
			return null;
		}
	}

	/**
	 * Ham xu ly link, chuyen doi link relative sang absolute
	 * 
	 * @param baseUrl
	 *            link goc
	 * @param relativeUrl
	 *            link relative can convert
	 * @return
	 */
	private String concatUrl(String baseUrl, String relativeUrl) {
		try {
			URL url = new URL(baseUrl);
			relativeUrl = url.getProtocol() + "://" + url.getHost()
					+ relativeUrl;
		} catch (MalformedURLException e2) {
			Log.e("RssreadrLib", "concat error on base url");
			return relativeUrl;
		}
		return relativeUrl;
	}
}