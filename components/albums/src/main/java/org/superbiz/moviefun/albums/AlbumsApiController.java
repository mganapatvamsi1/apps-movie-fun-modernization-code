package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsApiController {

    private AlbumsRepository albumsRepository;

    public AlbumsApiController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }


    @GetMapping
    public List<Album> findAll() {
        return albumsRepository.getAlbums();
    }

    @GetMapping("/{albumId}")
    public Album find(@PathVariable long albumId ) {
        return albumsRepository.find(albumId);
    }

    @PostMapping
    public void addAlbum(Album album) {
        albumsRepository.addAlbum(album);
    }
}
