package com.uos.upkodah.local.map.fragment.data;

import androidx.databinding.BindingAdapter;
import com.uos.upkodah.local.position.PositionInformation;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class KakaoMapBindingAdapter {
    @BindingAdapter({"android:mapCenterLon", "android:mapCenterLat"})
    public static void setCenter(MapView view, double longitude, double latitude){
        if(latitude==0 || longitude==0) return;
        view.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
    }
    @BindingAdapter("android:mapMarkers")
    public static void setMarkers(MapView view, List<? extends PositionInformation> positions){
        view.removeAllPOIItems();
        if(positions==null) return;
        for(PositionInformation p : positions){
            p.drawInto(view);
        }
    }
    @BindingAdapter("android:mapZoom")
    public static void setZoom(MapView view, int zoom){
        view.setZoomLevel(zoom, true);
    }
    @BindingAdapter("android:mapZoom")
    public static void setZoom(MapView view, float zoom){
        view.setZoomLevelFloat(zoom, true);
    }
}