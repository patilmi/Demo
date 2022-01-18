package com.mihir.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/calculate")
public class Homepage {

    @GetMapping(value="exponent/{x}", produces={"application/json"})
    @ResponseBody
    public double homepageExponent(@PathVariable Integer x) {

        return (Math.exp(x));
    }

    @GetMapping(value="binary/{op}/{x}/{y}", produces={"application/json"})
    @ResponseBody
    public int homepageSubtract(
            @PathVariable String op,
            @PathVariable Integer x, @PathVariable Integer y) {

        if (op.equals("subtract")) {
            return (x-y);
        }

        else if (op.equals("add")) {
            return (x+y);
        }

        throw new IllegalArgumentException("Operation " + op + " unrecognized");
    }

    @GetMapping(value="addList", produces={"application/json"})
    @ResponseBody
    public int listAdd(@RequestParam String nums) {

        int sum = 0;

        for (String x: nums.split(",")) {
            sum += Integer.valueOf(x);
        }

        return sum;
    }

}
