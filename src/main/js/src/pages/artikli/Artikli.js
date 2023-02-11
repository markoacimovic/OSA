import {useEffect, useState} from "react";
import ArtikliService from "../../services/ArtikliService";
import ArtikalRow from "../../components/artikli/ArtikalRow";
import {useHistory, useParams} from "react-router";
import NarudzbinaService from "../../services/NarudzbinaService";
import Comment from "../../components/Comment";
import {TokenService} from "../../services/auth/TokenService";
import {AuthService} from "../../services/auth/AuthService";
import {Dialog, DialogContent, DialogContentText, DialogTitle} from "@material-ui/core";
import CartRow from "../../components/kupci/CartRow";
import ArtikalSearch from "../../components/search/ArtikalSearch";

const Artikli = () => {

    const [artikli, setArtikli] = useState([])
    const [cart, setCart] = useState([])
    const [comments, setComments] = useState([])
    const [open, setOpen] = useState(false)
    const [ukupnaCena, setUkupnaCena] = useState(0.0)
    const token = TokenService.getToken()
    const role = AuthService.getRole()
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
        let list
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
        history.push("/")
    }

    const openPopup = () => {
        let korpa
        check()
        duplicates()
        korpa = duplicates()
        setOpen(true)
        setCart(korpa)
    }

    const plus = () => {
        let uk = 0
        let ukupno = 0
        cart.forEach((order) => {
            uk = order.cena * order.kolicina
            ukupno = ukupno + uk
        })
        setUkupnaCena(ukupno)
    }

    useEffect(()=>{
        plus()
    },[cart])
    return(
        <div className="container-fluid">
            <ArtikalSearch artikli={setArtikli}/>
            {artikli.map((artikal, index)=>(
                <ArtikalRow key={index} artikal={artikal} artikli={artikli} setArtikli={setArtikli} cart={cart} setCart={setCart}/>
                ))}
            {
                token && role === "ROLE_KUPAC" ? <div className="row">
                <button className="btn btn-danger offset-md-4" style={{marginTop: 30}} onClick={openPopup}>Zavrsi kupovinu</button>
                </div> : null
            }
            <div className="container-fluid">
                <h4 className="text-dark text-center">Komentari: </h4>
                {comments.map((komentar, index) => (
                    <Comment key={index} komentar={komentar}/>
                ))}
            </div>
            <Dialog open={open} onClose={() => {setOpen(false)}}>
                <DialogTitle>Vasa porudzbina</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        {cart.map( (order, index) =>
                            (<CartRow key={index} order={order}/>))}
                        <br/>
                        {"Ukupna cena: "+ ukupnaCena}
                    </DialogContentText>
                </DialogContent>
                <button className="btn btn-warning" onClick={() => {setOpen(false)}}>Odustani</button>
                {cart.length > 0 ?<button className="btn btn-danger" onClick={click}>Poruci</button> : null}
            </Dialog>
        </div>
    )
}

export default Artikli