package org.ersun.moviecatalogservice.resource;

import org.ersun.moviecatalogservice.models.CatalogItem;
import org.ersun.moviecatalogservice.models.Movie;
import org.ersun.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    @Qualifier(value = "resttemplate")
    // İki tane RestTemplate tipinde bean imiz olduğu durumda @Qualifier anotasyonu ile hangi beani kullanmak istediğimizi belirtebiliriz. Bunun için @Bean anotasyonunu "value" özelliği atamamız gerekir.
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient; //

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // get all rated movie IDs

        // We assume that this is the response which we got from the Rating API
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);

        // For each movie ID, call movie info service and get details


        return userRating.getRatings().stream().map(rating -> {

            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();

            // Put them all together
            return new CatalogItem(movie.getName(), "Test", rating.getRating());

        }).collect(Collectors.toList());

    }

}
