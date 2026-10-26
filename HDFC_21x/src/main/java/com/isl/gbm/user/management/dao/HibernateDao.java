/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.isl.gbm.user.management.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Convenience superclass for DAOs that contains annotations for injecting the session factory
 * and accessing the session.
 */
@Transactional
@Repository
public abstract class HibernateDao {

//    @Autowired
//    DataSource dataSource;

    /**
     * The session factory.
     */
    // @Autowired
    private SessionFactory sessionFactory;
    @PersistenceContext
    EntityManager em;

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets the session.
     *
     * @return the session
     */
    public Session getSession() {
//        System.out.println("PoolSize: " + dataSource.getPoolSize());
//        System.out.println("Max Idle: " + dataSource.getMaxIdle());
//        System.out.println("Max Active: " + dataSource.getMaxIdle());
//        System.out.println("PoolSize: " + dataSource.getPoolSize());
//        System.out.println("Active: " + dataSource.getNumActive());
//        System.out.println("Idle: " + dataSource.getNumIdle());
//        System.out.println("PoolName: " + dataSource.getPoolName());
//        System.out.println("PoolSize: " + dataSource.getPoolSize());
//        sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
//        sessionFactory = ;
//        Session    session = sessionFactory.getCurrentSession();
        return em.unwrap(Session.class);
    }

}
