package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="User_Data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @NotNull
    @Size(min=2)
    @Column(name="email", nullable = false)
    private String email;

    @NotNull
    @Size(min=2)
    @Column(name="password")
    private String password;

    @NotNull
    @Size(min=2)
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Size(min=2)
    @Column(name="last_name")
    private String lastName;

    @Column(name = "enabled")
    private boolean enabled;

    @NotNull
    @Size(min=2)
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min=2)
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Size(min=2)
    @Column(name="DOB")
    private String dateOfBirth;

    @NotNull
    @Size(min=2)
    @Column(name="countryOfCitizenship")
    private String originCountry;

    @NotNull
    @Size(min=2)
    @Column(name="card_First_Name")
    private String cardFirstName;

    @NotNull
    @Size(min=2)
    @Column(name="card_Last_Name")
    private String cardLastName;

    @NotNull
    @Min(1)
    @Column(name="cardNumber")
    private long cardNumber;

    private double totalCost;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Flight> flight;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<QRCodeGenerator> qrCode;


    public User(){

    }

    public User(String email, String password, String firstName, String lastName, boolean enabled, String username, String phone, String dateOfBirth, String originCountry){
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEnabled(enabled);
        this.setUsername(username);
        this.setPhone(phone);
        this.setDateOfBirth(dateOfBirth);
        this.setOriginCountry(originCountry);
    }

    public User(String email, String password, String firstName, String lastName, boolean enabled, String username, String phone, String dateOfBirth, String originCountry, String cardFirstName, String cardLastName, long cardNumber){
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEnabled(enabled);
        this.setUsername(username);
        this.setPhone(phone);
        this.setDateOfBirth(dateOfBirth);
        this.setOriginCountry(originCountry);
        this.setCardFirstName(cardFirstName);
        this.setCardLastName(cardLastName);
        this.setCardNumber(cardNumber);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder =
                new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getCardFirstName() {
        return cardFirstName;
    }

    public void setCardFirstName(String cardFirstName) {
        this.cardFirstName = cardFirstName;
    }

    public String getCardLastName() {
        return cardLastName;
    }

    public void setCardLastName(String cardLastName) {
        this.cardLastName = cardLastName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Set<Flight> getFlight() {
        return flight;
    }

    public void setFlight(Set<Flight> flight) {
        this.flight = flight;
    }

    public String getTotalCost() {
        int total = 0;

        for(Flight flight : getFlight()){



            total += (flight.getPrice() * flight.getQuantity());
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String totalFormat = formatter.format(total);


        return totalFormat;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Set<QRCodeGenerator> getQRCode() {
        return qrCode;
    }

    public void setQRCode(Set<QRCodeGenerator> QRCode) {
        this.qrCode = QRCode;
    }
}
