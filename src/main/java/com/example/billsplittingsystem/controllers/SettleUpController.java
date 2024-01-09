package com.example.billsplittingsystem.controllers;

import com.example.billsplittingsystem.dtos.SettleUpGroupRequestDto;
import com.example.billsplittingsystem.dtos.SettleUpGroupResponseDto;
import com.example.billsplittingsystem.dtos.SettleUpUserRequestDto;
import com.example.billsplittingsystem.dtos.SettleUpUserResponseDto;
import com.example.billsplittingsystem.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SettleUpController {
    @Autowired
    private SettleUpService settleUpService;
    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto settleUpGroupRequestDto) {
        // settleup service -> settleup strategy
        settleUpService.settleUpGroup(settleUpGroupRequestDto.getGroupId());

        return null;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto settleUpUserRequestDto) {
        return null;
    }
}
