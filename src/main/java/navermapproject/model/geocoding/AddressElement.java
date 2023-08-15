package navermapproject.model.geocoding;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressElement {
    public ArrayList<String> types;
    public String longName;
    public String shortName;
    public String code;
}
