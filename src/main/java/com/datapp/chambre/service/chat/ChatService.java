//package com.datapp.chambre.service.chat;
//
//import com.datapp.chambre.constant.KafkaConstants;
//import com.datapp.chambre.dto.response.GenericResponse;
//import com.datapp.chambre.model.chat.Message;
//import com.datapp.chambre.model.chat_room.ChatRoom;
//import com.datapp.chambre.service.GenericResponseService;
//import com.datapp.chambre.service.chat_room.ChatRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.concurrent.ExecutionException;
//
///**
// * Created by kmluns on 21.12.2020
// */
//@Service
//public class ChatService {
//
//    @Autowired
//    private KafkaTemplate<String, Message> kafkaTemplate;
//
//    @Autowired
//    private GenericResponseService genericResponseService;
//
//    @Autowired
//    private ChatRoomService chatRoomService;
//
//
//    public GenericResponse sendMessageToGroup(String chatRoomID, Message message){
//        if(chatRoomService.isThereChatRoom(chatRoomID)){
//            sendMessage(message,chatRoomID);
//        }
//        return genericResponseService.createResponseNoError(true);
//    }
//
//    private void sendMessage(Message message, String chatRoomID){
//        message.setTimestamp(LocalDateTime.now().toString());
//        try {
//            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//
//
//}
