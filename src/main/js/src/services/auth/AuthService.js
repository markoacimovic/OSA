import {TokenService} from "./TokenService";
import AxiosClient from "../client/AxiosClient";

export const AuthService = {
    login,
    registration,
    getRole,
    logout,
    getUsername
}

async function login(loginCredentials) {

    TokenService.removeToken()
    try {
        const response = await AxiosClient.post(
            "/auth/login",
            loginCredentials
        )
        console.log(response)
        const decoded_token = TokenService.decodeToken(response.data)
        if(decoded_token){
            TokenService.setToken(response.data)

            if(getRole() === ("ROLE_PRODAVAC")){
                window.location.assign("/prodavac-pocetna")
            } else if(getRole() === ("ROLE_KUPAC")){
                window.location.assign("/")
            } else {
                window.location.assign("/korisnici")
            }
        }
    } catch (e){
        console.error(e)
    }
}

async function registration(userInfo){

   const response = await AxiosClient.post(
                "/auth/registration",
                    userInfo)

    if(response.status === 201){
        window.location.assign("/uspesna-registracija")
    }
    if(response.status !== 201){
        window.location.assign("/neuspesna-registracija")
    }
}

function logout() {

    TokenService.removeToken()
    window.location.assign("/")
}

function getRole(){

    const token = TokenService.getToken()
    const decoded_token = token ? TokenService.decodeToken(token) : ""

    if(decoded_token){
        return decoded_token.role.authority
    }

    return ""
}

function getUsername() {

    const token = TokenService.getToken()
    const decoded_token = token ? TokenService.decodeToken(token) : ""

    if(decoded_token){
        return decoded_token.sub
    }
    return ""
}