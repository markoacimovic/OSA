import {useState} from "react";
import {UsersService} from "../services/UsersService";
import {useHistory} from "react-router";

const PasswordChange = () =>{

    const [warning, setWarning] = useState("")
    const [pass, setPass] = useState({
        currentPassword: "",
        newPassword: "",
        newRepeatedPassword: ""
    })
    const history = useHistory()

    const handleFormInputChange = (name) => (e) => {
        const val = e.target.value
        setPass({...pass, [name]: val})
    }

    async function change() {
        try {
            await UsersService.changePassword(pass)
        } catch (e) {
            console.error(e)
        }
    }

    const submit = (e) => {
        e.preventDefault()

        if(!pass.newPassword || !pass.currentPassword || !pass.newRepeatedPassword){
            setWarning("Sva polja moraju biti popunjena")
            return
        }

        change()
        history.push("/")

    }

    return(
        <div className="container-fluid" style={{marginTop: 50}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Promena lozinke</h3>
                    <div>
                        <div className="form-group">
                            <label>Trenutna lozinka</label>
                            <input type="password" value={pass.currentPassword} onChange={handleFormInputChange("currentPassword")}
                            placeholder="Trenutna lozinka" className="form-control"/>
                        </div>
                        <div className="form-grop">
                            <label>Nova lozinka</label>
                            <input type="password" value={pass.newPassword} onChange={handleFormInputChange("newPassword")}
                                   placeholder="Nova lozinka" className="form-control"/>
                        </div>
                        <div className="form-grop">
                            <label>Ponovljena nova lozinka</label>
                            <input type="password" value={pass.newRepeatedPassword} onChange={handleFormInputChange("newRepeatedPassword")}
                            placeholder="Ponovljena nova lozinka" className="form-control"/>
                        </div>
                        <p>{warning}</p>
                        <button className="btn btn-danger" onClick={submit}>Promeni</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PasswordChange