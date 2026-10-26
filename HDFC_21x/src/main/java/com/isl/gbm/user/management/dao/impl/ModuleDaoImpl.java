package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dao.ModuleDao;
import com.isl.gbm.user.management.model.Module;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * The Class ModuleDaoImpl.
 */
@Repository

public class ModuleDaoImpl extends HibernateDao implements ModuleDao {


    /**
     * The Constant STATUS.
     */
    private static final String STATUS = "status";

    @PersistenceContext
    EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.user.management.dao.ModuleDao#getModules()
     */
    @Override
    public List<Module> getModules() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<Module> criteriaQuery = builder.createQuery(Module.class);
        final Root<Module> root = criteriaQuery.from(Module.class);

        criteriaQuery.select(root).where(builder.equal(root.get(STATUS), 1));
        return session.createQuery(criteriaQuery).getResultList();
    }

}
