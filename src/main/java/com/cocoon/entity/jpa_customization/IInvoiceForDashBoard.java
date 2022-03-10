package com.cocoon.entity.jpa_customization;

/**
 * used for customizing the jpa native query with aggregation functions
 * we could not solve this dynamic table field problem with entity structure
 * we received this error: SQL Error: 1054 SQLState: 42S22 sqlexceptionhelper unknown column in 'field list'
 * resource: https://www.baeldung.com/jpa-queries-custom-result-with-aggregation-functions
 */
public interface IInvoiceForDashBoard {
    String getInvoiceNumber();
    String getInvoiceType();
    String getInvoiceDate();
    String getSezgin();
}
