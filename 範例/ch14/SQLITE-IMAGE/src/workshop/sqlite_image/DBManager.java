package workshop.sqlite_image;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class DBManager extends SQLiteOpenHelper {
	private ArrayList<String[]> books = new ArrayList<String[]>();
	public DBManager(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 建立book資料表，並解析books2.csv文件，加到資料到book資料表
		String sdcardDir = Environment.getExternalStorageDirectory().toString();
		String imgfile;
		readCSVfile();
		String sql = "INSERT INTO book (title,  publisher, year, author, isbn, language, price, cover) VALUES (?,?,?,?,?,?,?,?)";
		db.execSQL("CREATE TABLE book (_id INTEGER PRIMARY KEY, isbn TEXT unique not null, title TEXT not null, author TEXT, publisher TEXT, year INTEGER, language TEXT, price INTEGER, cover BLOB)");
		for (int i=0; i<books.size(); i++) {
			imgfile = sdcardDir + "/" + (books.get(i))[7];
			Bitmap bmp = ((BitmapDrawable)Drawable.createFromPath(imgfile)).getBitmap();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);	
			Object[] data = new Object[]{(books.get(i))[0], (books.get(i))[1], (books.get(i))[2], (books.get(i))[3], (books.get(i))[4], (books.get(i))[5], (books.get(i))[6], baos.toByteArray()};
			db.execSQL(sql, data);
		}	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	private void readCSVfile() {
		// 解析books2.csv檔，將資料加入ArrayList<String[]>
		String sdcardDir = Environment.getExternalStorageDirectory().toString();
		String csvfile = sdcardDir+"/books2.csv";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvfile)));
			String data = "";
			while ((data = br.readLine()) != null) {
				String[] temp = data.split(",");
				books.add(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
