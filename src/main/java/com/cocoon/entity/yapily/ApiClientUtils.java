package com.cocoon.entity.yapily;

import yapily.ApiClient;
import yapily.auth.HttpBasicAuth;

public class ApiClientUtils {
    public static ApiClient basicAuth() {
        ApiClient defaultClient = new ApiClient();
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("86e3ffea-61d8-4149-966c-3f321bebaa12");
        basicAuth.setPassword("fda7ddae-199a-437e-b14d-26fdc4b62e87");
        return defaultClient;
    }
}
