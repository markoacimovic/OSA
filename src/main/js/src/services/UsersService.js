import AxiosClient from "./client/AxiosClient";

export const UsersService = {
    getUsers,
    blockUser,
    changePassword,
    getUser,
    editUser
}

async function getUsers() {
    return await AxiosClient.get("administratori/all-users")
}

async function blockUser(blockedUser) {
    return await AxiosClient.post("administratori/block", blockedUser)
}

async function changePassword(password){
    return await AxiosClient.post("auth/change-password", password)
}

async function getUser(){
    return await AxiosClient.get("korisnici/korisnik")
}

async function editUser(user){
    return await AxiosClient.post("korisnici/korisnik", user)
}