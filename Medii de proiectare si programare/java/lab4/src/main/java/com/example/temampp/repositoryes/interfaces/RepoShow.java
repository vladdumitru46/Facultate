package com.example.temampp.repositoryes.interfaces;

import com.example.temampp.domains.Show;

import java.time.LocalDate;
import java.util.List;

public interface RepoShow {

    List<Show> searchArtistByDate(LocalDate date);
}
