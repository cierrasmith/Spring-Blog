package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model){
        Random r = new Random();

        int low = 1;
        int high = 6;
        int randomNum = r.nextInt(high-low) + low;
        model.addAttribute("num", randomNum);
        model.addAttribute("userGuess", n);
        model.addAttribute("isCorrect", randomNum == n);

        return "roll-dice";
    }
}
