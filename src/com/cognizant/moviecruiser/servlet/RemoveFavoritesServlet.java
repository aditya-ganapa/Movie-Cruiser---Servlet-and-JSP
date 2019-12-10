package com.cognizant.moviecruiser.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.moviecruiser.dao.FavoritesDao;
import com.cognizant.moviecruiser.dao.FavoritesDaoCollectionImpl;
import com.cognizant.moviecruiser.dao.FavoritesEmptyException;
import com.cognizant.moviecruiser.dao.MovieDao;
import com.cognizant.moviecruiser.dao.MovieDaoCollectionImpl;
import com.cognizant.moviecruiser.model.Favorites;

@WebServlet("/RemoveFavorites")
public class RemoveFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long movieId = Long.parseLong(request.getParameter("movieId"));
		FavoritesDao favoritesDao = new FavoritesDaoCollectionImpl();
		long userId = 1;
		favoritesDao.removeFavoriteMovie(userId, movieId);
		request.setAttribute("removeFavoritesMovieStatus", true);
		Favorites favorites = null;
		try {
			favorites = favoritesDao.getAllFavoriteMovies(userId);
		} catch (FavoritesEmptyException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("favorites-empty.jsp");
			requestDispatcher.forward(request, response);
		}
		request.setAttribute("favorites", favorites);
		MovieDao movieDao = null;
		try {
			movieDao = new MovieDaoCollectionImpl();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("removedFavoritesMovieName", movieDao.getMovie(movieId).getTitle());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("favorites.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (IllegalStateException e) {
		}
	}
}