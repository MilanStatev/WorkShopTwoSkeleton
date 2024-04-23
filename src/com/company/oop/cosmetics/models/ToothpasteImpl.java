package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Toothpaste;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ToothpasteImpl extends BaseProduct implements Toothpaste {
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int BRAND_NAME_MIN_LENGTH = 2;
    private static final int BRAND_NAME_MAX_LENGTH = 10;
    private static final String PRICE_IS_NEGATIVE = "%s should be non negative.";
    public static final int PRICE_LOWEST_VALUE = 0;


    private List<String> ingredients;


    public ToothpasteImpl(String name, String brandName, double price, GenderType genderType, List<String> ingredients) {
        super(name, brandName, price, genderType);
        this.ingredients = ingredients;
    }

    @Override
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    @Override
    protected void validateNumberValue(double price, String type) {
        if (price < PRICE_LOWEST_VALUE) {
            throw new IllegalArgumentException(String.format(PRICE_IS_NEGATIVE, type));
        }
    }

    @Override
    protected void validateNameLength(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Name");
    }

    @Override
    protected void validateBrandNameLength(String brand) {
        ValidationHelpers.validateStringLength(brand, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, "Brand");
    }

    @Override
    public String print() {
        return super.print() +
                String.format(" #Ingredients: %s", ingredients) + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToothpasteImpl toothpaste = (ToothpasteImpl) o;
        return getName().equals(toothpaste.getName()) &&
                getBrandName().equals(toothpaste.getBrandName()) &&
                getPrice() == toothpaste.getPrice() &&
                this.getGenderType().equals(toothpaste.getGenderType()) &&
                getIngredients().equals(toothpaste.getIngredients());
    }
}
