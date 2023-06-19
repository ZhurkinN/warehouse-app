package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.mvc.model.SearchProductDTO;
import ru.zhurkin.warehouseapp.repository.product.ProviderRepository;
import ru.zhurkin.warehouseapp.service.ProductService;
import ru.zhurkin.warehouseapp.support.dto.ProductBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProductMapper;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class MVCProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProviderRepository providerRepository;

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        Page<Product> productPage = productService.getAll(pageRequest);
        Page<ProductBodyDTO> productDtoPage = new PageImpl<>(productMapper.toDtos(productPage.getContent()), pageRequest, productPage.getTotalElements());
        model.addAttribute("products", productDtoPage);
        model.addAttribute("repository", providerRepository);
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
        model.addAttribute("repository", providerRepository);
        return "/products/viewProduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.softDelete(id);
        return "redirect:/products";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        productService.restore(id);
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

    @PostMapping("/search")
    public String search(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            @ModelAttribute("productSearchForm") SearchProductDTO dto,
            Model model
    ) {
        if (!StringUtils.hasText(dto.productTitle()) && !StringUtils.hasText(dto.providerName())) {
            return "redirect:/products";
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
            Page<Product> productPage = productService.findProducts(dto, pageRequest);
            Page<ProductBodyDTO> productDtoPage = new PageImpl<>(productMapper.toDtos(productPage.getContent()), pageRequest, productPage.getTotalElements());
            model.addAttribute("products", productDtoPage);
            model.addAttribute("repository", providerRepository);
            return "products/viewProducts";
        }
    }
}
