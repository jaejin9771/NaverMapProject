package navermapproject.view;


import lombok.SneakyThrows;
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
    JLabel resAddress, resX, resY, jibunAddress, imageLabel;
    JButton btnSearch, btnZoomIn, btnZoomOut;

    NaverMapApi naverMapApi = new NaverMapApi();

    Address searchResult;

    int level = 16;


    public MainScreen() {
        JFrame frm = new JFrame("Map View");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 상단 패널
        JPanel northPanel = new JPanel();
        JLabel addressLbl = new JLabel("주소 입력");
        address = new JTextField(45);
        btnSearch = new JButton("검색");
        btnZoomIn = new JButton("+");
        btnZoomOut = new JButton("-");

        northPanel.add(addressLbl);
        northPanel.add(address);
        northPanel.add(btnSearch);
        northPanel.add(btnZoomIn);
        northPanel.add(btnZoomOut);

        btnSearch.setActionCommand("search");
        btnSearch.addActionListener(this);

        btnZoomIn.setActionCommand("zoom-in");
        btnZoomIn.addActionListener(this);

        btnZoomOut.setActionCommand("zoom-out");
        btnZoomOut.addActionListener(this);

        // 지도 패널
        imageLabel = new JLabel();

        // 하단 패널
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

        // 프레임에 패널 추가
        Container c = frm.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(BorderLayout.NORTH, northPanel);
        c.add(BorderLayout.CENTER, imageLabel);
        c.add(BorderLayout.SOUTH, pan1);

        frm.setSize(730, 660);
        frm.setVisible(true);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("search")) {
            GeoCode result = naverMapApi.getGeoCode(address.getText());
            System.out.println(result.toString());
            searchResult = result.addresses.get(0);
            updateMap();

        } else if (e.getActionCommand().equals("zoom-in")) {
            level++;
            updateMap();
        } else if (e.getActionCommand().equals("zoom-out")) {
            level--;
            updateMap();
        }
    }

    public void updateMap() throws IOException {
        ImageIcon img = naverMapApi.getMapImage(searchResult, level);
        imageLabel.setIcon(img);
        resAddress.setText(searchResult.getRoadAddress());
        jibunAddress.setText(searchResult.getJibunAddress());
        resX.setText(searchResult.getX());
        resY.setText(searchResult.getY());
    }
}
