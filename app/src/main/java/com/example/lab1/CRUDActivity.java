package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CRUDActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    ListView lv;
    SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.COL_CID, DatabaseHelper.COL_NAME, DatabaseHelper.COL_BASICEDU, DatabaseHelper.COL_NOTABLEWORKS, DatabaseHelper.COL_HISTORY, DatabaseHelper.COL_EARNINGS };

    final int[] to = new int[] { R.id.cid, R.id.cname, R.id.pqual, R.id.cwork, R.id.cpast, R.id.cproperty };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        databaseManager = new DatabaseManager(this);
        databaseManager.open();
        Cursor cursor = databaseManager.fetch();

        lv = findViewById(R.id.listViewCRUD);

        adapter = new SimpleCursorAdapter(this, R.layout.listviewcrud, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView cidTV = view.findViewById(R.id.cid);
                TextView cnameTV = (TextView) view.findViewById(R.id.cname);
                TextView cbasiceduTV = (TextView) view.findViewById(R.id.pqual);
                TextView cworkTV = (TextView) view.findViewById(R.id.cwork);
                TextView cpastTV = (TextView) view.findViewById(R.id.cpast);
                TextView cpropertyTV = (TextView) view.findViewById(R.id.cproperty);

                String _id = cidTV.getText().toString();
                String name = cnameTV.getText().toString();
                String basicEdu = cbasiceduTV.getText().toString();
                String work = cworkTV.getText().toString();
                String past = cpastTV.getText().toString();
                String property = cpropertyTV.getText().toString();

                byte[][] images = databaseManager.getImages(Integer.parseInt(_id));

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCandidateActivity.class);
                modify_intent.putExtra("cid",_id);
                modify_intent.putExtra("pqual", basicEdu);
                modify_intent.putExtra("cname", name);
                modify_intent.putExtra("cwork", work);
                modify_intent.putExtra("cpast", past);
                modify_intent.putExtra("cproperty", property);
                modify_intent.putExtra("img1",images[0]);
                modify_intent.putExtra("img2",images[1]);

                startActivity(modify_intent);
            }
        });
    }
}
