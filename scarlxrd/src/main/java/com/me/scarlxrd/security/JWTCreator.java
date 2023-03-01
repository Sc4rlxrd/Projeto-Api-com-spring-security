package com.me.scarlxrd.security;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;


public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, String jwtObject) {
        DoubleStream Jwts = null;
        String token = "";
        Object SignatureAlgorithm = null;
         //= builder().setSubject(jwtObject.getSubject()).setIssuedAt(jwtObject.getIssuedAt()).setExpiration(jwtObject.getExpiration())
            //    .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())).signWith(SignatureAlgorithm.HS512, key).compact();
        return prefix + " " + token;
    }

    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
    }


}
