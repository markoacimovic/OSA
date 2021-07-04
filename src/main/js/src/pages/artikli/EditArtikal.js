import {useEffect, useState} from "react";
import {useHistory, useParams} from "react-router";
import ArtikliService from "../../services/ArtikliService";
import {AuthService} from "../../services/auth/AuthService";

const EditArtikal = () => {

    const [artikal, setArtikal] = useState({})
    const [warning, setWarning] = useState("")
    const history = useHistory()
    const param = useParams()

    async function edit() {
        try {
            await ArtikliService.editArtikal(param.id, artikal)
        }catch (e) {
            console.error(e)
        }
    }

    async function fetchArtikal(id){
        try {
            const response = await ArtikliService.getArtikal(id)
            setArtikal(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    useEffect(() => {
        fetchArtikal(param.id)
    },[param.id])

    const submit = (e) => {
        e.preventDefault()

        if(!artikal.naziv || !artikal.cena || !artikal.opis){
            setWarning("Sva polja moraju biti popunjena")
            return;
        }

        edit()
        history.push("/prodavci/" + AuthService.getUsername())
    }

    const handleFormInputChange = (name) => (e) => {
        const val = e.target.value
        setArtikal({...artikal, [name]: val})
    }

    const handleImageInput = (e) => {
        const val = e.target.files[0]
        setArtikal({...artikal, ["slika"]: val})
    }


    return(
        <div className="container-fluid" style={{marginTop: 50}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmeni artikal</h3>
                    <form>
                        <div className="form-group">
                            <label>Naziv </label>
                            <input type="text" value={artikal.naziv} onChange={handleFormInputChange("naziv")}
                                   className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Cena </label>
                            <input type="number" value={artikal.cena} onChange={handleFormInputChange("cena")}
                                   className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Opis </label>
                            <input type="text" value={artikal.opis} onChange={handleFormInputChange("opis")}
                                   className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Slika </label>
                            <input type="file" accept=".png, .jpg" className="form-control" onChange={handleImageInput}/>
                        </div>
                        <p className="text-dark">{warning}</p>
                        <button className="btn-lg btn-danger" type="submit" onClick={submit}>Izmeni artikal</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default EditArtikal