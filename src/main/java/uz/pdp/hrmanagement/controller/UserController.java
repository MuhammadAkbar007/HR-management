package uz.pdp.hrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.UserDto;
import uz.pdp.hrmanagement.security.JwtProvider;
import uz.pdp.hrmanagement.service.UserService;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    //YANGI USER(MANAGER, XODIM) QO'SHISH
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody UserDto userDto) throws MessagingException {
        ApiResponse apiResponse = userService.add(userDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    //MA'LUMOTLARNI O'ZGARTIRISH, FOYDALANUVCHILAR FAQAT O'ZLARINING MA'LUMOTLARINI O'ZGARTIRA OLADI
    @PutMapping
    public HttpEntity<?> edit(@Valid @RequestBody UserDto userDto) throws MessagingException {
        ApiResponse apiResponse = userService.edit(userDto);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    //TOKEN BO'YICHA FOYDALANUVCHI MA'LUMOTLARINI QAYTARADI
    @GetMapping("/me")
    public HttpEntity<?> getByToken(){
        ApiResponse apiResponse = userService.getOne();
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    //EMAIL BO'YICHA FOYDALANUVCHI MA'LUMOTLARINI QAYTARADI
    @GetMapping()
    public HttpEntity<?> getByEmail(@RequestParam String email){
        ApiResponse apiResponse = userService.getByEmail(email);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    //EMAILNI TASDIQLANG
    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String email, @RequestParam String code) {
        ApiResponse apiResponse = userService.verifyEmail(email, code);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
