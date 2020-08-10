/*
 * Copyright 2020 Christopher Zaborsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.examplemaps;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.location.LocationUpdateListener;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.Icon;
import com.tomtom.online.sdk.map.MapFragment;
import com.tomtom.online.sdk.map.Marker;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.map.TomtomMapCallback;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        LocationUpdateListener,
        TomtomMapCallback.OnMarkerClickListener {

    private static final double ZOOM_LEVEL = 12.0;
    private TomtomMap mTomtomMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getAsyncMap(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mTomtomMap.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull TomtomMap tomtomMap) {
        mTomtomMap = tomtomMap;

        tomtomMap.getMarkerSettings().setMarkersClustering(true);

        tomtomMap.setMyLocationEnabled(true);
        tomtomMap.addLocationUpdateListener(this);
        tomtomMap.addOnMarkerClickListener(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        mTomtomMap.setMyLocationEnabled(false);
        //mTomtomMap.removeLocationUpdateListener(this);

        mTomtomMap.removeMarkers();

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraPosition position = CameraPosition.builder(latLng).zoom(ZOOM_LEVEL).build();
        mTomtomMap.centerOn(position);

        addMarkers(location);
    }

    @Override
    public void onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this, "Marker Id " + marker.getId(), Toast.LENGTH_SHORT).show();
    }

    private void addMarkers(Location current) {
        for (Location location : TestData.getCluster(current)) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            SimpleMarkerBalloon balloon = new SimpleMarkerBalloon(location.getProvider());
            Icon icon = Icon.Factory.fromResources(this, R.drawable.ic_markedlocation);

            mTomtomMap.addMarker(new MarkerBuilder(latLng)
                    .markerBalloon(balloon)
                    .icon(icon)
                    .shouldCluster(true));
        }
    }
}
