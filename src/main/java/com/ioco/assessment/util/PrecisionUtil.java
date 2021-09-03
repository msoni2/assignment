package com.ioco.assessment.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrecisionUtil {

    public static final int precsion = 2;

    public static BigDecimal setDefaultScale(BigDecimal bigDecimal) {
        return bigDecimal.setScale(precsion, RoundingMode.HALF_DOWN);
    }
}
