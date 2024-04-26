package products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data     // Makes the properties
@NoArgsConstructor  // Makes the default Constructor
@AllArgsConstructor  // Makes the Parameterized Constructor
public class ProductLombok {

    // Tell the lombok what attributes
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
    private Product.Rating rating;


    //We also had inner class
    @Data     // Makes the properties
    @NoArgsConstructor  // Makes the default Constructor
    @AllArgsConstructor  // Makes the Parameterized Constructor
    public static class Rating {

        private int rate;
        private int count;
    }


}
