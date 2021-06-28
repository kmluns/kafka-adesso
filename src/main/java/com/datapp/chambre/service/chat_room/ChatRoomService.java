//package com.datapp.chambre.service.chat_room;
//
//import com.datapp.chambre.authorization.model.Account;
//import com.datapp.chambre.dto.request.chat_room.ChatRoomDTO;
//import com.datapp.chambre.dto.response.GenericResponse;
//import com.datapp.chambre.exception.runtime.AuthorizationException;
//import com.datapp.chambre.exception.runtime.NotFoundChatRoomException;
//import com.datapp.chambre.exception.runtime.UnCompletedException;
//import com.datapp.chambre.model.chat_room.ChatRoom;
//import com.datapp.chambre.model.chat_room.participant.Participant;
//import com.datapp.chambre.repository.chat_room.ChatRoomRepository;
//import com.datapp.chambre.service.GenericResponseService;
//import com.datapp.chambre.service.common.user.AccountService;
//import com.datapp.chambre.utils.token_util.TokenUtil;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
///**
// * Created by kmluns on 4.10.2020
// */
//@Service
//public class ChatRoomService {
//
//
//    @Autowired
//    GenericResponseService genericResponseService;
//
//    @Autowired
//    ChatRoomRepository chatRoomRepository;
//
//    @Autowired
//    ModelMapper modelMapper;
//
//    @Autowired
//    AccountService accountService;
//
//    @Autowired
//    TokenUtil<ChatRoom> chatRoomTokenUtil;
//
//
//    @Value("${chatRoom.shareLink.milliseconds}")
//    private long shareLinkMilliseconds;
//
//
//    public GenericResponse createRoom(Account authenticatedAccount, ChatRoomDTO chatRoomDTO) {
//        ChatRoom chatRoom = modelMapper.map(chatRoomDTO, ChatRoom.class);
//        chatRoom.setId(UUID.randomUUID().toString());
//        chatRoom.setOwner(authenticatedAccount);
//
//        chatRoom = chatRoomRepository.save(chatRoom);
//
//        accountService.addChatRoom(authenticatedAccount, chatRoom);
//
//        return genericResponseService.createResponseNoError(chatRoom);
//    }
//
//    public GenericResponse addParticipant(String chatRoomID, Participant participant) {
//        ChatRoom chatRoom = getById(chatRoomID);
//        chatRoom.getParticipantList().add(participant);
//        chatRoom = chatRoomRepository.save(chatRoom);
//        return genericResponseService.createResponseNoError(chatRoom);
//    }
//
//    public GenericResponse removeParticipant(String chatRoomID, Participant participant, Account authenticatedAccount) {
//        ChatRoom chatRoom = getById(chatRoomID);
//        if (chatRoom.getOwner().equals(authenticatedAccount)) {
//            List participantList = chatRoom.getParticipantList();
//            participantList.remove(participant);
//            chatRoom = chatRoomRepository.save(chatRoom);
//            return genericResponseService.createResponseNoError(chatRoom);
//        }
//        throw new AuthorizationException();
//    }
//
//    public ChatRoom getById(String chatRoomID) {
//        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(chatRoomID);
//        if (!optionalChatRoom.isPresent()) {
//            throw new NotFoundChatRoomException();
//        }
//
//        return optionalChatRoom.get();
//    }
//
//    public boolean isThereChatRoom(String chatRoomID) {
//        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(chatRoomID);
//        return optionalChatRoom.isPresent();
//    }
//
//
//    public GenericResponse createShareLink(String chatRoomID) {
//        ChatRoom chatRoom = getById(chatRoomID);
//        String shareLinkToken = chatRoomTokenUtil.generateToken(chatRoom, shareLinkMilliseconds);
//        return genericResponseService.createResponseNoError(shareLinkToken);
//    }
//
//    public GenericResponse join(String joinToken, String username, Account authenticatedAccount) {
//        if (!chatRoomTokenUtil.isTokenExpired(joinToken)) {
//            ChatRoom chatRoomToken = chatRoomTokenUtil.extractSubject(joinToken, ChatRoom.class);
//
//            Participant participant = new Participant();
//            participant.setId(UUID.randomUUID().toString())
//                    .setAccount(authenticatedAccount)
//                    .setUsername(username);
//
//            return addParticipant(chatRoomToken.getId(), participant);
//        }
//        throw new UnCompletedException(); // Exception should be created!
//    }
//
//    public GenericResponse isExistChatRoomByJoinToken(String joinToken) {
//        if (!chatRoomTokenUtil.isTokenExpired(joinToken)) {
//            ChatRoom chatRoomToken = chatRoomTokenUtil.extractSubject(joinToken, ChatRoom.class);
//            return genericResponseService.createResponseNoError(chatRoomToken);
//        }
//        throw new UnCompletedException(); // Exception should be created!
//    }
//
//    public GenericResponse unanonymousRoom(String roomID, Account authenticatedAccount) {
//        boolean result = false;
//
//        List<ChatRoom> chatRoomList = authenticatedAccount.getChatRoomList();
//        for (ChatRoom cr : chatRoomList) {
//            if (cr.getId().equals(roomID)) {
//                List<Participant> participantList = cr.getParticipantList();
//                for (Participant p : participantList) {
//                    if (p.getAccount().equals(authenticatedAccount)) {
//                        p.setAnonymous(true);
//                        p.setAccountInformation(authenticatedAccount);
//                        chatRoomRepository.save(cr);
//                        result = true;
//                    }
//                }
//            }
//        }
//        return genericResponseService.createResponseNoError(result);
//    }
//}
