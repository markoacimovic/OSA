import {useState} from "react";

const AddRemoveArtikal = ({item, artikal, cart, setCart}) => {

    const [count, setCount] = useState(0)

    const handleFormInputChange = (e) => {
        const val = e.target.value
        item.kolicina = val
        setCount(val)
        setCart([...cart, item])
    }

    return(
        <div className="row">
            <div style={{fontSize: 30}}>Kolicina: </div>
            <input style={{fontSize: 30}} type="number" min="0" max="100" value={count} onChange={handleFormInputChange}/>
        </div>
            )
}

export default AddRemoveArtikal