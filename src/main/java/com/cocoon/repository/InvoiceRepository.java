package com.cocoon.repository;

import com.cocoon.entity.Company;
import com.cocoon.entity.Invoice;
import com.cocoon.entity.InvoiceProduct;
import com.cocoon.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findInvoicesByCompanyAndInvoiceType(Company company, InvoiceType invoiceType);
    List<Invoice> findInvoiceByCompany(Company company);

    @Query(nativeQuery = true, value = "select i.*, intable.cost_with_tax as _cost_with_tax from invoice as i left join " +
            "    (select id as invoice_prodcut_id, invoice_id, sum((qty * price) + (qty * price * tax * 0.01)) as cost_with_tax " +
            "           from invoice_product where invoice_id in (select id from invoice where company_id=?) group by invoice_id) " +
            " as intable on i.id=intable.invoice_id where i.company_id=? order by i.invoice_date, i.id desc limit 3;")
    List<Invoice> getDashboardInvoiceTop3(Long companyId, Long companyId2);
}
