package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateCreamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    public static final String CREAM_PRODUCT = "Cream";

    private final CosmeticsRepository cosmeticsRepository;

    public CreateCreamCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String creamName = parameters.get(0);
        String creamBrand = parameters.get(1);
        double price = ParsingHelpers.tryParseDouble(parameters.get(2), ParsingHelpers.INVALID_PRICE);
        GenderType genderType = ParsingHelpers.tryParseGender(parameters.get(3));
        ScentType scentType = ParsingHelpers.tryParseScent(parameters.get(4));

        return createCream(creamName, creamBrand, price, genderType, scentType);
    }

    private String createCream(String creamName, String creamBrand, double price, GenderType genderType, ScentType scentType) {
        if (cosmeticsRepository.productExist(creamName)) {
            throw new IllegalArgumentException(String.format(ParsingHelpers.PRODUCT_NAME_ALREADY_EXISTS, CREAM_PRODUCT, creamName));
        }

        cosmeticsRepository.createCream(creamName, creamBrand, price, genderType, scentType);

        return String.format(ParsingHelpers.PRODUCT_CREATED, CREAM_PRODUCT, creamName);
    }

}
