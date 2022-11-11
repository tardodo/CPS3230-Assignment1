package com.screenscraper.components;

import com.screenscraper.components.pageobjects.Product;

public class TechAlert {

    int alertType;
    String heading;
    String description;
    String url;
    String imageUrl;
    String postedBy;
    int priceInCents;

    public TechAlert(Product prod){
        alertType = 6;
        heading = prod.productTitle;
        description = prod.productDescription;
        url = prod.productLink;
        imageUrl = prod.productImage;
        postedBy = "c483fe67-d39d-429a-9afa-273d26d2fe35";
        priceInCents = prod.productPrice;

    }

}
