import {useParams} from "react-router";
import {useEffect, useState} from "react";
import ArtikliService from "../../services/ArtikliService";

const Artikal = () =>{

    const [artikal, setArtikal] = useState({})
    const {id} = useParams()

    async function fetchArtikal(id) {
        try {
            const response = await ArtikliService.getArtikal(id)
            setArtikal(response.data)
        }catch (e){
            console.error(e)
        }

    }

    useEffect(() => {
        fetchArtikal(id)
    },[id])
    

    return(
        <div className="row">
            <div className="col-md-12">
                <h3 className="text-center text-dark" style={{fontSize: 50}}>{artikal.naziv}</h3>
            </div>
            <div className="col-md-12">
                <img src={artikal.putanjaSlike} className="align-content-center"/>
            </div>
            <div className="text-center text-dark col-md-12" style={{fontSize: 30}}>{artikal.cena}</div>
            <div className="text-center text-dark col-md-12" style={{fontSize: 30}}>{artikal.opis}</div>
        </div>
    )
}

export default Artikal