import axios from "axios";
import {TokenService} from "../auth/TokenService";
import {AuthService} from "../auth/AuthService";

const AxiosClient = axios.create({
    baseURL: "http://localhost:8080/prodavnica/"
    }
)

//presretac za dodavanje tokena na request
AxiosClient.interceptors.request.use(function success(config) {
    const token = TokenService.getToken()
    if(token){
        if(TokenService.didTokenExire()){
            return false
        }
        config.headers["Authorization"] = "Bearer " + token
    }
    return config
}, error => {
    Promise.reject(error)
})

AxiosClient.interceptors.response.use(
    function success(response) {
        return response
    },
    async function failure(error){
        const  originalReq = error.config;

        const token = TokenService.getToken()
        if(token){
            if(error.response && error.response.status === 403){
                AuthService.logout()
                window.location.assign("/prijava")
            }
        }
        console.error(error)
    }
)

export default AxiosClient;