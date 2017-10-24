package home.inna.fc.controller;

import home.inna.fc.data.Account;
import home.inna.fc.repository.AccountRepository;
import home.inna.fc.service.HeroService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {

    private AccountRepository accountRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private HeroService heroService;

    public SignUpController(AccountRepository accountRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public void post(@RequestBody SignUp signUp) {
        Account account = new Account();
        account.setEmail(signUp.email);
        account.setPassword(bCryptPasswordEncoder.encode(signUp.getPassword()));
        accountRepository.save(account);

        heroService.save(account.getId(), signUp.getName());
    }

    @Data
    public static class SignUp {
        private String email;
        private String password;
        private String name;
    }


}
