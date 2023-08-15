package navermapproject.model.geocoding;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GeoCode {
    public String status;
    public Meta meta;
    public ArrayList<Address> addresses;
    public String errorMessage;
}
