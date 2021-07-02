import {Link} from "react-router-dom";
import {AuthService} from "../../services/auth/AuthService";

const Logout = () => {

    const styleButton = {
        border: "0",
        borderRadius: "none",
    }

    return(
        <li className="nav-item">
            <button className="btn btn-danger text-dark" style={styleButton} onClick={AuthService.logout}>Odjavi se</button>
        </li>
    )
}

export default Logout