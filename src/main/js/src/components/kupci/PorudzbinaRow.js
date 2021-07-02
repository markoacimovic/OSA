import {useState} from "react";
import NarudzbinaService from "../../services/NarudzbinaService";
import {useHistory} from "react-router";

const PorudzbinaRow = ({porudzbina}) => {

    const [por, setPor] = useState(porudzbina)
    console.log(por)
    const [error, setError] = useState("")
    const history = useHistory()
    const style = {
        marginTop: 10
    }
    const marg = {
        marginLeft: 10
    }

    const handleFormInputChange = (name) => (e) => {
        const val = e.target.value
        setPor({...por, [name]: val})
    }

    async function edit(id) {
        try {
            return await NarudzbinaService.editNarudzbina(id, por)
        } catch (e) {
            console.error(e)
        }
    }

    const checkBoxtrue = (name) => (e) => {
        const val = e.target.value
        const ret = val === "on" ? true : false
        setPor({...por, [name]: ret})
    }

    const submit = () => {
        if(por.dostavljeno !== true ){
            setError("Morate stiklirati dostavljeno")
            return
        }
        if(por.komentar === null || por.ocena === 0){
            setError("Komentar i ocena su obavezni")
            return
        }

        edit(por.id, por)
        history.push("/")
    }

    return(
        <div className="row">
            <p className="text-black" style={{fontSize: 20}}>{por.prodavac}</p>
            <div style={marg}>
                <label className="text-dark" style={style}>Dostavljeno:</label>
                <input className="text-dark btn-check" type="checkbox"  onChange={checkBoxtrue("dostavljeno")}/>
            </div>
            <div style={marg}>
                <label className="text-dark" style={style}>Komentar:</label>
                <input className="text-dark" type="text" value={por.komentar} onChange={handleFormInputChange("komentar")} required/>
            </div>
            <div style={marg}>
                <label className="text-dark" style={style}>Ocena:</label>
                <input className="text-dark" type="number" min="1" max="5" onChange={handleFormInputChange("ocena")} required/>
            </div>
            <div style={marg}>
                <label className="text-dark" style={style}>Anonimni komentar:</label>
                <input className="text-dark" type="checkbox" onChange={checkBoxtrue("anonimniKomentar")}/>
            </div>
            <p>{error}</p>
            <button className="btn btn-danger" onClick={submit} style={marg}>Posalji</button>
        </div>
    )
}

export default PorudzbinaRow