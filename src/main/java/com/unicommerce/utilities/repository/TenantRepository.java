package com.unicommerce.utilities.repository;

import com.unicommerce.utilities.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Integer> {
    public Tenant findTenantById(Integer id);
}
