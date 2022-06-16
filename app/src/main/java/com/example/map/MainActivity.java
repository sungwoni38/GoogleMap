package com.example.map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private GoogleMap googleMap;
    ImageView bottom_like, bottom_search , bottom_card, bottom_more;
    ImageView img_noti;
    Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bottom_like = findViewById(R.id.bottom_like);
        bottom_like.setOnClickListener(this);

        bottom_search = findViewById(R.id.bottom_search);
        bottom_search.setOnClickListener(this);

        bottom_card = findViewById(R.id.bottom_card);
        bottom_card.setOnClickListener(this);

        bottom_more = findViewById(R.id.bottom_more);
        bottom_more.setOnClickListener(this);

        img_noti = findViewById(R.id.img_noti);
        img_noti.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        // 37.557667, 126.926546 홍대입구역

        LatLng latLng = new LatLng(37.557667, 126.926546);
        LatLng latLng2 = new LatLng(37.5992293, 127.078578);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("홍대입구역");
        MarkerOptions markerOptions2 = new MarkerOptions().position(latLng2).title("곰분식");
        googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptions2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            checkLocationPermissionWithRationale();
    }
}public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("위치정보")
                        .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bottom_like) {
            Intent intent01 = new Intent(MainActivity.this, LikeActivity.class);
            startActivity(intent01);
        }
        if(view.getId() == R.id.bottom_search) {
            Intent intent01 = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent01);
        }
        if(view.getId() == R.id.bottom_card) {
            Intent intent03 = new Intent(MainActivity.this, CardActivity.class);
            startActivity(intent03);
        }
        if(view.getId() == R.id.bottom_more) {
            Intent intent04 = new Intent(MainActivity.this, MoreActivity.class);
            startActivity(intent04);
        }
        if(view.getId() == R.id.img_noti) {
            Intent intent05 = new Intent(MainActivity.this, NotiActivity.class);
            startActivity(intent05);
        }
    }
}