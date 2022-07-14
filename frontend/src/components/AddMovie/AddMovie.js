import Topbar from "../TopBar/TopBar"
import { useState,useEffect } from "react"

const AddMovie=({didUserLogin,setDidUserLogin}) =>
{

    const [productName,setProductName]=useState("")
    const [productDesc,setProductDesc]=useState("")
    const [productUrl,setProductUrl]=useState("")
    const [productRate,setProductRate]=useState("")
    const [categoryId,setCategoryId]=useState("")
    const [categories,setCategories]=useState("")

    useEffect(() =>
    {   
        const requestUrl="http://localhost:8080/api/v1/categories/all"

        const requestBody={
            method:"GET",
            mode:"cors",
            headers: {
                'Content-Type': 'application/json'
              }
            }

            fetch(requestUrl,requestBody).then(response =>{
        
                response.json().then(result =>
                    {   
                        console.log(result);

                        let categoryPlaceholder="";

                        result.data.map((element,index) =>
                        {
                            categoryPlaceholder+=element.categoryName+":"+element.id+", "
                        })

                        setCategories(categoryPlaceholder)
                    })
            })
            
            
        
    },[])

    const buttonOnclick=function()
    {   
        const requestUrl="http://localhost:8080/api/v1/products"

        const requestBody={
            method:"POST",
            mode:"cors",
            headers: {
                'Content-Type': 'application/json'
              },
            body:JSON.stringify({
                productName:productName,
                productDesc:productDesc,
                productUrl:productUrl,
                productRate:productRate,
                categoryId:categoryId,
            })
        }

        fetch(requestUrl,requestBody).then(response =>{
        
            response.json().then(result =>
                {   
                    if(result.success===true)
                    {
                        alert("Succesful!");
                        setProductName("");
                        setProductDesc("");
                        setProductUrl("");
                        setProductRate("");
                        setCategoryId("");

                    }

                    else
                    {
                        alert("Failure!\n Error Message:"+result.message)
                    }
                })
        })
    }

    

    return (
        <div style={Style.MainDiv} >
            
            <Topbar setDidUserLogin={setDidUserLogin}  didUserLogin={didUserLogin}/>

            <div style={Style.containerDiv} >

                <div style={Style.fieldDiv}>
                    <p style={Style.inputName} >Media Title:</p>
                    <input onChange={e=>setProductName(e.target.value)} value={productName} style={Style.inputField} type="text" placeholder="Media title"></input>
                </div>

                <div style={Style.fieldDiv}>
                    <p style={Style.inputName}>Media Overview:</p>
                    <input onChange={e=>setProductDesc(e.target.value)} value={productDesc} style={Style.inputField} type="text" placeholder="Media overview"></input>
                </div>

                <div style={Style.fieldDiv}>
                    <p style={Style.inputName}>Poster Address:</p>
                    <input onChange={e=>setProductUrl(e.target.value)} value={productUrl} style={Style.inputField} type="text" placeholder="Poster Adress"></input>
                </div>

                <div style={Style.fieldDiv}>
                    <p style={Style.inputName}>Rating:</p>
                    <input onChange={e=>setProductRate(e.target.value)} value={productRate} style={Style.inputField} type="text" placeholder="Rating"></input>
                </div>

                <div style={Style.fieldDiv}>
                    <p style={Style.inputName}>Media Categord Id:</p>
                    <input onChange={e=>setCategoryId(e.target.value)} value={categoryId} style={{...Style.inputField,width:"15vw"}} type="text" placeholder={categories}></input>
                </div>
                
                <div style={Style.fieldDiv}>
                    <button onClick={buttonOnclick} style={Style.button} >Add!</button>
                </div>

            </div>
        </div>
    )
}


export default AddMovie


const Style={

    MainDiv:{
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        width:"100%",
        height:"100%",
    },

    containerDiv:{
        width:"40vw",
        height:"60vh",
        backgroundColor:"rgb(177, 179, 182)",
        marginTop:"150px",
    },

    fieldDiv:{

        display:"block",
        justifyContent:"center",
        alignItems:"center",
        marginTop:"2vh",
        marginLeft:"240px"
        
    },

    inputName:{
        fontFamily:"serif",
        fontSize:"20px",
        margin:"10px"
    },

    inputField:{
        marginLeft:"2vw",
        width:"10vw",
        height:"4vh",
        borderRadius:"10px",
    },

    button:{
        width:"10vw",
        height:"6vh",
        marginLeft:"55px",
        marginBottom:"300px"
    },
    

}