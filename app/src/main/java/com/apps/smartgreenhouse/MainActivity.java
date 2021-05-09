package com.apps.smartgreenhouse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTempAndHumid, tvSmoke, tvTankLevel, tvPHLevel, tvMoisture, tvLDR;
    Button btnTempAndHumid, btnSmoke, btnTankLevel, btnPHLevel, btnMoisTure, btnLDR;
    private DatabaseReference databaseReference;
    FirebaseUser mUser;
    Query lastQuery;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        tvTempAndHumid = findViewById(R.id.tvTempAndHumid);
        tvSmoke = findViewById(R.id.tvSmoke);
        tvTankLevel = findViewById(R.id.tvTankLevel);
        tvPHLevel = findViewById(R.id.tvPHLevel);
        tvMoisture = findViewById(R.id.tvMoisture);
        tvLDR = findViewById(R.id.tvLDR);
        btnTempAndHumid = findViewById(R.id.btnTempAndHumid);
        btnSmoke = findViewById(R.id.btnSmoke);
        btnTankLevel = findViewById(R.id.btnTankLevel);
        btnPHLevel = findViewById(R.id.btnPHLevel);
        btnMoisTure = findViewById(R.id.btnMoisTure);
        btnLDR = findViewById(R.id.btnLDR);
        btnTempAndHumid.setOnClickListener(this);
        btnSmoke.setOnClickListener(this);
        btnTankLevel.setOnClickListener(this);
        btnPHLevel.setOnClickListener(this);
        btnMoisTure.setOnClickListener(this);
        btnLDR.setOnClickListener(this);

        //Calling the Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //  getdata();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnTempAndHumid:
                lastQuery = databaseReference.child("dht11-readings").orderByKey().limitToLast(1);
                lastQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        for (String childKey: map.keySet()){
                            Map<String, Object> currentObject = (Map<String, Object>) map.get(childKey);
                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                           //  Log.d(TAG, "Value is: " + currentObject);
                            tvTempAndHumid.setText("" + currentObject);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Failed to read value.", error.toException());

                    }
                });
                break;
            case R.id.btnSmoke:
                lastQuery = databaseReference.child("smoke readings").orderByKey().limitToLast(1);
                lastQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        for (String childKey: map.keySet()){
                            Map<String, Object> currentLubnaObject = (Map<String, Object>) map.get(childKey);
                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                            Log.d(TAG, "Value is: " + currentLubnaObject);
                            tvSmoke.setText("" + currentLubnaObject);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Failed to read value.", error.toException());

                    }
                });
                break;
            case R.id.btnTankLevel:
                lastQuery = databaseReference.child("ultrasonic readings").orderByKey().limitToLast(1);
                lastQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        for (String childKey: map.keySet()){
                            Map<String, Object> currentLubnaObject = (Map<String, Object>) map.get(childKey);
                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                            Log.d(TAG, "Value is: " + currentLubnaObject);
                            tvTankLevel.setText("" + currentLubnaObject);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Failed to read value.", error.toException());

                    }
                });

                break;
//            case R.id.btnPHLevel:
//                lastQuery = databaseReference.child("ldr - readings").orderByKey().limitToLast(1);
//                lastQuery.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                        for (String childKey: map.keySet()){
//                            Map<String, Object> currentLubnaObject = (Map<String, Object>) map.get(childKey);
//                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
//                            Log.d(TAG, "Value is: " + currentLubnaObject);
//                            tvPHLevel.setText("" + currentLubnaObject);
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//                        Log.w(TAG, "Failed to read value.", error.toException());
//
//                    }
//                });
//                break;
            case R.id.btnMoisTure:
                lastQuery = databaseReference.child("moisture-readings").orderByKey().limitToLast(1);
                lastQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        for (String childKey: map.keySet()){
                            Map<String, Object> currentLubnaObject = (Map<String, Object>) map.get(childKey);
                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                            Log.d(TAG, "Value is: " + currentLubnaObject);
                            tvMoisture.setText("" + currentLubnaObject);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Failed to read value.", error.toException());

                    }
                });
                break;
            case R.id.btnLDR:
                lastQuery = databaseReference.child("LDR - readings").orderByKey().limitToLast(1);
                lastQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        for (String childKey: map.keySet()){
                            Map<String, Object> currentLubnaObject = (Map<String, Object>) map.get(childKey);
                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                            Log.d(TAG, "Value is: " + currentLubnaObject);
                            tvLDR.setText("" + currentLubnaObject);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Failed to read value.", error.toException());

                    }
                });
                break;
            case R.id.btn_capture:
                break;
            case R.id.btn_select_from_gallary:
                break;

        }

    }
}











//    private void getdata() {
//        lastQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String address =snapshot.child("dh11_value").getValue(String.class);
//               // tvTempAndHumid.setText(address);
//                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                Log.d(TAG, "Value is: " + map);
//                tvTempAndHumid.setText("" + map);
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//                Log.w(TAG, "Failed to read value.", error.toException());
//
//            }
//        });
//    }