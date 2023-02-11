import {useState} from "react";
import NarudzbinaService from "../../services/NarudzbinaService";
import {useHistory} from "react-router";

const DostavljenaPorudzbinaRow = ({porudzbina}) => {

    const [por, setPor] = useState(porudzbina)
    const style = {
        marginTop: 10
    }
    const marg = {
        marginLeft: 30
    }

    return(
        <div className="row">
            <p className="text-black" style={{fontSize: 20}}>{por.prodavac}</p>
            <div style={marg}>
                <label className="text-dark" style={style}>Dostavljeno</label>
            </div>
            <div style={marg}>
                <p className="text-dark" style={style}>Komentar:</p>
                <p className="text-dark">{por.komentar}</p>
            </div>
            <div style={marg}>
                <p className="text-dark" style={style}>Ocena:</p>
                <p className="text-dark">{por.ocena}</p>
            </div>
            <div style={marg}>
                <p className="text-dark" style={style}>Anonimni komentar:</p>
                <p className="text-dark">{por.anonimniKomentar ? "da" : "ne"}</p>
            </div>
            <div style={marg}>
                <p className="text-dark" style={style}>Vreme dostave:</p>
                <p className="text-dark">{por.satnica}</p>
            </div>
        </div>
    )
}

export default DostavljenaPorudzbinaRow