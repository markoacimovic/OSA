import {useState} from "react";
import {SearchArtikalService} from "../../services/SearchArtikalService";

const ArtikalSearch = ({artikli}) => {

    const [naziv, setNaziv] = useState({
        field: "naziv",
        value: ""
    })

    const [opis, setOpis] = useState({
        field: "opis",
        value: ""
    })

    const [minCena, setMinCena] = useState(0)
    const [maxCena, setMaxCena] = useState(999999)
    const [minOcena, setMinOcena] = useState(0)
    const [maxOcena, setMaxOcena] = useState(999999)
    const [minKomentara, setMinKomentara] = useState(0)
    const [maxKomentara, setMaxKomentara] = useState(999999)

    const handleFormInputChangeNaziv = (name) => (e) => {
        const val = e.target.value
        setNaziv({...naziv, [name]: val})
    }

    const handleFormInputChangeOpis = (name) => (e) => {
        const val = e.target.value
        setOpis({...opis, [name]: val})
    }


    const searchName = async () => {
        try {
            const response = await SearchArtikalService.findByNaziv(naziv)
            artikli(response.data)
        }catch (e){
            console.error(e)
        }

    }

    const searchOpis = async () => {
        try {
            const response = await SearchArtikalService.findByOpis(opis)
            artikli(response.data)
        }catch (e){
            console.error(e)
        }

    }

    const searchCena = async () => {
        try {
            const response = await SearchArtikalService.findByCena(minCena, maxCena)
            artikli(response.data)
        }catch (e){
            console.error(e)
        }
    }

    const searchCenaAndNaziv = async () => {
        try {
            const response = await SearchArtikalService.findByNazivCena(minCena, maxCena, naziv)
            artikli(response.data)
        }catch (e){
            console.error(e)
        }
    }

    const searchOcena = async () => {
      try {
          const response = await SearchArtikalService.findByOcena(minOcena, maxOcena);
          artikli(response.data)
      }catch (e) {
          console.error(e)
      }
    }

    const searchBrojKomentara = async () => {
        try {
            const response = await SearchArtikalService.findByBrojKomentara(minKomentara, maxKomentara);
            artikli(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    const submit = (e) => {
        e.preventDefault()

        if(naziv.value !== "" && minCena > 0 && maxCena < 999999){
            searchCenaAndNaziv()
            console.log("ovde")
            return;
        }

        if(naziv.value !== ""){
            searchName()
            return;
        }
        if (opis.value !== ""){
            searchOpis()
            return;
        }
        if(minCena > 0 || maxCena < 999999){
            searchCena(minCena, maxCena)
            return;
        }

        if(minOcena > 0 || maxOcena < 999999){
            searchOcena()
            return;
        }

        if(minKomentara > 0 || maxKomentara < 999999){
            searchBrojKomentara()
            return;
        }
    }

    return(
        <div className="col-md-6 offset-md-3">
            <input className="form-control" onChange={handleFormInputChangeNaziv("value")} placeholder="Naziv"/>
            <input className="form-control" onChange={handleFormInputChangeOpis("value")} placeholder="Opis"/>
            <input className="form-control" type="number" onChange={e => setMinCena(e.target.value)} placeholder="Minimalna cena"/>
            <input className="form-control" type="number" onChange={e => setMaxCena(e.target.value)} placeholder="Maksimalna cena"/>
            <input className="form-control" type="number" onChange={e => setMinOcena(e.target.value)} placeholder="Minimalna ocena"/>
            <input className="form-control" type="number" onChange={e => setMaxOcena(e.target.value)} placeholder="Maksimalna ocena"/>
            <input className="form-control" type="number" onChange={e => setMinKomentara(e.target.value)} placeholder="Minimalan broj komentara"/>
            <input className="form-control" type="number" onChange={e => setMaxKomentara(e.target.value)} placeholder="Maksimalan broj komentara"/>
            <button className="btn btn-danger" onClick={submit}>Pretraga</button>
        </div>
    )
}

export default ArtikalSearch