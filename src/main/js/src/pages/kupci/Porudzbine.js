import {useEffect, useState} from "react";
import NarudzbinaService from "../../services/NarudzbinaService";
import PorudzbinaRow from "../../components/kupci/PorudzbinaRow";

const Porudzbine = () => {

    const [porudzbine, setPorudzbine] = useState([])

    async function fetchPorudzbine() {
        try {
            const response = await NarudzbinaService.getNarudzbine()
            setPorudzbine(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    useEffect(() => {
        fetchPorudzbine()
    }, [])

    return (
        <div className="container-fluid" style={{marginTop: 50}}>
            <h3 className="text-dark text-center" style={{marginBottom: 20}}>Porudzbine</h3>
            {porudzbine.map((porudzbina, index) => (
                <PorudzbinaRow key={index} porudzbina={porudzbina}/>
            ))}
        </div>
    )
}

export default Porudzbine