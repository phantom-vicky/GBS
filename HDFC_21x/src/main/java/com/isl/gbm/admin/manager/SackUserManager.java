package com.isl.gbm.admin.manager;

import com.isl.gbm.admin.dao.GbmBondBulkIntPmtDao;
import com.isl.gbm.admin.dao.SackUserDao;
import com.isl.gbm.admin.dto.SackUserDto;
import com.isl.gbm.admin.model.SackUserMaster;
import com.isl.gbm.common.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SackUserManager {


    /**
     * The sack user dao.
     */
    @Autowired
    private SackUserDao sackUserDao;

    /**
     * The gbm bond bulk int pmt dao.
     */
    @Autowired
    private GbmBondBulkIntPmtDao gbmBondBulkIntPmtDao;

    /**
     * Gets the sack user list.
     *
     * @param userId the user id
     * @param solId  the sol id
     * @return the sack user list
     */
    public List<SackUserDto> getSackUserList(final String userId, final String solId) {

        final List<SackUserMaster> masterList = this.sackUserDao.getSackUserList(userId, solId);
        final List<SackUserDto> dtoList = new ArrayList<>();
        for (final SackUserMaster master : masterList) {
            final SackUserDto dto = new SackUserDto();
            dto.setId(master.getId());
            dto.setSolId(master.getSolId());
            dto.setUserId(master.getUserId());
            dto.setInTime(master.getInTime());
            dtoList.add(dto);
        }
        return dtoList;
    }


    public SackUserDto getSolId() {

        final SackUserMaster masterDtls = this.sackUserDao.getUserId(CommonConstants.USER_ID);
        final SackUserDto dto = new SackUserDto();
        dto.setSolId(masterDtls.getSolId());

        return dto;
    }

    /**
     * Sack master.
     *
     * @param dtoList the dto list
     * @return the sack user master
     */
    private SackUserMaster sackMaster(final SackUserDto dtoList) {
        final SackUserMaster masterDtls = new SackUserMaster();
        masterDtls.setId(dtoList.getId());
        masterDtls.setSolId(dtoList.getSolId());
        masterDtls.setOutTime(new Date());
        masterDtls.setInTime(dtoList.getInTime());
        masterDtls.setUserId(dtoList.getUserId());
        return masterDtls;

    }

    /**
     * Sets the gbm bond bulk int pmt dao.
     *
     * @param gbmBondBulkIntPmtDao the new gbm bond bulk int pmt dao
     */
    public void setGbmBondBulkIntPmtDao(final GbmBondBulkIntPmtDao gbmBondBulkIntPmtDao) {
        this.gbmBondBulkIntPmtDao = gbmBondBulkIntPmtDao;
    }

    /**
     * Sets the sack user dao.
     *
     * @param sackUserDao the new sack user dao
     */
    public void setSackUserDao(final SackUserDao sackUserDao) {
        this.sackUserDao = sackUserDao;
    }

    /**
     * Update sack user list.
     *
     * @param userId  the user id
     * @param solId   the sol id
     * @param dtoList the dto list
     */
    public void updateSackUserList(final String userId, final String solId, final List<SackUserDto> dtoList) {
        final List<SackUserMaster> sackUserMasterDtls = new ArrayList<>(dtoList.size());

        for (final SackUserDto dto : dtoList) {
            sackUserMasterDtls.add(this.sackMaster(dto));
        }

        this.sackUserDao.updateSackUserList(userId, solId, sackUserMasterDtls);
        this.gbmBondBulkIntPmtDao.updateGbmBondBulkIntPmt(userId, solId, sackUserMasterDtls);

    }
}
