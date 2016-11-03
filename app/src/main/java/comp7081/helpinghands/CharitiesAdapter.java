package comp7081.helpinghands;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CharitiesAdapter extends CursorAdapter {
    public CharitiesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_list_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //ImageView charityLogo = (ImageView) view.findViewById(R.id.CharityImage);
        TextView charityName = (TextView) view.findViewById(R.id.CharityName);

        String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));

        charityName.setText(name);
        //charityLogo.setImageDrawable(draw);
    }
}
