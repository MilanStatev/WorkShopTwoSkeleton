package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;

public abstract class BaseProduct implements Product {
    private String name;
    private String brand;
    private double price;
    private final GenderType genderType;

    protected BaseProduct(String name, String brand, double price, GenderType genderType) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        this.genderType = genderType;
    }

    private void setName(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void setBrand(String brand) {
        validateBrandNameLength(brand);
        this.brand = brand;
    }

    private void setPrice(double price) {
        validateNumberValue(price, "Price");
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBrandName() {
        return this.brand;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public GenderType getGenderType() {
        return this.genderType;
    }

    protected abstract void validateNumberValue(double element, String type);

    protected abstract void validateNameLength(String name);

    protected abstract void validateBrandNameLength(String brand);

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("#%s %s", name, brand)).append(System.lineSeparator());
        sb.append(String.format(" #Price: $%.2f", price)).append(System.lineSeparator());
        sb.append(String.format(" #Gender: %s", genderType.toString())).append(System.lineSeparator());

        return sb.toString();
    }


}
