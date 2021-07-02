import {Link} from "react-router-dom";
import Logout from "./Logout";

const AdminHeader = () => {

    return(
        <ul className="navbar-nav ml-auto">
            <li className="nav-item">
                <Link className="nav-link" to="/">Pocetna</Link>
            </li>
            <Logout/>
        </ul>
    )
}

export default AdminHeader