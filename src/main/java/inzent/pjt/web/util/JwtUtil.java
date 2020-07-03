package inzent.pjt.web.util;

import java.security.Key;
import inzent.pjt.vo.AdminVo;
import inzent.pjt.vo.UserVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
  
  Key pkey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  
  public String createKey(UserVo vo) {
    String token = Jwts.builder()
                  .setHeaderParam("typ", "JWT")
                  .claim("email", vo.getEmail())
                  .claim("name", vo.getName())
                  .claim("co_name", vo.getCo_name())
                  .claim("co_location", vo.getCo_location())
                  .claim("co_tel", vo.getCo_tel())
                  .claim("co_number", vo.getCo_number())
                  .claim("manager_type", vo.getManager_type())
                  .claim("reg_date", vo.getReg_date())
                  .signWith(pkey)
                  .compact();
    
    return token;
  }
  
  public String createKey(AdminVo vo) {
    String token = Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .claim("email", vo.getEmail())
        .claim("name", vo.getName())
        .claim("co_number", vo.getCo_number())
        .signWith(pkey)
        .compact();

return token;
  }
}