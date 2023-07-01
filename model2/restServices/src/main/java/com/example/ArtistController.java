package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mpp/artist")
//@CrossOrigin(origins = "http://localhost:5173")
public class ArtistController {
//    @Autowired
//    private IRepository<String, Artist> artistRepo;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public List<Artist> getAll() {
//        System.out.println("Get all artists...");
//        return (List<Artist>) artistRepo.findAll();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> getById(@PathVariable String id) {
//        System.out.println("Get by id...");
//        Artist artist = artistRepo.findOne(id);
//        if (artist == null) {
//            return new ResponseEntity<String>("Artist not found", HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<Artist>(artist, HttpStatus.OK);
//        }
//    }
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

