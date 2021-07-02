import {Link} from "react-router-dom";
import Logout from "./Logout";
import {useHistory, useParams} from "react-router";
import ArtikliService from "../../services/ArtikliService";
import {AuthService} from "../../services/auth/AuthService";

const ProdavacHeader = () => {

    const username = AuthService.getUsername()
    const history = useHistory()

    function click() {
        history.push(`/prodavci/${username}`)
    }


    return(
        <ul className="navbar-nav ml-auto">
            <li className="nav-item">
                <Link className="nav-link" to="/">Pocetna</Link>
            </li>
            <li className="nav-item">
                <button className="nav-link btn-danger btn" onClick={click}>Pregled artikala</button>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/artikli/dodavanje">Dodaj artikal</Link>
            </li>
            <Logout/>
        </ul>
    )
}

export default ProdavacHeader
