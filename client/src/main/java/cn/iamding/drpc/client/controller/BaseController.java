package cn.iamding.drpc.client.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author XuDing
 * @date 2018/5/10
 */
public abstract class BaseController {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONObject handleException(Exception e) {
        return buildResponse(-1, 0L, e.getMessage(), "");
    }


    protected JSONObject buildResponse(int code, long timeMills, String server, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("timeMills", timeMills);
        jsonObject.put("server", server);
        jsonObject.put("data", data);
        return jsonObject;
    }
}
