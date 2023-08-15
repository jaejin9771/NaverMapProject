package navermapproject.model.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationProperties {

    private Naver naver;

    @Getter
    @Setter
    public static class Naver {
        private MapClass map;

        @Getter
        @Setter
        public static class MapClass {
            private API api;

            @Getter
            @Setter
            public static class API {
                private String id;
                private String key;
            }
        }
    }
}
