package com.phamtantb24.appff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProviderContact extends AppCompatActivity {
    ListView contactLV;
    List<String> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_contact);
        contactLV = findViewById(R.id.contactListView);
        contactList = new ArrayList<>();
        getPhoneContact();
        contactLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProviderContact.this,DetailContact.class);
                intent.putExtra("Contact",contactList.get(position));
                startActivity(intent);

            }
        });

    }

    private void getPhoneContact() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_CONTACTS}, 0);

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri,null,null,null,null);
        if (cursor.getCount() >0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add("Name || "+contactName+" || Phone || "+contactNumber);
            }

        }
        ArrayAdapter adapter = new ArrayAdapter(
                ProviderContact.this,
                android.R.layout.simple_list_item_1,
                contactList
        );
        contactLV.setAdapter(adapter);
    }
}