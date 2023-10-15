package com.example.repositoryes;

import com.example.repositoryes.interfaces.RepoBuyer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BuyerRepo implements RepoBuyer {
    private final JDBCUtils jdbcUtils = new JDBCUtils();
//    private static final Logger logger = LogManager.getLogger();

    public BuyerRepo(Properties properties) {
//        logger.info("\"Initializing BuyerRepo with properties: {} \"", properties);

    }

    @Override
    public void sellTicketsToShow(String showName, String buyerName, Integer noOfTickets) {
//        logger.traceEntry("sell tickets {} {} {}", showName, buyerName, noOfTickets);
        String query = "Insert into buyers(name, \"noTicketsBought\", \"showName\") values (?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, buyerName);
            statement.setInt(2, noOfTickets);
            statement.setString(3, showName);
            statement.executeUpdate();
        } catch (SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
    }
}
