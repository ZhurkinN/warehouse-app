package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.user.RoleRepository;
import ru.zhurkin.warehouseapp.service.UserService;
import ru.zhurkin.warehouseapp.support.dto.UserBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.UserMapper;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class MVCUserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "login"));
        Page<User> userPage = userService.getAll(pageRequest);
        Page<UserBodyDTO> userDtoPage = new PageImpl<>(userMapper.toDtos(userPage.getContent()), pageRequest, userPage.getTotalElements());
        model.addAttribute("users", userDtoPage);
        model.addAttribute("repository", roleRepository);
        return "/users/viewUsers";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id,
                          Model model) {
        model.addAttribute("user", userMapper.toDto(userService.getById(id)));
        model.addAttribute("repository", roleRepository);
        return "/users/viewUser";
    }
}
