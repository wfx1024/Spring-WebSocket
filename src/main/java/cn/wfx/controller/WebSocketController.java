package cn.wfx.controller;

/**
 * Created by WFX on 2018-02-26.
 */

import cn.wfx.handler.SpringWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 业务使用（暂未使用）
 * Created by zhangwenchao on 2017/11/20.
 */
@Controller
public class WebSocketController {

    @Bean//这个注解会从Spring容器拿出Bean
    public SpringWebSocketHandler infoHandler() {

        return new SpringWebSocketHandler();
    }


    @RequestMapping("/websocket/loginPage")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/websocket/login";
    }


    @RequestMapping("/websocket/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getSession().getId();
        System.out.println(username+"登录");
        HttpSession session = request.getSession(false);
        session.setAttribute("SESSION_USERNAME", username); //一般直接保存user实体
        return "/websocket/send";
    }

    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        String message = request.getParameter("message");
        infoHandler().sendMessageToUser(username, new TextMessage(message));
        return null;
    }

    @RequestMapping("/websocket/broad")
    @ResponseBody
    public  String broad(String id) {
        infoHandler().sendMessageToUsers(id,new TextMessage("发送一条小Broad"));
        System.out.println("群发成功");
        return "broad";
    }
}
