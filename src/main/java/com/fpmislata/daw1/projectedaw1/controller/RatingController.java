package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.RatingIoc;
import com.fpmislata.daw1.projectedaw1.common.container.UserIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.Alert;
import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.domain.service.RatingService;
import com.fpmislata.daw1.projectedaw1.domain.service.UserService;
import com.fpmislata.daw1.projectedaw1.security.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class RatingController {

    private final RatingService ratingService;

    public RatingController() {
        ratingService = RatingIoc.createService();
    }

    @PostMapping("/rating")
    public String login(@RequestParam("isbn") String isbn,
                        @RequestParam("my-rating") int rating,
                        RedirectAttributes redirectAttributes) {

        if (!UserSession.isUserLoggedIn()) {
            return "redirect:/llibre/" + isbn;
        }

        List<Alert> alerts = new ArrayList<>();

        String username = Objects.requireNonNull(UserSession.getUser()).getUsername();
        if (rating == -1) {
            ratingService.delete(isbn, username);
            alerts.add(new Alert("info", "S'ha eliminat la valoració"));
            redirectAttributes.addFlashAttribute("alerts", alerts);
            return "redirect:/llibre/" + isbn;
        }

        LocalDate now = LocalDate.now();
        Rating ratingObject = new Rating(isbn, username, rating, now);
        ratingService.save(ratingObject);
        alerts.add(new Alert("success", "S'ha guardat la valoració"));

        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/llibre/" + isbn;
    }
}
