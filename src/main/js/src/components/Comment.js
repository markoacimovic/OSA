
const Comment = ({komentar}) => {



    return(
        <div className="row">
            {komentar.anonimno === false ?
                <span className="text-danger" style={{marginRight: 20}}>{komentar.username}:</span> :
                <span className="text-danger" style={{marginRight: 20}}>Anonimno</span>}
            <span className="text-danger">{komentar.komentar}</span>
        </div>
    )
}

export default Comment