package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import com.isl.gbm.rbibond.dao.RbiBondCentralizedInterestTrailRunDao;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("RbiBondInterestPaymentValidationWriter")
public class RbiBondInterestPaymentValidationWriter implements ItemWriter<RbiBondCentralizedInterestTrailRunDtoRequest> {
    @Autowired
    private RbiBondCentralizedInterestTrailRunDao rbiBondCentralizedInterestTrailRunDao;

    @Override
    public void write(org.springframework.batch.item.Chunk<? extends RbiBondCentralizedInterestTrailRunDtoRequest> chunk) throws Exception {
        for (RbiBondCentralizedInterestTrailRunDtoRequest item : chunk) {
            rbiBondCentralizedInterestTrailRunDao.responseBatchForRbiBondIntValidation(item);
        }
    }
}
