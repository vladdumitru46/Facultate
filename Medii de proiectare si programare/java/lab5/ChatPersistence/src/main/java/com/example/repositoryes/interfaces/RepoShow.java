package com.example.repositoryes.interfaces;

import com.example.Show;

import java.time.LocalDate;
import java.util.List;

public interface RepoShow extends Repository<String, Show> {

    List<Show> searchArtistByDate(LocalDate date);
}
