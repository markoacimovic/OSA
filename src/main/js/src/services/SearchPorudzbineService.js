import AxiosClient from "./client/AxiosClient";

export const SearchPorudzbineService = {

    findByKomentar,
    findByOcena
}

async function findByKomentar(text){
    return AxiosClient.post(`porudzbine-search/komentar`, text)
}

async function findByOcena(min, max){
    return AxiosClient.get(`porudzbine-search/ocena?min=${min}&max=${max}`)
}