package com.esisba.msorder.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PutMapping("invoices/{id}")
    public String updateInvoice(@PathVariable("id") String id, @RequestParam("state") String state) {
        return invoiceService.updateInvoice(Integer.parseInt(id), state);
    }

    @GetMapping("invoices/{supplier-name}")
    public List<Invoice> getAllInvoices(@PathVariable("supplier-name") String supplier) {
        return invoiceService.getCompanyInvoices(supplier);
    }


}
