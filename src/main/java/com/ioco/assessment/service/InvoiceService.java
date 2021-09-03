package com.ioco.assessment.service;

import com.ioco.assessment.model.Invoice;
import com.ioco.assessment.repository.InvoiceRepository;
import com.ioco.assessment.util.DaoToViewUtil;
import com.ioco.assessment.view.model.InvoiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    public List<InvoiceView> getAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        return DaoToViewUtil.invoiceListToViewList(invoiceList);
    }

    public InvoiceView saveInvoice(Invoice invoice) {
        Invoice result = invoiceRepository.save(invoice);
        return DaoToViewUtil.invoiceToView(result);
    }

    public InvoiceView getInoviceById(Long invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if(!invoice.isPresent())
            return null;

        return DaoToViewUtil.invoiceToView(invoice.get());
    }
}
