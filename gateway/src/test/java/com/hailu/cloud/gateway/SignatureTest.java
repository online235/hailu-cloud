package com.hailu.cloud.gateway;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

@Disabled
@Slf4j
@DisplayName("签名测试")
public class SignatureTest {

    @Test
    @DisplayName("生成随机公私钥")
    public void getAndSetStringTest() {
        RSA rsa = new RSA();
        log.info("公钥：" + rsa.getPublicKeyBase64());
        log.info("私钥：" + rsa.getPrivateKeyBase64());
    }

    @Test
    @DisplayName("测试加密")
    public void encode() {
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK/7yJPjQbUyHDeQT0DsXxIOEpAT6xDi1enCTmhTrrz2hAJe+vhUPiF6g5B7v6hjAaSFa4hj7/FcXxo5+2SPZuX3V8euSFoc4t37MHPETvG/AkarX7J/rf41frPOLk77A+/OQiteuENCMu4kKQLxFXx9KjAB2H+J4y8tMAPmtpfXAgMBAAECgYAR960oZZJUBibkXhBWwBYKzrNYJiMgs7rZutQXib0Jn5+lwFMeXzeCt91OjJvqUhrHqd7rQMzYLIasiPv2Qz/i5520Gd1fu4D6LKIKv0vvyGow03VKteEI3S2GhGeo+yjv9WP46NmpK0mpLZGUuyFE+AYefXo5bB1VOkNd/8O1eQJBAOmpHOCKsS2w8yno5OG9G9+yVUywdPAR6kr7hkrkMvTnCj/G4WIUg5MQ7NPoqYF2AKhTM1BYTCiduV7amfJJjV8CQQDAzwN63zjw5vq6rDXf1V3aFaH4M8Vcz5qZouMqLTPVoOtB5tTBDee0lb250XmUH1fEX0Q6dhJ6BzSW1BFl/BCJAkB4oRABdFioSBQhbNTNHb5ILF8RKoAUeItGjrfz7f/Io4aNCnXNFt4ejLxXYmQwK+WHAP0a25rl1RegOqcDiKltAkBNwzBkHiOZBY+aOlqALE3t50usDzVKeXChBlEYcaK2RnCb471rH3CUJNCgYRvn4bxENygNbBGHpeBP9LgPqdoBAkAnhVM6NwG53tsaMx8rvpf98ZixgqslRX+nNN6kNGGqM64bTTP4yYWnvFSVqZjMD/8LSAkvODsaZPDtPiroJi/X";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCv+8iT40G1Mhw3kE9A7F8SDhKQE+sQ4tXpwk5oU6689oQCXvr4VD4heoOQe7+oYwGkhWuIY+/xXF8aOftkj2bl91fHrkhaHOLd+zBzxE7xvwJGq1+yf63+NX6zzi5O+wPvzkIrXrhDQjLuJCkC8RV8fSowAdh/ieMvLTAD5raX1wIDAQAB";
        long start = System.currentTimeMillis();
        RSA rsa = new RSA(privateKey, publicKey);
        log.info("耗时：{} ms", DateUtil.spendMs(start));
        String oldToken = IdUtil.simpleUUID();
        log.info("原token: {}", oldToken);
        byte[] tokenByte = rsa.encrypt(StrUtil.bytes(oldToken, CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        String hex = HexUtil.encodeHexStr(tokenByte);
        log.info("加密后token:{}", hex);
        log.info("base64后token:{}", Base64.encode(tokenByte));
    }

    @Test
    @DisplayName("测试解密")
    public void decode() {
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK/7yJPjQbUyHDeQT0DsXxIOEpAT6xDi1enCTmhTrrz2hAJe+vhUPiF6g5B7v6hjAaSFa4hj7/FcXxo5+2SPZuX3V8euSFoc4t37MHPETvG/AkarX7J/rf41frPOLk77A+/OQiteuENCMu4kKQLxFXx9KjAB2H+J4y8tMAPmtpfXAgMBAAECgYAR960oZZJUBibkXhBWwBYKzrNYJiMgs7rZutQXib0Jn5+lwFMeXzeCt91OjJvqUhrHqd7rQMzYLIasiPv2Qz/i5520Gd1fu4D6LKIKv0vvyGow03VKteEI3S2GhGeo+yjv9WP46NmpK0mpLZGUuyFE+AYefXo5bB1VOkNd/8O1eQJBAOmpHOCKsS2w8yno5OG9G9+yVUywdPAR6kr7hkrkMvTnCj/G4WIUg5MQ7NPoqYF2AKhTM1BYTCiduV7amfJJjV8CQQDAzwN63zjw5vq6rDXf1V3aFaH4M8Vcz5qZouMqLTPVoOtB5tTBDee0lb250XmUH1fEX0Q6dhJ6BzSW1BFl/BCJAkB4oRABdFioSBQhbNTNHb5ILF8RKoAUeItGjrfz7f/Io4aNCnXNFt4ejLxXYmQwK+WHAP0a25rl1RegOqcDiKltAkBNwzBkHiOZBY+aOlqALE3t50usDzVKeXChBlEYcaK2RnCb471rH3CUJNCgYRvn4bxENygNbBGHpeBP9LgPqdoBAkAnhVM6NwG53tsaMx8rvpf98ZixgqslRX+nNN6kNGGqM64bTTP4yYWnvFSVqZjMD/8LSAkvODsaZPDtPiroJi/X";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCv+8iT40G1Mhw3kE9A7F8SDhKQE+sQ4tXpwk5oU6689oQCXvr4VD4heoOQe7+oYwGkhWuIY+/xXF8aOftkj2bl91fHrkhaHOLd+zBzxE7xvwJGq1+yf63+NX6zzi5O+wPvzkIrXrhDQjLuJCkC8RV8fSowAdh/ieMvLTAD5raX1wIDAQAB";
        long start = System.currentTimeMillis();
        RSA rsa = new RSA(privateKey, publicKey);
        log.info("耗时：{} ms", DateUtil.spendMs(start));
        String token = "3e34d9329d868361c14e272db552f7218da2db1e0fe63e74bf86fcebc7166da77f8dbd042521911e7147600a0924535d328a9f52dc033634ad92204cb9f56da48dbf9e484f210b76932a9ebc27a4fff95a463d752eddf51ec854406db833a4522c73a247a066010c199cdd04d58f8a6829e7e867074f3ea110916b02793c00e6";
        log.info("原token: {}", token);
        byte[] tokenByte = HexUtil.decodeHex(token);
        String passToken = StrUtil.str(rsa.decrypt(tokenByte, KeyType.PublicKey), CharsetUtil.CHARSET_UTF_8);
        log.info("解密后后token:{}", passToken);
    }

    @Test
    @DisplayName("测试解密")
    public void decode2() {
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK/7yJPjQbUyHDeQT0DsXxIOEpAT6xDi1enCTmhTrrz2hAJe+vhUPiF6g5B7v6hjAaSFa4hj7/FcXxo5+2SPZuX3V8euSFoc4t37MHPETvG/AkarX7J/rf41frPOLk77A+/OQiteuENCMu4kKQLxFXx9KjAB2H+J4y8tMAPmtpfXAgMBAAECgYAR960oZZJUBibkXhBWwBYKzrNYJiMgs7rZutQXib0Jn5+lwFMeXzeCt91OjJvqUhrHqd7rQMzYLIasiPv2Qz/i5520Gd1fu4D6LKIKv0vvyGow03VKteEI3S2GhGeo+yjv9WP46NmpK0mpLZGUuyFE+AYefXo5bB1VOkNd/8O1eQJBAOmpHOCKsS2w8yno5OG9G9+yVUywdPAR6kr7hkrkMvTnCj/G4WIUg5MQ7NPoqYF2AKhTM1BYTCiduV7amfJJjV8CQQDAzwN63zjw5vq6rDXf1V3aFaH4M8Vcz5qZouMqLTPVoOtB5tTBDee0lb250XmUH1fEX0Q6dhJ6BzSW1BFl/BCJAkB4oRABdFioSBQhbNTNHb5ILF8RKoAUeItGjrfz7f/Io4aNCnXNFt4ejLxXYmQwK+WHAP0a25rl1RegOqcDiKltAkBNwzBkHiOZBY+aOlqALE3t50usDzVKeXChBlEYcaK2RnCb471rH3CUJNCgYRvn4bxENygNbBGHpeBP9LgPqdoBAkAnhVM6NwG53tsaMx8rvpf98ZixgqslRX+nNN6kNGGqM64bTTP4yYWnvFSVqZjMD/8LSAkvODsaZPDtPiroJi/X";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCv+8iT40G1Mhw3kE9A7F8SDhKQE+sQ4tXpwk5oU6689oQCXvr4VD4heoOQe7+oYwGkhWuIY+/xXF8aOftkj2bl91fHrkhaHOLd+zBzxE7xvwJGq1+yf63+NX6zzi5O+wPvzkIrXrhDQjLuJCkC8RV8fSowAdh/ieMvLTAD5raX1wIDAQAB";
        long start = System.currentTimeMillis();
        RSA rsa = new RSA(privateKey, publicKey);
        log.info("耗时：{} ms", DateUtil.spendMs(start));
        String token = "PjTZMp2Gg2HBTicttVL3IY2i2x4P5j50v4b868cWbad/jb0EJSGRHnFHYAoJJFNdMoqfUtwDNjStkiBMufVtpI2/nkhPIQt2kyqevCek//laRj11Lt31HshUQG24M6RSLHOiR6BmAQwZnN0E1Y+KaCnn6GcHTz6hEJFrAnk8AOY=";
        log.info("原token: {}", token);
        byte[] tokenByte = Base64.decode(token);
        String passToken = StrUtil.str(rsa.decrypt(tokenByte, KeyType.PublicKey), CharsetUtil.CHARSET_UTF_8);
        log.info("解密后后token:{}", passToken);
    }

    private String signKey = "hailu.com";

    @Test
    @DisplayName("生成一个密码")
    public void pwd(){
        String pwdMd5 = SecureUtil.sha256(123456 + "&key=" + signKey);
        log.info(pwdMd5);
    }

}
