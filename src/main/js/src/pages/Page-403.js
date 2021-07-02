
const Page403 = () => {

    const time = 5000

    setTimeout(function (){
        window.location.href = "http://localhost:3000/"
    }, time)

    return(
        <div className="container-fluid">
            <h1 className="text-black-50">403 Unauthorized</h1>
            <br/>
            <p>You will be redirected in 5 seconds on home page.</p>
        </div>
    )

}

export default Page403