package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondTocSlabMaintenanceDTO;

public interface RbiBondTocSlabMaintenanceDAO {
    ResponseDataDTO selectModifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

    ResponseDataDTO selectVerifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

    ResponseDataDTO getAddDetails(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

    ResponseDataDTO save(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

    ResponseDataDTO modify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

    ResponseDataDTO verify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

    ResponseDataDTO delete(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

    ResponseDataDTO inquiry(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO);

}
