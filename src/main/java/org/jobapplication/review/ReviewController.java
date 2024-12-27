package org.jobapplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable ("companyId") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviewService.getReviews(id), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable ("companyId") Long id,
                                            @RequestBody Review review){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reviewService.addReview(id, review);
        return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
    }


    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable ("companyId") Long companyId,
                                            @PathVariable ("reviewId") Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable ("companyId") Long companyId,
                                               @PathVariable ("reviewId") Long reviewId,
                                               @RequestBody Review review){
       boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
       if(isUpdated){
           return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable ("companyId") Long companyId,
                                               @PathVariable ("reviewId") Long reviewId){
        boolean isDeleted = reviewService.deleteReviews(companyId, reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not found for delete", HttpStatus.NOT_FOUND);
        }
    }
}
