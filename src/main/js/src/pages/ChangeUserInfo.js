import {useEffect, useState} from "react";
import {UsersService} from "../services/UsersService";
import {AuthService} from "../services/auth/AuthService";
import {useHistory} from "react-router";

const ChangeUserInfo = () => {

    const [warning, setWarning] = useState("")
    const [user, setUser] = useState({})
    const role = AuthService.getRole()
    const history = useHistory()

    async function getUserInfo() {
        try {
            const response = await UsersService.getUser()
            setUser(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    async function editUser(){
        try {
            await UsersService.editUser(user)
        } catch (e){
            console.error(e)
        }
    }

    useEffect(()=>{
        getUserInfo()
    },[])

    const handleFormInputChange = (name) => (e) => {
        const val = e.target.value
        setUser({...user, [name]: val})
    }


    const submit = (e) => {
        e.preventDefault()
        console.log(user)
        if(!user.ime || !user.prezime || !user.username){
            setWarning("Sva polja moraju biti popunjena")
            return
        }
        editUser()
        history.push("/")
    }
    return(
        <div className="container-fluid" style={{marginTop: 50}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmeni podatke</h3>
                    <div>
                        <div className="form-group">
                            <label>Ime</label>
                            <input type="text" value={user.ime} onChange={handleFormInputChange("ime")}
                            className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Prezime</label>
                            <input type="text" value={user.prezime} onChange={handleFormInputChange("prezime")}
                                   className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label>Username</label>
                            <input type="text" value={user.username} onChange={handleFormInputChange("username")}
                                   className="form-control"/>
                        </div>
                        {role !== "ROLE_ADMINISTRATOR" ?
                            <div className="form-group">
                                <label>Adresa</label>
                                <input type="text" value={user.adresa} onChange={handleFormInputChange("adresa")}
                                       className="form-control"/>
                            </div>
                            :
                            null}
                        <p>{warning}</p>
                        <button className="btn btn-danger" onClick={submit}>Izmeni</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ChangeUserInfo