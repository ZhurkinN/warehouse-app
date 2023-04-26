package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.service.ProviderService;
import ru.zhurkin.warehouseapp.support.dto.ProviderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProviderMapper;

@Controller
@RequestMapping("/providers")
@RequiredArgsConstructor
public class MVCProviderController {

    private final ProviderService providerService;
    private final ProviderMapper providerMapper;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("providers", providerMapper.toDtos(providerService.getAll()));
        return "providers/viewProviders";
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
        return "/providers/viewProvider";
    }

    @GetMapping("/delete/{id}")
    public String deleteProvider(@PathVariable Long id) {
        providerService.delete(id);
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
}
