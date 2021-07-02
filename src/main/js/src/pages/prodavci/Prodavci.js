import {useEffect, useState} from "react";
import ProdavciService from "../../services/ProdavciService";
import ProdavacRow from "../../components/prodavci/ProdavacRow";

const Prodavci = () => {

    const [prodavci, setProdavci] = useState([])

    async function fetchProdavci() {
        try {
            const response = await ProdavciService.getProdavci()
            setProdavci(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    useEffect(() => {
        fetchProdavci()
    }, [])

    return (
        <div className="container-fluid">
            {prodavci.map((prodavac, index) => (
                <ProdavacRow key={index} prodavac={prodavac}/>
            ))}
        </div>
    )
}

export default Prodavci