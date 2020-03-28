package com.xianjinyi.gameConsumer.stream;

import com.xianjinyi.gameConsumer.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author: xianjinyi
 * @date 2020/03/27
 */

@Component
// 绑定
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceive {


    @StreamListener("MyMessage")
    public void receive(String obj){
        log.info("MyMessage接收：{}",obj);
    }

    @StreamListener("User")
    @SendTo("MyMessage") // 回复消息
    public String receive(User obj){
        log.info("User接收：{}",obj);
        return "ok";
    }


//    public static void main(String[] args) throws  Exception{
//        ArrayList arrayList = new ArrayList<User>();
//        User user1 = new User(1L, "小明");
//        User user2 = new User(2L, "小华");
//        User user3 = new User(3L, "小花");
//
//        arrayList.add(user1);
//        arrayList.add(user2);
//        arrayList.add(user3);
//
//
//        toTest(JSON.toJSONString(arrayList));
//    }
//
//    private static void toTest (String arrayList) throws  Exception{
//        ObjectMapper objectMapper = new ObjectMapper();
////        User user = objectMapper.readValue(arrayList, User.class);
//        List<User> o = objectMapper.readValue(arrayList, new TypeReference<List<User>>() {
//        });
//        System.out.println(o);
//    }
}
