package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.user.management.dao.IntimateWarDeploymentDAO;
import com.isl.gbm.user.management.dto.IntimateWarDeploymentDTO;
import com.isl.gbm.user.management.util.CbdtProcNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Repository
public class IntimateWarDeploymentDAOImpl implements IntimateWarDeploymentDAO {

    private static final Logger log = LoggerFactory.getLogger(IntimateWarDeploymentDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO getWarDeploymentDetails() {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        IntimateWarDeploymentDTO intimateWarDeploymentResponseDTO = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(CbdtProcNameEnum.SP_GBM_WAR_DEP_ALERT.getProcName());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);


                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    intimateWarDeploymentResponseDTO = new IntimateWarDeploymentDTO();
                    intimateWarDeploymentResponseDTO.setWarDeploymentDate(stmt.getString(1));
                } else {
                    resultMsg = CommonConstants.NDF;
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(intimateWarDeploymentResponseDTO);
        return responseDataDTO;
    }
}
