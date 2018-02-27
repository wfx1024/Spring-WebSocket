package cn.wfx.config;


import cn.wfx.handler.SpringWebSocketHandler;
import cn.wfx.interceptor.SpringWebSocketHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by WFX on 2018-02-26.
 */
@Configuration
@EnableWebSocket
public class SpringWebSocketConfig  implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(),"/websocket/socketServer.do")
                .addInterceptors(new SpringWebSocketHandlerInterceptor());

        registry.addHandler(webSocketHandler(), "/sockjs/socketServer.do")
                .addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
    }

    @Bean
    public TextWebSocketHandler webSocketHandler(){

        return new SpringWebSocketHandler();
    }

}