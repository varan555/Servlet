package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.Logic.Model;
import ru.appline.Logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by JUVA on 18.11.2020.
 */
@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
        int id = jobj.get("id").getAsInt();
        String name = jobj.get("name").getAsString();
        String lastName = jobj.get("lastName").getAsString();
        double salary = jobj.get("salary").getAsDouble();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        User user = new User(name, lastName, salary);
        model.getUsers().put(id, user);
        pw.print(gson.toJson(model.getUsers()));}
}
