package com.ioco.assessment.util;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PrecisionUtilTest {

    @Test
    public void setDefaultScaleTest() {
        BigDecimal bigDecimal = new BigDecimal("123.126");
        BigDecimal bigDecimal1Expected = new BigDecimal("123.13");

        BigDecimal result = PrecisionUtil.setDefaultScale(bigDecimal);
        Assert.assertEquals(bigDecimal1Expected, result);

        bigDecimal = new BigDecimal("123");
        bigDecimal1Expected = new BigDecimal("123.00");
        result = PrecisionUtil.setDefaultScale(bigDecimal);
        Assert.assertEquals(bigDecimal1Expected, result);

        bigDecimal = new BigDecimal("123.124");
        bigDecimal1Expected = new BigDecimal("123.12");
        result = PrecisionUtil.setDefaultScale(bigDecimal);
        Assert.assertEquals(bigDecimal1Expected, result);

    }

}