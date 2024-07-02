package com.xworkz.crisis.dto;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "crisis")
public class CrisisDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crisis_id")
    private int id;

    @NotNull(message = "First Name cannot be null")
    @Size(min = 3, max = 50, message = "First Name must be between 3 and 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @Size(min = 3, max = 50, message = "Last Name must be between 3 and 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    @Column(name = "email")
    @Email(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Contact Number cannot be null")
    @Digits(integer = 10, fraction = 0, message = "Contact number should be numeric and less than 10 digits")
    @Column(name = "contact_number")
    private long contactNumber;

    @Digits(integer = 10, fraction = 0, message = "Contact number should be numeric and less than 10 digits")
    @Column(name = "alternative_contact_number")
    private long alternativeContactNumber;

    @NotNull(message = "Address cannot be null")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    @Column(name = "address")
    private String address;

    @Transient
    private boolean agreement;

    private String createdBy;

    private LocalDateTime createdOn;

    private String updatedBy;

    private LocalDateTime updatedOn;

    private boolean isActive;

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{}|;:',.<>?]+$", message = "Password must be alphanumeric with special characters")
    @Column(name = "password")
    private String password;

    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts;

    public static final int MAX_LOGIN_ATTEMPTS = 3;
    @Column(name = "account_locked")
    private boolean accountLocked;

    @Column(name="new_Password")
    private String newPassword;
    @Transient
    private String confirmPassword;


    public CrisisDto() {
        System.out.println("no param form crisis dto");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public long getAlternativeContactNumber() {
        return alternativeContactNumber;
    }

    public void setAlternativeContactNumber(long alternativeContactNumber) {
        this.alternativeContactNumber = alternativeContactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setAuditColumns(String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive) {
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "CrisisDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", alternativeContactNumber='" + alternativeContactNumber + '\'' +
                ", address='" + address + '\'' +
                ", agreement=" + agreement +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", isActive=" + isActive +
                ", password='" + password + '\'' +
                ", failedLoginAttempts=" + failedLoginAttempts +
                ", accountLocked=" + accountLocked +
                '}';
    }
}
