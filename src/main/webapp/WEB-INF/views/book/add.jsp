<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <title>添加</title>
</head>
<body>
<h2>添加书籍</h2>
<form action="/insert" method="post">
    <table>
        <tr>
            <td>书名:</td>
            <td><input type="text" name="title" required></td>
        </tr>
        <tr>
            <td>作者:</td>
            <td><input type="text" name="author"></td>
        </tr>
        <tr>
            <td>格式:</td>
            <td><input type="text" name="format"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Insert Book"></td>
        </tr>
    </table>
</form>
</body>
</html>
