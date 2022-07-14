import {useState} from "react"
import {useNavigate} from "react-router-dom"

import Topbar from "../TopBar/TopBar";
import "./LoginScreen.css"


const LoginScreen=({setDidUserLogin}) =>
{   
    const [email,setEmail]=useState("");
    const [sifre,setSifre]=useState("");
    
    const navigate=useNavigate();

    const buttonOnclick=function()
    {
        const requstUrl="http://localhost:8080/auth/login"

        const requestBody={
            method:"POST",
            mode:"cors",
            headers: {
                'Content-Type': 'application/json'
              },
            body:JSON.stringify({
                userName:email,
                password:sifre,
                fullName:"string",
            })
        }


        fetch(requstUrl,requestBody).then(response =>{
        
            response.json().then(result =>
                {   
                    if(result.success===true)
                    {
                        alert("Login Succesful !")
                        setDidUserLogin(true);
                        navigate("/Main")
                    }

                    else
                    {
                        alert("Login failed, Check the Information!");
                    }
                })
        })
    }


    return (

        <div className="main-div">
            <Topbar/>
            <div className="register-div">
                
                <h1>Login</h1>
                
                <div className="field-div" >
                <p className="field-name" >UserName</p>
                <input className="input-field" onInput={e =>(setEmail(e.target.value))} placeholder="UserName" type="text"></input>
                </div>

                <div className="field-div" >
                <p className="field-name" >Password</p>
                <input className="input-field" onInput={e =>(setSifre(e.target.value))} placeholder="Password" type="text"></input>
                </div>

                <button className="button" onClick={buttonOnclick} >Submit</button>

            </div>
        </div>

    );
}


export default LoginScreen;


