import React from 'react'
import "./Dropdown.css"


function filterCategories(res, category){

    const array = Object.keys(res).map((key) => res[key])

    if (category === "none"){
        return true
    }
    else if (category === "All"){
        return true
    }
    else if (category === "Movie" && array[5] === 52){
        return true
    } else if (category === "Television Series" && array[5] === 54){
        return true
    } else if (category === "Documentary" && array[5] === 53){
        return true
    } else {
        return false
    }

}


const Category = ({info, handleCategory}) => {
    return(

        <div className='dropdown-category'
            onClick={() => handleCategory(info)}
        >{info}</div>

    )
}

export {Category, filterCategories};