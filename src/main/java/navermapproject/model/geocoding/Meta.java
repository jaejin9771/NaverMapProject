package navermapproject.model.geocoding;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Meta {
    public int totalCount;
    public int page;
    public int count;
}
