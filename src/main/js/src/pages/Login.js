import {useState} from "react";
import {AuthService} from "../services/auth/AuthService";

const Login = () => {

    const [warning, setWarning] = useState("")
    const [loginCredentials, setLoginCredentials] = useState({
        username: "",
        password: ""
    })

    async function login() {
        try {
            await AuthService.login(loginCredentials).then((e) => e === undefined ? setWarning("Neuspesno prijavljivanje") : "")
        } catch (e){
            console.error(e)
        }
    }

    const handleFormInputChange = (name) => (e) => {
        const val = e.target.value
        setLoginCredentials({...loginCredentials, [name]: val})
    }

    const submit = (e) => {
        e.preventDefault()

        if(!loginCredentials.username || !loginCredentials.password){
            setWarning("Sva polja moraju biti popunjena")
            return;
        }
        login()
    }

    return(
        <div className="container-fluid" style={{marginTop: 50}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Prijava</h3>
                    <form>
                        <div className="form-group">
                            <label>Korsnicko ime </label>
                            <input type="text" value={loginCredentials.username}
                                   onChange={handleFormInputChange("username")}
                                   placeholder="Korisnicko ime" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Lozinka</label>
                            <input type="password" value={loginCredentials.password}
                                   onChange={handleFormInputChange("password")}
                                   placeholder="Lozinka" className="form-control"/>
                        </div>
                        <p className="text-dark">{warning}</p>
                        <button className="btn btn-danger" type="submit" onClick={submit}>Prijavite se</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Login