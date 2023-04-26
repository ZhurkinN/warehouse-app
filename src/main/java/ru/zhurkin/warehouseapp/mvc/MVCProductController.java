package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.service.ProductService;
import ru.zhurkin.warehouseapp.support.dto.ProductBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProductMapper;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class MVCProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", productMapper.toDtos(productService.getAll()));
        return "/products/viewProducts";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "products/addProduct";
    }

    @PostMapping("/add")
    public String addProvider(@ModelAttribute("productForm") ProductBodyDTO dto) {
        productService.add(productMapper.toEntity(dto));
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id,
                             Model model) {
        model.addAttribute("product", productMapper.toDto(productService.getById(id)));
        return "/products/viewProduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                Model model) {
        model.addAttribute("product", productMapper.toDto(productService.getById(id)));
        return "products/updateProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("productForm") ProductBodyDTO dto) {
        productService.update(productMapper.toEntity(dto));
        return "redirect:/products";
    }
}
