package uz.pdp.hrmanagement.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.Role;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.enums.RoleName;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.repository.UserRepository;
import uz.pdp.hrmanagement.security.JwtProvider;

import java.util.Optional;
import java.util.Set;

@Component
public class Checker {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    public boolean check(String role) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            Set<Role> roles = userOptional.get().getRoles();
            String position = userOptional.get().getPosition();//XODIMLARNI QO'SHISH UCHUN HRMANAGER BO'LISHI KK
            if (role.equals(RoleName.ROLE_DIRECTOR.name()))
                return false;

            for (Role adminrole : roles) {
                if (role.equals(RoleName.ROLE_MANAGER.name()) &&
                        adminrole.getName().name().equals(RoleName.ROLE_DIRECTOR.name())) {
                    return true;
                }

                if (role.equals(RoleName.ROLE_STAFF.name()) &&
                        ((adminrole.getName().name().equals(RoleName.ROLE_MANAGER.name()) &&
                                position.equalsIgnoreCase("hrmanagement")) ||
                                adminrole.getName().name().equals(RoleName.ROLE_DIRECTOR.name()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public ApiResponse checkForAny(String role) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            Set<Role> roles = userOptional.get().getRoles(); //qo'shmoqchi bo'lgan role(hrManagement, direktor)
            if (role.equals(RoleName.ROLE_DIRECTOR.name()))
                return new ApiResponse("False!", false);

            for (Role adminrole : roles) {
                if (role.equals(RoleName.ROLE_MANAGER.name()) &&
                        adminrole.getName().name().equals(RoleName.ROLE_DIRECTOR.name())) {
                    return new ApiResponse("True", true, userOptional.get());
                }

                if (role.equals(RoleName.ROLE_STAFF.name()) &&
                        ((adminrole.getName().name().equals(RoleName.ROLE_MANAGER.name()) ||
                                adminrole.getName().name().equals(RoleName.ROLE_DIRECTOR.name())))) {
                    return new ApiResponse("True", true, userOptional.get());
                }
            }
        }
        return new ApiResponse("False!", false);
    }

    public boolean check() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            for (Role role : user.getRoles()) {
                if (role.getName().name().equals(RoleName.ROLE_DIRECTOR.name())
                        || ((role.getName().name().equals(RoleName.ROLE_MANAGER.name())
                        && user.getPosition().equalsIgnoreCase("hrmanagement")))) {
                    return true;
                }
            }
        }
        return false;
    }
}
