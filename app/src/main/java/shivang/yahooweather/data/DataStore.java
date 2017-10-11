package shivang.yahooweather.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHIVVVV on 10/10/2017.
 */

public class DataStore {

    private static String description;
    private static List<ForecastObject> dataList = null;


    public static String getDescription() {
        return description;
    }

    public static void setDescription(String descriptionData) {
        description = descriptionData;
    }

    public static List<ForecastObject> getDataList() {
        return dataList;
    }

    public static void setDataList(List<ForecastObject> dataListVal) {
        if(dataList == null){
            dataList = new ArrayList<>();
            dataList.addAll(dataListVal);
        }
        else{
            dataList = dataListVal;
        }
    }


}
