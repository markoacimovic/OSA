import {useState} from "react";
import {SearchPorudzbineService} from "../../services/SearchPorudzbineService";

const PorudzbinaSearch = ({porudzbine}) => {

    const [komentar, setKomentar] = useState({
        field: "komentar",
        value: ""
    })
    const [minCena, setMinCena] = useState(0)
    const [maxCena, setMaxCena] = useState(999999)
    const [minOcena, setMinOcena] = useState(0)
    const [maxOcena, setMaxOcena] = useState(5)


    const handleFormInputChangeKomentar = (name) => (e) => {
        const val = e.target.value
        setKomentar({...komentar, [name]: val})
    }

    const searchKomentar = async () => {
        try {
            const response = await SearchPorudzbineService.findByKomentar(komentar);
            porudzbine(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    const searchOcena = async () => {
        try {
            const response = await SearchPorudzbineService.findByOcena(minOcena, maxOcena)
            porudzbine(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    const submit = (e) => {
        e.preventDefault()

        if (komentar.value !== "") {
            searchKomentar()
            console.log(komentar.value)
            return;
        }
        if (minOcena > 0 || maxOcena < 6) {
            searchOcena()
            console.log(minOcena)
            return;
        }
    }

    return (
        <div className="col-md-6 offset-md-3">
            <input className="form-control" onChange={handleFormInputChangeKomentar("value")} placeholder="Komentar"/>
            <input className="form-control" type="number" onChange={e => setMinCena(e.target.value)}
                   placeholder="Minimalna cena"/>
            <input className="form-control" type="number" onChange={e => setMaxCena(e.target.value)}
                   placeholder="Maksimalna cena"/>
            <input className="form-control" type="number" onChange={e => setMinOcena(e.target.value)}
                   placeholder="Minimalna ocena"/>
            <input className="form-control" type="number" onChange={e => setMaxOcena(e.target.value)}
                   placeholder="Maksimalna ocena"/>
            <button className="btn btn-danger" onClick={submit}>Pretraga</button>
        </div>
    )
}

export default PorudzbinaSearch