package com.ioco.assessment.controller;

import com.ioco.assessment.model.Invoice;
import com.ioco.assessment.service.InvoiceService;
import com.ioco.assessment.view.model.InvoiceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Invoice REST Controller", description = "REST controller for create and fetch invoice")
@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;


    @ApiOperation(value = "REST call for saving Invoice", response = InvoiceView.class)
    @ApiParam()
    @PostMapping("/invoice")
    public InvoiceView addInvoice(@Valid @RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);

    }

    @ApiOperation(value = "REST call for fetch all Invoice",response = InvoiceView.class,responseContainer = "List")
    @GetMapping("/invoices")
    public List<InvoiceView> viewAllInvoices() {
        return invoiceService.getAllInvoices();
    }


    @ApiOperation(value = "REST call for fetch Invoice by id",response = InvoiceView.class)
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceView viewInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInoviceById(invoiceId);
    }


}
