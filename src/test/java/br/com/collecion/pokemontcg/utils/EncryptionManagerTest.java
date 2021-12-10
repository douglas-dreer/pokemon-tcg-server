package br.com.collecion.pokemontcg.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class EncryptionManagerTest {
    private EncryptionManager encryptionManager;

    private static final String password = "This is a completely secure password. It complies with all 10 safety requirements.";
    private static final int hashSize = 2086;

    @Test
    public void mustReturnSuccess_WhenEncript() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String result = EncryptionManager.encript(password);

        assertNotNull(result);
    }

    @Test
    public void mustReturnSuccess_WhenValidate() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hash = EncryptionManager.encript(password);
        boolean result = EncryptionManager.validate(password, hash);

        assertTrue(result);
    }

    @Test
    public void privateConstructorTest() {
        assertThrows(IllegalStateException.class, () -> EncryptionManager.applicationError());
    }
}
