package workshop.xmlfile;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookHandler extends DefaultHandler {
	private ArrayList<Book> booklist;
	private Book book;
	private String currentValue = null;
	public ArrayList<Book> getBooks()
	{
		return booklist;
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// 元素解析中
		currentValue = new String(ch, start, length);
		super.characters(ch, start, length);
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// 完成元素解析作業
		if (localName.equals("book"))			
			booklist.add(book);
		else if (localName.equals("title"))
			book.setTitle(currentValue);
		else if (localName.equals("publisher"))
			book.setPublisher(currentValue);
		super.endElement(uri, localName, qName);
	}
	@Override
	public void startDocument() throws SAXException {
		// 建立用來儲存XML文件資料的ArrayList
		booklist = new ArrayList<Book>();
		super.startDocument();
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// 開始元素解析作業
		if (localName.equals("book"))
		{	
			book = new Book();
			book.setIsbn(attributes.getValue("isbn"));
			book.setYear(attributes.getValue("year"));
		}
		super.startElement(uri, localName, qName, attributes);
	}
}
