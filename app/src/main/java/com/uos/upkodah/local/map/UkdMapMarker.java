package com.uos.upkodah.local.map;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

import com.uos.upkodah.local.position.PositionInformation;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class UkdMapMarker implements MapDrawable{
    private MapPOIItem marker = new MapPOIItem();

    protected UkdMapMarker(PositionInformation positionInformation){
        // 기본적으로 좌표와 기본핀 이미지를 설정하고, 이름은 주소 전체로 합니다.
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(positionInformation.getLatitude(), positionInformation.getLongitude()));
        marker.setItemName(positionInformation.getPostalAddress());
    }

    public static UkdMapMarker.Builder getBuilder(PositionInformation positionInformation){
        UkdMapMarker mapMarker = new UkdMapMarker(positionInformation);
        return mapMarker.new Builder();
    }

    @Override
    public void drawInto(MapView mapView) {
        mapView.addPOIItem(this.marker);
    }

    public class Builder{
        /**
         * 마커의 이미지를 커스텀 이미지로 전환합니다.
         * @param bitmap
         * @return
         */
        public Builder setMarkerImage(Bitmap bitmap){
            UkdMapMarker.this.marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            UkdMapMarker.this.marker.setCustomImageBitmap(bitmap);
            UkdMapMarker.this.marker.setCustomImageAutoscale(false);
            UkdMapMarker.this.marker.setCustomImageAnchor(0.5f, 1.0f);

            return this;
        }

        /**
         * 마커의 이름을 정합니다.
         * @param name
         * @return
         */
        public Builder setName(String name){
            UkdMapMarker.this.marker.setItemName(name);

            return this;
        }

        public UkdMapMarker build(){
            return UkdMapMarker.this;
        }
    }
}