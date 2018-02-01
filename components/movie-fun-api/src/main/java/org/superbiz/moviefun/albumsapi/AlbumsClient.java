package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType =
            new ParameterizedTypeReference<List<AlbumInfo>>() {
            };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo albumInfo) {
        restOperations.postForEntity(albumsUrl, albumInfo, AlbumInfo.class);
    }


    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(albumsUrl, HttpMethod.GET,
               null, albumListType).getBody();
    }

    public AlbumInfo find(long albumId) {
        return restOperations.getForObject(albumsUrl + "/" + albumId, AlbumInfo.class);
    }
}
