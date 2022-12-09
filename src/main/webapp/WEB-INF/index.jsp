<%--
  Created by IntelliJ IDEA.
  User: duarte
  Date: 09/12/2022
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>The weather calendar</title>
  <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<aside>
  <a href="#">
    <img id="logo" src="ressources/images/logo.jpg">
    <div>THE WEATHER CALENDAR</div>
  </a>
</aside>
<div id="main-container" class="bcg-blue txt-white">
  <header class="margin-centered">
    <nav>
      <label for="toggle">☰</label>
      <input type="checkbox" id="toggle">
      <div class="main_pages">
        <a href="#">ACCUEIL</a>
        <a href="#">METEO</a>
        <a href="#">SE CONNECTER</a>
        <a href="#">S'INSCRIRE</a>
      </div>
    </nav>

  </header>
  <main class="margin-centered">
    <h1>Bienvenue</h1>
    <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
      Dignissimos, quibusdam adipisci corrupti cumque doloremque eos?
      Ad quibusdam, quae facilis iste eos, ipsum officia velit eaque
      assumenda accusamus et odio. Deserunt tempora esse eius voluptatum
      deleniti error repellendus rem mollitia eaque autem eum fugiat quas odio,
      quisquam assumenda porro vel dignissimos.</p>

  </main>
  <footer class="margin-centered">
    <a href="#">Mentions légales</a>
    <a href="#">Politique de confidentialité</a>
    <a href="#">Qui sommes-nous ?</a>
  </footer>
</div>

</body>
</html>