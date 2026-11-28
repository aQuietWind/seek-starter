package seek.starter.starter.web.jwt.starter.core;


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
import seek.starter.starter.web.jwt.starter.config.PropertiesConfig;

import java.util.List;

public class TokenTemplate {
    private final RedisTemplate redisTemplate;
    private final JWTTemplate jwtTemplate;
    private final PropertiesConfig propertiesConfig;
    private final DefaultRedisScript<Boolean> tokenAddScript= RedisScriptUtil.luaQuickInit("lua/token_add.lua");
    @Autowired
    public TokenTemplate(RedisTemplate redisTemplate, JWTTemplate jwtTemplate, PropertiesConfig propertiesConfig) {
        this.redisTemplate = redisTemplate;
        this.jwtTemplate = jwtTemplate;
        this.propertiesConfig = propertiesConfig;
    }

    //统一获取token
    public String createToken(Long tokenId, JWTRoleData jwtRoleData) {
        if (tokenId==null) throw new BizException(ErrorCodeEnum.DATA_IS_EMPTY);
        //登录校验成功，生成JWT Token
        return jwtTemplate.obtainJwt(tokenId,jwtRoleData);
    }

    //获取token并且放在Cookie上一同返回
    public void putTokenCookieOnResponse(String token
            , HttpServletResponse response, PropertiesConfig.JWTGlobalData jwtGlobalData, JWTRoleData jwtRoleData){
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
    public String getAndRecordToken(Long tokenId, HttpServletResponse response,String roleName){
        //获取token，并且放在请求头上
        String token= createToken(tokenId);
        // 构建Servlet Cookie
        putTokenCookieOnResponse(token,response);
        //存储token于Redis
        recordToken(tokenId,token,redisKey,maxStore);
        return token;
    }


}
