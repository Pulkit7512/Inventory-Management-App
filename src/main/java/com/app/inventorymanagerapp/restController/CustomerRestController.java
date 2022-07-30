package com.app.inventorymanagerapp.restController;


import com.app.inventorymanagerapp.entity.Customer;
import com.app.inventorymanagerapp.exceptionHandlers.UserNameAlreadyExists;
import com.app.inventorymanagerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customerApi")
public class CustomerRestController {
    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String getCustomers(Model model){
        model.addAttribute("customer",customerService.findAll());
        return "list-customers";
    }

    @GetMapping("/customers/{custId}")
    public Customer getCustomer(@PathVariable int custId){

        Customer c = customerService.findById(custId);
        if(c == null){
            throw new RuntimeException("Customer Id not found- "+custId);
        }
        return c;
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer c){
            if (customerService.findByEmailId(c)) {
                throw new UserNameAlreadyExists("User Already exists with this username");
            }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(c.getPassword());
        c.setPassword(encoded);
        c.setUserId(0);
        c.setRole("User");
        customerService.save(c);
        return "redirect:/productApi/productsView";
    }

    @GetMapping("/success")
    public String registerSuccess(){
        return "register-success";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer c){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(c.getPassword());
        c.setPassword(encoded);
        customerService.save(c);
        return "redirect:/customerApi/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(value="id") int id,Model model){
        Customer c = customerService.findById(id);

        if(c == null){
            throw new RuntimeException("Customer Id not found- "+id);
        }
        customerService.deleteById(id);
        return "redirect:/customerApi/customers";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateFunc(@PathVariable(value="id") int id, Model model){
        Customer c = customerService.findById(id);
        model.addAttribute("customer",c);
        return "update-a-customer";
    }

    @GetMapping("/showFormForRegistration")
    public String addFunc(Model model){
        Customer c = new Customer();
        model.addAttribute("customer",c);
        return "register";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/customerApi/showFormForRegistration";
    }

    @GetMapping("")
    public String viewHome(){
        return "index";
    }

    @GetMapping("/adminPage")
    public String adminHome(){
        return "redirect:/productApi/products";
    }

    @GetMapping("/errorPage")
    public String error(){
        return "errorPage";
    }

}
