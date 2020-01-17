package by.onliner.test.data.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@Builder
@ToString

public class ExpectedData {

 //   protected List<String> materials;
    private int numberOfSearchResults;
    private int numberOfSelectedProducts;
 //   private String engineType;

}
