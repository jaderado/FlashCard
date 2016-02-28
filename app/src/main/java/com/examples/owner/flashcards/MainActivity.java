package com.examples.owner.flashcards;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }

     public void startAct(View view){
//        ParseObject testObject= new ParseObject("TestObject");
//         testObject.put("foo2","bar2");
//         testObject.saveInBackground();
//         startActivity(new Intent(this, LoginActivity.class));

         ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
         query.whereEqualTo("foo", "Arturo");
         query.findInBackground(new FindCallback<ParseObject>() {
             public void done(List<ParseObject> scoreList, ParseException e) {
                 if (e == null) {
                     for(int i = 0;i<scoreList.size();i++)
                         Log.e("Number that Arturo cont", "Here are the numbers of Arturo " +
                                 scoreList.get(i).get("Numbers").toString());
                     Log.e("score", "Retrieved " + scoreList.size() + " Numbers of Arturo");
                 } else {
                     Log.e("score", "Error: " + e.getMessage());
                 }
             }
         });
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
