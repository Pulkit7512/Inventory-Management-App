package com.app.inventorymanagerapp.restController;


import com.app.inventorymanagerapp.dto.ProductDto;
import com.app.inventorymanagerapp.entity.Product;
import com.app.inventorymanagerapp.exceptionHandlers.ProductNotFoundException;
import com.app.inventorymanagerapp.service.ProductDtoService;
import com.app.inventorymanagerapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productApi")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    private ProductDtoService dtoService;

    @Autowired
    public ProductRestController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/")
    public String home(){
        return "redirect:products";
    }

    @GetMapping("/getProductsUsingDto")
    @ResponseBody
    public List<ProductDto> getProducts(){
        List<ProductDto> products = dtoService.findAll();
        return products;
    }

    @GetMapping("/addProduct")
    public String addFunc(Model model){
        Product b = new Product();
        model.addAttribute("product",b);
        return "add-a-product";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateFunc(@PathVariable(value="id") int id, Model model){
        Product b = productService.findById(id);
        model.addAttribute("product",b);
        return "update-a-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value="id") int id,Model model){
        Product b = productService.findById(id);

        if(b == null){
            throw new ProductNotFoundException("Product Id not found- "+id);
        }
        productService.deleteById(id);
        return "redirect:/productApi/products";
    }

    @GetMapping("/products")
    public String findAll(Model model){
        model.addAttribute("products",productService.findAll());
        return "listproducts";
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable int productId){

        Product b = productService.findById(productId);
        if(b == null){
            throw new ProductNotFoundException("Product Id not found- "+productId);
        }
        return b;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product b){
        productService.save(b);
        return "redirect:products";
    }

    @GetMapping("/productsView")
    public String productsViewForUser(Model model){
        model.addAttribute("products",productService.findAll());
        return "list-of-products-for-user";
    }

}
