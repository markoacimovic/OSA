import {UsersService} from "../../services/UsersService";
import {useState} from "react";

const UserRow = ({user}) =>{

    const [blockUser, setBlockUser] = useState({})
    let blokiran = {}

    console.log("Korisnik " + user.blokiran)
    async function block(blockUser) {
        try {
            await UsersService.blockUser(blockUser)
        }catch (e){
            console.error(e)
        }
    }

    const blockU = (blockUser) => {
        if(user.blokiran){
            blokiran = {
                username: user.username,
                blokiran: false
            }
        }
        if(!user.blokiran){
            blokiran = {
                username: user.username,
                blokiran: true
            }
        }
        console.log(blockUser)
        block(blokiran)
    }

    const style = {
        marginLeft: 20
    }

    return(
        <div className="row">
            <span className="text-dark" style={style}>{user.ime}</span>
            <span className="text-dark" style={style}>{user.prezime}</span>
            <span className="text-dark" style={style}>{user.username}</span>
            <span className="text-dark" style={style}>{user.blokiran}</span>
            <button className="btn btn-warning" style={style} onClick={() => blockU(blockUser)}>Blokiraj</button>
        </div>
    )
}

export default UserRow