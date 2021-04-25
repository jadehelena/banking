package com.jadehelena.banking.config.security

import com.jadehelena.banking.model.User
import org.springframework.stereotype.Service
import org.springframework.security.core.Authentication
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

@Service
class TokenService {

    private String expirationTime = "3600000"
    private String secretPassword = """A+X;fTJP&Pd,TD9dwVq(hsHX,ya^<wsD_UK7L+@=S;{'CydP]{v@}G'b>et;yz\$*\\yL5S8EJN:%P:X%H9>#nYLrX}@\\s?CQcpspH,2emzBc!Q[V'AYa~uzF8WR~AUrMzxp/V\$9([S9X#zj/CH('#]B_Hc+%fGhe27YB;^j4\\Xk=Ju\"Ap~_&<L;=!Z;!,2UP;!hF3P]j85#*`&T]/kB/W^6\$v~u6qpejL>kY^f)sy4:qTq_Ec!-z!@aAp~sLKGU>\$"""

    String generateToken(Authentication authentication) {
        User userLogged = (User) authentication.getPrincipal()
        Date today = new Date()
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expirationTime))

        return Jwts.builder()
        .setIssuer("Banking API")
        .setSubject(userLogged.getId().toString())
        .setIssuedAt(today)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS256, secretPassword)
        .compact()
    }

    boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secretPassword).parseClaimsJws(token)
            return true
        } catch (Exception e) {
            return false
        }
    }

    Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secretPassword).parseClaimsJws(token).getBody()
        return Long.parseLong(claims.getSubject())
    }
}
