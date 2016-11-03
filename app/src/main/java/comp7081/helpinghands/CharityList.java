package comp7081.helpinghands;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class CharityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_list);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        CharitiesDbHelper myDbHelper = new CharitiesDbHelper(this);
        myDbHelper.addEntry("Helping Hands", "logo", "This is a test", 1000);
        myDbHelper.addEntry("Testing Hands", "logo", "This is a test too", 2000);

        ListView lv = (ListView)findViewById(android.R.id.list);
        CharitiesAdapter customAdapter = new CharitiesAdapter(this, myDbHelper.getAllRows(), 0);
        //Toast.makeText(this, "Custom adapter created.", Toast.LENGTH_LONG).show();
        lv.setAdapter(customAdapter);
        //Toast.makeText(this, "Custom Adapter set.", Toast.LENGTH_LONG).show();

            /*
        String dbName = "charitiesDB.db";
        SQLiteDatabase db = openOrCreateDatabase(dbName, 0, null);

        if(!db.isOpen()) {
            Toast.makeText(this, "Db Open Error", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        //First insert the record
        db.beginTransaction();
        try{
            //create a table
            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "Charities"
                    + " (id INT PRIMARY KEY, name VARCHAR , image VARCHAR, biography VARCHAR, donated INT);");

            //Add a test entry
            addEntry(db, 1, "Helping Hands", "logo", "This is a test", 1000);
            addEntry(db, 2, "Testing Hands", "logo", "This is a test too", 2000);

            db.setTransactionSuccessful();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        finally
        {
            db.endTransaction();
        }



        Cursor dbCursor = null;


        if(!db.isOpen()) {
            Toast.makeText(this, "Db Open Error", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        try{
            dbCursor = db.query( "Charities", null, null, null, null, null, null); //so many nulls, I am good at databases :D


            int nameCol = dbCursor.getColumnIndex("name");
            int numberCol = dbCursor.getColumnIndex("donated");

            if (dbCursor != null) {
                dbCursor.moveToFirst();
                if (dbCursor.getCount() != 0) {
                    int i = 0;
                    do {
                        i++;
                        String name = dbCursor.getString(nameCol);
                        String number    = dbCursor.getString(numberCol);
                        Toast.makeText(this, name + "  " + number, Toast.LENGTH_SHORT)
                                .show();

                    } while (dbCursor.moveToNext());
                }
            }
            ListView lv = (ListView)findViewById(android.R.id.list);
            CharitiesAdapter customAdapter = new CharitiesAdapter(this, dbCursor, 0);
            Toast.makeText(this, "Custom adapter created.", Toast.LENGTH_LONG).show();
            lv.setAdapter(customAdapter);
            Toast.makeText(this, "Custom Adapter set.", Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    */


        /*
        dbCursor = db.query( "Charities", null, null, null, null, null, null);

        ListAdapter adapter = new SimpleCursorAdapter(
                this, // Context.
                R.layout.custom_list_row,  // Specify the row template to use (here, two columns bound to the two retrieved cursor rows).
                dbCursor,                                              // Pass in the cursor to bind to.
                new String[] {"name", "image"},           // Array of cursor columns to bind to.
                new int[] {R.id.CharityName, R.id.CharityImage},
                0);  // Parallel array of which template objects to bind to those columns.

        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setAdapter(adapter);
        */
    }

}
