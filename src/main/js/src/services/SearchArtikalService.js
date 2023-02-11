import AxiosClient from "./client/AxiosClient";

export const SearchArtikalService = {
    findByNaziv,
    findByOpis,
    findByCena,
    findByNazivCena,
    findByBrojKomentara,
    findByOcena
}

async function findByNaziv(search){
    return await AxiosClient.post("artikli-search/naziv", search)
}

async function findByOpis(search){
    return await AxiosClient.post("artikli-search/opis", search)
}

async function findByCena(min, max){
    return await AxiosClient.get(`artikli-search/cena?min=${min}&max=${max}`)
}

async function findByNazivCena(min, max, search){
    return await AxiosClient.post(`artikli-search/multisearch?min=${min}&max=${max}`, search)
}

async function findByOcena(min, max){
    return await AxiosClient.get(`artikli-search/ocena?min=${min}&max=${max}`)
}

async function findByBrojKomentara(min, max){
    return await AxiosClient.get(`artikli-search/komentar?min=${min}&max=${max}`)
}