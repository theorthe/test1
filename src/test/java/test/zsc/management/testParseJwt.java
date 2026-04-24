package test.zsc.management;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

public class testParseJwt {
    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser().setSigningKey("aXRjYXN0")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAsInVzZXJuYW1lIjoic29uZ2ppYW5nIiwiZXhwIjoxNzc2Nzg0Mzk1fQ.09Zf-GJ03aRT1Bpd7HAIcWzHhJWFEZzYTFpB6OTaLZo")
//                下面把9改成8报错
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ8.eyJpZCI6MTAsInVzZXJuYW1lIjoic29uZ2ppYW5nIiwiZXhwIjoxNzc2Nzg0Mzk1fQ.09Zf-GJ03aRT1Bpd7HAIcWzHhJWFEZzYTFpB6OTaLZo")
                .getBody();
        System.out.println(claims);
    }
}
