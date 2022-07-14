import Card from '../Card/Card';
import Dropdown from '../Category/Dropdown'
import {filterCategories} from '../Category/Category'
import {Search, searchResults} from '../Search/Search'
import './Main.css';
import React, {useState,useEffect} from 'react';
import Topbar from '../TopBar/TopBar';


const Main = ({didUserLogin,setDidUserLogin}) => {

    const [movieData, setMovieData] = useState([]);
    const [searchText, setSearchText] = useState("")
    const [category, setCategory] = useState("none")



    useEffect(() =>
    {
     
        fetch("http://localhost:8080/api/v1/products/all",{method:"GET"}).then(response =>
    response.json()).then(result =>
        {
            setMovieData(result.data)
        })
    },[])

    return(
    
        <div className='main'>
            <Topbar setDidUserLogin={setDidUserLogin}  didUserLogin={didUserLogin} />

            <Dropdown handleCategory={setCategory}/>

            <Search  handleSearch={setSearchText}/>

            {
                (movieData.length === 0) ? <p>Failed</p> : movieData.map((results, index) => {
                    return(
                        (searchResults(results, searchText))  && filterCategories(results, category) 
                        ? <Card info={results} key={index}/> :
                        null
                    )
                })
            }

        </div>

    )

}

export default Main;
