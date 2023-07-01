package com.example;


import com.example.interfaces.IRepoClue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mpp/clue")
public class ClueController {

    @Autowired
    private IRepoClue repoClue;

    @RequestMapping(method = RequestMethod.POST)
    public Clue create(@RequestBody Clue clue) {
        System.out.println("adding user...");
        return repoClue.add(clue);
    }
}
