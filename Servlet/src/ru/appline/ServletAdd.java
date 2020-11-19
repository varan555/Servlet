package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.Logic.Model;
import ru.appline.Logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by JUVA on 15.11.2020.
 */
@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html; charset=utf-8");
//
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String lastName = request.getParameter("lastName");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name, lastName, salary);
//        model.add(user, counter.getAndIncrement());
//        pw.print("<html>" +
//        "<h3> Пользователь " + name + " " + lastName + " с зарплатой " + salary + " успешно создан </h3> + " +
//                "<a href =\"addUser.html\">Создать нового пользователя</a><br/> " +
//                "<a href =\"index.jsp\">Домой</a></br> + " +
//                "</html>");
//
//
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer buffer = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine())!=null){
                buffer.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        JsonObject jobj = gson.fromJson(String.valueOf(buffer), JsonObject.class);

        request.setCharacterEncoding("UTF-8");
        String name = jobj.get("name").getAsString();
        String lastName = jobj.get("lastName").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        User user = new User(name, lastName, salary);
        model.add(user, counter.getAndIncrement());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(model.getUsers()));
    }
}
