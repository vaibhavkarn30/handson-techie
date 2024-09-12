package org.handsontechie.onlineIndicatorService.service;


import org.handsontechie.onlineIndicatorService.model.UserOnlineIndicator;
import org.handsontechie.onlineIndicatorService.repository.UserOnlineIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserOnlineIndicatorService {
    @Autowired
    private UserOnlineIndicatorRepository userOnlineIndicatorRepository;

    /**
     * checks if the user is online
     * @param userId
     * @return
     */
    public boolean checkIfUserIsOnline(int userId){
       Optional<UserOnlineIndicator> user =  userOnlineIndicatorRepository.findById(userId);
       if(user.isEmpty() || !Boolean.TRUE.equals(user.get().getOnline())) {
           return false;
       }
       return true;
    }

    /**
     * updates the status or inserts the user if the user is not present
     * @param userId
     * @param status
     */
    public void updateUserStatus(int userId, boolean status){
        Optional<UserOnlineIndicator> userOptional = userOnlineIndicatorRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserOnlineIndicator user = userOptional.get();
            user.setOnline(status);
            userOnlineIndicatorRepository.save(user);
        } else {
            UserOnlineIndicator user = new UserOnlineIndicator();
            user.setUserId(userId);
            user.setOnline(status);
            userOnlineIndicatorRepository.save(user);
        }

    }
}
