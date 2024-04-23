package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Shampoo;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class ShampooImpl extends BaseProduct implements Shampoo {
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int BRAND_NAME_MIN_LENGTH = 2;
    private static final int BRAND_NAME_MAX_LENGTH = 10;
    private static final String PRICE_IS_NEGATIVE = "%s should be non negative.";
    public static final int PRICE_LOWEST_VALUE = 0;


    private int milliliters;
    private UsageType usageType;

    public ShampooImpl(String name, String brand, double price, GenderType genderType, int milliliters, UsageType usageType) {
        super(name, brand, price, genderType);
        setMilliliters(milliliters);
        this.usageType = usageType;
    }

    private void setMilliliters(int milliliters) {
        validateNumberValue(milliliters, "Milliliters");
        this.milliliters = milliliters;
    }

    @Override
    public int getMillilitres() {
        return milliliters;
    }

    @Override
    public UsageType getUsageType() {
        return usageType;
    }

    @Override
    protected void validateNumberValue(double price, String type) {
        if (price < PRICE_LOWEST_VALUE) {
            throw new IllegalArgumentException(String.format(PRICE_IS_NEGATIVE, type));
        }
    }

    @Override
    protected void validateNameLength(String stringToValidate) {
        ValidationHelpers.validateStringLength(stringToValidate, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Name");
    }

    @Override
    protected void validateBrandNameLength(String brand) {
        ValidationHelpers.validateStringLength(brand, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, "Brand");
    }

    @Override
    public String print() {
        return super.print() +
                String.format(" #Milliliters: %d", milliliters) + System.lineSeparator() +
                String.format(" #Usage: %s", usageType.toString()) + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShampooImpl shampoo = (ShampooImpl) o;
        return getName().equals(shampoo.getName()) &&
                getBrandName().equals(shampoo.getBrandName()) &&
                getPrice() == shampoo.getPrice() &&
                getGenderType().equals(shampoo.getGenderType()) &&
                getMillilitres() == shampoo.getMillilitres() &&
                getUsageType().equals(shampoo.getUsageType());
    }
}
