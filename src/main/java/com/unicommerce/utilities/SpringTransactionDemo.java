package com.unicommerce.utilities;

import com.unicommerce.utilities.entity.Tenant;
import com.unicommerce.utilities.services.TenantService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringTransactionDemo {
    public static void main(String[] args) {
        annotatedTransaction();

    }
    public static void annotatedTransaction() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.unicommerce");
        context.refresh();

        TenantService tenantService = context.getBean(TenantService.class);
        Tenant tenant = tenantService.getTenant(2);

        System.out.println(tenant);

    }
}
