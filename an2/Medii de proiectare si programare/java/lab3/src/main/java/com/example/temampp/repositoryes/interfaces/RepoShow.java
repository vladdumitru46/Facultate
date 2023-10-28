package com.example.temampp.repositoryes.interfaces;

import com.example.temampp.domains.Artist;

import java.time.LocalDate;
import java.util.List;

public interface RepoShow {

    List<Artist> searchArtistByDate(LocalDate date);


}
