
const StoreName = ({naziv, onChange}) =>{

    return(
        <div className="form-group">
            <label>Naziv </label>
            <input type="text" value={naziv} onChange={onChange}
                   placeholder="Naziv" className="form-control"/>
        </div>
    )
}

export default StoreName