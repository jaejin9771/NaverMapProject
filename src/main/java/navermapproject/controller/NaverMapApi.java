package navermapproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import navermapproject.model.geocoding.Address;
import navermapproject.model.geocoding.GeoCode;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.io.IOException;

public class NaverMapApi {

    final String URL = "https://naveropenapi.apigw.ntruss.com";
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    String clientId = "";
    String clientKey = "";


    public GeoCode getGeoCode(String query) throws Exception {
        HttpUrl httpUrl = HttpUrl.parse(URL).newBuilder()
                .addPathSegment("map-geocode")
                .addPathSegment("v2")
                .addPathSegment("geocode")
                .addQueryParameter("query", query)
                .build();

        Request request = new Request.Builder()
                .addHeader("X-NCP-APIGW-API-KEY-ID", clientId)
                .addHeader("X-NCP-APIGW-API-KEY", clientKey)
                .url(httpUrl.url())
                .build();

        Response response = client.newCall(request).execute();

        return objectMapper.readValue(response.body().string(), GeoCode.class);
    }

    public ImageIcon getMapImage(Address address, int level) throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(URL).newBuilder()
                .addPathSegment("map-static")
                .addPathSegment("v2")
                .addPathSegment("raster")
                .addQueryParameter("center", address.getX() + "," + address.getY())
                .addQueryParameter("level", String.valueOf(level))
                .addQueryParameter("w", "700")
                .addQueryParameter("h", "500")
                .addQueryParameter("markers", "type:t|size:mid|pos:" + address.getX() + " " + address.getY() + "|label:" + address.getRoadAddress())
                .build();

        Request request = new Request.Builder()
                .addHeader("X-NCP-APIGW-API-KEY-ID", clientId)
                .addHeader("X-NCP-APIGW-API-KEY", clientKey)
                .url(httpUrl.url())
                .build();

        Response response = client.newCall(request).execute();

        return new ImageIcon(response.body().bytes());
    }
}
