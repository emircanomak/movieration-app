import { useLocation,useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import ReactPlayer from 'react-player/youtube'

import Topbar from "../TopBar/TopBar";


const FilmProfile=({didUserLogin,setDidUserLogin}) =>
{
    const location=useLocation();

    const [trailerUrl,setTrailerUrl]=useState("");

    const {movieAttributes}=location.state

    const videoRequestUrl="https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q="+movieAttributes.productName+" trailer"+"&key=AIzaSyDAG8GcMhDl4Yasd1296l_uoyds7cfS1TY"

    const navigate=useNavigate();

    useEffect(() => {
        fetch(videoRequestUrl).then(response => response.json()).then(jsonResponse => {
            
            if(jsonResponse.hasOwnProperty("error"))
            {
                setTrailerUrl(false);
                alert("Video kotası dolu!");
            }

            else
            {
                setTrailerUrl(trailerUrl =>"https://www.youtube.com/watch?v="+jsonResponse.items[0].id.videoId)
            }
            
        });
    }, [])

    const deleteOnclick=function()
    {   

        const requstUrl="http://localhost:8080/api/v1/products/{id}?id="+movieAttributes.id
        const requestBody={
            method:"DELETE",
            mode:"cors",
            headers: {
                'Content-Type': 'application/json'
              },
            }

        fetch(requstUrl,requestBody).then(response =>{
        
            response.json().then(result =>
                {   
                    console.log(result);
                    if(result.success===true)
                    {
                        alert("Delete Succesful");
                        navigate("/Main")
                    }

                    else
                    {
                        alert("Delete Failed!");
                    }
                })
        })
    }
    
    return (

        <div style={style.mainDiv}>

            <Topbar setDidUserLogin={setDidUserLogin}  didUserLogin={didUserLogin}/>

                <div  style={style.containerDiv}>

                    <img alt={"Görsel"} style={style.image} src={movieAttributes.productUrl}/>
                
                    <div style={{marginLeft:"4vw"}}>

                        <div style={style.fieldDiv} >

                            <p style={style.fieldName} >Media Title:</p>
                            <p style={style.fieldValue} >{movieAttributes.productName}</p>
                        
                        </div>

                        <div style={style.fieldDiv} >

                            <p style={style.fieldName} >Media Overview:</p>
                            <p style={{...style.fieldValue,height:"10vh",overflowY:"scroll"}} >{movieAttributes.productDesc}</p>
                        
                        </div>

                        <div style={style.fieldDiv} >

                            <p style={style.fieldName} >Type:</p>
                            <p style={style.fieldValue} >{movieAttributes.categoryName}</p>
                        
                        </div>

                        <div style={style.fieldDiv} >

                            <p style={style.fieldName} >Rating:</p>
                            <p style={style.fieldValue} >{movieAttributes.productRate}</p>
                        
                        </div>

                       {
                            didUserLogin ?(
                                <div>
                                    <button onClick={deleteOnclick} style={style.button} >Sil!</button>
                                </div>
                            ) : (
                                <div>
                                
                                </div>
                            )
                       }

                    </div>

                </div>

                <ReactPlayer width="50vw" style={style.video} controls={true}   url={trailerUrl} />

            
            
        </div>



    );
}


export default FilmProfile;

const style={
    mainDiv:{
        width:"100vw",
        height:"100vh",
        overflowY:"scroll",
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        backgroundColor:"#rgb(240, 230, 230);",
        flexDirection:"column",
        marginTop:"6vh",
      
    },

    containerDiv:{
        width:"50vw",
        height:"55vh",
        backgroundColor:"rgb(177, 179, 182)",
        border:" 1px solid grey",
         borderRadius:"20px",
        display:"flex",
        flexDirection:"row",
        margin:"15px",
        padding:"15px"
        
        
    },

    image:{
        width:"40vh",
        height:"50vh",
        marginTop:"1vh",
        marginLeft:"1vw",

    },

    fieldDiv:{
        display:"flex",
        flexDirection:"row",
        marginTop:"5vh",
        color:"rgb(185, 13, 13)"

    },

    fieldName:{
        fontSize:"25px",
        fontFamily:"sans-serif",

    },

    fieldValue:{

        fontSize:"25px",
        marginLeft:"2vw",
        color:"black",
        fontFamily:"sans-serif",

    },

    video:{
        marginTop:"5vh",
    },

    button:{
        width:"10vw",
        height:"8vh",
        marginTop:"4vh",
        marginLeft:"5vw",
    },
}