package rs.ftn.osa.dto;

import java.util.ArrayList;
import java.util.List;

public class KorpaDTO {

    private List<NarudzbinaDTO> listaStavki;

    public KorpaDTO() {
    }

    public List<NarudzbinaDTO> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<NarudzbinaDTO> listaStavki) {
        this.listaStavki = listaStavki;
    }
}
