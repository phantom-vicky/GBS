/**
 *
 */
package com.isl.gbm.transaction.channel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Class GbmFlags.
 *
 * @author Rahul.Kumar
 */
@Entity
@Table(name = "GBM_FLAGS")
public class GbmFlags {

    /** The id. */
    @Id
    @Column(name = "ID")
    private Integer id;

    /** The finacle integration flag. */
    @Column(name = "FINACLE_INTEGRATION_FLG")
    private Character finacleIntegrationFlag;

    /** The cash posting flag. */
    @Column(name = "CASH_POSTING_FLG")
    private Character cashPostingFlag;

    /** The transfer posting falg. */
    @Column(name = "TRANSFER_POSTING_FLG")
    private Character transferPostingFalg;

    /** The clearing posting flag. */
    @Column(name = "CLG_POSTING_FLG")
    private Character clearingPostingFlag;

    /** The cash payment flag. */
    @Column(name = "CASH_PAYMENT_FLG")
    private Character cashPaymentFlag;

    /** The transfer payment flag. */
    @Column(name = "TRANSFER_PAYMENT_FLG")
    private Character transferPaymentFlag;

    /** The clearing payment flag. */
    @Column(name = "CLG_PAYMENT_FLG")
    private Character clearingPaymentFlag;

    /**
     * Gets the cash payment flag.
     *
     * @return the cash payment flag
     */
    public Character getCashPaymentFlag() {
        return this.cashPaymentFlag;
    }

    /**
     * Gets the cash posting flag.
     *
     * @return the cash posting flag
     */
    public Character getCashPostingFlag() {
        return this.cashPostingFlag;
    }

    /**
     * Gets the clearing payment flag.
     *
     * @return the clearing payment flag
     */
    public Character getClearingPaymentFlag() {
        return this.clearingPaymentFlag;
    }

    /**
     * Gets the clearing posting flag.
     *
     * @return the clearing posting flag
     */
    public Character getClearingPostingFlag() {
        return this.clearingPostingFlag;
    }

    /**
     * Gets the finacle integration flag.
     *
     * @return the finacle integration flag
     */
    public Character getFinacleIntegrationFlag() {
        return this.finacleIntegrationFlag;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Gets the transfer payment flag.
     *
     * @return the transfer payment flag
     */
    public Character getTransferPaymentFlag() {
        return this.transferPaymentFlag;
    }

    /**
     * Gets the transfer posting falg.
     *
     * @return the transfer posting falg
     */
    public Character getTransferPostingFalg() {
        return this.transferPostingFalg;
    }

    /**
     * Sets the cash payment flag.
     *
     * @param cashPaymentFlag
     *            the new cash payment flag
     */
    public void setCashPaymentFlag(final Character cashPaymentFlag) {
        this.cashPaymentFlag = cashPaymentFlag;
    }

    /**
     * Sets the cash posting flag.
     *
     * @param cashPostingFlag
     *            the new cash posting flag
     */
    public void setCashPostingFlag(final Character cashPostingFlag) {
        this.cashPostingFlag = cashPostingFlag;
    }

    /**
     * Sets the clearing payment flag.
     *
     * @param clearingPaymentFlag
     *            the new clearing payment flag
     */
    public void setClearingPaymentFlag(final Character clearingPaymentFlag) {
        this.clearingPaymentFlag = clearingPaymentFlag;
    }

    /**
     * Sets the clearing posting flag.
     *
     * @param clearingPostingFlag
     *            the new clearing posting flag
     */
    public void setClearingPostingFlag(final Character clearingPostingFlag) {
        this.clearingPostingFlag = clearingPostingFlag;
    }

    /**
     * Sets the finacle integration flag.
     *
     * @param finacleIntegrationFlag
     *            the new finacle integration flag
     */
    public void setFinacleIntegrationFlag(final Character finacleIntegrationFlag) {
        this.finacleIntegrationFlag = finacleIntegrationFlag;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Sets the transfer payment flag.
     *
     * @param transferPaymentFlag
     *            the new transfer payment flag
     */
    public void setTransferPaymentFlag(final Character transferPaymentFlag) {
        this.transferPaymentFlag = transferPaymentFlag;
    }

    /**
     * Sets the transfer posting falg.
     *
     * @param transferPostingFalg
     *            the new transfer posting falg
     */
    public void setTransferPostingFalg(final Character transferPostingFalg) {
        this.transferPostingFalg = transferPostingFalg;
    }

}
