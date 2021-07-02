import AxiosClient from "./client/AxiosClient";

export const NarudzbinaService = {
    getNarudzbine,
    createNarudzbina,
    editNarudzbina,
    getKomentari
}

async function createNarudzbina(cart) {
    return await AxiosClient.post("stavke/korpa", cart)
}

async function getNarudzbine() {
    return await AxiosClient.get("porudzbine/porudzbine-kupca")
}

async function editNarudzbina(id, por) {
    return await AxiosClient.put(`porudzbine/${id}`, por)
}

async function getKomentari(username) {
    return await AxiosClient.get(`porudzbine/komentari/${username}`)
}

export default NarudzbinaService