package com.isl.gbm.user.management.dto;

import com.isl.gbm.user.management.security.shiro.config.ValidPassword;
import com.isl.gbm.user.management.util.UserAccountUtils;

/**
 * The Class PasswordDto.
 */
public class PasswordDto {

    /**
     * The password.
     */
    //@Pattern(message = "Password should match the password policy", regexp = "/^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!#$%&'()*+,-./:;<=>?@[\\]^_` {|}~])[A-Za-z\\d !#$%&'()*+,-./:;<=>?@[\\]^_` {|}~]{6,25}$/")
    private String password;

    /**
     * Password After Decoding password
     */
    @ValidPassword
    private String decodedPassword;

    /**
     * The confirm password.
     */
    private String confirmPassword;

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the Decoded password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
        this.decodedPassword = UserAccountUtils.decodeAesPassword(password);
    }

    /**
     * Gets the confirm password.
     *
     * @return the confirm password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirm password.
     *
     * @param confirmPassword the new confirm password
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Checks if is valid.
     *
     * @return true, if is valid
     */
    //@AssertTrue(message = "password should be equal to confirm password")
    private boolean isValid() {
        return this.password.equals(this.confirmPassword);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PasswordDto [password=" + password + ", confirmPassword=" + confirmPassword + "]";
    }

}
