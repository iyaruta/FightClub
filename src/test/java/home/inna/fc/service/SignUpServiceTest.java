package home.inna.fc.service;

import home.inna.fc.entity.Account;
import home.inna.fc.entity.Hero;
import home.inna.fc.exception.ValidationException;
import home.inna.fc.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SignUpServiceTest {

    /*
    * Тесты:
    *  - все хорош: сохранился аккаунт и герой
    *  - почта для аккаунта занята, сохранений не было
    *  - имя героя занято - ни аккаунт, ни герой не сохраняются
    * */

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private HeroService heroService;

    @InjectMocks
    private SignUpService signUpService;


    @Test
    public void happyPath() {
        when(passwordEncoder.encode("b")).thenReturn("b_encoded");
        when(accountRepository.save(any(Account.class))).then(answer -> {
            Account account = answer.getArgumentAt(0, Account.class);
            account.setId(5L);
            return account;
        });

        signUpService.signUp("a", "b", "c");

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());
        Account account = accountCaptor.getValue();
        assertNotNull(account);
        assertEquals("a", account.getEmail());
        assertEquals("b_encoded", account.getPassword());

        ArgumentCaptor<Long> accIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        verify(heroService).save(accIdCaptor.capture(), nameCaptor.capture());

        assertEquals(Long.valueOf(5), accIdCaptor.getValue());
        assertEquals("c", nameCaptor.getValue());
    }

    @Test
    public void heroExists() {
        when(heroService.findByName("c")).thenReturn(new Hero());

        try {
            signUpService.signUp("a", "b", "c");
            fail("Validation exception should be thrown");
        } catch (ValidationException e) {
            verify(accountRepository, never()).save(any(Account.class));
        }

    }

    @Test
    public void emailExists() {
        when(accountRepository.findByEmail("a")).thenReturn(new Account());

        try {
            signUpService.signUp("a", "b", "c");
            fail("Validation exception should be thrown");
        } catch (ValidationException e) {
            verify(heroService, never()).findByName(anyString());
            verify(accountRepository, never()).save(any(Account.class));

        }

    }


}