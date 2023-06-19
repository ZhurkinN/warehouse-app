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
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.repository.product.ProductRepository;
import ru.zhurkin.warehouseapp.service.ProviderService;
import ru.zhurkin.warehouseapp.support.dto.ProviderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProviderMapper;

@Controller
@RequestMapping("/providers")
@RequiredArgsConstructor
public class MVCProviderController {

    private final ProviderService providerService;
    private final ProviderMapper providerMapper;
    private final ProductRepository productRepository;

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        Page<Provider> providerPage = providerService.getAll(pageRequest);
        Page<ProviderBodyDTO> providerDtoPage = new PageImpl<>(providerMapper.toDtos(providerPage.getContent()), pageRequest, providerPage.getTotalElements());
        model.addAttribute("providers", providerDtoPage);
        model.addAttribute("repository", productRepository);
        return "/providers/viewProviders";
    }

    @GetMapping("/add")
    public String addProvider() {
        return "providers/addProvider";
    }

    @PostMapping("/add")
    public String addProvider(@ModelAttribute("providerForm") ProviderBodyDTO dto) {
        providerService.add(providerMapper.toEntity(dto));
        return "redirect:/providers";
    }

    @GetMapping("/{id}")
    public String getProvider(@PathVariable Long id,
                              Model model) {
        model.addAttribute("provider", providerMapper.toDto(providerService.getById(id)));
        model.addAttribute("repository", productRepository);
        return "/providers/viewProvider";
    }

    @GetMapping("/delete/{id}")
    public String deleteProvider(@PathVariable Long id) {
        providerService.softDelete(id);
        return "redirect:/providers";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        providerService.restore(id);
        return "redirect:/providers";
    }

    @GetMapping("/update/{id}")
    public String updateProvider(@PathVariable Long id,
                                 Model model) {
        model.addAttribute("provider", providerMapper.toDto(providerService.getById(id)));
        return "providers/updateProvider";
    }

    @PostMapping("/update")
    public String updateProvider(@ModelAttribute("providerForm") ProviderBodyDTO dto) {
        providerService.update(providerMapper.toEntity(dto));
        return "redirect:/providers";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            @ModelAttribute("providerSearchForm") ProviderBodyDTO dto,
            Model model
    ) {
        if (!StringUtils.hasText(dto.getName()) || !StringUtils.hasLength(dto.getName())) {
            return "redirect:/providers";
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));
            Page<Provider> providerPage = providerService.findProviders(dto.getName(), pageRequest);
            Page<ProviderBodyDTO> providerDtoPage = new PageImpl<>(providerMapper.toDtos(providerPage.getContent()), pageRequest, providerPage.getTotalElements());
            model.addAttribute("providers", providerDtoPage);
            model.addAttribute("repository", productRepository);
            return "providers/viewProviders";
        }
    }
}
