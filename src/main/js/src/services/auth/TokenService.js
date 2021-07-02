import jwtDecode from "jwt-decode";

export const TokenService = {
    getToken,
    removeToken,
    setToken,
    didTokenExire,
    decodeToken
}

function getToken() {
    try {
        return localStorage.getItem("token")
    }catch {
        return ""
    }
}

function setToken(token) {
    localStorage.setItem("token", token)
}

function removeToken() {
    localStorage.removeItem("token")
}

function didTokenExire() {

    const token = getToken()
    const decoded_token = token ? decodeToken(token) : null

    return decoded_token ? decoded_token.exp_date < Date.now() : null;
}

function decodeToken(token) {

    try {
        return jwtDecode(token)
    }catch {
        return ""
    }
}