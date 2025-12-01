package com.example.demo.domain.member;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthTokenServiceTest {

    @Autowired
    private AuthTokenService authTokenService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("jjwt 최신 방식으로 JWT 생성, {name=\"Paul\", age=23}")
    void t1() {
        // 토큰 만료기간: 1년
        long expireMillis = 1000L * 60 * 60 * 24 * 365;

        byte[] keyBytes = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890".getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        // 발행 시간과 만료 시간 설정
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + expireMillis);

        String jwt = Jwts.builder()
                .claims(Map.of("name", "Paul", "age", 23)) // 내용
                .issuedAt(issuedAt) // 생성날짜
                .expiration(expiration) // 만료날짜
                .signWith(secretKey) // 키 서명
                .compact();

        assertThat(jwt).isNotBlank();

        System.out.println("jwt = " + jwt);
    }

    @Test
    @DisplayName("AuthTokenService를 통해서 accessToken 생성")
    void t4() {

        Member member1 = memberRepository.findByUsername("user3").get();
        String accessToken = authTokenService.genAccessToken(member1);
        assertThat(accessToken).isNotBlank();

        System.out.println("accessToken = " + accessToken);

        Map<String, Object> payload = authTokenService.payload(accessToken);

        System.out.println("id : " + payload.get("id"));
        System.out.println("username : " + payload.get("username"));

    }
}
