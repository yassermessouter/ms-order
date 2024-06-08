package com.esisba.msorder.invoice;

import com.esisba.msorder.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final OrderService orderService;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public String updateInvoice(int id, String state) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        invoice.setStatus(state);
        invoiceRepository.save(invoice);
        return "Ok";

    }

    public List<Invoice> getCompanyInvoices(String supplier) {
        List<Invoice> invoices = invoiceRepository.findByCompanyName(supplier);
        return invoices.stream()
                .filter(invoice -> !orderService.isCurrentDateInSameMonth(invoice.getDate() + "-01"))
                .collect(Collectors.toList());
    }

}
