package com.travelpedia.api.review.controller;

import com.travelpedia.api.experiences.model.ExperienceModel;
import com.travelpedia.api.experiences.service.ExperienceService;
import com.travelpedia.api.review.model.ReviewModel;
import com.travelpedia.api.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService rs;
    @Autowired
    ExperienceService es;
    @GetMapping("/{experienceTitle}")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<ReviewModel> getByExperienceId(@PathVariable("experienceTitle")String Title) {
        Long id=es.getExperienceByTitle(Title).getExperienceId();
        return rs.getByExperienceId(id);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/add")
    public ReviewModel addReview(@RequestParam("experienceId") Long experienceId  , @RequestParam ("username") String username , @RequestParam("review") String review) {
        ExperienceModel ex=es.getExperience(experienceId );
        ReviewModel r=new ReviewModel();
        r.setExperience(ex);
        r.setUsername(username);
        r.setReview(review);
        return rs.addReview(r);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        rs.deleteReview(reviewId);
    }

}