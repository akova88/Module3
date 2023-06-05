<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css" integrity="sha512-rt/SrQ4UNIaGfDyEXZtNcyWvQeOq0QLygHluFQcSjaGB04IxWhal71tKuzP6K8eYXYB6vJV4pHkXcmFGGQ1/0w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>FORM LOGIN</title>
    <style>
        html, body {
            height: 100%;
        }

        body {
            display: flex;
            padding-top: 40px;
            padding-bottom: 40px;
            justify-content: center;
            align-content:center ;
        }

        .form-signin {
            width: 100%;
            max-width: 350px;
            padding: 20px;
            margin: 0 auto;
            background-color: #f5f5f5;
            border-radius: 10px;
            border: 2px solid #0e4749;
            box-shadow: 0 2px 2px 0;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

    </style>
</head>
<body class="text-center">
<form class="form-signin col-5 m-sm-auto" action="/login" method="post">
    <img class="mb-3" src="./pngegg.png" alt="" width="72px", height="72px">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <input type="text" name="username" id="inputemail" class="form-control" placeholder="Email address" required autofocus>
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required >
    <div class="checkbox mb-3">
        <input class="mt-3" type="checkbox" value="remember-me"> Remember me
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-3 mb-3 text-muted">&copy; 2017-2018</p>
</form>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>