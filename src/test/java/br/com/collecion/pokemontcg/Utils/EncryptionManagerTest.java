package br.com.collecion.pokemontcg.Utils;

import br.com.collecion.pokemontcg.utils.EncryptionManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EncryptionManagerTest {
    private static final String PASSWORD = "Administrator@321";

    @Test
    public void mustReturnSuccess_WhenEncript() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String result = EncryptionManager.encript(PASSWORD);
        assertTrue(EncryptionManager.validate(PASSWORD, result));
    }
}
