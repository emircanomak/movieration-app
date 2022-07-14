import React from 'react';
import "./Search.css"



function searchResults(res, searchText){

    const array = Object.keys(res).map((key) => res[key])

    return array[1].toUpperCase().includes(searchText.toUpperCase()) // index 1 refers to movie title

}


const Search = ({handleSearch}) => {

    return(

        <div className='search'>
            <input type="text" placeholder="type to search.."
                onChange={(event) => handleSearch(event.target.value)}>

            </input>
        </div>

    )

}

export {Search, searchResults};