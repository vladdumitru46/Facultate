package com.example;

import com.example.interfaces.IRepoGropi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mpp/game")
//@CrossOrigin(origins = "http://localhost:5173")
public class GameController {
    @Autowired
    private IRepoGropi repoGropi;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String id) {
        System.out.println("Get by id...");
        List<Game> list = repoGropi.findAllGamesForPlayer(id);
        if (list == null) {
            return new ResponseEntity<String>("Artist not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Game>>(list, HttpStatus.OK);
        }
    }

    //
//    @RequestMapping(method = RequestMethod.POST)
//    public Game create(@RequestBody Integer id, String position) {
//        System.out.println("adding user...");
//        return repoGropi.addPositionToGame(id, position);
//    }


    @RequestMapping(value = "/{id}/{position}", method = RequestMethod.PUT)
    public Game update(@PathVariable Integer id, @PathVariable String position) {
        return repoGropi.addPositionToGame(id, position);
    }


}

