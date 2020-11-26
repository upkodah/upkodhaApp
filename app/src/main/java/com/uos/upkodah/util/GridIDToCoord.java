package com.uos.upkodah.util;

import androidx.annotation.Nullable;

import com.uos.upkodah.data.local.gps.GeoCoordinate;
import com.uos.upkodah.data.local.gps.GeoCoordinateUtil;

import static com.uos.upkodah.util.CoordConverter.dmsToDegree;

public class GridIDToCoord {
    public final static int X_ID_LENGTH = 4;
    public final static double KOR_BND_UPP = dmsToDegree(43,0,36);
    public final static double KOR_BND_LOW = dmsToDegree(32,7,22);
    public final static double KOR_BND_RIGHT = dmsToDegree(131,52,22);
    public final static double KOR_BND_LEFT = dmsToDegree(124,10,47);

    public final static double LON_GRID_SIZE = 0.0036d;
    public final static double LAT_GRID_SIZE = 0.0036d;


    @Nullable
    public static GeoCoordinate convert(int gridID){
        // ID를 String으로 변환하여 넘긴다.
        return convert(Integer.toString(gridID));
    }
    @Nullable
    public static GeoCoordinate convert(String gridID){
        // X_ID_LENGTH만큼의 앞자리는 Longitude, 나머지는 Latitude다.
        try{
            String lonStr = gridID.substring(0,X_ID_LENGTH);
            String latStr = gridID.substring(X_ID_LENGTH);

            double longitude = Integer.parseInt(lonStr);
            double latitude = Integer.parseInt(latStr);

            longitude = KOR_BND_LEFT + LON_GRID_SIZE * (longitude + 0.5);
            latitude = KOR_BND_LOW + LAT_GRID_SIZE * (latitude + 0.5);

            return GeoCoordinateUtil.getInstance(longitude, latitude);
        }
        catch(NumberFormatException e){
            return null;
        }
    }


}
