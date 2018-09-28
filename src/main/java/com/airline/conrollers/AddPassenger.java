package com.airline.conrollers;

import com.airline.models.Gender;
import com.airline.models.Passenger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/AddPassenger")
public class AddPassenger extends HttpServlet {

    public AddPassenger() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("errors", false);
        Passenger passenger = new Passenger();
        String firstName = request.getParameter("first-name");
        System.out.println("firstName: " + firstName);
        if (firstName.length() == 0) {
            System.out.println("invalid first name");
            request.setAttribute("errors", true);
            request.setAttribute("firstName_error", true);
        } else {
            passenger.setFirstName(firstName);
        }

        String lastName = request.getParameter("last-name");
        System.out.println("lastName: " + lastName);
        if (lastName.length() == 0) {
            System.out.println("invalid last Name");
            request.setAttribute("errors", true);
            request.setAttribute("lastName_error", true);
        } else {
            passenger.setLasName(lastName);
        }

        String dob_raw = request.getParameter("dob");
        String p = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(dob_raw);

        if (matcher.find()) {
            String dobArray[] = dob_raw.split("\\/");
            String month = dobArray[0];
            String day = dobArray[1];
            String year = dobArray[2];

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.valueOf(year));
            calendar.set(Calendar.MONTH, Integer.valueOf(month));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day));
            Date dob = calendar.getTime();
            System.out.println("birthDate : " + dob);
            passenger.setDob(dob);
        } else {
            System.out.println("invalid dae of birth");
            request.setAttribute("errors", true);
            request.setAttribute("date_format_error", true);

        }

        String gender = request.getParameter("gender");
        System.out.println("gender" + gender);
        passenger.setGender(Gender.valueOf(gender));

        if ((Boolean) request.getAttribute("errors")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addPassenger.jsp");
            dispatcher.forward(request, response);
        } else {
            ServletContext servletContext = this.getServletContext();
            synchronized (this) {
                ArrayList<Passenger> passengers = (ArrayList<Passenger>) servletContext.getAttribute("passengers");
                passengers.add(passenger);
                servletContext.setAttribute("passengers", passengers);
            }
            response.sendRedirect("/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addPassenger.jsp");
        dispatcher.forward(request, response);
    }
}
