package com.example;

import com.example.interfaces.IRepoGame;
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
public class PlayerController {
    @Autowired
    private IRepoGame repoGame;

    //    @RequestMapping(method = RequestMethod.GET)
//    public List<Artist> getAll() {
//        System.out.println("Get all artists...");
//        return (List<Artist>) artistRepo.findAll();
//    }
//
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        System.out.println("Get by id...");
        List<Game> list = repoGame.allGamesByPlayer(id);
        if (list == null) {
            return new ResponseEntity<String>("Artist not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Game>>(list, HttpStatus.OK);
        }
    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public Artist create(@RequestBody Artist artist) {
//        System.out.println("adding user...");
//        return artistRepo.add(artist);
//    }
//
//    @RequestMapping(value = "/{artistName}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> delete(@PathVariable String artistName) {
//        Artist artist = artistRepo.delete(artistName);
//        System.out.println("deleting artist..." + artist);
//        if (artist == null) {
//            return new ResponseEntity<String>("Artist not found", HttpStatus.BAD_REQUEST);
//        } else {
//            return new ResponseEntity<Artist>(artist, HttpStatus.OK);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public Artist update(@RequestBody Artist artist) {
//        System.out.println("Updating artist...");
//        return artistRepo.update(artist);
//    }


}

