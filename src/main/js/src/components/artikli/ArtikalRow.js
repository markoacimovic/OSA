import {useHistory, useParams} from "react-router";
import {AuthService} from "../../services/auth/AuthService";
import AddRemoveArtikal from "./AddRemoveArtikal";
import {useState} from "react";
import {TokenService} from "../../services/auth/TokenService";
import EditRemoveArtikal from "../prodavci/EditRemoveArtikal";

const ArtikalRow = ({artikal, artikli, setArtikli, cart, setCart}) => {

    const [item, setItem] = useState({
        artikalId: artikal.id,
        kolicina: 0,
    })
    const token = TokenService.getToken()
    const role = AuthService.getRole()

    const history = useHistory()
    const param = useParams()
    const style = {
        fontSize: 30
    }

    function click() {
        history.push(`/prodavci/${param.username}/artikli/${artikal.id}`)
    }

    return (
        <div className="row">
            <div className="col-md-3">
                <div className="text-dark btn-link" style={style} onClick={click}>{artikal.naziv}</div>
            </div>
            <div className="col-md-3">
                <div className="text-dark" style={style}>{artikal.cena}</div>
            </div>
            {
                token && role === "ROLE_KUPAC" ? <AddRemoveArtikal item={item} artikal={artikal} cart={cart} setCart={setCart}/> : null
            }
            {
                token && role === "ROLE_PRODAVAC" ? <EditRemoveArtikal artikal={artikal} artikli={artikli} setArtikli={setArtikli}/> : null
            }
        </div>
    )

}
export default ArtikalRow