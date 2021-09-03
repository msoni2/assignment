package com.ioco.assessment.view.model;

import com.ioco.assessment.model.LineItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LineItemView {
    private Long id;
    private Long quantity;
    private BigDecimal unitePrice;
    private BigDecimal LineItemTotal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(BigDecimal unitePrice) {
        this.unitePrice = unitePrice;
    }

    public BigDecimal getLineItemTotal() {
        return LineItemTotal;
    }

    public void setLineItemTotal(BigDecimal lineItemTotal) {
        LineItemTotal = lineItemTotal;
    }
}
