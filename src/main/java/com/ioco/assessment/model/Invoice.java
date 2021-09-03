package com.ioco.assessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioco.assessment.util.PrecisionUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "client name can not be null")
    private String client;

    @Min(value = 0L,message = "VAT rate can not be negative")
    private Long vatRate;

    @NotNull(message = "invoice date can not be null")
    private LocalDate invoiceDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private List<LineItem> lineItems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }


    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public BigDecimal getSubTotal() {
        if(CollectionUtils.isEmpty(lineItems))
            return BigDecimal.ZERO;

        BigDecimal subTotal = lineItems.stream()
                .map(a -> a.getUnitePrice().multiply(BigDecimal.valueOf(a.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return PrecisionUtil.setDefaultScale(subTotal);
    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public BigDecimal getVat() {
        if(CollectionUtils.isEmpty(lineItems))
            return BigDecimal.ZERO;

        BigDecimal subTotal = getSubTotal();
        BigDecimal vatAmount = subTotal.multiply(BigDecimal.valueOf(getVatRate())).divide(BigDecimal.valueOf(100L));
        return PrecisionUtil.setDefaultScale(vatAmount);

    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public BigDecimal getTotal() {
        if(CollectionUtils.isEmpty(lineItems))
            return BigDecimal.ZERO;

        return PrecisionUtil.setDefaultScale(getSubTotal().add(getVat()));
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", vatRate=" + vatRate +
                ", invoiceDate=" + invoiceDate +
                ", lineItems=" + lineItems +
                '}';
    }
}
