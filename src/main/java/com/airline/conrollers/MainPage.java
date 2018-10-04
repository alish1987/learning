package com.airline.conrollers;

import com.airline.models.Passenger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/")
public class MainPage extends HttpServlet {
    public MainPage() {
        super();
    }

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        ServletContext servletContext = this.getServletContext();
        ArrayList<Passenger> passengers = (ArrayList<Passenger>) servletContext.getAttribute("passengers");
        servletContext.setAttribute("passengers", passengers);
        out.println("Passenger has been added to list.Number of Passengers : " + passengers.size());
    }
}
