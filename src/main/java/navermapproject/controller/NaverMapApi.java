package navermapproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import navermapproject.model.geocoding.Address;
import navermapproject.model.geocoding.GeoCode;
import navermapproject.model.properties.ApplicationProperties;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class NaverMapApi {

    final String hostUrl = "https://naveropenapi.apigw.ntruss.com";
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    String clientId ,clientKey;

    public NaverMapApi() throws FileNotFoundException {
        // application.yaml 파일을 읽어서 clientId, clientKey를 가져온다.
        URL resource = getClass().getClassLoader().getResource("application.yaml");
        ApplicationProperties applicationProperties = new Yaml().loadAs(new FileReader(resource.getPath()), ApplicationProperties.class);

        this.clientId = applicationProperties.getNaver().getMap().getApi().getId();
        this.clientKey = applicationProperties.getNaver().getMap().getApi().getKey();
    }

    public GeoCode getGeoCode(String query) throws Exception {
        HttpUrl httpUrl = HttpUrl.parse(hostUrl).newBuilder()
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
        HttpUrl httpUrl = HttpUrl.parse(hostUrl).newBuilder()
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
