package com.controllers;

import com.models.Coin;
import com.models.VendProduct;
import com.services.CoinService;
import com.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineController {
    private CoinService coinService;
    private ProductService productService;

    public VendingMachineController(CoinService coinService, ProductService productService) {
        this.coinService = coinService;
        this.productService = productService;
    }

    public VendProduct purchase(String productSelection, List<Coin> coins) {
        List<Coin> validCoins = coins.stream()
                .filter(CoinService::isValidCoin)
                .collect(Collectors.toList());
        coinService.countChange(validCoins);

        productService.getProductCost(productSelection);

        return null;
    }
}
