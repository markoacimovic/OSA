import Select from "react-dropdown-select";
import StoreName from "../components/StoreName";
import {useState} from "react";
import validator from "validator";
import {useHistory} from "react-router";
import {AuthService} from "../services/auth/AuthService";

const Registration = () => {

    const [selectedOption, setSelectedOption] = useState(null)
    const [warning, setWarning] = useState("")
    const history = useHistory()

    const [userInfo, setUserInfo] = useState({
        ime: "",
        prezime: "",
        username: "",
        password: "",
        adresa: "",
        email: "",
        naziv: "",
        tipKorisnika: ""
    })

    async function register(){
        try {
            await AuthService.registration(userInfo)
        } catch (e){
            console.error(e)
        }
    }

    const options = [
        {label: "Prodavac", value: "PRODAVAC"},
        {label: "Kupac", value: "KUPAC"}
    ]

    const submit = (e) => {
        e.preventDefault()

        if(!userInfo.ime || !userInfo.prezime || !userInfo.username || !userInfo.password
            || !userInfo.adresa || !userInfo.tipKorisnika) {
            setWarning("Sva polja moraju biti popunjena")
            return;
        }

        if(!validator.isEmail(userInfo.email)){
            setWarning("Email nije validan")
            return;
        }

        register()
        history.push("/")
    }

    const handleFormInputChange = (name) => (e) => {
        const val = e.target.value
        setUserInfo({...userInfo, [name]: val})
    }

    const handleChange = selectedOption => {
        setSelectedOption({selectedOption})
        setUserInfo({...userInfo, ["tipKorisnika"]: selectedOption[0].value})
    }

    return(
        <div className="container-fluid" style={{marginTop: 50}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Registracija</h3>
                    <form>
                        <div className="form-group">
                            <label>Ime </label>
                            <input type="text" value={userInfo.ime} onChange={handleFormInputChange("ime")}
                            placeholder="Ime" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Prezime </label>
                            <input type="text" value={userInfo.prezime} onChange={handleFormInputChange("prezime")}
                                   placeholder="Prezime" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Korsnicko ime </label>
                            <input type="text" value={userInfo.username} onChange={handleFormInputChange("username")}
                                   placeholder="Korisnicko ime" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Lozinka</label>
                            <input type="password" value={userInfo.password} onChange={handleFormInputChange("password")}
                                   placeholder="Lozinka" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Ponovljena lozinka</label>
                            <input type="password" placeholder="Ponovljenja lozinka" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Adresa </label>
                            <input type="text" value={userInfo.adresa} onChange={handleFormInputChange("adresa")}
                                   placeholder="Adresa" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Email </label>
                            <input type="text" value={userInfo.email} onChange={handleFormInputChange("email")}
                                   placeholder="Email" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Tip korisnika </label>
                            <Select options={options} valueField={selectedOption} onChange={handleChange}/>
                        </div>
                        {userInfo.tipKorisnika === "PRODAVAC" ? <StoreName naziv={userInfo.naziv} onChange={handleFormInputChange("naziv")}/> : ""}
                        <p className="text-dark">{warning}</p>
                        <button className="btn-lg btn-danger" type="submit" onClick={submit}>Registrujte se</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Registration