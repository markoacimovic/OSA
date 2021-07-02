import {useHistory} from "react-router";
import ArtikliService from "../../services/ArtikliService";

const EditRemoveArtikal = ({artikal, artikli, setArtikli}) => {

    const history = useHistory()

    async function remove(id) {
        try {
            await ArtikliService.deleteArtikal(id)
            setArtikli(() => artikli.filter((ar)=> ar.id !== id))
        }catch (e){
            console.error(e)
        }
    }

    const edit = (id) => {
        history.push(`/artikli/${id}`)
    }

    const removeArtikal = (id) => {
        remove(id)
    }

    return(
        <div className="row">
            <button className="btn btn-primary" onClick={() => edit(artikal.id)}>Izmeni</button>
            <button className="btn btn-warning" onClick={() => removeArtikal(artikal.id)}>Obrisi</button>
        </div>
    )
}

export default EditRemoveArtikal