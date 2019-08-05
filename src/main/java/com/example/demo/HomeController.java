package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

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
    public String index(Model model){
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
//        model.addAttribute("users", userRepository.findAll());
//        model.addAttribute("flights", flightRepository.findAll());

        model.addAttribute("flights", flightRepository.findByDiscount(true));

//        //If there is a user logged in get the user
//        if(userService.getUser() != null) {
//            model.addAttribute("user", userService.getUser());
//        }

        return "deals";
    }

    @PostMapping("/processFlight")
    public String processFlight(@Valid @ModelAttribute("flight") Flight flight,
                                @RequestParam("flightId") long id,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("flights", flightRepository.findAll());
            return "home";
        }

        System.out.println("old flight is " + id);
        Flight f = flightRepository.findById(id).get();
        f.setQuantity(flight.getQuantity());
        f.setSeatClass(flight.getSeatClass());
        f.setSeatType(flight.getSeatType());

        flightRepository.save(f);

        return "redirect:/payment/" + id;

    }

    @RequestMapping("/payment/{id}")
    public String createCard(@PathVariable("id") long id,
                             @ModelAttribute Flight flight, Model model){

        model.addAttribute("flight", flightRepository.findById(id).get());

        model.addAttribute("card", new Card());

        return "payment";
    }

    @PostMapping("/payment")
    public String payment(@Valid Card card, @RequestParam("flightId") long id,
                          BindingResult result){
        if(result.hasErrors()){
            return "payment";
        }
        Flight flight = flightRepository.findById(id).get();
        flight.setUser(userService.getUser());
        flightRepository.save(flight);

        card.setUser(userService.getUser());
        cardRepository.save(card);
        return "redirect:/ticket";
    }


    @RequestMapping("/updateFlight")
    public String updateFlight(Model model){
        model.addAttribute("flights", flightRepository.findAll());
        return "home";
    }

    @RequestMapping("/cancelFlight")
    public String cancelFlight(@ModelAttribute Flight flight, Model model){
        model.addAttribute("flights", flightRepository.findAll());
        flight.setQuantity(0);
        flight.setSeatClass(null);
        flight.setSeatType(null);
        return "home";
    }

    @RequestMapping("/ticket")
    public String ticketPrint(Model model){
//    model.addAttribute("user", userRepository.findById(id).get());
        model.addAttribute("flights", flightRepository.findAllByUser(userService.getUser()));
        model.addAttribute("user", userService.getUser());
//        QRCodeGenerator qr = new QRCodeGenerator();
//        qr.setUser(userService.getUser());
//        qrCodeRepository.save(qr);
//        qr.getQR(id);

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

    @RequestMapping("/addNewCard")
    public String addNewCard(Model model){
        model.addAttribute("card", new Card());

        return "card";
    }

    @RequestMapping("/updateCard/{id}")
    public String updateCard(@PathVariable("id") long id, Model model){
        model.addAttribute("card", cardRepository.findById(id));
        return "card";
    }

    @PostMapping("/processCard")
    public String processCard(@Valid Card card, BindingResult result){
        if(result.hasErrors()){
            return "card";
        }
        card.setUser(userService.getUser());
        cardRepository.save(card);
        return "userProfile";
    }

    @RequestMapping("/deleteCard/{id}")
    public String deleteCard(@PathVariable("id") long id){
        cardRepository.deleteById(id);
        return "userProfile";
    }

//Yun Created /about (contact page)
@GetMapping("/about")
public String getAbout() {
    return "about";
}




}
