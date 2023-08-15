package navermapproject.controller;

import navermapproject.model.geocoding.GeoCode;
import org.junit.jupiter.api.Test;

class NaverMapApiTest {

    NaverMapApi naverMapApi = new NaverMapApi();

    @Test
    void getGeoCode() throws Exception {
        GeoCode result = naverMapApi.getGeoCode("엄광로 176");

        System.out.println(result.toString());
    }
}
