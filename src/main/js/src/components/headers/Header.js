import {AuthService} from "../../services/auth/AuthService";
import KupacHeader from "./KupacHeader";
import ProdavacHeader from "./ProdavacHeader";
import LoginAndRegister from "./LoginAndRegister";
import Logout from "./Logout";
import AdminHeader from "./AdminHeader";

const Header = () => {

    function header() {
        if(AuthService.getRole() === "ROLE_KUPAC"){
            return <KupacHeader/>
        }
        if(AuthService.getRole() === "ROLE_PRODAVAC"){
            return <ProdavacHeader/>
        }
        if(AuthService.getRole() === "ROLE_ADMINISTRATOR"){
            return <AdminHeader/>
        }
        return <LoginAndRegister/>
    }

    return(
        <header>
            <nav className="navbar navbar-expand-lg navbar-light bg-danger sticky-top">
                <div className="container">
                    <a className="navbar-brand" href="/">Naruči hranu</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                            aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarResponsive">
                        {header()}
                    </div>
                </div>
            </nav>
        </header>
    )
}

export default Header