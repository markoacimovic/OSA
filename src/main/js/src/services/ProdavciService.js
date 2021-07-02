import AxiosClient from "./client/AxiosClient";

export const ProdavciService = {
    getProdavci,

}

async function getProdavci() {
    return await AxiosClient.get("prodavci")
}

async function getProdavac(id) {
    return await AxiosClient.get(`prodavci/${id}`)
}

export default ProdavciService