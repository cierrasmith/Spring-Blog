package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{number1}/and/{number2}")
    @ResponseBody
    public int add (
            @PathVariable int number1,
            @PathVariable int number2
    ){
        return number1 + number2;
    }

    @GetMapping("/subtract/{number1}/from/{number2}")
    @ResponseBody
    public int subtract (
            @PathVariable int number1,
            @PathVariable int number2
    ){
        return number2 - number1;
    }

    @GetMapping("/multiply/{number1}/and/{number2}")
    @ResponseBody
    public int multiply (
            @PathVariable int number1,
            @PathVariable int number2
    ){
        return number1 * number2;
    }

    @GetMapping("/divide/{number1}/by/{number2}")
    @ResponseBody
    public int divide (
            @PathVariable int number1,
            @PathVariable int number2
    ){
        return number1 / number2;
    }


}
