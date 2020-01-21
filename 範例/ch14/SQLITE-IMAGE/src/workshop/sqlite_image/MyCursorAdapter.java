package workshop.sqlite_image;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {

	public MyCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}

	public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// �N��ܸ��ô����UI����
		ImageView imgCover = (ImageView) view.findViewById(R.id.imgCover);
		TextView txtTitle = (TextView) view.findViewById(R.id.textTitle);
		TextView txtAuthor = (TextView) view.findViewById(R.id.textAuthor);
		TextView txtIsbn = (TextView) view.findViewById(R.id.textIsbn);
		TextView txtOthers = (TextView) view.findViewById(R.id.textOthers);
		txtTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
		txtAuthor.setText(cursor.getString(cursor.getColumnIndex("author")));
		txtIsbn.setText(cursor.getString(cursor.getColumnIndex("isbn")));
		String misc = cursor.getString(cursor.getColumnIndex("publisher")) + " " + cursor.getString(cursor.getColumnIndex("year"));
		txtOthers.setText(misc);
		byte[] photo = cursor.getBlob(cursor.getColumnIndex("cover"));
		Bitmap imgphoto = BitmapFactory.decodeByteArray(photo, 0, photo.length);
		imgCover.setImageBitmap(imgphoto);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// �M��R.layout.entry�����إ�UI����
		LayoutInflater entryInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = entryInflater.inflate(R.layout.entry, null);
		return view;
	}

}
