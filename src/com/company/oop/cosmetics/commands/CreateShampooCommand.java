package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateShampooCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    public static final String SHAMPOO_PRODUCT = "Shampoo";

    private final CosmeticsRepository cosmeticsRepository;

    public CreateShampooCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String shampooName = parameters.get(0);
        String shampooBrand = parameters.get(1);
        double price = ParsingHelpers.tryParseDouble(parameters.get(2), ParsingHelpers.INVALID_PRICE);
        GenderType genderType = ParsingHelpers.tryParseGender(parameters.get(3));
        int milliliters = ParsingHelpers.tryParseInt(parameters.get(4), ParsingHelpers.INVALID_MILLILITRES);
        UsageType usageType = ParsingHelpers.tryParseUsageType(parameters.get(5));

        return createShampoo(shampooName, shampooBrand, price, genderType, milliliters, usageType);
    }

    private String createShampoo(String shampooName, String shampooBrand, double price, GenderType genderType, int milliliters, UsageType usageType) {
        if (cosmeticsRepository.productExist(shampooName)) {
            throw new IllegalArgumentException(String.format(ParsingHelpers.PRODUCT_NAME_ALREADY_EXISTS, SHAMPOO_PRODUCT, shampooName));
        }

        cosmeticsRepository.createShampoo(shampooName, shampooBrand, price, genderType, milliliters, usageType);

        return String.format(ParsingHelpers.PRODUCT_CREATED, SHAMPOO_PRODUCT, shampooName);
    }

}
