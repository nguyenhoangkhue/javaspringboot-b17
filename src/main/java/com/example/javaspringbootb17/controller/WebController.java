package com.example.javaspringbootb17.controller;

import com.example.javaspringbootb17.model.enums.MovieType;
import com.example.javaspringbootb17.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final WebService webService;
    @GetMapping("")
    public String getHomePage(Model model){
        return "/web/index";
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
        return "web/chi-tiet-phim";
    }
    @GetMapping("/phim/dang-nhap")
    public String getSignIn(Model model){
        return "web/signin";
    }
    @GetMapping("/phim/dang-ky")
    public String getSignUp(Model model){
        return "web/signup";
    }
}