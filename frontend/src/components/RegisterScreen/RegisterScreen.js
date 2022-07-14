import {Formik} from "formik"
import { useState } from "react"
import * as Yup from "yup"
import "./RegisterScreen.css"
import TopBar from "../TopBar/TopBar"
import { useNavigate } from "react-router-dom";

const RegisterScreen=() => { 
    
    
    const [username,setUsername]=useState("");
    const [password,setPassword]=useState("");
    const navigate=useNavigate();

   const buttonOnclick= function buttonOnClick()
    {
        const requestUrl="http://localhost:8080/auth/register"
        
        const requestBody={
            method:"POST",
            mode:"cors",
            headers: {
                'Content-Type': 'application/json'
              },
            body:JSON.stringify({
                fullname:"string",
                userName:username,
                password:password,
            })
        }

        fetch(requestUrl,requestBody).then(response =>{
        
            response.json().then(result =>
                {   
                    console.log(result);

                    if(result.success===true)
                    {
                        alert("Registration succed!");
                        navigate("/LoginScreen");
                    }

                    else
                    {
                        alert("Registration failure,username taken!");
                    }
                })
        })
    }

  return (
     <div className='container'>
        <TopBar/>
        <div className='brand-box'>
          
            <p>img</p>

        </div>
    
        <div className='magic-form'>
            <Formik
            initialValues={{
                username : "",
                email : "",
                password: ""
            }}
            validationSchema= {Yup.object({
             
                    username:Yup.string().required("Kulalnıcı adı boş bırakılamaz !"),
                    email:Yup.string().email("Geçerli bir email adresi girin..").required("Email boş bırakılamaz"),
                    password:Yup.string().min(6,"6 haneli bir parola girin ..").required(" Parola boş bırakılamaz").matches(
                     

                    )
                    

                })}   
                
                onSubmit={(values, {resetForm,setSubmitting})=>{
                    console.log(values);    
                    setTimeout(()=> {
                    resetForm();
                    },2000)
                }}
                >            
                {({ values , errors, handleChange ,handleSubmit , touched , dirty , isSubmitting  }) => (
                    <form onSubmit={handleSubmit}>
                        <h3>Register</h3>
                        <label htmlFor='name'>Username </label>

                        <input id='username' type="text" 
                        placeholder="Emircan..."  
                        className="input" 
                        value={values.username} 
                        onInput={e =>(setUsername(e.target.value))}
                        onChange={handleChange}>
                            
                        </input>
                        {
                            errors.username && touched.username && (
                                 <div className='input-feedback'>{errors.username}
                                    
                                 </div>

                            )}

                            <label htmlFor='password' className='topMargin'>Password</label>
                            <input id='password' type="password" 
                            placeholder="******"  
                            className="input" 
                            value={values.password} 
                            onInput={e =>(setPassword(e.target.value))}
                            onChange={handleChange}>

                           </input>

                        {
                            errors.password && touched.password && (
                                 <div className='input-feedback'>{errors.password}
                                    
                                 </div>

                            )}
                        
                        <label htmlFor='name' className='topMargin'>E-mail </label>

                        <input id='email' type="email" 
                        placeholder="omakemircan@hotmail.com"  
                        className="input" 
                        value={values.email} 
                        onChange={handleChange}>
                        </input>
                        {
                            errors.email && touched.email && (
                                 <div className='input-feedback'>{errors.email}
                                    
                                 </div>

                            )}
                        <button onClick={buttonOnclick} type='submit' disabled={!dirty || isSubmitting}>
                        Submit
                        </button>
                    
                    </form> 
                )}
          
           </Formik>

        </div>
    </div>
  )
}

export default RegisterScreen