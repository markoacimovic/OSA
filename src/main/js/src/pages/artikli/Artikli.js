import {useEffect, useState} from "react";
import ArtikliService from "../../services/ArtikliService";
import ArtikalRow from "../../components/artikli/ArtikalRow";
import {useHistory, useParams} from "react-router";
import NarudzbinaService from "../../services/NarudzbinaService";
import Comment from "../../components/Comment";
import {TokenService} from "../../services/auth/TokenService";
import {AuthService} from "../../services/auth/AuthService";

const Artikli = () => {

    const [artikli, setArtikli] = useState([])
    const [cart, setCart] = useState([])
    const [comments, setComments] = useState([])
    const token = TokenService.getToken()
    const role = AuthService.getRole()

    //TODO Ovde prebaciti na stranicu koja prikazuje sta je kupljeno
    const history = useHistory()
    const {username} = useParams()

    async function fetchArtili() {
        try {
            const response = await ArtikliService.getArtikliForProdavac(username)
            setArtikli(response.data)
        }catch (e) {
            console.error(e)
        }
    }
    async function fetchKomentari() {
        try {
            const response = await NarudzbinaService.getKomentari(username)
            setComments(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    useEffect(()=>{
        fetchArtili()
        fetchKomentari()
    }, [])

    const check = () => {
        let list = []

        cart.filter((selected) => selected.kolicina !== 0 ? list.push(selected) : console.log(selected))
        setCart(list)
    }

    const duplicates = () => {
        let list = []
        const set = new Set(cart)

        list = [...set]
        setCart(list)
        return list
    }

    async function create(ret) {
        try {
            return await NarudzbinaService.createNarudzbina(ret)
        }catch (e) {
            console.error(e)
        }
    }

    function click() {
        let korpa
        check()
        duplicates()
        korpa = duplicates()
        create(korpa)
        console.log(korpa)
    }

    return(
        <div className="container-fluid">
            {artikli.map((artikal, index)=>(
                <ArtikalRow key={index} artikal={artikal} artikli={artikli} setArtikli={setArtikli} cart={cart} setCart={setCart}/>
                ))}
            {
                token && role === "ROLE_KUPAC" ? <div className="row">
                <button className="btn btn-danger offset-md-4" style={{marginTop: 30}} onClick={click}>Zavrsi kupovinu</button>
                </div> : null
            }
            <div className="container-fluid">
                <h4 className="text-dark text-center">Komentari: </h4>
                {comments.map((komentar, index) => (
                    <Comment key={index} komentar={komentar}/>
                ))}
            </div>
        </div>
    )
}

export default Artikli