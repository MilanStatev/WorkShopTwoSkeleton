package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateToothpasteCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    public static final String TOOTHPASTE_PRODUCT = "Toothpaste";

    private final CosmeticsRepository cosmeticsRepository;

    public CreateToothpasteCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String name = parameters.get(0);
        String brandName = parameters.get(1);
        double price = ParsingHelpers.tryParseDouble(parameters.get(2),ParsingHelpers.INVALID_PRICE);
        GenderType genderType = ParsingHelpers.tryParseGender(parameters.get(3));
        List<String> ingredients = List.of(parameters.get(4).split(","));

        return createToothpaste(name, brandName, price, genderType, ingredients);
    }

    private String createToothpaste(String productName, String brandName, double price, GenderType genderType, List<String> ingredients) {
        if (cosmeticsRepository.productExist(productName)) {
            throw new IllegalArgumentException(String.format(ParsingHelpers.PRODUCT_NAME_ALREADY_EXISTS, TOOTHPASTE_PRODUCT, productName));
        }

        cosmeticsRepository.createToothpaste(productName,brandName,price,genderType,ingredients);

        return String.format(ParsingHelpers.PRODUCT_CREATED, TOOTHPASTE_PRODUCT, productName);
    }


}