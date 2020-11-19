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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by JUVA on 18.11.2020.
 */
@WebServlet(urlPatterns = "/del")
public class ServletDel extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        if (id == 0) {pw.print(gson.toJson(model.getUsers()));}
        else if (id > 0) {
            if (id>model.getUsers().size()) {
                pw.print("Под данным ID нет записи");
            }
            else {
                //Находим по id элемент, удаляем его из коллекции - выводим коллекцию
                User user = model.getUsers().get(id);
                model.getUsers().values().remove(user);
                pw.print(gson.toJson(model.getUsers()));}
        } else {pw.print("ID должен быть положительным числом ");}
    }
}

