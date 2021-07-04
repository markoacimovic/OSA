import {Link} from "react-router-dom";
import Logout from "./Logout";

const AdminHeader = () => {

    return(
        <ul className="navbar-nav ml-auto">
            <li className="nav-item">
                <Link className="nav-link" to="/">Pocetna</Link>
            </li>
            <li>
                <Link className="nav-link" to="/korisnici">Korisnici</Link>
            </li>
            <li>
                <Link className="nav-link" to="/korisnik">Vasi podaci</Link>
            </li>
            <li>
                <Link className="nav-link" to="/korisnik/lozinka">Promena lozinke</Link>
            </li>
            <Logout/>
        </ul>
    )
}

export default AdminHeader