package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    private UserService userService;

    @Autowired
    QRCodeRepository qrCodeRepository;

    @Autowired
    CardRepository cardRepository;







    //Home
    @RequestMapping("/")
    public String index(@Valid Flight flight, BindingResult result, Model model){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("flights", flightRepository.findAll());

        //If there is a user logged in get the user
        if(userService.getUser() != null) {
            model.addAttribute("user", userService.getUser());
        }
        return "home";
    }

    @RequestMapping("/deals")
    public String deals(Model model){
        model.addAttribute("flights", flightRepository.findByDiscount(true));

        return "deals";
    }

    @RequestMapping("/flightoptions")
    public String flightOptions(Model model){
        model.addAttribute("flights", flightRepository.findAll());

        return "options";
    }

    @RequestMapping("/payment/{id}")
    public String createCard(@PathVariable("id") long id,
                             @ModelAttribute Flight flight, Model model){

        model.addAttribute("flights", flightRepository.findById(id).get());
        model.addAttribute("card", new Card());

        return "payment";
    }

    @PostMapping("/payment")
    public String payment(@PathVariable("id") long id,
            @Valid Card card, @ModelAttribute Flight flight,
                          BindingResult result, Model model){
//        model.addAttribute("flights", flightRepository.findAllByUser(userService.getUser()));
        if(result.hasErrors()){
            return "payment";
        }
        flight.setUser(userService.getUser());
        flightRepository.save(flight);

        model.addAttribute("user", userService.getUser());
        model.addAttribute("flight", flightRepository.findById(id).get());
        card.setUser(userService.getUser());
        cardRepository.save(card);
        return "ticket";
    }









    @RequestMapping("/updateFlight")
    public String updateFlight(Model model){
        model.addAttribute("flights", flightRepository.findAll());
        return "home";
    }

    @RequestMapping("/cancelFlight/{id}")
    public String cancelFlight(@PathVariable("id") long id, Model model){
        flightRepository.deleteById(id);
        return "home";
    }

    @RequestMapping("/ticket/{id}")
    public String ticketPrint(@PathVariable("id") long id,Model model){
//    model.addAttribute("user", userRepository.findById(id).get());
        flightRepository.findAllByUser(userService.getUser());
        model.addAttribute("user", userService.getUser());
        QRCodeGenerator qr = new QRCodeGenerator();
        qr.setUser(userService.getUser());
        qrCodeRepository.save(qr);
        qr.getQR(id);


        return "ticket";
    }

    @RequestMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", userRepository.findById(id).get());
        return "updateUser";
    }

    @PostMapping("/process")
    public String processUpdates(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){

        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "updateUser";
        }

        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model){
        userRepository.deleteById(id);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin";
    }

//Yun Created /about (contact page)
@GetMapping("/about")
public String getAbout() {
    return "about";
}




}
