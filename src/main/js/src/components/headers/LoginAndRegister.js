import {Link} from "react-router-dom";

const LoginAndRegister = () => {

    return(
        <ul className="navbar-nav ml-auto">
            <li className="nav-item">
                <Link className="nav-link" to="/">Pocetna</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/prijava">Prijavi se</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/registracija">Registruj se</Link>
            </li>
        </ul>
    )
}

export default LoginAndRegister