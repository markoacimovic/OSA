import Prodavci from "./prodavci/Prodavci";
import {AuthService} from "../services/auth/AuthService";

const FrontPage = () => {

    const role = AuthService.getRole()

    return(
        <>
            {role !== "ROLE_ADMINISTRATOR" && role !== "ROLE_PRODAVAC" ?
                <div className="container-fluid">
                    <h1 className="text-center text-dark">Prodavci</h1>
                    <Prodavci/>
                </div> : null
            }
        </>
    )

}

export default FrontPage