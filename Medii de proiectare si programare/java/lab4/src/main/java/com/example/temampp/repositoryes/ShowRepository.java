package com.example.temampp.repositoryes;

import com.example.temampp.domains.Show;
import com.example.temampp.repositoryes.interfaces.RepoShow;
import com.example.temampp.repositoryes.interfaces.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ShowRepository implements Repository<String, Show>, RepoShow {

    private final JDBCUtils jdbcUtils = new JDBCUtils();
    private static final Logger logger = LogManager.getLogger();

    public ShowRepository(Properties properties) {
        logger.info("\"Initializing ShowRepo with properties: {} \"", properties);
    }

    @Override
    public List<Show> searchArtistByDate(LocalDate date) {
        logger.traceEntry("search {}", date);
        List<Show> list = new ArrayList<>();
        String query = "SELECT * FROM shows where \"date\" = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String showName = resultSet.getString("showName");
                String artistName = resultSet.getString("artistName");
                String placeOfShow = resultSet.getString("placeOfShow");
                Integer noTicketsAvailable = resultSet.getInt("noTicketsAvailable");
                Integer noTicketsSold = resultSet.getInt("noTicketsSold");
                Show show = new Show(showName, artistName, date, placeOfShow, noTicketsAvailable, noTicketsSold);
                list.add(show);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        logger.traceExit();
        return list;
    }

    @Override
    public Show findOne(String s) {
        logger.traceEntry("find one {}", s);
        String query = "SELECT * FROM shows where \"showName\" = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String artistName = resultSet.getString("artistName");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String placeOfShow = resultSet.getString("placeOfShow");
                Integer noTicketsAvailable = resultSet.getInt("noTicketsAvailable");
                Integer noTicketsSold = resultSet.getInt("noTicketsSold");
                return new Show(s, artistName, date, placeOfShow, noTicketsAvailable, noTicketsSold);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public Iterable<Show> findAll() {
        logger.traceEntry("find all");
        List<Show> list = new ArrayList<>();
        String query = "SELECT * FROM shows";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String showName = resultSet.getString("showName");
                String artistName = resultSet.getString("artistName");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String placeOfShow = resultSet.getString("placeOfShow");
                Integer noTicketsAvailable = resultSet.getInt("noTicketsAvailable");
                Integer noTicketsSold = resultSet.getInt("noTicketsSold");
                Show show = new Show(showName, artistName, date, placeOfShow, noTicketsAvailable, noTicketsSold);
                list.add(show);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        logger.traceExit();
        return list;
    }

    @Override
    public Show add(Show entity) {
        logger.traceEntry("save {}", entity);
        String query = "INSERT INTO shows(\"showName\", \"artistName\", \"date\", \"placeOfShow\", \"noTicketsAvailable\", \"noTicketsSold\") values (?,?,?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getShowName());
            statement.setString(2, entity.getArtistName());
            statement.setDate(3, Date.valueOf(entity.getDate()));
            statement.setString(4, entity.getPlaceOfShow());
            statement.setInt(5, entity.getNoTicketsAvailable());
            statement.setInt(6, entity.getNoTicketsSold());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public Show delete(String entity) {
        logger.traceEntry("delete {}", entity);
        String query = "DELETE FROM shows WHERE \"showName\" = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        logger.traceExit();
        return findOne(entity);
    }

    @Override
    public Show update(Show entity) {
        logger.traceEntry("update {}", entity);
        String query = "UPDATE shows SET \"artistName\"=?, \"date\"=?, \"placeOfShow\"=?, \"noTicketsAvailable\"=?, \"noTicketsSold\"=? WHERE \"showName\"=?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getArtistName());
            statement.setDate(2, Date.valueOf(entity.getDate()));
            statement.setString(3, entity.getPlaceOfShow());
            statement.setInt(4, entity.getNoTicketsAvailable());
            statement.setInt(5, entity.getNoTicketsSold());
            statement.setString(6, entity.getShowName());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        logger.traceExit();
        return entity;
    }
}
