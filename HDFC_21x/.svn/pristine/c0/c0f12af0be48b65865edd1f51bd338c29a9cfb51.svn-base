package com.isl.gbm.common.dao;

import com.isl.gbm.common.model.GbmSchemeParameterMaster;

import java.math.BigDecimal;
import java.util.List;

public interface GbmSchemeParameterMasterDao {

    List<GbmSchemeParameterMaster> getGeneralSchemeParameterMaintenance(String schemeType);

    GbmSchemeParameterMaster findById(BigDecimal id);

    void update(List<GbmSchemeParameterMaster> modelList);

    void verify(List<GbmSchemeParameterMaster> modelList);

    GbmSchemeParameterMaster getSysParamDetails(final String paramName);
}
