package navermapproject.controller;

import navermapproject.model.geocoding.GeoCode;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class NaverMapApiTest {

    public NaverMapApiTest() throws FileNotFoundException {
        this.naverMapApi = new NaverMapApi();
    }

    NaverMapApi naverMapApi;


    @Test
    void getGeoCode() throws Exception {
        GeoCode result = naverMapApi.getGeoCode("엄광로 176");

        System.out.println(result.toString());
    }
}
