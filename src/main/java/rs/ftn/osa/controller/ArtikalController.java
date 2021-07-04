package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ftn.osa.dto.ArtikalBackendDTO;
import rs.ftn.osa.dto.ArtikalDTO;
import rs.ftn.osa.dto.ArtikalIdDTO;
import rs.ftn.osa.model.entity.Artikal;
import rs.ftn.osa.security.TokenUtils;
import rs.ftn.osa.service.implementation.ArtikalService;
import rs.ftn.osa.service.implementation.ProdavacService;
import rs.ftn.osa.support.ImageSaveOpen;
import rs.ftn.osa.support.LoggerStatic;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/artikli")
@CrossOrigin
public class ArtikalController {

    @Autowired
    private ArtikalService  artikalService;

    @Autowired
    private ProdavacService prodavacService;

    @Autowired
    private TokenUtils tokenUtils;

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

    @PermitAll
    @GetMapping("/{username}/artikli")
    public ResponseEntity<List<ArtikalDTO>> getArtikalsForProdavac(@PathVariable String username){

        List<Artikal> artikli = artikalService.findAllByProdavac(username);
        List<ArtikalDTO> retVal = new ArrayList<>();

        for (Artikal artikal : artikli){
            retVal.add(new ArtikalDTO(artikal));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ArtikalDTO> getArtikal(@PathVariable long id) {

        Artikal artikal = artikalService.findOne(id);
        if (artikal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArtikalDTO> createArtikal(@RequestBody ArtikalBackendDTO artikalDTO, Principal principal) throws IOException {

        Artikal artikal = new Artikal();
        artikal.setNaziv(artikalDTO.getNaziv());
        artikal.setCena(artikalDTO.getCena());
        artikal.setOpis(artikalDTO.getOpis());
        artikal.setPutanjaSlike(ImageSaveOpen.saveImage(artikalDTO.getSlika()));
        artikal.setAkcije(new HashSet<>());
        artikal.setProdavac(prodavacService.findByUsername(principal.getName()));
        artikal.setStavke(new HashSet<>());

        artikal = artikalService.save(artikal);
        return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @PostMapping(value = "/slika", consumes = "multipart/form-data")
    public ResponseEntity<ArtikalIdDTO> setImage(@RequestParam("image") MultipartFile file, Principal principal) throws IOException {


        Artikal artikal = new Artikal();
        artikal.setPutanjaSlike(ImageSaveOpen.saveImage(file));
        artikal.setProdavac(prodavacService.findByUsername(principal.getName()));
        artikal = artikalService.save(artikal);
        Long id = artikal.getId();
        ArtikalIdDTO artikalIdDTO = new ArtikalIdDTO();
        artikalIdDTO.setId(id);

        LoggerStatic.logInFile(ArtikalController.class, "Kreiran je artikal.");

        return new ResponseEntity<>(artikalIdDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<ArtikalDTO> updateArtikal(@PathVariable("id") long id,
                                                    @RequestBody ArtikalBackendDTO artikalDTO, Principal principal) throws IOException {

        Artikal artikal = artikalService.findOne(id);
        if(artikal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        artikal.setNaziv(artikalDTO.getNaziv());
        artikal.setCena(artikalDTO.getCena());
        artikal.setOpis(artikalDTO.getOpis());
        //artikal.setPutanjaSlike(ImageSaveOpen.saveImage(artikalDTO.getSlika()));
        artikal.setProdavac(prodavacService.findByUsername(principal.getName()));

        artikal = artikalService.save(artikal);
        return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtikal(@PathVariable("id") long id, Principal principal){

        Artikal artikal = artikalService.findOne(id);
        if(!artikal.getProdavac().getUsername().equals(principal.getName())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(artikal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        artikalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

