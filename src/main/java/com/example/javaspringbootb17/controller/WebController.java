package com.example.javaspringbootb17.controller;

import com.example.javaspringbootb17.entity.*;
import com.example.javaspringbootb17.model.enums.MovieType;
import com.example.javaspringbootb17.service.FavoriteService;
import com.example.javaspringbootb17.service.WebService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final WebService webService;
    private final FavoriteService favoriteService;
    private final HttpSession session;
    @GetMapping("")
    public String getHomePage(Model model){
        List<Movie> listHot=webService.getHotMovie();
        List<Movie> listBo=webService.findByType(MovieType.PHIM_BO,true,1,6).getContent();
        List<Movie> listLe=webService.findByType(MovieType.PHIM_LE,true,1,6).getContent();
        List<Movie> listChieuRap=webService.findByType(MovieType.PHIM_CHIEU_RAP,true,1,6).getContent();
        model.addAttribute("listBo",listBo);
        model.addAttribute("listLe",listLe);
        model.addAttribute("listChieuRap",listChieuRap);
        model.addAttribute("listHot",listHot);
        return "web/index";
    }
    //http://localhost:8095/phim-bo
    //http://localhost:8095/phim-bo?page=1&limit=12
    @GetMapping("/phim-bo")
    public String getPhimBo(Model model,
                            @RequestParam(defaultValue = "1")Integer page,
                            @RequestParam(defaultValue = "12")Integer limit){
        model.addAttribute("pageData",webService.findByType(MovieType.PHIM_BO,true,page,limit));
        model.addAttribute("currentPage",page);
        return "web/phim-bo";
    }
    @GetMapping("/phim-le")
    public String getPhimLe(Model model,
                            @RequestParam(defaultValue = "1")Integer page,
                            @RequestParam(defaultValue = "12")Integer limit){
        model.addAttribute("pageData",webService.findByType(MovieType.PHIM_LE,true,page,limit));
        model.addAttribute("currentPage",page);
        return "web/phim-le";
    }
    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRap(Model model,
                                  @RequestParam(defaultValue = "1")Integer page,
                                  @RequestParam(defaultValue = "12")Integer limit){
        model.addAttribute("pageData",webService.findByType(MovieType.PHIM_LE,true,page,limit));
        model.addAttribute("currentPage",page);
        return "web/phim-chieu-rap";
    }
    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetail(@PathVariable Integer id,
                                 @PathVariable String slug,
                                 Model model){
        User user=(User) session.getAttribute("currentUser");
        Movie movie=webService.getMovieDetail(id,slug);
        List<Movie>relateMovies=webService.getRelateMovies(movie);
        List<Episode>listEpisode=webService.getEpisodes(movie);
        List<Review>listReviews=webService.getReviews(movie);
        if (user!=null){
            boolean isFavorite = favoriteService.isFavorite(id);
            model.addAttribute("isFavorite",isFavorite);
        }
        model.addAttribute("movie",movie);
        model.addAttribute("relateMovies",relateMovies);
        model.addAttribute("listEpisode",listEpisode);
        model.addAttribute("listReviews",listReviews);
        return "web/chi-tiet-phim";
    }
    @GetMapping("/dang-nhap")
    public String getSignIn(Model model){
        return "web/signin";
    }
    @GetMapping("/dang-ky")
    public String getSignUp(Model model){
        return "web/signup";
    }
    @GetMapping("/xem-phim/{id}/{slug}")
    public String getMovieStreamingDetailPage(@PathVariable Integer id,
                                 @PathVariable String slug,
                                 @RequestParam String tap,
                                 Model model){
        Movie movie=webService.getMovieDetail(id,slug);
        List<Movie>relateMovies=webService.getRelateMovies(movie);
        List<Episode>listEpisode=webService.getEpisodes(movie);
        List<Review>listReviews=webService.getReviews(movie);
        Episode currentEpisode=webService.currentEpisode(movie, tap);
        model.addAttribute("movie",movie);
        model.addAttribute("relateMovies",relateMovies);
        model.addAttribute("listEpisode",listEpisode);
        model.addAttribute("listReviews",listReviews);
        model.addAttribute("currentEpisode",currentEpisode);
        return "web/xem-phim";
    }
    @GetMapping("/phim-yeu-thich")
    public String getFavoritePage(Model model) {
        List<Favorite> favorites = favoriteService.getAllFavoritesByCurrentUser();
        model.addAttribute("favorites", favorites);
        return "web/phim-yeu-thich";
    }
    @GetMapping("/thong-tin-ca-nhan")
    public String getProfilePage(Model model) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);
        return "web/thong-tin-ca-nhan";
    }
}
