package com.example.protobuffProtocol;

import com.example.Buyers;
import com.example.EmployeeAtOffice;

public class ProtoUtils {
    public static String getError(Protobufs.Response response) {
        return response.getError();
    }

    public static Protobufs.Request createSellTicketRequest(Buyers buyers) {
        Protobufs.Buyers show = Protobufs.Buyers.newBuilder().setShowName(buyers.getShowName()).setBuyerName(buyers.getBuyerName()).setNoOfTickets(String.valueOf(buyers.getNoTicketsBought())).build();
        return Protobufs.Request.newBuilder().setType(Protobufs.Request.Type.SellTicketsRequest).setShow(show).build();
    }

    public static Protobufs.Request createLogOutRequest(EmployeeAtOffice employeeAtOffice) {
        Protobufs.EmployeeAtOffice employee = Protobufs.EmployeeAtOffice.newBuilder().setEmail(employeeAtOffice.getEmail()).setPassword(employeeAtOffice.getPassword()).build();
        return Protobufs.Request.newBuilder().setType(Protobufs.Request.Type.LogOut).setEmployee(employee).build();
    }

    public static Protobufs.Request createLogInRequest(EmployeeAtOffice employeeAtOffice) {
        Protobufs.EmployeeAtOffice employee = Protobufs.EmployeeAtOffice.newBuilder().setEmail(employeeAtOffice.getEmail()).setPassword(employeeAtOffice.getPassword()).build();
        return Protobufs.Request.newBuilder().setType(Protobufs.Request.Type.LogIn).setEmployee(employee).build();
    }

    public static Buyers getBuyers(Protobufs.Response updateResponse) {
        Buyers buyers = new Buyers();
        buyers.setShowName(updateResponse.getShow().getShowName());
        return buyers;
    }

    public static Buyers getBuyers(Protobufs.Request request) {
        Buyers buyers = new Buyers();
        buyers.setShowName(request.getShow().getShowName());
        buyers.setBuyerName(request.getShow().getBuyerName());
        buyers.setNoTicketsBought(Integer.parseInt(request.getShow().getNoOfTickets()));
        return buyers;
    }

    public static EmployeeAtOffice getEmployee(Protobufs.Request request) {
        EmployeeAtOffice employeeAtOffice = new EmployeeAtOffice();
        employeeAtOffice.setEmail(request.getEmployee().getEmail());
        employeeAtOffice.setPassword(request.getEmployee().getPassword());
        return employeeAtOffice;
    }

    public static Protobufs.Response createOkResponse() {
        return Protobufs.Response.newBuilder().setType(Protobufs.Response.Type.Ok).build();
    }

    public static Protobufs.Response createErrorResponse(String message) {
        return Protobufs.Response.newBuilder().setType(Protobufs.Response.Type.Error).setError(message).build();
    }

    public static Protobufs.Response createNewSellTicketResponse(Buyers buyers) {
        Protobufs.Buyers show = Protobufs.Buyers.newBuilder().setShowName(buyers.getShowName()).setBuyerName(buyers.getBuyerName()).setNoOfTickets(String.valueOf(buyers.getNoTicketsBought())).build();
        return Protobufs.Response.newBuilder().setType(Protobufs.Response.Type.SellTicketsResponse).setShow(show).build();
    }
}
