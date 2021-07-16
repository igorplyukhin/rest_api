package com.igor.rest_api.payload.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class ProductRequest {
    @NotBlank
    @Size(max = 20)
    private String sku;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String type;

    @NotNull
    @DecimalMin("0.0")
    private Double price;

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }
}
