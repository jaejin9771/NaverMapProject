package navermapproject.model.geocoding;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {
    public String roadAddress;
    public String jibunAddress;
    public String englishAddress;
    public ArrayList<AddressElement> addressElements;
    public String x;
    public String y;
    public double distance;
}
