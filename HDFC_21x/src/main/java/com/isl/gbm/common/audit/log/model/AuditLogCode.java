package com.isl.gbm.common.audit.log.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gbm_audit_log_code")
public class AuditLogCode {

    @Id
    @Column(name = "link_code")
    private String link_code;



    @Column(name = "link_name")
    private String linkName;


    public String getLink_code() {
        return link_code;
    }

    public void setLink_code(String link_code) {
        this.link_code = link_code;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
}
