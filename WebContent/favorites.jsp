<!DOCTYPE html>
<html>
<head>
	<script src="js/script.js"></script>
	<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<nav>
	<p>
		<a href="home.html" class="home-link">
			Movie Cruiser
			<img src="images/logo.jpg" alt="logo">
		</a>
		<a href="ShowFavorites" class="favorites-link">Favorites</a>
		<a href="ShowMovieListCustomer" class="customer-movies-link">Movies</a>
	</p>
</nav>
<section>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<h2>Favorites</h2>
	<c:if test="${removeFavoritesMovieStatus}">
		<p class="notification">${removedFavoritesMovieName} removed from favorites successfully.</p>
	</c:if>
	<table>
		<tr>
			<th>Title</th>
			<th>Box Office</th>
			<th>Genre</th>
			<th></th>
		</tr>
		<c:forEach items="${favorites.movieList}" var="movie">
			<tr>
				<td><c:out value="${movie.title}"/></td>
				<td><fmt:formatNumber value="${movie.boxOffice}" type="currency" currencySymbol="$" maxFractionDigits='0'/></td>
				<td><c:out value="${movie.genre}"/></td>
				<td><a href="RemoveFavorites?movieId=<c:out value='${movie.id}'/>">Delete</a></td>	
			</tr>
		</c:forEach>
	</table>
	<p id="numberOfFavorites"><b>No. of Favorites:</b> <c:out value="${favorites.numberOfFavorites}"/></p>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>