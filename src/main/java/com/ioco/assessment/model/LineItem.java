package com.ioco.assessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioco.assessment.util.PrecisionUtil;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "quantity name can not be null")
    @Min(value = 0L,message = "VAT rate can not be negative")
    private Long quantity;

    private String description;

    @NotNull(message = "client name can not be null")
    @DecimalMin(value = "0.0")
    private BigDecimal unitePrice;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(BigDecimal unitePrice) {
        this.unitePrice = unitePrice;
    }

    @JsonIgnore
    public BigDecimal lineItemTotal() {
        return PrecisionUtil.setDefaultScale(unitePrice.multiply(BigDecimal.valueOf(quantity)));
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", unitePrice=" + unitePrice +
                '}';
    }
}
