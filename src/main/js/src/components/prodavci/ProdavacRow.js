import {useHistory} from "react-router";

const ProdavacRow = ({prodavac}) => {

    const history = useHistory()

    function click() {
        history.push(`/prodavci/${prodavac.username}`)
    }

    return(
        <div className="row">
            <button className="btn align-content-lg-start col-md-3 text-dark btn-link" style={{fontSize: 30}}
            onClick={click}>{prodavac.naziv}</button>
        </div>
    )
}

export default ProdavacRow