<%@page import = "ru.appline.Logic.Model" %>
<%--
  Created by IntelliJ IDEA.
  User: JUVA
  Date: 14.11.2020
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите ID пользователя (0 - чтобы вывести весь список пользователей)
  <br/>
  Доступно: <% Model model = Model.getInstance();
  out.print(model.getUsers().size());%>
  <form method="get" action="get">
    <label>ID:
      <input type="text" name="ID"></label><br/>
    </label>
    <button type="submit">Поиск</button><br/>
  </form>
  <br/>
  Введите ID пользователя, чтобы удалить его из списка
  <br/>
  <form method="delete" action="delete">
    <label>ID:
      <input type="text" name="ID"></label><br/>
    </label>
    <button type="submit">Удалить пользователя</button><br/>
    <br/>
    <a href="addUser.html">Создать нового пользователя</a><br/>
    <a href="putUser.html">Заменить пользователя</a>
  </form>
  </body>
</html>
