import {useEffect, useState} from "react";
import {UsersService} from "../../services/UsersService";
import UserRow from "../../components/administratori/UserRow";

const AllUsers = () => {

    const [users, setUsers] = useState([])

    async function fetchUsers() {
        try {
            const response = await UsersService.getUsers()
            setUsers(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    useEffect(() => {
        fetchUsers()
    }, [])

    return (
        <div className="container-fluid">
            <h3 className="text-center text-dark" style={{marginBottom: 30}}>Korisnici</h3>
            {users.map((user, index) => (<UserRow key={index} user={user}/>))}
        </div>
    )
}

export default AllUsers