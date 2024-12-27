package org.jobapplication.review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Long companyId);
    void addReview(Long companyId, Review review);

    Review getReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReviews(Long companyId, Long reviewId);
}
