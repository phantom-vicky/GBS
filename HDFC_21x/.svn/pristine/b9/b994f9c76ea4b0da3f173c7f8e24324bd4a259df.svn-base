package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.GbmBctView;
import com.isl.gbm.cbs.integration.manager.BankBranchesDetailsManager;
import com.isl.gbm.cbs.integration.service.BankBranchesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankBranchesDetailsServiceImpl implements BankBranchesDetailsService {

    @Autowired
    private BankBranchesDetailsManager bankBranchesDetailsManager;

    public List<GbmBctView> isValidSortCode(final String sortCode) {

        return this.bankBranchesDetailsManager.isValidSortCode(sortCode);

    }


}
