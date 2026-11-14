package seek.starter.starter.web.jwt.starter.core;


import seek.starter.starter.support.config.object.web.jwt.JWTGlobalData;
import seek.starter.starter.support.config.object.web.jwt.JWTRoleData;
import seek.starter.starter.support.config.object.db.redis.RedisKeyData;
import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;
import seek.starter.starter.db.redis.starter.util.RedisScriptUtil;
import seek.starter.starter.db.redis.starter.core.RedisTemplate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.List;

public class TokenTemplate {
    private final RedisTemplate redisTemplate;
    private final JWTTemplate jwtTemplate;
    private final DefaultRedisScript<Boolean> tokenAddScript= RedisScriptUtil.luaQuickInit("lua/token_add.lua");
    @Autowired
    public TokenTemplate(RedisTemplate redisTemplate, JWTTemplate jwtTemplate) {
        this.redisTemplate = redisTemplate;
        this.jwtTemplate = jwtTemplate;
    }

    //统一获取token
    public String createToken(Long tokenId, JWTRoleData jwtRoleData) {
        if (tokenId==null) throw new BizException(ErrorCodeEnum.DATA_IS_EMPTY);
        //登录校验成功，生成JWT Token
        String token = jwtTemplate.obtainJwt(tokenId,jwtRoleData);
        return token;
    }

    public void putTokenCookieOnResponse(String token, HttpServletResponse response, JWTGlobalData jwtGlobalData, JWTRoleData jwtRoleData){
        // 构建Servlet Cookie
        Cookie cookie = new Cookie(jwtGlobalData.getRequestHeaderTokenName()
                , jwtRoleData.getHeaderSign()+jwtGlobalData.getTokenHeaderSeparator()+token);
        cookie.setHttpOnly(true);
        //生产环境改为true
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge((int) (jwtRoleData.getTokenDurationMillis()/1000));
        response.addCookie(cookie);
    }

    public void recordToken(Long tokenId,String token,RedisKeyData redisKey,String maxStore){
        redisTemplate.doScript(tokenAddScript         //执行脚本
                , List.of(redisKey.getRedisKey(tokenId))       //KEYS参数
                ,maxStore,token, ""+ System.currentTimeMillis());     //ARGV参数
    }

    //统一进行token的获取与Redis存储
    //发放登录信息
    public String getAndRecordToken(Long tokenId, HttpServletResponse response
    , JWTGlobalData jwtGlobalData, JWTRoleData jwtRoleData
    , RedisKeyData redisKey, String maxStore){
        //获取token，并且放在请求头上
        String token= createToken(tokenId, jwtRoleData);
        // 构建Servlet Cookie
        putTokenCookieOnResponse(token,response,jwtGlobalData,jwtRoleData);
        //存储token于Redis
        recordToken(tokenId,token,redisKey,maxStore);
        return token;
    }


}
