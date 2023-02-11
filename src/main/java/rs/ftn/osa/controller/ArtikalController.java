package rs.ftn.osa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ftn.osa.dto.*;
import rs.ftn.osa.model.entity.Artikal;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.security.TokenUtils;
import rs.ftn.osa.service.implementation.ArtikalService;
import rs.ftn.osa.service.implementation.ProdavacService;
import rs.ftn.osa.support.ImageSaveOpen;
import rs.ftn.osa.support.LoggerStatic;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/artikli")
@CrossOrigin
public class ArtikalController {

    private final ArtikalService  artikalService;
    private final ProdavacService prodavacService;
    private final TokenUtils tokenUtils;

    public ArtikalController(ArtikalService artikalService, ProdavacService prodavacService, TokenUtils tokenUtils) {
        this.artikalService = artikalService;
        this.prodavacService = prodavacService;
        this.tokenUtils = tokenUtils;
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR, ANONYMOUS')")
    @GetMapping
    public ResponseEntity<List<ArtikalDTO>> getArtikals(){

        List<Artikal> artikli = artikalService.findAll();
        List<ArtikalDTO> retVal = new ArrayList<>();

        for (Artikal artikal : artikli){
            retVal.add(new ArtikalDTO(artikal));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR, ANONYMOUS')")
    @GetMapping("/{username}/artikli")
    public ResponseEntity<List<ArtikalDTO>> getArtikalsForProdavac(@PathVariable String username){

        List<Artikal> artikli = artikalService.findAllByProdavac(username);
        List<ArtikalDTO> retVal = new ArrayList<>();

        if(artikli == null || artikli.isEmpty() ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Artikal artikal : artikli){
            retVal.add(new ArtikalDTO(artikal));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ArtikalDTO> getArtikal(@PathVariable String id) {

        Artikal artikal = artikalService.findOne(id);
        if (artikal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArtikalDTO> createArtikal(@RequestBody ArtikalBackendDTO artikalDTO, Principal principal) throws IOException {

        Prodavac prodavac = prodavacService.findByUsername(principal.getName());

        Artikal artikal = new Artikal();
        artikal.setNaziv(artikalDTO.getNaziv());
        artikal.setCena(artikalDTO.getCena());
        artikal.setOpis(artikalDTO.getOpis());
        //artikal.setFilename(ImageSaveOpen.saveImage(artikalDTO.getSlika()));
        artikal.setProdavac(principal.getName());
        artikal.setStavke(new HashSet<>());

        artikal = artikalService.save(artikal);
        return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('PRODAVAC')")
//    @PostMapping(value = "/slika", consumes = "multipart/form-data")
//    public ResponseEntity<ArtikalIdDTO> setImage(@RequestParam("image") MultipartFile file, Principal principal) throws IOException {
//
//        Prodavac prodavac = prodavacService.findByUsername(principal.getName());
//
//        Artikal artikal = new Artikal();
//        artikal.setFilename(ImageSaveOpen.saveImage(file));
//        artikal.setProdavac(principal.getName());
//        artikal = artikalService.save(artikal);
//        String id = artikal.getId();
//        ArtikalIdDTO artikalIdDTO = new ArtikalIdDTO();
//        artikalIdDTO.setId(id);
//
//        LoggerStatic.logInFile(ArtikalController.class, "Kreiran je artikal.");
//
//        return new ResponseEntity<>(artikalIdDTO, HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<ArtikalDTO> updateArtikal(@PathVariable("id") String id,
                                                    @RequestBody ArtikalBackendDTO artikalDTO, Principal principal) throws IOException {

        Prodavac prodavac = prodavacService.findByUsername(principal.getName());

        Artikal artikal = artikalService.findOne(id);
        if(artikal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        artikal.setNaziv(artikalDTO.getNaziv());
        artikal.setCena(artikalDTO.getCena());
        artikal.setOpis(artikalDTO.getOpis());
        //artikal.setPutanjaSlike(ImageSaveOpen.saveImage(artikalDTO.getSlika()));
        artikal.setProdavac(principal.getName());

        artikal = artikalService.save(artikal);
        return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtikal(@PathVariable("id") String id, Principal principal){

        Artikal artikal = artikalService.findOne(id);
        if(!artikal.getProdavac().equals(principal.getName())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(artikal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        artikalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR, ANONYMOUS')")
    @GetMapping("/reindex")
    public void reindex() {
        artikalService.reindex();
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @PostMapping(path = "/pdf", consumes = { "multipart/form-data", "application/json" })
    public void multiUploadFileModel(@ModelAttribute ArtikalBackendDTO artikalBackendDTO, Principal principal) throws IOException {
        artikalService.indexUploadedFile(artikalBackendDTO, principal.getName());
    }

}

