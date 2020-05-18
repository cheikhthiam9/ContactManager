package com.bawp.contactmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bawp.contactmanager.data.DatabaseHandler;
import com.bawp.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private ArrayList<Contact> contactArrayList;
 private RecyclerViewAdapter recyclerViewAdapter;
 private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   listView = findViewById(R.id.listview);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        contactArrayList = new ArrayList<>();

        //Referencing to DatabaseHandler class by creating a db object
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);


        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.d("MainActivity", "onCreate: " + contact.getName());
            //Looping thru the ist of contacts contained in the database
            //And storing the name of each contact in our contactArrayList
            contactArrayList.add(contact);
        }



        //Setup Adapter
        recyclerViewAdapter = new RecyclerViewAdapter(this, contactArrayList);

        recyclerView.setAdapter (recyclerViewAdapter);
    }

}

//        Creating a contact object

//        Contact a = new Contact("Greg", "91645");
//        db.addContact(a);

//        UPDATE A CONTACT
//        Contact c = db.getContact(1);
//        c.setName = ("Cheikh");
//        c.setphoneNumber = ("12334");
//         int updateRow = db.updateContact(c);
//
//        DELETE A CONTACT
//        db.deleteContact(c);
//
//        Getting numbers of rows in the database
//        int numberOfRowsInDatabase = db.getCount();
//        Creating a contact object
//        db.addContact(new Contact("James","213986"));
//        db.addContact(new Contact("Greg","098765"));
//        db.addContact(new Contact("Helena","40678765"));
//        db.addContact(new Contact("Carimo","768345"));
//        db.addContact(new Contact("Silo","3445"));
//        db.addContact(new Contact("Santos","6665"));
//        db.addContact(new Contact("Litos","5344"));
//        db.addContact(new Contact("Karate","96534"));
//        db.addContact(new Contact("Guerra","158285"));
//        db.addContact(new Contact("Gema","78130"));
//        db.addContact(new Contact("Cheikh","213986"));
//        db.addContact(new Contact("Daba","098765"));
//        db.addContact(new Contact("Mamy","40678765"));
//        db.addContact(new Contact("Pathia","768345"));
//        db.addContact(new Contact("Rose","3445"));
//        db.addContact(new Contact("Djibril","6665"));
//        db.addContact(new Contact("Bebe","5344"));
//        db.addContact(new Contact("Saliou","96534"));
//        db.addContact(new Contact("Annick","158285"));
//        db.addContact(new Contact("Rose","78130"));
//




//
//        //create array adapter
//        //passing this, xml layout, data values
//        arrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                contactArrayList
//        );
//
//        //add to our listview
//        //This has to be done when the list of data has already been adapted with the array adapter
//        listView.setAdapter(arrayAdapter);
//
//        //Attach eventlistener to listview
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("List", "onItemClick: " + contactArrayList.get(position));
//
//            }
//        });
