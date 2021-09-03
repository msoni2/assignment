package com.ioco.assessment.view.model;

import com.ioco.assessment.model.LineItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceView {
    private Long id;
    private String client;
    private Long vatRate;
    private LocalDate invoiceDate;
    private List<LineItemView> lineItemViews;
    private BigDecimal subTotal;
    private BigDecimal vatAmount;
    private BigDecimal total;

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

    public List<LineItemView> getLineItems() {
        return lineItemViews;
    }

    public void setLineItems(List<LineItemView> lineItemViews) {
        this.lineItemViews = lineItemViews;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "InvoiceView{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", vatRate=" + vatRate +
                ", invoiceDate=" + invoiceDate +
                ", lineItems=" + lineItemViews +
                ", subTotal=" + subTotal +
                ", vatAmount=" + vatAmount +
                ", total=" + total +
                '}';
    }

}
