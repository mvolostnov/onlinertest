package by.onliner.test.data.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class MainMenuData {

    private String topMenuSection;
    private String catalogCategory;
    private String catalogSubCategory;
    private String productType;
    private String menuHeaderItem;

}
