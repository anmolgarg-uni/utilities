package com.unicommerce.utilities.services.impl;

import com.unicommerce.utilities.entity.Tenant;
import com.unicommerce.utilities.repository.TenantRepository;
import com.unicommerce.utilities.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Override
    @Transactional(readOnly = true)
    public Tenant getTenant(Integer id) {
        Tenant tenant = tenantRepository.findTenantById(id);
        return tenant;
    }
}
