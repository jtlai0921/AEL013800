package workshop.simple_cursor_adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

	public DBManager(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE book (_id INTEGER PRIMARY KEY,isbn TEXT unique not null,title TEXT not null,author TEXT,publisher TEXT,year INTEGER,language TEXT,price INTEGER)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-111-818-348-9','Professional Android Sensor Programming','Greg Milette','Wiley',2012,'EN',1750)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-986-276-397-1','Android初學特訓班','鄧文淵','碁峰',2011,'TW',480)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-986-276-313-1','Android應用程式開發實戰','林城','碁峰',2012,'TW',680)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-986-201-410-3','深入淺出Android遊戲程式開發範例大全','吳亞峰','博碩',2011,'TW',620)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-143-023-930-7','Pro Android 4','Satya Komatineni','Apress',2012,'EN',1750)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-143-023-987-1','Beginning Android 4 Games Development','Mario Zechner','Apress',2011,'EN',1400)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-986-201-449-3','深入淺出Android專題實作','郭宏志','博碩',2011,'TW',580)");
		db.execSQL("INSERT INTO book(isbn,title,author,publisher,year,language,price) VALUES('978-986-607-200-0','Android SDK開發範例大全','余志龍','悅知',2012,'TW',950)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
