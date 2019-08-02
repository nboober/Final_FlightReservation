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
    @Size(min=10)
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Size(min=2, max=10)
    @Column(name="dateOfBirth")
    private String dateOfBirth;

    @NotNull
    @Size(min=2)
    @Column(name="countryOfCitizenship")
    private String originCountry;

    @NotNull
    @Size(min=2)
    private String address;

    @NotNull
    @Size(min=2)
    private String city;

    @NotNull
    @Size(min=2)
    private String state;

    @NotNull
    @Size(min=2)
    private String zipcode;

    private double totalCost;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Flight> flight;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<QRCodeGenerator> qrCode;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Card> card;


    public User(){

    }

    public User(String email, String password, String firstName, String lastName, boolean enabled, String username, String phone, String dateOfBirth, String originCountry, String address, String city, String state, String zipcode){
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEnabled(enabled);
        this.setUsername(username);
        this.setPhone(phone);
        this.setDateOfBirth(dateOfBirth);
        this.setOriginCountry(originCountry);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setZipcode(zipcode);

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

    public Set<Flight> getFlight() {
        return flight;
    }

    public void setFlight(Set<Flight> flight) {
        this.flight = flight;
    }

    public String getTotalCost() {
        int total = 0;
        int additionalCosts = 0;

        for(Flight flight : getFlight()){

            if(flight.getSeatType() == "window"){
                additionalCosts += 5;
            }

            total += ((flight.getPrice() + additionalCosts) * flight.getQuantity());
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

    public Set<QRCodeGenerator> getQrCode() {
        return qrCode;
    }

    public void setQrCode(Set<QRCodeGenerator> qrCode) {
        this.qrCode = qrCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Set<Card> getCard() {
        return card;
    }

    public void setCard(Set<Card> card) {
        this.card = card;
    }
}
