package cn.iamding.drpc.client.controller;

import cn.iamding.drpc.client.rpc.DRPCClient;
import cn.iamding.drpc.client.rpc.student.Student;
import cn.iamding.drpc.client.rpc.student.SubmitResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuDing
 * @date 2018/5/10
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private DRPCClient rpcClient;

    @RequestMapping(value = "/submit/student", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject submitStudent(@RequestBody JSONObject request) {
        String photoUrl = request.getString("photo");
        String name = request.getString("name");
        int age = request.getInteger("age");
        String sex = request.getString("sex");
        StopWatch stopWatch = new StopWatch();
        if (request.getBooleanValue("rpc")) {
            stopWatch.start();
            SubmitResult result = rpcClient.submitStudent(name, age, sex, photoUrl);
            Student student = result.getData();
            stopWatch.stop();
            Map<String, Object> data = new HashMap<>();
            data.put("name", student.getName());
            data.put("age", student.getAge());
            data.put("sex", student.getSex());
            data.put("photo", student.getPhoto());
            return buildResponse(0, stopWatch.getTotalTimeMillis(), result.getServer(), data);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("age", age);
        data.put("sex", sex);
        data.put("photo", photoUrl);
        return buildResponse(0, 0, "test", data);
    }

}
