package org.superbiz.moviefun.moviesapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class MoviesClient {

    private String moviesUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<MovieInfo>> movieListType =
            new ParameterizedTypeReference<List<MovieInfo>>() {
    };

    public MoviesClient(String moviesUrl, RestOperations restOperations) {
        this.moviesUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public void addMovie(MovieInfo movieInfo) {
        restOperations.postForEntity(moviesUrl, movieInfo, MovieInfo.class);
    }

    public int countAll() {
       return restOperations.getForObject(moviesUrl + "/count",  Integer.class);
    }

    public int count(String field, String searchTerm){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(moviesUrl)
                .path("/count")
                .queryParam("field", field)
                .queryParam("searchTerm", searchTerm)
                ;
        URI uri = uriComponentsBuilder.build().toUri();
        return restOperations.getForObject(uri, Integer.class);
    }

    public void deleteMovieId(long id) {
        restOperations.delete(moviesUrl + "/" + id);
    }

    public List<MovieInfo> findAll(int start, int pageSize) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(moviesUrl)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize)
                ;
        String uri = uriComponentsBuilder.build().toUriString();
        ResponseEntity<List<MovieInfo>> responseEntity =
                restOperations.exchange(uri, HttpMethod.GET, null, movieListType);
        return responseEntity.getBody();
    }

    public List<MovieInfo> findRange(String field, String key, int start, int pageSize) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(moviesUrl)
                .queryParam("field", field)
                .queryParam("key", key)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize)
                ;
        URI uri = uriComponentsBuilder.build().toUri();
        ResponseEntity<List<MovieInfo>> responseEntity = restOperations.exchange(uri, HttpMethod.GET, null, movieListType);
        return responseEntity.getBody();

    }

    public List<MovieInfo> getMovies() {
        ResponseEntity<List<MovieInfo>> responseEntity =
                restOperations.exchange(moviesUrl, HttpMethod.GET, null, movieListType);
        return responseEntity.getBody();
    }


}
