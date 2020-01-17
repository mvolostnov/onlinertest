package by.onliner.test.data.entities;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class QuadrocopterData {

    protected List<String> materials;
    private String minimalRange;
    private String engineType;

    /*
    public QuadrocopterDataBuilder builder() {
        return new QuadrocopterDataBuilder();
    }

    public class QuadrocopterDataBuilder extends QuadrocopterData {

        public void material(String material) {
            this.material = material;
        }

        public QuadrocopterData build() {
            return new QuadrocopterData(material, ***);
        }

    }
     */
}
