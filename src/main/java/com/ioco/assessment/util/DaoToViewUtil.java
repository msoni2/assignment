package com.ioco.assessment.util;

import com.ioco.assessment.model.Invoice;
import com.ioco.assessment.model.LineItem;
import com.ioco.assessment.view.model.InvoiceView;
import com.ioco.assessment.view.model.LineItemView;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DaoToViewUtil {

    public static InvoiceView invoiceToView(Invoice invoice) {

        if (invoice == null)
            return null;

        InvoiceView invoiceView = new InvoiceView();
        invoiceView.setId(invoice.getId());
        invoiceView.setClient(invoice.getClient());
        invoiceView.setVatRate(invoice.getVatRate());
        invoiceView.setInvoiceDate(invoice.getInvoiceDate());
        invoiceView.setSubTotal(invoice.getSubTotal());
        invoiceView.setVatAmount(invoice.getVat());
        invoiceView.setTotal(invoice.getTotal());

        List<LineItemView> lineItemViewList = new ArrayList<>();
        for (LineItem lineItem : invoice.getLineItems()) {
            LineItemView lineItemView = new LineItemView();
            lineItemView.setId(lineItem.getId());
            lineItemView.setQuantity(lineItem.getQuantity());
            lineItemView.setUnitePrice(lineItem.getUnitePrice());
            lineItemView.setLineItemTotal(lineItem.lineItemTotal());
            lineItemViewList.add(lineItemView);
        }
        invoiceView.setLineItems(lineItemViewList);


        return invoiceView;
    }

    public static List<InvoiceView> invoiceListToViewList(List<Invoice> invoiceList) {

        if (CollectionUtils.isEmpty(invoiceList))
            return null;

        List<InvoiceView> invoiceViewList = new ArrayList<>();
        for (Invoice invoice : invoiceList) {
            InvoiceView invoiceView = invoiceToView(invoice);
            invoiceViewList.add(invoiceView);
        }
        return invoiceViewList;
    }
}
