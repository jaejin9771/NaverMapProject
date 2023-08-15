package navermapproject.view;


import navermapproject.controller.NaverMapApi;
import navermapproject.model.geocoding.Address;
import navermapproject.model.geocoding.GeoCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainScreen implements ActionListener {

    JTextField address;
    JLabel resAddress, resX, resY, jibunAddress;
    JLabel imageLabel;

    NaverMapApi naverMapApi = new NaverMapApi();

    int level = 16;


    public MainScreen() {
        JFrame frm = new JFrame("Map View");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = frm.getContentPane();
        imageLabel = new JLabel("지도");
        JPanel pan = new JPanel();
        JLabel addressLbl = new JLabel("주소입력");
        address = new JTextField(50);
        JButton btn = new JButton("클릭");
        pan.add(addressLbl);
        pan.add(address);
        pan.add(btn);
        btn.addActionListener(this);
        JPanel pan1 = new JPanel();
        pan1.setLayout(new GridLayout(4, 1));
        resAddress = new JLabel("도로명");
        jibunAddress = new JLabel("지번주소");
        resX = new JLabel("경도");
        resY = new JLabel("위도");
        pan1.add(resAddress);
        pan1.add(jibunAddress);
        pan1.add(resX);
        pan1.add(resY);
        c.add(BorderLayout.NORTH, pan);
        c.add(BorderLayout.CENTER, imageLabel);
        c.add(BorderLayout.SOUTH, pan1);
        frm.setSize(730, 660);
        frm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GeoCode result = naverMapApi.getGeoCode(address.getText());
            System.out.println(result.toString());
            map_service(result.addresses.get(0));
        } catch (Exception err) {
            System.out.println(err);
            err.getStackTrace();
        }
    }

    public void map_service(Address vo) throws IOException {
        ImageIcon img = naverMapApi.getMapImage(vo, level);
        imageLabel.setIcon(img);
        resAddress.setText(vo.getRoadAddress());
        jibunAddress.setText(vo.getJibunAddress());
        resX.setText(vo.getX());
        resY.setText(vo.getY());
    }
}
