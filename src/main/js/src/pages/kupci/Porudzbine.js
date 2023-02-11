import {useEffect, useState} from "react";
import NarudzbinaService from "../../services/NarudzbinaService";
import PorudzbinaRow from "../../components/kupci/PorudzbinaRow";
import DostavljenaPorudzbinaRow from "../../components/kupci/DostavljenaPorudzbinaRow";
import PorudzbinaSearch from "../../components/search/PorudzbinaSearch";

const Porudzbine = () => {

    const [porudzbine, setPorudzbine] = useState([])
    const [dostavljene, setDostavljene] = useState([])
    const [pretraga, setPretraga] = useState([])

    async function fetchPorudzbine() {
        try {
            const response = await NarudzbinaService.getNarudzbine()
            setPorudzbine(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    async function fetchDostavljenePorudzbine() {
        try {
            const response = await NarudzbinaService.getDostavljeneNarudzbine()
            setDostavljene(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    useEffect(() => {
        fetchPorudzbine()
        fetchDostavljenePorudzbine()
    }, [])

    return (
        <div className="container-fluid" style={{marginTop: 50}}>
            <h3 className="text-dark text-center" style={{marginBottom: 20}}>Porudzbine</h3>
            {porudzbine.map((porudzbina, index) => (
                <PorudzbinaRow key={index} porudzbina={porudzbina}/>
            ))}
            <h4 className="text-dark text-center" style={{marginBottom: 20}}>Dostavljene</h4>
            <PorudzbinaSearch porudzbine={setPretraga}/>
            {pretraga.length === 0 ? dostavljene.map((porudzbina, index) => (
                <DostavljenaPorudzbinaRow key={index} porudzbina={porudzbina}/>
            )) : ""}
            {pretraga.length > 0 ? <h4 className="text-dark text-center" style={{marginBottom: 20}}>Pretraga</h4> : ""}
            {pretraga.length > 0 ? pretraga.map((porudzbina, index) => (
                <DostavljenaPorudzbinaRow key={index} porudzbina={porudzbina}/>
            )) : ""}
        </div>
    )
}

export default Porudzbine