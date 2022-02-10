package com.cocoon.controller;

import com.cocoon.dto.ProductDTO;
import com.cocoon.entity.ClientVendor;
import com.cocoon.enums.ProductStatus;
import com.cocoon.enums.Unit;
import com.cocoon.exception.CocoonException;
import com.cocoon.service.CategoryService;
import com.cocoon.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getAllProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "product/product-list";
    }

    @GetMapping("/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("productStatus", ProductStatus.values());
        model.addAttribute("unit", Unit.values());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/product-add";
    }

    @PostMapping("/create")
    public String saveProduct(ProductDTO productDTO){
        productService.save(productDTO);
        return "redirect:/product/list";
    }

    @GetMapping("/update/{id}")
    public String getUpdateProductPage(@PathVariable("id") Long id, Model model) throws CocoonException {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/product-edit";
    }

    @PostMapping("/update")
    public String updateProduct(ProductDTO productDTO){
        productService.save(productDTO);
        return "redirect:/product/list";
    }
    // TODO @Sezgin bring the product page to be deleted
    @GetMapping("/edit/{id}")
    public String getDeleteProductPage(@PathVariable("id") Long id, Model model) throws CocoonException {
        model.addAttribute("product", productService.getProductById(id));

        model.addAttribute("productStatus", ProductStatus.values());
        model.addAttribute("units", Unit.values());
        model.addAttribute("categories", categoryService.getAllCategories());
        //TODO @Sezgin Once security implemented, based on company user, company name will be displayed.

//        model.addAttribute("product", productService.getProductById(id).getProductStatus().getValue());
//        model.addAttribute("product", productService.getProductById(id).getUnit().getValue());
//        model.addAttribute("product", productService.getProductById(id).getCategory().getDescription());

        return "product/product-edit";
    }

    @GetMapping("/delete")
    public String delete(@PathVariable("id") Long id) throws CocoonException {
        productService.deleteById(id);

        return "redirect:/product/list";
    }

}
