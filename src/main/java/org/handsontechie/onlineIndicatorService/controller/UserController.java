package org.handsontechie.onlineIndicatorService.controller;


import org.handsontechie.onlineIndicatorService.service.UserOnlineIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userOnline")
public class UserController {

    @Autowired
    private UserOnlineIndicatorService userOnlineIndicatorService;
    @GetMapping("/status")
    public boolean checkUserOnline(@RequestParam("userId") int userId) {
       boolean res =  userOnlineIndicatorService.checkIfUserIsOnline(userId);
       return res;
    }


}
