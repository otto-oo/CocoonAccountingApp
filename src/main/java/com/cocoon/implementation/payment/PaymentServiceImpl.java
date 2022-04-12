package com.cocoon.implementation.payment;

import com.cocoon.dto.CompanyDTO;
import com.cocoon.dto.PaymentDTO;
import com.cocoon.entity.Company;
import com.cocoon.entity.Payment;
import com.cocoon.enums.Months;
import com.cocoon.repository.PaymentRepository;
import com.cocoon.service.CompanyService;
import com.cocoon.util.MapperUtil;
import com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers;
import com.stripe.Stripe;
import com.stripe.exception.*;

import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yapily.ApiException;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class PaymentServiceImpl implements com.cocoon.service.PaymentService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;
    private MapperUtil mapperUtil;
    private CompanyService companyService;

    public PaymentServiceImpl(MapperUtil mapperUtil, CompanyService companyService, PaymentRepository paymentRepository) {
        this.mapperUtil = mapperUtil;
        this.companyService = companyService;
        this.paymentRepository = paymentRepository;
    }

    private PaymentRepository paymentRepository;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();

        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());

        return Charge.create(chargeParams);
    }
    @Override
    public List<PaymentDTO> getAllPaymentsByYear(int year) {

        LocalDate instance = LocalDate.now().withYear(year);
        LocalDate dateStart = instance.with(firstDayOfYear());
        LocalDate dateEnd = instance.with(lastDayOfYear());
        var company = companyService.getCompanyByLoggedInUser();


        List<Payment> payments = paymentRepository.findAllByYearBetweenAndCompanyId(dateStart, dateEnd, company.getId());

        return payments.stream().map(obj -> mapperUtil.convert(obj, new PaymentDTO())).collect(Collectors.toList());
    }

    @Override
    public void createPaymentsIfNotExist(int year) {

        LocalDate instance = LocalDate.now().withYear(year);
        LocalDate dateStart = instance.with(firstDayOfYear());
        LocalDate dateEnd = instance.with(lastDayOfYear());
        CompanyDTO companyDto = companyService.getCompanyByLoggedInUser();

        List<Payment> payments = paymentRepository.findAllByYearBetweenAndCompanyId(dateStart, dateEnd, companyDto.getId());


        if (payments.size() == 0) {
            for (Months month : Months.values()){
                Payment payment = new Payment();
                payment.setMonth(month);
                payment.setYear(LocalDate.now().withYear(year));
                payment.setPaid(false);
                payment.setAmount(250);
                payment.setCompany(mapperUtil.convert(companyDto, new Company()));
                paymentRepository.save(payment);
            }
        }

    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.getById(id);
        return mapperUtil.convert(payment, new PaymentDTO());
    }






    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDTO) {
        return null;
    }

    @Override
    public void makePaymentWithSelectedInstitution(String id) throws ApiException, URISyntaxException {

    }
}
