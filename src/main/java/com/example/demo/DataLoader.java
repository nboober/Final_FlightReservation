package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));

        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim", "240-477-0000", "05/16/1992", "USA", "11404 Berland Place", "Germantown", "MD", "20876");

        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin", "301-999-9999", "N/A", "N/A", "888 Alekerky Way", "Rockville", "Tennessee", "999999");
        user.setRoles(Arrays.asList(adminRole, userRole));
        userRepository.save(user);

        Flight dallasToDC = new Flight("Dallas Love Field", "American Airlines", "Dallas, TX", "Dulles International, Washington D.C.", 300.00, "10/16/2019", "10/16/2019", "9:00am", "10:00pm", false, false, 220, 25, false);
        flightRepository.save(dallasToDC);

        Flight dcToDallas1 = new Flight("Dulles International","United Airlines", "Washington D.C.", "Dallas, TX", 350.00, "10/17/2019", "10/17/2019", "10:00am", "11:00pm", false, false,  220, 25, false);
        flightRepository.save(dcToDallas1);

        Flight dcToDallas2 = new Flight("Dulles International", "United Airlines", "Washington D.C.", "Dallas, TX", 800.00, "10/16/2019", "10/26/2019", "9:00am", "10:00pm", true, false,  220, 25, false);
        flightRepository.save(dcToDallas2);

        Flight dcToFlorida = new Flight("Dulles International", "American Airlines", "Washington D.C.", "Miami, FL", 150.00, "12/08/2019", "12/08/2019", "4:15pm", "6:00pm", false, false,  220, 25, true);
        flightRepository.save(dcToFlorida);


    }

}
