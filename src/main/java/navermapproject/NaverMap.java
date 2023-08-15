package navermapproject;

import navermapproject.controller.NaverMapApi;
import navermapproject.model.geocoding.Address;
import navermapproject.model.geocoding.GeoCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NaverMap implements ActionListener {

    NaverMapApi naverMapApi = new NaverMapApi();

    App naverMap;

    public NaverMap(App naverMap) {
        this.naverMap = naverMap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GeoCode result = naverMapApi.getGeoCode(naverMap.address.getText());
            System.out.println(result.toString());
            map_service(result.addresses.get(0));
        } catch (Exception err) {
            System.out.println(err);
            err.getStackTrace();
        }
    }

    public void map_service(Address vo) throws IOException {
        ImageIcon img = naverMapApi.getMapImage(vo, 16);
        naverMap.imageLabel.setIcon(img);
        naverMap.resAddress.setText(vo.getRoadAddress());
        naverMap.jibunAddress.setText(vo.getJibunAddress());
        naverMap.resX.setText(vo.getX());
        naverMap.resY.setText(vo.getY());
    }
}
