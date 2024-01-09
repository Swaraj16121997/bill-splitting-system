package com.example.billsplittingsystem.operations;

import com.example.billsplittingsystem.controllers.SettleUpController;
import com.example.billsplittingsystem.dtos.SettleUpUserRequestDto;
import com.example.billsplittingsystem.dtos.SettleUpUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUser implements Operation {
    /*
    Expected Input : SettleUp <user_id>
     */
    //SettleUpUser is a command from the user input.
    private SettleUpController settleUpController;

    @Autowired
    public SettleUpUser(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {;
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(0).equals(OperationKeywords.SETTLE_UP_USER);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        SettleUpUserRequestDto settleUpUserRequestDto = new SettleUpUserRequestDto();
        settleUpUserRequestDto.setUserId(Long.valueOf(words.get(1)));

        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(settleUpUserRequestDto);
    }
}
