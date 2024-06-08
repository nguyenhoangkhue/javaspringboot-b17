const stars = document.querySelectorAll(".rating .star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;

stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}
document.addEventListener("DOMContentLoaded", function() {
    let rating = 0;

    document.querySelectorAll(".star").forEach(function(star) {
        star.addEventListener("click", function() {
            rating = this.getAttribute("data-rating");
            document.getElementById("rating-value").textContent = `Rating: ${rating}`;
        });
    });

    document.getElementById("form-review").addEventListener("submit", function(event) {
        event.preventDefault();
        const content = document.getElementById("review-content").value;
        const modalReview = document.getElementById("modalReview");
        const movieId = modalReview.getAttribute('data-movie-id');
        if (!content || rating === 0) {
            alert("Please enter content and select a rating.");
            return;
        }

        const reviewData = {
            content: content,
            rating: rating,
            movieId: movieId
        };

        axios.post("/api/reviews", reviewData)
            .then(response => {
                console.log(response.data);
                alert("Review created successfully!");
            })
            .catch(error => {
                console.error(error);
                alert("There was an error creating the review.");
            });
    });
});
