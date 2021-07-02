import {Link} from "react-router-dom";
import Logout from "./Logout";

const KupacHeader = () => {

    return(
        <ul className="navbar-nav ml-auto">
            <li className="nav-item">
                <Link className="nav-link" to="/">Pocetna</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/porudzbine">Porudzbine</Link>
            </li>
            <Logout/>
        </ul>
    )

}

export default KupacHeader