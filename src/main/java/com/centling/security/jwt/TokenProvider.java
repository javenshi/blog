package com.centling.security.jwt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.centling.config.ApplicationProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.centling.security.TokenUser;
import com.centling.utils.GsonUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final String TOKEN_USER_KEY = "token_user";
    private static final String TOKEN_USER_ID_KEY = "token_user_id";

    private String secretKey;

    private long tokenValidityInSeconds;

    private long tokenValidityInSecondsForRememberMe;

    @Autowired
    private ApplicationProperties properties;

    @PostConstruct
    public void init() {
        this.secretKey =
        		properties.getSecurity().getAuthentication().getJwt().getSecret();
        this.tokenValidityInSeconds =
            1000 * properties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInSecondsForRememberMe =
            1000 * properties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, Boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now);
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInSeconds);
        }
        
        TokenUser tokenUser = (TokenUser)authentication.getPrincipal();
       // String json = GsonUtil.toJSON(tokenUser.getUser());
        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
           // .claim(TOKEN_USER_KEY, json)
            .claim(TOKEN_USER_ID_KEY, tokenUser.getUserId())
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .setExpiration(validity)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();

//        String principal = claims.getSubject();
        String json = claims.get(TOKEN_USER_KEY).toString();

        //TODO 后面修改
        Collection<? extends GrantedAuthority> authorities =
            Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

//        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        TokenUser principal = new TokenUser(claims.getSubject(), "", authorities);
        //principal.setUser(GsonUtil.getJSON(json, SUser.class));
        principal.setUserId((Integer)(claims.get(TOKEN_USER_ID_KEY)));

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            Date expirationDate = parseClaimsJws.getBody().getExpiration();
            if(expirationDate.before(new Date())){
            	return false;
            }
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature: " + e.getMessage());
            return false;
        }
    }
}
