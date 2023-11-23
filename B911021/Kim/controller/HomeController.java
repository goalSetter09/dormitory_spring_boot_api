package B911021.Kim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//washer
// id
// isUsing
// start time
// end time
// remaining time

@RestController
@RequestMapping("/laundry")
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String CreateWasher() {
        return "hello";
    }



}
