
const CartRow = ({order}) => {

    return(
        <div className="row">
            {order.naziv + " " + order.kolicina}
            <br/>
        </div>
    )
}

export default CartRow