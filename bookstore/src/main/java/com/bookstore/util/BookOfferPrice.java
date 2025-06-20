package com.bookstore.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookOfferPrice {
    public static float discountPrice(float actualPrice, float percentageDiscount) {
        float discountAmount = actualPrice - (actualPrice * percentageDiscount / 100);
        BigDecimal rounded = new BigDecimal(discountAmount).setScale(2, RoundingMode.HALF_UP);
//        System.out.println(rounded.floatValue());
        return rounded.floatValue();
    }

}
