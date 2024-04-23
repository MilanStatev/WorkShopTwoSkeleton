package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class CreamImpl extends BaseProduct implements Cream {
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int BRAND_NAME_MIN_LENGTH = 3;
    private static final int BRAND_NAME_MAX_LENGTH = 15;
    private static final String PRICE_MUST_BE_POSITIVE = "%s should be non negative.";
    public static final int PRICE_LOWEST_VALUE = 1;

    private ScentType scentType;

    public CreamImpl(String name, String brand, double price, GenderType genderType, ScentType scentType) {
        super(name, brand, price, genderType);
        this.scentType = scentType;
    }

    @Override
    public ScentType getScent() {
        return scentType;
    }


    @Override
    protected void validateNumberValue(double price, String type) {
        if (price < PRICE_LOWEST_VALUE) {
            throw new IllegalArgumentException(String.format(PRICE_MUST_BE_POSITIVE, type));
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
                String.format(" #Scent: %s", scentType.toString()) + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreamImpl cream = (CreamImpl) o;
        return getName().equals(cream.getName()) &&
                getBrandName().equals(cream.getBrandName()) &&
                getPrice() == cream.getPrice() &&
                getGenderType().equals(cream.getGenderType()) &&
                getScent() == cream.getScent();
    }
}
