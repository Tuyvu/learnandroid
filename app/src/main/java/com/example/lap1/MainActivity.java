package com.example.lap1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.EGLExt;
import android.os.Build;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] tinhThanh = {"Chọn tỉnh thành",
            "An Giang", "Bà Rịa - Vũng Tàu", "Bạc Liêu", "Bắc Giang", "Bắc Kạn", "Bắc Ninh",
            "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau",
            "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai",
            "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương",
            "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang",
            "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định",
            "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình",
            "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La",
            "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang",
            "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
    };

    Person per;
    ArrayList<Person> list,listAdapter;
    protected EditText txtHoTen,txtSDT,stSearch;
    protected RadioButton RbNam,Rbnu;
    protected Button btnAdd;
    protected Spinner Spn ;
    protected ListView lv;
    protected ImageView avt;
    public static Integer SelectedItemID;
    protected Button btnthem, btnCallogs,btnbrowser;
    ArrayAdapter<String> adapter;
    private  ContentPrvaider cp;
    private static final int PRRC=100;
    private MyDB db;
    private static final int REQUEST_READ_CALL_LOG = 1;
    private static final int REQUEST_READ_BROWSER_HISTORY = 2;
    private int currentFunction = 0;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenu, menu);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHoTen = findViewById(R.id.txtHoten);
        txtSDT = findViewById(R.id.txtSDT);
        RbNam = findViewById(R.id.RBnam);
        Rbnu = findViewById(R.id.RBnu);
        btnAdd = findViewById(R.id.btnadd);
        lv = findViewById(R.id.lv);
        Spn = findViewById(R.id.spnQH);
        avt = findViewById(R.id.avt);
        btnthem = findViewById(R.id.btnthem);
        stSearch = findViewById(R.id.stSearch);
        btnbrowser = findViewById(R.id.btnbrh);
        btnCallogs = findViewById(R.id.btncallogs);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,tinhThanh);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,tinhThanh);
        Spn.setAdapter(adapter);
        list= new ArrayList<>();
//        db = new MyDB(this, "ContactDB",null,1);
////        db.addContact(new Person(1,"vu","img1","123454"));
////        db.addContact(new Person(2,"vu2","img2","123454"));
////        db.addContact(new Person(3,"vu3","img3","123454"));
//        list = db.getAllContact();
//        ArrayAdapter adap = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,list);
////        Adaptercustom adap = new Adaptercustom(MainActivity.this,android.R.layout.simple_list_item_1,list);
//
//        lv.setAdapter(adap);
        ShowContact();
        // filter
//        stSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                adap.getFilter().filter(s.toString());
//                adap.notifyDataSetChanged();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gt;
                if(RbNam.isChecked()){
                    gt=1;
                }else{
                    gt=0;
                }
                per =new Person(txtHoTen.getText().toString(),txtSDT.getText().toString(),gt,Spn.getSelectedItem().toString());
                list.add(per);
//                Adaptercustom adap = new Adaptercustom(MainActivity.this, R.layout.layout_list ,list);
//                lv.setAdapter(adap);
                ArrayAdapter adap = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                lv.setAdapter(adap);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(lv);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedItemID = position;
                return false;
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivitySub.class);
                startActivityForResult(intent, 1000);
            }
        });
        avt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1000);
            }
        });
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_READ_CALL_LOG);
//        } else {
//            displayCallLogs();
//        }
//        btnCallogs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentFunction = 0;
//                displayCallLogs();
//            }
//        });
//        btnbrowser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentFunction = 1;
//                displayVideoList();
//            }
//        });

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Person p = list.get(SelectedItemID);
        if(item.getItemId() == R.id.mnuedit){
            Intent intent = new Intent(MainActivity.this, ActivitySub.class);

            Bundle b = new Bundle();
            b.putString("Name", p.getHoTen().toString());
            b.putString("SDT", p.getSDT().toString());
            intent.putExtras(b);
            startActivityForResult(intent,150);
        } else if(item.getItemId() == R.id.mnuCall){
            Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+p.getSDT()));
            startActivity(in);
        } else if(item.getItemId() == R.id.mnuSMS){
            Intent in = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + p.getSDT()));
            startActivity(in);
        }

        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            try {
                // Get the URI of the selected image
                if (data.getData() != null) {
                    // Convert the URI to a Bitmap
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());

                    // Set the Bitmap to the ImageView
                    avt.setImageBitmap(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Bundle b =data.getExtras();
        String Name = b.getString("Name");
        String SDT = b.getString("SDT");
        Person person = new Person(4,Name, "", SDT);
        db.addContact(person);
        list.add(person);
        lv.deferNotifyDataSetChanged();

//        txtHoTen.setText(Name.toString());
//        txtSDT.setText(SDT.toString());
//        Person pre = new Person(Name,SDT,)

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.mnuSname){
            Toast.makeText(this,"Sort by name",Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.mnuSphone){
            Toast.makeText(this,"Sort by Phone",Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.mnuB){
//            Toast.makeText(this,"Sort by name",Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.mnuedit){
            Toast.makeText(this,"edit",Toast.LENGTH_SHORT).show();
        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.actionmenu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    private void ShowContact(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
        checkSelfPermission(Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},150);
        }else {

            cp =new ContentPrvaider(this);
            list = cp.getAllContact();
            ArrayAdapter newAdap = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
            lv.setAdapter(newAdap);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_CALL_LOG) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayCallLogs();
            }
        }
        if (requestCode == REQUEST_READ_BROWSER_HISTORY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayBrowserHistory();
            }
        }
    }
    private void displayCallLogs() {
        ArrayList<String> callLogList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int dateIndex = cursor.getColumnIndex(CallLog.Calls.DATE);
            int typeIndex = cursor.getColumnIndex(CallLog.Calls.TYPE);

            do {
                String number = cursor.getString(numberIndex);
                long date = cursor.getLong(dateIndex);
                int type = cursor.getInt(typeIndex);

                String callType = null;
                switch (type) {
                    case CallLog.Calls.INCOMING_TYPE:
                        callType = "Incoming";
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        callType = "Outgoing";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        callType = "Missed";
                        break;
                }

                callLogList.add("Number: " + number + "\n" + "Type: " + callType + "\n" + "Date: " + date + "\n\n");
            } while (cursor.moveToNext());

            cursor.close();
        }
        ArrayAdapter newAdap = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,callLogList);
        lv.setAdapter(newAdap);
    }
    private void displayBrowserHistory() {
        ArrayList<String> browserHistoryList = new ArrayList<>();
//        Cursor cursor = getContentResolver().query(Browser.EXTRA_HEADERS, null, null, null, Browser.BookmarkColumns.DATE + " DESC");
//
//        if (cursor != null && cursor.moveToFirst()) {
//            int titleIndex = cursor.getColumnIndex(Browser.EXTRA_HEADERS.TITLE);
//            int urlIndex = cursor.getColumnIndex(Browser.BookmarkColumns.URL);
//
//            do {
//                String title = cursor.getString(titleIndex);
//                String url = cursor.getString(urlIndex);
//
//                browserHistoryList.add("Title: " + title + "\n" + "URL: " + url + "\n\n");
//            } while (cursor.moveToNext());
//
//            cursor.close();
//        }

        ArrayAdapter<String> browserHistoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, browserHistoryList);
        lv.setAdapter(browserHistoryAdapter);
    }
    private ArrayList<String> getMusicList() {
        ArrayList<String> musicList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.Media.TITLE + " ASC"
        );
        if (cursor != null && cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            do {
                String title = cursor.getString(titleIndex);
                musicList.add(title);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return musicList;
    }


    private ArrayList<String> getVideoList() {
        ArrayList<String> videoList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Video.Media.TITLE + " ASC"
        );
        if (cursor != null && cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
            do {
                String title = cursor.getString(titleIndex);
                videoList.add(title);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return videoList;
    }
    private void displayMusicList() {
        ArrayList<String> musicList = getMusicList();
        ArrayAdapter<String> musicAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, musicList);
        lv.setAdapter(musicAdapter);
    }

    private void displayVideoList() {
        ArrayList<String> videoList = getVideoList();
        ArrayAdapter<String> videoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, videoList);
        lv.setAdapter(videoAdapter);
    }

}