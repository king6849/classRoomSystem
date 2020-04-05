package com.myteam.classroomsystem.scas.service;

import com.myteam.classroomsystem.scas.utils.RestTemplateConfig;
import com.myteam.classroomsystem.scas.utils.ResultVO;
import com.myteam.classroomsystem.scas.utils.Token;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService {

    @Resource
    private Token mytoken;

    @Resource
    private RestTemplateConfig restTemplateConfig;

    /**
     * 小程序商户登录
     */
    public ResultVO studentLogin(String code) throws RuntimeException {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String appid = "wx2766c4dd09b4cf2b";
        String secret = "407ff0b62ea3d9632307c7a9bed5122f";
        String js_code = code;
        String grant_type = "authorization_code";
        String codereslut = restTemplateConfig.getForObject(url + "?appid=" + appid + "&secret=" + secret + "&js_code=" + js_code + "&grant_type=" + grant_type, String.class);
        JSONObject jsonObject = JSONObject.fromObject(codereslut);
        //解析出session_key，openid
        String session_key = jsonObject.getString("session_key");
        String openid = jsonObject.getString("openid");
        //生成token
        String token = mytoken.generateToken(openid, session_key);
        //返回结果
        return ResultVO.getSuccessResultWithData("登陆成功", token);
    }

}
