package com.unicommerce.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.unicommerce.utilities.entity.AccessResource;
import com.unicommerce.utilities.entity.Feature;
import com.unicommerce.utilities.entity.Product;
import com.unicommerce.utilities.entity.Tenant;
import com.unicommerce.utilities.services.TenantService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Transaction transaction = null;
        Session session = null;
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from Tenant t where t.code = :code");
            query.setParameter("code","stgenterprise1");
            Tenant tenant = (Tenant) query.uniqueResult();

            System.out.println(tenant);



            transaction.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
        finally {
            session.close();
        }

    }






    private static Feature createFeature() {
        Feature f = new Feature();
        f.setCode("test-code"+System.currentTimeMillis());
        f.setName("test-name"+System.currentTimeMillis());
        f.setCreated(new Date());
        f.setUpdated(new Date());
        return f;
    }

    private static void createTenant(Session session) {

        Tenant t = new Tenant();
        t.setCode("test-code");
        t.setName("test-name");
        t.setAccessUrl("test-url");
        t.setCompanyName("test-companyName");
        t.setCreated(new Date());
        t.setMode(Tenant.Mode.LIVE);
        t.setStatusCode(Tenant.StatusCode.ACTIVE);
        t.setProduct(session.load(Product.class,1));

        session.persist(t);


        System.out.println(t);
    }

    private static void nPlusOneProblem(Session session) {

        Query query = session.createQuery("from AccessResource ar join fetch ar.accessResourceGroup arg where ar.name in (:names)");
        query.setParameterList("names", Arrays.asList("ALERT","ADMIN_ALERT"));

        List<AccessResource> accessResources = query.getResultList();
       for(AccessResource accessResource:accessResources) {
            System.out.println(accessResource.getName() + " - " + accessResource.getAccessResourceGroup().getName());
       }
    }



    private static Tenant criteriaQuery(SessionFactory sessionFactory, Session session) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Tenant> criteriaQuery = criteriaBuilder.createQuery(Tenant.class);
        Root<Tenant> from = criteriaQuery.from(Tenant.class);
        criteriaQuery.select(from)
                .where(criteriaBuilder.equal(from.get("code"),"test-code"));

        Query<Tenant> query = (Query<Tenant>) session.createQuery(criteriaQuery);
        return query.uniqueResult();
    }

}

