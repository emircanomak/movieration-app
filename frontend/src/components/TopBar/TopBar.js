import { Link } from "react-router-dom";
import {useState} from "react"
import { useNavigate } from "react-router-dom";

import "./TopBar.css"




const Topbar=({didUserLogin,setDidUserLogin}) =>
{   
    const navigate=useNavigate()

    const exitOnclick=function()
    {
        alert("Çıkış Başarılı")
        setDidUserLogin(false);
        navigate("/LoginScreen")
    }

    return(
        <nav className="navv">

            {
                didUserLogin ?(
                    <div className="container">
                        <li>
                         <ul><Link style={{color:"white",textDecoration:"none"}} to="/Main" >Main Page</Link></ul>
                         <ul style={{marginLeft:"20px"}}> <Link  style={{color:"white",textDecoration:"none"}} to="/AddMovie">Add Media</Link></ul>
                         <ul style={{marginLeft:"1300px",color:"white"}} onClick={exitOnclick} > Log Out </ul>
                        </li>
                   

                    </div>
                ) : (
                    <div className="topbar">
                        <li>
                         <ul ><Link style={{color:"white",textDecoration:"none"}} to="/Main" >Main Page</Link></ul>
                         <ul style={{marginLeft:"25px"}}> <Link style={{color:"white",textDecoration:"none"}} to="/RegisterScreen">Register</Link></ul>
                         <ul style={{marginLeft:"1200px"}}><Link style={{color:"white",textDecoration:"none"}} to="/LoginScreen"  >Login</Link></ul>
                         </li>
                    
                    </div>
                )
            }

        </nav>
    );
}


export default Topbar



