import AxiosClient from "./client/AxiosClient";

export const ArtikliService = {
    getArtikli,
    getArtikal,
    getArtikliForProdavac,
    createArtikal,
    editArtikal,
    deleteArtikal,
    saveImage
}

async function getArtikli() {
   return await AxiosClient.get("artikli")
}

async function getArtikliForProdavac(username){
    return await AxiosClient.get(`artikli/${username}/artikli`)
}

async function getArtikal(id) {
    return await AxiosClient.get(`artikli/${id}`)
}

async function createArtikal(artikal) {
    return await AxiosClient.post("artikli", artikal)
}

async function editArtikal(id, artikal) {
    return await AxiosClient.put(`artikli/${id}`, artikal)
}

async function deleteArtikal(id){
    return await AxiosClient.delete(`artikli/${id}`)
}

async function saveImage(image){
    return await AxiosClient.post("artikli/slika", image)
}

export default ArtikliService