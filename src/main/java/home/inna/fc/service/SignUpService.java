package home.inna.fc.service;

import home.inna.fc.data.Account;
import home.inna.fc.data.Hero;
import home.inna.fc.exception.ValidationException;
import home.inna.fc.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HeroService heroService;

    public Hero signUp(String email, String password, String name) {
        checkAccountExists(email);
        checkHeroExists(email, name);

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
        return heroService.save(account.getId(), name);

    }

    private void checkAccountExists(String email) {
        Account accounts = accountRepository.findByEmail(email);
        if (accounts != null) {
            String message = String.format("Hero with email '%s' already exists", email);
            throw new ValidationException(message);
        }
    }

    private void checkHeroExists(String email, String name) {
        Hero hero = heroService.findByName(name);
        if (hero != null) {
            String message = String.format("Hero with name '%s' already name", email);
            throw new ValidationException(message);
        }
    }

}
