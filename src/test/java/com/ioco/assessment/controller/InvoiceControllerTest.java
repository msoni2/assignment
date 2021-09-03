package com.ioco.assessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.ioco.assessment.model.Invoice;
import com.ioco.assessment.model.LineItem;
import com.ioco.assessment.repository.InvoiceRepository;
import com.ioco.assessment.repository.LineItemRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class InvoiceControllerTest {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    LineItemRepository lineItemRepository;

    ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        objectMapper.registerModule(new JSR310Module());
        invoiceRepository.deleteAll();
        lineItemRepository.deleteAll();
    }

    @Test
    public void addInvoice() throws Exception {
        Invoice invoice = getDemoInvoice();


        MockHttpServletResponse response = mockMvc.perform(post("/invoice")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(invoice)))
                .andDo(print())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.client").value("Sam Electronics"))
                .andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        assertEquals(true, invoiceRepository.findById(Long.valueOf(id)).isPresent());

    }

    private Invoice getDemoInvoice() {
        Invoice invoice = new Invoice();
        invoice.setClient("Sam Electronics");
        invoice.setVatRate(5L);
        invoice.setInvoiceDate(LocalDate.now());

        LineItem lineItem = new LineItem();
        lineItem.setDescription("Mouse");
        lineItem.setQuantity(20L);
        lineItem.setUnitePrice(BigDecimal.valueOf(20.20d));

        LineItem lineItem2 = new LineItem();
        lineItem2.setDescription("Keyboard");
        lineItem2.setQuantity(13L);
        lineItem2.setUnitePrice(BigDecimal.valueOf(30.5d));

        List<LineItem> lineItemList = new ArrayList<>();
        lineItemList.add(lineItem);
        lineItemList.add(lineItem2);

        invoice.setLineItems(lineItemList);
        return invoice;
    }

    @Test
    public void viewAllInvoices() throws Exception {
        addInvoice();
        MockHttpServletResponse response = mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", greaterThan(0)))
                .andReturn()
                .getResponse();

        Assert.notNull(response.getContentAsString());

    }

    @Test
    @AfterTestMethod()
    public void viewInvoice() throws Exception {
        addInvoice();
        MockHttpServletResponse response = mockMvc.perform(get("/invoices/1"))
                .andDo(print())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andReturn()
                .getResponse();

        Assert.notNull(response.getContentAsString());
    }

}