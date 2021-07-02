import {useState} from "react";
import ArtikliService from "../../services/ArtikliService";
import {AuthService} from "../../services/auth/AuthService";
import {useHistory} from "react-router";

const AddArtikal = () => {

    const [warning, setWarning] = useState("")
    const [artikal, setArtikal] = useState({
        naziv: "",
        opis: "",
        cena: "",
        putanjaSlike: ""
    })
    const history = useHistory()

    const handleFormInputChange = (name) => (e) => {
        const val = e.target.value
        setArtikal({...artikal, [name]: val})
    }

    async function create() {
        try {
            await ArtikliService.createArtikal(artikal)
        }catch (e){
            console.error(e)
        }
    }

    const submit = (e) => {
        e.preventDefault()

        if(!artikal.naziv || !artikal.cena || !artikal.opis){
            setWarning("Sva polja moraju biti popunjena")
            return;
        }

        create()
        history.push("/prodavci/" + AuthService.getUsername())
    }

    return(
        <div className="container-fluid" style={{marginTop: 50}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Dodaj artikal</h3>
                    <form>
                        <div className="form-group">
                            <label>Naziv </label>
                            <input type="text" value={artikal.naziv} onChange={handleFormInputChange("naziv")}
                                   placeholder="Naziv" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Cena </label>
                            <input type="number" value={artikal.cena} onChange={handleFormInputChange("cena")}
                                   placeholder="Cena" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Opis </label>
                            <input type="text" value={artikal.opis} onChange={handleFormInputChange("opis")}
                                   placeholder="Opis" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Slika </label>
                            <input type="file" accept=".png, .jpg" className="form-control"/>
                        </div>
                        <p className="text-dark">{warning}</p>
                        <button className="btn-lg btn-danger" type="submit" onClick={submit}>Dodaj artikal</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default AddArtikal