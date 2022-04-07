package com.cocoon.repository;

import com.cocoon.entity.Company;
import com.cocoon.entity.Invoice;
import com.cocoon.entity.InvoiceProduct;
import com.cocoon.entity.jpa_customization.IInvoiceForDashBoard;
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

    //todo Avoiding  writing native queries asap
    @Query(nativeQuery = true, value = "select i.*, intable.cost_with_tax as sezgin from invoice as i left join " +
            "    (select id as invoice_prodcut_id, invoice_id, sum((qty * price) + (qty * price * tax * 0.01)) as cost_with_tax " +
            "           from invoice_product where invoice_id in (select id from invoice where company_id=?) group by invoice_id) " +
            " as intable on i.id=intable.invoice_id where i.company_id=? order by i.invoice_date, i.id desc limit 3;")
    List<Invoice> getDashboardInvoiceTop3(Long companyId, Long companyId2);

    //todo Avoiding  writing native queries asap
    //todo @Query("SELECT NEW com.powerapp.domain.dto.BalanceDetailDTO(ubd.amount, ubd.balanceType, ubd.createdDate, ubd.affectedBy) " +
    //            " from UserBalanceDetail ubd join ubd.userBalance ub" +
    //            " join ub.user u " +
    //            " where u.guid = :userId and ubd.createdDate between :startDate and :endDate and ubd.balanceType= :balanceType order by ubd.id desc ")
    //    List<BalanceDetailDTO> retrieveBalanceDetailByCriteria(@Param("userId") UUID userId,
    //                                                           @Param("balanceType") BalanceType balanceType,
    //                                                           @Param("startDate") Date startDate,
    //                                                           @Param("endDate") Date endDate);
    @Query(nativeQuery = true, value = "select i.invoice_type as InvoiceType, i.invoice_date as InvoiceDate, i.invoice_number as InvoiceNumber, intable.cost_with_tax as Sezgin from invoice as i left join " +
            "    (select id as invoice_prodcut_id, invoice_id, sum((qty * price) + (qty * price * tax * 0.01)) as cost_with_tax " +
            "           from invoice_product where invoice_id in (select id from invoice where company_id=51) group by invoice_id) " +
            " as intable on i.id=intable.invoice_id where i.company_id=51 order by i.invoice_date, i.id desc limit 3;")
    //todo sql injection
    List<IInvoiceForDashBoard> getDashboardInvoiceTop3Interface(Long companyId, Long companyId2);
}
