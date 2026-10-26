/*
 *
 */
package com.isl.gbm.cbs.integration.dao.impl;


import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.SolDateDao;
import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.model.GbmSolDate;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
//import com.isl.gbm.stateTax.util.ProcNameEnum;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.internal.OracleTypes;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository

public class SolDateDaoImpl extends HibernateDao implements SolDateDao {

    private static final Logger log = LoggerFactory.getLogger(SolDateDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SolDateDao#getAll()
     */
    @Override
    public GbmSolDate getSolDateRecord() {
        final Session session = this.getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<GbmSolDate> criteriaQuery = builder.createQuery(GbmSolDate.class);
        final Root<GbmSolDate> root = criteriaQuery.from(GbmSolDate.class);
        criteriaQuery.select(root);
        final List<GbmSolDate> solDateList = session.createQuery(criteriaQuery).getResultList();
        GbmSolDate solDate = CollectionUtils.isEmpty(solDateList) ? null : solDateList.get(0);
        if (null != solDate) {
            session.evict(solDate);
        } else {
            solDate = new GbmSolDate();
        }
        return solDate;
    }

    @Override
    public ResponseDataDTO getSolDateRecordInquiry() {
        Connection connection = null;
        CallableStatement stmt = null;
        SolDateDto solDate = null;
        List<SolDateDto> detailsList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResultSet rs = null;
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_cbs_pop_sol_date_inq.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    rs = (ResultSet) stmt.getObject(2);
                    solDate = new SolDateDto();
                    if (rs != null) {

                        detailsList = new ArrayList<>();
                        while (rs.next()) {

                            solDate = new SolDateDto();
                            solDate.setId(rs.getInt(1));
                            solDate.setSolDate1(rs.getString(2));
                            solDate.setAddlText1(rs.getString(3));
                            solDate.setAddlText2(rs.getString(4));
                            solDate.setAddlText3(rs.getString(5));
                            solDate.setModifiedDate(rs.getDate(6));

                            detailsList.add(solDate);

                        }



                    }


                }


            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(detailsList);
        return responseDataDTO;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SolDateDao#saveOrUpdate(com.isl.gbm
     * .cbs.integration.model.SolDate)
     */
/*    @Override
    public void saveOrUpdate(final GbmSolDate solDate) {
        //solDate.setSolDate(DateUtils.getDateWithoutTime(solDate.getSolDate()));
        final Session session = getSession();
        final String HQL = "update GbmSolDate set solDate = :solDate, addlText1=:addlText1, addlText2=:addlText2,addlText3=:addlText3 where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter("solDate", DateUtils.getDateWithoutTime(solDate.getSolDate()));
        query.setParameter("addlText1", solDate.getAddlText1());
        query.setParameter("addlText2", solDate.getAddlText2());
        query.setParameter("addlText3", solDate.getAddlText3());
        query.setParameter("id", solDate.getId());
        query.executeUpdate();
        //getSession().saveOrUpdate(solDate);
    }*/

    @Override
    public ResponseDataDTO saveDetailsSolDate(SolDateDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_cbs_pop_sol_date.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSolDate1());
                stmt.setString(index++, dto.getAddlText1());
                stmt.setString(index++, dto.getAddlText2());
                stmt.setString(index++, dto.getAddlText3());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


}
