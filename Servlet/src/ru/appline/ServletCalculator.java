package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.Logic.Math;

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
@WebServlet(urlPatterns = "/calc")
public class ServletCalculator extends HttpServlet {
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
        double a = jobj.get("a").getAsDouble();
        double b = jobj.get("b").getAsDouble();
        Math math = new Math(a, b);
        String operations = jobj.get("math").getAsString();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        switch (operations){
            case ("+"):
                math.plus();
                pw.print(gson.toJson(math.getResult()));
                break;
            case ("-"):
                math.minus();
                pw.print(gson.toJson(math.getResult()));
                break;
            case ("*"):
                math.multiplication();
                pw.print(gson.toJson(math.getResult()));
                break;
            case ("/"):
            {   if (b == 0) {
                pw.print("деление на ноль запрещено");}
            else {
                math.delay();
                pw.print(gson.toJson(math.getResult()));
            }}
                break;
            default:
                pw.print("Неверное значение операции");
            }
            }
        }

